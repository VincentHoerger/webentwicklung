package server.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import server.exception.HolidayNotFoundException;
import server.model.Holiday;
import server.model.Vacation;
import server.repository.HolidayRepository;

@RestController
@CrossOrigin
@RequestMapping(name="holiday",path="/api")
@Tag(name = "holiday", description = "Api f�r 'Holiday'")
public class HolidayController {
	
	@Autowired
	private HolidayRepository holidayRepository;
	
	@GetMapping("/holiday/{id}")
	@Operation(summary = "Get Holiday", description = "Ruft die Holiday unter angegebener Id ab.")
	public Optional<Holiday> findById(@PathVariable long id) {
		Optional<Holiday> holiday = holidayRepository.findById(id);
		holiday.orElseThrow(() -> new HolidayNotFoundException(id));
		return holiday;
	}
	
	@GetMapping("/holiday/{id}/vacations/highestpriority")
	public Optional<Vacation> getHighestPriority(@PathVariable long id) {
		Optional<Holiday> holiday = holidayRepository.findById(id);
		holiday.orElseThrow(() -> new HolidayNotFoundException(id));
		return holiday.get().highestPriorityVacation();
	}
	
	@GetMapping("/holiday")
	public List<Holiday> findHolidays() {
		List<Holiday> holidays = new ArrayList<Holiday>();
		holidayRepository.findAll().forEach(holidays::add);
		return holidays;
	}
	
	@GetMapping("/holiday/findByTitle")
	public Optional<Holiday> findByTitle(@Valid @NotBlank @RequestParam String title) {
		Optional<Holiday> holiday = holidayRepository.findByTitle(title);
		holiday.orElseThrow(() -> new HolidayNotFoundException(title));
		return holiday;
	}
	
	@PostMapping("/holiday")
	@ResponseStatus(HttpStatus.CREATED)
	public Holiday postHoliday(@NotNull @Valid @RequestBody final Holiday holiday) {
		Holiday _holiday = holidayRepository.save(new Holiday(holiday.getTitle(), holiday.getStartDate(),holiday.getEndDate()));
		return _holiday;
	}
	
	@PutMapping("/holiday/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Holiday updatHoliday(@PathVariable("id") long id, @RequestBody final Holiday holiday) {
		Optional<Holiday> holidayData = holidayRepository.findById(id);
		holidayData.orElseThrow(() -> new HolidayNotFoundException(id));
		Holiday  _holiday = holidayData.get();
		_holiday.setTitle(holiday.getTitle());
		_holiday.setStartDate(holiday.getStartDate());
		_holiday.setEndDate(holiday.getEndDate());
		// TODO save Ergebnis abwarten -> Unique index or primary key violation
		return holidayRepository.save(_holiday);
	}
	
	@DeleteMapping("/holiday/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteHoliday(@PathVariable long id) {
		Optional<Holiday> holiday = holidayRepository.findById(id);
		holiday.orElseThrow(() -> new HolidayNotFoundException(id));
		holidayRepository.deleteById(id);
	}
	
}

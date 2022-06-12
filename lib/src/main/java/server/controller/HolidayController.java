package server.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import server.repository.HolidayRepository;

@RestController
@RequestMapping("/api/holiday")
@Tag(name = "Holiday", description = "Api für 'Holiday'")
public class HolidayController {
	
	@Autowired
	private HolidayRepository repository;
	
	@GetMapping("/{id}")
	@Operation(summary = "Get Holiday", description = "Ruft die Holiday unter angegebener Id ab.")
	public Optional<Holiday> findById(@PathVariable Long id) {
		Optional<Holiday> holiday = repository.findById(id);
		holiday.orElseThrow(() -> new HolidayNotFoundException(id));
		return holiday;
	}
	
	@GetMapping("/")
	public List<Holiday> findHolidays() {
		List<Holiday> holidays = new ArrayList<Holiday>();
		repository.findAll().forEach(holidays::add);
		return holidays;
	}
	
	@GetMapping("/findByTitle")
	public Optional<Holiday> findByTitle(@Valid @NotBlank @RequestParam String title) {
		Optional<Holiday> holiday = repository.findByTitle(title);
		holiday.orElseThrow(() -> new HolidayNotFoundException(title));
		return holiday;
	}
	
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Holiday postHoliday(@NotNull @Valid @RequestBody final Holiday holiday) {
		Holiday _holiday = repository.save(new Holiday(holiday.getTitle(), holiday.getStartDate(),holiday.getEndDate()));
		return _holiday;
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Holiday updatHoliday(@PathVariable("id") long id, @RequestBody final Holiday holiday) {
		Optional<Holiday> holidayData = repository.findById(id);
		holidayData.orElseThrow(() -> new HolidayNotFoundException(id));
		Holiday  _holiday = holidayData.get();
		_holiday.setTitle(holiday.getTitle());
		_holiday.setStartDate(holiday.getStartDate());
		_holiday.setEndDate(holiday.getEndDate());
		return repository.save(_holiday);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public long deleteHoliday(@PathVariable long id) {
		repository.deleteById(id);
		return id;
	}
}

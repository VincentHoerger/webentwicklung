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
import server.exception.VacationNotFoundException;
import server.model.Holiday;
import server.model.Vacation;
import server.repository.HolidayRepository;
import server.repository.VacationRepository;

@RestController
@CrossOrigin
@RequestMapping(name="vacation",path="/api")
@Tag(name = "vacation", description = "Api f�r 'Vacation'")
public class VacationController {

	@Autowired
	private VacationRepository vacationRepository;
	
	@Autowired
	private HolidayRepository holidayRepository;
	
	@GetMapping("/vacation/{id}")
	@Operation(summary = "Get Vacation", description = "Ruft die Vacation unter angegebener Id ab.")
	public Optional<Vacation> findById(@PathVariable long id) {
		Optional<Vacation> vacation = vacationRepository.findById(id);
		vacation.orElseThrow(() -> new VacationNotFoundException(id));
		return vacation;
	}
	
	@GetMapping("/holiday/{id}/vacations")
	@Tag(name = "holiday")
	public List<Vacation> getAllVacationsByHolidayId(@PathVariable("id") long id) {    
		Optional<Holiday> holiday = holidayRepository.findById(id);
		holiday.orElseThrow(() -> new HolidayNotFoundException(id));
			
		List<Vacation> vacations = new ArrayList<Vacation>();
		
	    vacations.addAll(holiday.get().getVacations());
	    
	    return vacations;
	}
	
	@GetMapping("/vacation/findByTitle")
	public Optional<Vacation> findByTitle(@Valid @NotBlank @RequestParam String title) {
		Optional<Vacation> vacation = vacationRepository.findByTitle(title);
		vacation.orElseThrow(() -> new VacationNotFoundException(title));
		return vacation;
	}
	
	@PostMapping("/holiday/{id}/vacations")
	@Tag(name = "holiday")
	@ResponseStatus(HttpStatus.CREATED)
	public Vacation createVacation(@PathVariable("id") long id, @NotNull @Valid @RequestBody final Vacation vacation) {
		Optional<Holiday> _holiday = holidayRepository.findById(id);
		_holiday.orElseThrow(() -> new HolidayNotFoundException(id));
		_holiday.get().getVacations().add(vacation);
		return vacationRepository.save(vacation);
	}
	
	@PutMapping("/vacation/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Vacation updatVacation(@PathVariable("id") long id, @RequestBody final Vacation vacation) {
		Optional<Vacation> vacationData = vacationRepository.findById(id);
		vacationData.orElseThrow(() -> new VacationNotFoundException(id));
		Vacation  _vacation = vacationData.get();
		_vacation.setTitle(vacation.getTitle());
		_vacation.setDestination(vacation.getDestination());
		_vacation.setDescription(vacation.getDescription());
		return vacationRepository.save(_vacation);
	}
	
	@DeleteMapping("/vacation/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteVacation(@PathVariable long id) {
		Optional<Vacation> vacation = vacationRepository.findById(id);
		vacation.orElseThrow(() -> new VacationNotFoundException(id));
		vacationRepository.deleteById(id);
	}
	
	@DeleteMapping("/holiday/{id}/vacations")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAllVacationsofHoliday(@PathVariable("id") long id) {
		Optional<Holiday> _holiday = holidayRepository.findById(id);
		_holiday.orElseThrow(() -> new HolidayNotFoundException(id));
		_holiday.get().removeVacations();
		holidayRepository.save(_holiday.get());
	}
}

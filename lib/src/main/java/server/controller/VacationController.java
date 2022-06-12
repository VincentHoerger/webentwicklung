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
import server.exception.VacationNotFoundException;
import server.model.Vacation;
import server.repository.VacationRepository;

@RestController
@RequestMapping("/api/vacation")
@Tag(name = "Vacation", description = "Api für 'Vacation'")
public class VacationController {

	@Autowired
	private VacationRepository repository;
  
	@GetMapping("/{id}")
	@Operation(summary = "Get Vacation", description = "Ruft die Vacation unter angegebener Id ab.")
	public Optional<Vacation> findById(@PathVariable Long id) {
		Optional<Vacation> vacation = repository.findById(id);
		vacation.orElseThrow(() -> new VacationNotFoundException(id));
		return vacation;
	}
	
	@GetMapping("/")
	public List<Vacation> findVacations() {
		List<Vacation> vacations = new ArrayList<Vacation>();
		repository.findAll().forEach(vacations::add);
		return vacations;
	}
	
	@GetMapping("/findByTitle")
	public Optional<Vacation> findByTitle(@Valid @NotBlank @RequestParam String title) {
		Optional<Vacation> vacation = repository.findByTitle(title);
		vacation.orElseThrow(() -> new VacationNotFoundException(title));
		return vacation;
	}
	
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Vacation postVacation(@NotNull @Valid @RequestBody final Vacation vacation) {
		Vacation _vacation = repository.save(new Vacation(vacation.getTitle(),vacation.getDestination(), vacation.getDescription()));
		return _vacation;
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Vacation updatVacation(@PathVariable("id") long id, @RequestBody final Vacation vacation) {
		Optional<Vacation> vacationData = repository.findById(id);
		vacationData.orElseThrow(() -> new VacationNotFoundException(id));
		Vacation  _vacation = vacationData.get();
		_vacation.setTitle(vacation.getTitle());
		_vacation.setDestination(vacation.getDestination());
		_vacation.setDescription(vacation.getDescription());
		return repository.save(_vacation);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public long deleteVacation(@PathVariable long id) {
		repository.deleteById(id);
		return id;
	}
}

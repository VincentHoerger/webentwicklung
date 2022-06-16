package server.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

import io.swagger.v3.oas.annotations.tags.Tag;
import server.exception.MemberNotFoundException;
import server.exception.VacationNotFoundException;
import server.model.Member;
import server.model.Vacation;
import server.model.VacationPriority;
import server.repository.MemberRepository;
import server.repository.VacationPriorityRepository;
import server.repository.VacationRepository;

@RestController
@RequestMapping(name="member",path="/api")
@Tag(name = "Member", description = "Api für 'Member'")
public class MemberController {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private VacationRepository vacationRepository;
	
	@Autowired
	private VacationPriorityRepository vacationPriorityRepository;

  
	@GetMapping("/member/{id}")
	public Optional<Member> findById(@PathVariable Long id) {
		Optional<Member> member = memberRepository.findById(id);
		member.orElseThrow(() -> new MemberNotFoundException(id));
		return member;
	}
	
	@GetMapping("/member/{id}/priorities")
	public Set<VacationPriority> getVacationProperties(@PathVariable Long id) {
		Optional<Member> _member = memberRepository.findById(id);
		_member.orElseThrow(() -> new MemberNotFoundException(id));
		return _member.get().getPriorities();
	}
	
	@GetMapping("/member")
	public List<Member> findMembers() {
		List<Member> members = new ArrayList<Member>();
		memberRepository.findAll().forEach(members::add);
		return members;
	}
	
	@GetMapping("/member/findByUsername")
	public Optional<Member> findByTitle(@Valid @NotBlank @RequestParam String username) {
		Optional<Member> member = memberRepository.findByUsername(username);
		member.orElseThrow(() -> new MemberNotFoundException(username));
		return member;
	}
	
	@PostMapping("/member")
	@ResponseStatus(HttpStatus.CREATED)
	public Member postMember(@NotNull @Valid @RequestBody final Member member) {
		Member _member = memberRepository.save(new Member(member.getUsername(),member.getFirstname(), member.getLastname(), member.getDateOfBirth()));
		return _member;
	}
	
	@PostMapping("/member/{id}/priorities")
	@ResponseStatus(HttpStatus.CREATED)
	public Member addVacationPriority(@PathVariable Long id, @Valid @NotBlank @RequestParam long vacationId, @Valid @NotBlank @RequestParam int priority) {
		Optional<Member> _member = memberRepository.findById(id);
		_member.orElseThrow(() -> new MemberNotFoundException(id));
		Member member = _member.get();
		
		Optional<Vacation> _vacation = vacationRepository.findById(id);
		_vacation.orElseThrow(() -> new VacationNotFoundException(id));
		
		//memberRepository.
		//VacationPriority vacationPriority = VacationPriority(member,_vacation.get(),priority);
		member.getPriorities().add(new VacationPriority(member,_vacation.get(),priority));
		
		//member.addPriority(_vacation.get(), priority);

		//return member;
		//return memberRepository.
		return memberRepository.save(member);
//		Member _member = repository.save(new Member(member.getUsername(),member.getFirstname(), member.getLastname(), member.getDateOfBirth()));
//		return _member;
	}
	
	@PutMapping("/member/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Member updatMember(@PathVariable("id") long id, @RequestBody final Member member) {
		Optional<Member> memberData = memberRepository.findById(id);
		memberData.orElseThrow(() -> new MemberNotFoundException(id));
		Member  _member = memberData.get();
		_member.setUsername(member.getUsername());
		_member.setFirstname(member.getFirstname());
		_member.setLastname(member.getLastname());
		_member.setDateOfBirth(member.getDateOfBirth());
		return memberRepository.save(_member);
	}
	
	@DeleteMapping("/member/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteMember(@PathVariable long id) {
		Optional<Member> member = memberRepository.findById(id);
		member.orElseThrow(() -> new MemberNotFoundException(id));
		memberRepository.deleteById(id);
	}
}

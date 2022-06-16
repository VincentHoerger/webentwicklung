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

import io.swagger.v3.oas.annotations.tags.Tag;
import server.exception.MemberNotFoundException;
import server.model.Member;
import server.repository.MemberRepository;

@RestController
@RequestMapping(name="member",path="/api")
@Tag(name = "Member", description = "Api f�r 'Member'")
public class MemberController {

	@Autowired
	private MemberRepository repository;

  
	@GetMapping("/member/{id}")
	public Optional<Member> findById(@PathVariable Long id) {
		Optional<Member> member = repository.findById(id);
		member.orElseThrow(() -> new MemberNotFoundException(id));
		return member;
	}
	
	@GetMapping("/member/")
	public List<Member> findMembers() {
		List<Member> members = new ArrayList<Member>();
		repository.findAll().forEach(members::add);
		return members;
	}
	
	@GetMapping("/member/findByUsername")
	public Optional<Member> findByTitle(@Valid @NotBlank @RequestParam String username) {
		Optional<Member> member = repository.findByUsername(username);
		member.orElseThrow(() -> new MemberNotFoundException(username));
		return member;
	}
	
	@PostMapping("/member/")
	@ResponseStatus(HttpStatus.CREATED)
	public Member postMember(@NotNull @Valid @RequestBody final Member member) {
		Member _member = repository.save(new Member(member.getUsername(),member.getFirstname(), member.getLastname(), member.getDateOfBirth()));
		return _member;
	}
	
	@PutMapping("/member/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Member updatMember(@PathVariable("id") long id, @RequestBody final Member member) {
		Optional<Member> memberData = repository.findById(id);
		memberData.orElseThrow(() -> new MemberNotFoundException(id));
		Member  _member = memberData.get();
		_member.setUsername(member.getUsername());
		_member.setFirstname(member.getFirstname());
		_member.setLastname(member.getLastname());
		_member.setDateOfBirth(member.getDateOfBirth());
		return repository.save(_member);
	}
	
	@DeleteMapping("/member/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteMember(@PathVariable long id) {
		Optional<Member> member = repository.findById(id);
		member.orElseThrow(() -> new MemberNotFoundException(id));
		repository.deleteById(id);
	}
}

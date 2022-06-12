package server.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import server.exception.MemberNotFoundException;
import server.model.Member;
import server.repository.MemberRepository;

@RestController
@RequestMapping("/api/member")
public class MemberController {

	@Autowired
	private MemberRepository repository;

  
	@GetMapping("/{id}")
	public Optional<Member> findById(@PathVariable Long id) {
		Optional<Member> member = repository.findById(id);
		member.orElseThrow(() -> new MemberNotFoundException(id));
		return member;
	}
	
	@GetMapping("/")
	public List<Member> findMembers() {
		List<Member> members = new ArrayList<Member>();
		repository.findAll().forEach(members::add);
		return members;
	}
	
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Member postMember(@NotNull @Valid @RequestBody final Member member) {
		Member _member = repository.save(new Member(member.getUsername(),member.getFirstname(), member.getLastname(), member.getDateOfBirth()));
		return _member;
	}
	
	@PutMapping("/{id}")
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
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public long deleteMember(@PathVariable long id) {
		repository.deleteById(id);
		return id;
	}
}

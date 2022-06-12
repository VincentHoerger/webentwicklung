package server.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import server.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
	

	Optional<Member> findById(long id);
	Optional<Member> findByUsername(Optional<String> username);
	List<Member> findByLastname(String lastname);
	List<Member> findByFirstname(String firstname);

}
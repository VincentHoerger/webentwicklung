package server.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import server.model.Vacation;

public interface VacationRepository extends JpaRepository<Vacation, Long>{

	Optional<Vacation> findByTitle(String title);
	
}

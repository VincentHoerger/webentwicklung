package server.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import server.model.Holiday;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {
	
	Optional<Holiday> findByTitle(String title);
	
}

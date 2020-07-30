package guru.springframework.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.domain.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, String> {
	
	Optional<UnitOfMeasure> findById(String id);
	Optional<UnitOfMeasure> findByDescription(String description);

}

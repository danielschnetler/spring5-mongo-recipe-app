package guru.springframework.domain;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import guru.springframework.repositories.UnitOfMeasureRepository;

@RunWith(SpringRunner.class)
public class UnitOfMeasureIT {
	
	@Autowired
	UnitOfMeasureRepository unitOfMeasureRepository;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	@Ignore
	public void findByDescription() throws Exception{

		Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
		
		assertEquals("Teaspoon", uomOptional.get().getDescription());
	}
	
	@Test
	@Ignore
	public void findByDescriptionCup() throws Exception{

		Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription("Cup");
		
		assertEquals("Cup", uomOptional.get().getDescription());
	}

}

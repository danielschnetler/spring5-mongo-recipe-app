package guru.springframework.domain;

import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ingredient {

	private String id = UUID.randomUUID().toString();
	private String description;
	private BigDecimal amount;
	
	@DBRef
	private UnitOfMeasure uom;

	public Ingredient() {}
	
	public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
		this.description = description;
		this.amount = amount;
		this.uom = uom;
	}

}

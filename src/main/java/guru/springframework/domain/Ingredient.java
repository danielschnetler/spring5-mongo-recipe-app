package guru.springframework.domain;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ingredient {

	private String id;
	private String description;
	private BigDecimal amount;
	private UnitOfMeasure uom;
    private Recipe recipe;
	
	public Ingredient() {}
	
	public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
		this.description = description;
		this.amount = amount;
		this.uom = uom;
	}

	public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom, Recipe recipe) {
		super();
		this.description = description;
		this.amount = amount;
		this.uom = uom;
		this.recipe = recipe;
	}

}
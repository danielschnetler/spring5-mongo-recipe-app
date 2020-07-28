package guru.springframework.commands;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {
	private String id;
	private String RecipeId;
	private String description;
	private BigDecimal amount;
	private UnitOfMeasureCommand uom;
}

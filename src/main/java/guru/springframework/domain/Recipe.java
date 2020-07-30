package guru.springframework.domain;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
public class Recipe {

	private String id;
	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	private String url;
	private String directions;
	private Difficulty difficulty;
	private Set<Ingredient> ingredients = new HashSet<>();
	private Byte[] image;
	private Notes notes;
	
	@DBRef
	private Set<Category> categories = new HashSet<>();

	public Recipe addIngredient(Ingredient ingredient) {
		this.ingredients.add(ingredient);
		return this;
	}

	public void setNotes(Notes notes) {
		if (notes != null) {
			this.notes = notes;
		}
	}
}

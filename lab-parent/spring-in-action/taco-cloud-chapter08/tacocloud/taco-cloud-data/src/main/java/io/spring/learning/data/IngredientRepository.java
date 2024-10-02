package io.spring.learning.data;

import io.spring.learning.tacos.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}

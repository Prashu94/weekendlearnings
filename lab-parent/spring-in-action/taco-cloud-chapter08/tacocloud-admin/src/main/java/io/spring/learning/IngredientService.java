package io.spring.learning;

public interface IngredientService {

    Iterable<Ingredient> findAll();

    Ingredient addIngredient(Ingredient ingredient);
}

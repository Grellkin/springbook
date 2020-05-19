package ru.vas.spring.springbook.repository.jdbc;

import ru.vas.spring.springbook.model.Ingredient;

import java.util.Collection;

public interface IngredientRepository {

    Collection<Ingredient> findAll();

    Ingredient findById(String id);

    Ingredient save(Ingredient ingredient);
}

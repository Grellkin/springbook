package ru.vas.spring.springbook.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import ru.vas.spring.springbook.model.Ingredient;

import java.util.List;

//первый параметр шо храним, а второй какого класса ключ, в ингредиенте стринговые кллючи
public interface IngredientRepository extends CrudRepository<Ingredient, String> {

    List<Ingredient> findAll();

}

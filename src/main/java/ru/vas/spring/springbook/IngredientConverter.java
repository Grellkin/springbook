package ru.vas.spring.springbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import ru.vas.spring.springbook.model.Ingredient;
import ru.vas.spring.springbook.repository.jdbc.JdbcIngredientRepository;

@Component
public class IngredientConverter implements Converter<String, Ingredient> {

    private final JdbcIngredientRepository ingredientRepo;

    @Autowired
    public IngredientConverter(JdbcIngredientRepository ingredientRepo) {

        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public Ingredient convert(@Nullable String source) {
        return ingredientRepo.findById(source);
    }

}

package ru.vas.spring.springbook.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@Entity
public class Ingredient {

    @Id
    private final String id;
    private final String name;
    @Enumerated(EnumType.STRING)
    private final TypeOfIngredient type;

    public enum TypeOfIngredient {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}

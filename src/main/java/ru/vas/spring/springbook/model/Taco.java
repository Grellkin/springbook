package ru.vas.spring.springbook.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "createdat")
    private LocalDateTime createdAt;

    @NotNull
    @Size(min = 5, message = "Name should be at least 5 characters long.")
    private String name;

    @ManyToMany(targetEntity = Ingredient.class)
    @JoinTable(
            name = "taco_ingredients_mapper",
            joinColumns = @JoinColumn(name = "taco_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    @Size(min = 1, message = "You should choose at least one ingredient.")
    private List<Ingredient> ingredients;

    @PrePersist
    void createdAt() {
        this.createdAt = LocalDateTime.now();
    }
}

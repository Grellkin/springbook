package ru.vas.spring.springbook.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.vas.spring.springbook.model.Ingredient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ru.vas.spring.springbook.model.Ingredient.TypeOfIngredient;
import ru.vas.spring.springbook.model.Order;
import ru.vas.spring.springbook.model.Taco;
import ru.vas.spring.springbook.repository.jpa.IngredientRepository;
import ru.vas.spring.springbook.repository.jpa.TacoRepository;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private IngredientRepository ingredientRepository;
    private TacoRepository tacoRepository;

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }

    @GetMapping
    public String designTaco(Model model) {
        List<Ingredient> ingredients = new ArrayList<>(ingredientRepository.findAll());

        for (TypeOfIngredient type :
                TypeOfIngredient.values()) {
            model.addAttribute(type.name(), filterByType(ingredients, type));
        }

        return "design";
    }

    @PostMapping
    public String constructTaco(@Valid Taco taco, Errors error, @ModelAttribute("order") Order order) {
        if (error.hasErrors()) {
            return "design";
        }
        Taco saved = tacoRepository.save(taco);
        order.addTaco(saved);
        log.info("Processing design: " + taco);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, TypeOfIngredient type) {
        return ingredients.stream()
                .filter(ingredient -> ingredient.getType().equals(type))
                .collect(Collectors.toList());
    }

}

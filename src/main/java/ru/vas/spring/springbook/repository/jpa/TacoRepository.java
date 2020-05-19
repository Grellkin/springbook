package ru.vas.spring.springbook.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import ru.vas.spring.springbook.model.Ingredient;
import ru.vas.spring.springbook.model.Taco;

//первый параметр шо храним, а второй какого класса ключ, в ингредиенте стринговые кллючи
public interface TacoRepository extends CrudRepository<Taco, Long> {

}

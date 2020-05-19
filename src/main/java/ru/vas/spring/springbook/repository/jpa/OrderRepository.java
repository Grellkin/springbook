package ru.vas.spring.springbook.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import ru.vas.spring.springbook.model.Ingredient;
import ru.vas.spring.springbook.model.Order;

//первый параметр шо храним, а второй какого класса ключ, в ингредиенте стринговые кллючи
public interface OrderRepository extends CrudRepository<Order, Long> {

}

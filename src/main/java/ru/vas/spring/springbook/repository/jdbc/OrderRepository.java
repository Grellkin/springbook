package ru.vas.spring.springbook.repository.jdbc;

import ru.vas.spring.springbook.model.Order;

public interface OrderRepository {

    Order save(Order order);
}

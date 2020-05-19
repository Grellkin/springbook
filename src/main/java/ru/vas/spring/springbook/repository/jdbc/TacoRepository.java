package ru.vas.spring.springbook.repository.jdbc;

import ru.vas.spring.springbook.model.Taco;

public interface TacoRepository {

    Taco save(Taco taco);
}

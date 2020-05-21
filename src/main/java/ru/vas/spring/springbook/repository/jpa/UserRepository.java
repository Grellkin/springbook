package ru.vas.spring.springbook.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import ru.vas.spring.springbook.model.User;
//4
public interface UserRepository extends CrudRepository<User, String> {

    User findByUsername(String username);
}

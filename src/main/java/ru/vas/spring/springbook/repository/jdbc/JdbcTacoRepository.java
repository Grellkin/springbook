package ru.vas.spring.springbook.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.vas.spring.springbook.model.Ingredient;
import ru.vas.spring.springbook.model.Taco;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Repository
public class JdbcTacoRepository implements TacoRepository {

    private JdbcTemplate template;

    @Autowired
    public JdbcTacoRepository(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Taco save(Taco taco) {
        taco.setCreatedAt(LocalDateTime.now());
        long id = saveTaco(taco);
        taco.setId(id);
        saveMapping(taco, id);
        return taco;
    }

    private void saveMapping(Taco taco, long id) {
        for (Ingredient ing :
                taco.getIngredients()) {
            template.update("insert into taco_ingredients_mapper(taco_id, ingredient_id) " +
                    "VALUES (?,?)", id, ing.getId());
        }
    }

    private Long saveTaco(Taco taco) {
        KeyHolder holder = new GeneratedKeyHolder();
        template.update(con -> {
            PreparedStatement ps = con.prepareStatement("insert into taco(name, createdat)" +
                    " VALUES(?, ?)", new String[]{"id"});
            ps.setString(1, taco.getName());
            ps.setObject(2, taco.getCreatedAt());
            return ps;
        }, holder);

        return Objects.requireNonNull(holder.getKey()).longValue();

    }
}

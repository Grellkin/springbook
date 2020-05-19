package ru.vas.spring.springbook.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.vas.spring.springbook.model.Ingredient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {

    private JdbcTemplate template;

    @Autowired
    public JdbcIngredientRepository(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Collection<Ingredient> findAll() {
        return template.query("select * from ingredient;", this::mapRow);

    }

    @Override
    public Ingredient findById(String id) {
        return template.queryForObject("select * from ingredient where id = ?;", this::mapRow, id);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {

        template.update("insert into ingredient(id, name, type) values(?,?,?);",
                ingredient.getId(), ingredient.getName(), ingredient.getType().name());
        return ingredient;
    }

    private Ingredient mapRow(ResultSet set, int rowNum) throws SQLException {
        return new Ingredient(set.getString("id"),
                set.getString("name"),
                Ingredient.TypeOfIngredient.valueOf(set.getString("type")));
    }
}

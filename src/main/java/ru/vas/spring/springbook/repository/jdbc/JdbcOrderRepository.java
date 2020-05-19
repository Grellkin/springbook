package ru.vas.spring.springbook.repository.jdbc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.vas.spring.springbook.model.Order;
import ru.vas.spring.springbook.model.Taco;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Repository
public class JdbcOrderRepository implements OrderRepository {

    private SimpleJdbcInsert orderInsert;
    private SimpleJdbcInsert mapperInsert;
    private ObjectMapper objectMapper;

    @Autowired
    public JdbcOrderRepository(JdbcTemplate template) {
        this.orderInsert = new SimpleJdbcInsert(template)
                .withTableName("taco_order")
                .usingGeneratedKeyColumns("id");

        this.mapperInsert = new SimpleJdbcInsert(template)
                .withTableName("taco_order_taco_mapper");

        objectMapper = new ObjectMapper();
    }

    @Override
    public Order save(Order order) {
        order.setPlacedAt(LocalDateTime.now());
        long id = saveOrder(order);
        order.setId(id);
        saveMapper(order);
        return order;
    }

    private void saveMapper(Order order) {
        for (Taco taco :
                order.getTacos()) {
            Map<String, Object> map = new HashMap<>();
            map.put("order_id", order.getId());
            map.put("taco_id", taco.getId());
            mapperInsert.execute(map);
        }
    }

    private long saveOrder(Order order) {
        @SuppressWarnings("unchecked")
        Map<String, Object> map = objectMapper.convertValue(order, Map.class);
        map.put("placedAt", order.getPlacedAt());
        return orderInsert.executeAndReturnKey(map).longValue();
    }
}

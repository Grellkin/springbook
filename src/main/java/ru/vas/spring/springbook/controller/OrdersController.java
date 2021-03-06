package ru.vas.spring.springbook.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.vas.spring.springbook.model.Order;
import ru.vas.spring.springbook.model.User;
import ru.vas.spring.springbook.repository.jpa.OrderRepository;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrdersController {

    private OrderRepository orderRepository;

    @Autowired
    public OrdersController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String buildCurrentOrder(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("user", user);
        return "orderForm";
    }

    @PostMapping
    public String saveOrder(@Valid @ModelAttribute("order") Order order, Errors error, SessionStatus sessionStatus,
                            @AuthenticationPrincipal User user) {
        if (error.hasErrors()){
            return "orderForm";
        }
        //order.setUser(user);
        orderRepository.save(order);
        sessionStatus.setComplete();
        log.info("Persist order to DB: " + order);
        return "redirect:/";
    }

}

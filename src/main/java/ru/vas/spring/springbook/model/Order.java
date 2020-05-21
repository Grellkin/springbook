package ru.vas.spring.springbook.model;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "taco_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "placedat")
    private LocalDateTime placedAt;

    @NotBlank(message = "Name should not be empty.")
    private String name;
    @NotBlank(message = "Street should not be empty.")
    private String street;
    @NotBlank(message = "City should not be empty.")
    private String city;
    @NotBlank(message = "State should not be empty.")
    private String state;
    @NotBlank(message = "Zip code should not be empty.")
    private String zip;

    @CreditCardNumber(message = "Not valid card number.")
    @Column(name = "ccnumber")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([/])([1-9][0-9])$", message = "Not valid expiration date.")
    @Column(name = "ccexpiration")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Not valid CVV.")
    @Column(name = "cccvv")
    private String ccCVV;

    @ManyToMany(targetEntity = Taco.class)
    @JoinTable(
            name = "taco_order_taco_mapper",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "taco_id")
    )
    List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco saved) {
        tacos.add(saved);
    }

    @PrePersist
    void placedAt() {
        placedAt = LocalDateTime.now();
    }

    @ManyToOne
    private User user;
}

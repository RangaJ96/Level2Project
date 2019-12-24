package com.slintec.backend.data;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(targetEntity = Institute.class)
    private Institute institute;

    @OneToOne(targetEntity = Order.class)
    private Order orders;

    private Double ammount;

    public Payment(Double ammount) {
        this.ammount = ammount;
    }
}

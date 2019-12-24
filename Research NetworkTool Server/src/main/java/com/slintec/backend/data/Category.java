package com.slintec.backend.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    String Category;

    @ManyToOne(fetch = FetchType.LAZY, optional = false,targetEntity = Instrument.class)
    @JoinColumn(nullable = false)
    @JsonIgnore
    private Instrument instrument;

    public Category(String category) {
    }
}

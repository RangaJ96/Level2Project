package com.slintec.backend.payload;

import com.slintec.backend.data.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstrumentProfile {

    private Long instumentId;

    private String image;

    private List<String> coverImages;

    private String instrumentName;

    private String model;

    private String brand;

    private Date manufacturedDate;

    private Date depriciatedDate;

    private Integer state;

    private  Double price;
    //
    private List<Strength> strengths;

    private List<Limitation> limitations;

    private List<Application> applications;

    private List<TechnicalSpecification> technicalSpecifications;

    private Set<Category> categories = new HashSet<>();

    //
    private  String description;

    private List<Order> orders;

    private List<Comment> comments;


    private Integer rating;

    @Email
    private String custodianEmail;

    private String instituteMail;
}

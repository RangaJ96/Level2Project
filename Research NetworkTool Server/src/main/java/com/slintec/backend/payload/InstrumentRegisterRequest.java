package com.slintec.backend.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class InstrumentRegisterRequest {

    private String image;

    //private List<String> coverImages;

    private String instrumentName;

    private String model;

    private String brand;

    //private List<String> specifications;

    //
    private List<String> strengthsList;

    private List<String> limitationsList;

    private List<String> applicationsList;

    private List<String> technicalSpecificationsList;

    private Set<String> categoriesSet = new HashSet<>();

    //


    @Temporal(TemporalType.DATE)
    private Date manufacturedDate;

    @Temporal(TemporalType.DATE)
    private Date depriciatedDate;

    private Integer state;

    private  Double price;

    private  String description;

    private Integer rating;

    private String custodianUsernameOrEmailOrNic;


}

package com.slintec.backend.payload;

import com.slintec.backend.data.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstituteProfile {

    private Long instituteId;

    private String image;

    private List<String> coverImages;

    private String instituteName;

    private String businessRegistrationNo;

    private String instituteEmail;

    private String no;

    private String street;

    private String city;

    private String province;

//telepone

    private String instituteTelephone;


    private User instituteHead;

    private String headEmail;

    private Integer rating;

    private Payment payment;

    private Double credit;

    private Integer state;

    private Instant created;

    private Instant updated;

    private User createdBy;

    private User updatedBy;

    private List<Department> departments;

   // private List<Instrument> instruments;

   // private List<Comment> comment;
}

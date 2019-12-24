package com.slintec.backend.payload;

import com.slintec.backend.data.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstituteRegisterRequest {

    private String image;

   // private List<String> coverImages;

    @Column(updatable = false, unique=true)
    private String businessRegistrationNo;

    @Column(unique=true)
    private String instituteName;


    @Email
    @NaturalId
    @Column(nullable = false, updatable = false,unique=true)
    private String instituteEmail;



    //address details
    private String no;

    private String street;

    private String city;

    private String province;

//telepone

    private String instituteTelephone;

    private String headUsernameOrEmailOrNic;

}

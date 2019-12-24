package com.slintec.backend.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    @NotEmpty(message = "FULLNAME CANNOT BE BLANK")
    @Size(min = 4,max = 70)
    private String fullName;

    @NotEmpty(message = "USERNAME CANNOT BE BLANK")
    @Size(min = 4,max = 15)
    @Column(nullable = false, updatable = false)
    private String userName;

    private String image;


    @NotEmpty(message = "NIC CANNOT BE BLANK")
    @Size(min = 10,max = 15)
    @NaturalId
    private String nic;

    @NaturalId
    @NotEmpty(message = "EMAIL CANNOT BE BLANK")
    @Size(min = 10,max = 40)
    @Email
    @Column(nullable = false, updatable = false)
    private String email;

    @NotBlank
    @NotEmpty(message = "MOBILE_NO CANNOT BE BLANK")
    @Size(min = 10,max = 12)
    private  String mobileNo;

    @NotEmpty(message = "PASSWORD CANNOT BE BLANK")
    @Size(min = 4,max = 100)
    private String password;

}

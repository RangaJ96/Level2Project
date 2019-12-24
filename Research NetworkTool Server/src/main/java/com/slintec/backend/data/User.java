package com.slintec.backend.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.slintec.backend.data.audit.DateAudit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "FULLNAME CANNOT BE BLANK")
    @Size(max = 70)
    private String fullName;

    @NotEmpty(message = "USERNAME CANNOT BE BLANK")
    @Size(max = 15)
    @NaturalId
    private String userName;

    private String image;


    @NotEmpty(message = "NIC CANNOT BE BLANK")
    @Size(max = 15)
    @Column(nullable = false, updatable = false)
    private String nic;

    @NaturalId
    @NotEmpty(message = "EMAIL CANNOT BE BLANK")
    @Size(max = 40)
    @Email
    @Column(nullable = false, updatable = false)
    private String email;

    @NotBlank
    @NotEmpty(message = "MOBILE_NO CANNOT BE BLANK")
    @Size(min = 10,max = 12)
    private  String mobileNo;

    @NotEmpty(message = "PASSWORD CANNOT BE BLANK")
    @Size(max = 100)
    @JsonIgnore
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    private String Institute;

    private  String Department;

   // private Long Instrument;

    private RoleName Role;
    /*
    credit details for transactions
    private Double credit;
    */
    private  Boolean deleted=false;

    public void setRoles(Role roles) {
        this.roles.add(roles);
    }

    public void dropRoles(Role roles) {
        this.roles.remove(roles);
    }
}

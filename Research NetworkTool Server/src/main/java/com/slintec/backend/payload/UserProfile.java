package com.slintec.backend.payload;

import com.slintec.backend.data.Role;
import com.slintec.backend.data.RoleName;
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
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {

    private Long Id;

    private String fullName;

    private String userName;

    private String image;

    private Set<Role> roles = new HashSet<>();

    private String nic;

    private String email;

    private RoleName Role;

    private  String mobileNo;

    private Instant joinedAt;

    private Instant lastLoggedIn;

}

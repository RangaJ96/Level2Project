package com.slintec.backend.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSummery {
    private String fullName;

    private String userName;

    private String image;

    private String nic;

    private String email;
}

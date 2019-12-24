package com.slintec.backend.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstituteSummery {
    private Long instituteId;

    private String image;

    private String instituteName;

    private String businessRegistrationNo;

    private String instituteEmail;

    private String instituteTelephone;

    private Integer state;
}

package com.slintec.backend.payload;

import com.slintec.backend.data.Comment;
import com.slintec.backend.data.Institute;
import com.slintec.backend.data.Instrument;
import com.slintec.backend.data.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRegisterRequest {

    private String image;

    @NaturalId
    private String departmentName;

    @Email
    private String email;

    private String headUsernameOrEmailOrNic;

    private  String telephone;
}

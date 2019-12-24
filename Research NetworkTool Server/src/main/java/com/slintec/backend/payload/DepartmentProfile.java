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
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentProfile {


    private String image;

    @NaturalId
    private String departmentName;

    private List<Instrument> instruments;

    private UserSummery departmentHead;

    private List<Comment> comments;

    private Integer rating;

    private Integer state;

    private String email;


    private  String telephone;
}

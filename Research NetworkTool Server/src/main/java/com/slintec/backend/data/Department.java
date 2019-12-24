package com.slintec.backend.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.slintec.backend.data.audit.UserDateAudit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","department","institute"})
public class Department extends UserDateAudit{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;

    private String image;

    @NaturalId
    private String departmentName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false,targetEntity = Institute.class)
    @JoinColumn(nullable = false)
    private Institute institute;

    @OneToMany(mappedBy = "department",targetEntity = Instrument.class)
    private List<Instrument> instruments;

    @OneToOne(targetEntity = User.class)
    private User departmentHead;

    @OneToMany(targetEntity = Comment.class)
    private List<Comment> comment;

    private Integer rating;

    private Integer state=1;

    private String email;

    private String headEmail;

    private  String telephone;

    private  Boolean deleted=false;
}

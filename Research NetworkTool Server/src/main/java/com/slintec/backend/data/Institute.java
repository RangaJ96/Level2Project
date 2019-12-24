package com.slintec.backend.data;

import com.slintec.backend.data.audit.UserDateAudit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Institute extends UserDateAudit{

    @Id
    @GeneratedValue
    @Column(name="instituteId")
    private Long instituteId;

    private String image;

  //  private List<String> coverImages;

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

    private Integer state=0;

    @OneToMany(mappedBy = "institute",targetEntity = Department.class)
    private List<Department> departments;

    @OneToMany(mappedBy = "institute",targetEntity = Instrument.class)
    private List<Instrument> instruments;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "id")
    private User instituteHead;

    private String headEmail;

    @OneToMany(targetEntity = Comment.class)
    private List<Comment> comments;

    private Integer rating;

    @OneToOne(targetEntity = Payment.class)
    private Payment payment;

    Double credit;

    private  Boolean deleted=false;

}

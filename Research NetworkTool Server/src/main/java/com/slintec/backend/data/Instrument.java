package com.slintec.backend.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.slintec.backend.data.audit.UserDateAudit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","department","institute"})
public class Instrument extends UserDateAudit{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long instumentId;

    private String image;

   // private List<String> coverImages;

    private String instrumentName;

    private String model;

    private String brand;

  //  private List<String> specifications;
    @OneToMany(targetEntity = Strength.class,mappedBy = "instrument")
    private List<Strength> strengths;

    @OneToMany(targetEntity = Limitation.class,mappedBy = "instrument")
    private List<Limitation> limitations;

    @OneToMany(targetEntity = Application.class,mappedBy = "instrument")
    private List<Application> applications;


    @OneToMany(targetEntity = TechnicalSpecification.class,mappedBy = "instrument")
    private List<TechnicalSpecification> technicalSpecifications;

    @OneToMany(targetEntity = Category.class,mappedBy = "instrument")
    private Set<Category> categories = new HashSet<>();

    //
    @Temporal(TemporalType.DATE)
    private Date manufacturedDate;

    @Temporal(TemporalType.DATE)
    private Date depriciatedDate;

    private Integer state=0;

    @ManyToOne(fetch = FetchType.LAZY, optional = false,targetEntity = Department.class)
    @JoinColumn(nullable = false)
    private Department department;

    @OneToOne(targetEntity = User.class)
    private User custodian;

    private  Double price;

    private  String description;

    @OneToMany(targetEntity = Order.class,mappedBy = "instrument")
    private List<Order> order;


    @OneToMany(targetEntity = Comment.class)
    private List<Comment> comment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false,targetEntity = Institute.class)
    @JoinColumn(nullable = false)
    private Institute institute;

    private Integer rating;

    private String custodianMail;

    private String instituteMail;

    private  Boolean deleted=false;

    public void setStrengths(Strength strength) {
        this.strengths.add(strength);
    }

    public void setLimitations(Limitation limitation) {
        this.limitations.add(limitation);
    }

    public void setApplications(Application application) {
        this.applications.add(application);
    }

    public void setTechnicalSpecifications(TechnicalSpecification technicalSpecification) {
        this.technicalSpecifications.add(technicalSpecification);
    }

    public void setCategories(Category category) {
        this.categories.add(category);
    }
}

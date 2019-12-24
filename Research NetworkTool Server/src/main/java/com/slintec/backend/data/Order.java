package com.slintec.backend.data;


import com.slintec.backend.data.audit.UserDateAudit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="OrderDetail")
public class Order extends UserDateAudit{

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long orderId;

    @ManyToOne(targetEntity = Instrument.class)
    private Instrument instrument;

    @OneToOne(targetEntity = User.class)
    private User reserver;

//details related to the tranasaction

    @Temporal(TemporalType.DATE)
    private Date requestStartDate;

    @Temporal(TemporalType.DATE)
    private Date requestEndDate;

    private Integer requestedQuantity;

    private Integer state;

   // private String acceptedBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderStart;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderEnd;

    private Integer paymentStatus;

    @OneToOne(targetEntity = Payment.class)
    private Payment payment;

    private Integer consumptionStatus;

    private Integer rating;

    private  Boolean deleted=false;
//end
}


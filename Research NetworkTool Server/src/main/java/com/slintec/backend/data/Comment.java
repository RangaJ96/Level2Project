package com.slintec.backend.data;

import com.slintec.backend.data.audit.DateAudit;
import com.slintec.backend.data.audit.UserDateAudit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Comment extends UserDateAudit {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long commentId;

    @ManyToOne(fetch = FetchType.LAZY,targetEntity = Institute.class)
    @JoinColumn(nullable = true)
    private Institute Institute;

    @ManyToOne(fetch = FetchType.LAZY,targetEntity = Department.class)
    @JoinColumn(nullable = true)
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY,targetEntity = Instrument.class)
    @JoinColumn(nullable = true)
    private Instrument instrument;

    private String body;

    private  Boolean deleted=false;

}

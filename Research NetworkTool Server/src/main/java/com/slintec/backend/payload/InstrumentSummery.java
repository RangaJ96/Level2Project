package com.slintec.backend.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstrumentSummery {
    private Long instumentId;

    private String image;

    private List<String> coverImages;

    private String instrumentName;

    private String model;

    private String brand;

    @Temporal(TemporalType.DATE)
    private Date manufacturedDate;

    @Temporal(TemporalType.DATE)
    private Date depriciatedDate;

    private Integer state;

    private Double price;
}

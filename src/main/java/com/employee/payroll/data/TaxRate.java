package com.employee.payroll.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



import lombok.Data;
@Entity(name = "TAXRATE")
@Data
public class TaxRate {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "FINYEAR")
    private String finYear;
    @Column(name = "LOWERLIMIT")
    private Double lowerLimit;
    @Column(name = "UPPERLIMIT")
    private Double upperLimit;
    @Column(name = "TAX")
    private Double tax;
    private int ordinal;
   
    @Temporal(TemporalType.DATE)
    private Date periodStartDate;
    
    @Temporal(TemporalType.DATE)
    private Date periodEndDate;

}


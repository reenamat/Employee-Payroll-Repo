package com.employee.payroll.data;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
@Entity
@Data
public class Employee {

    public static final int STATE_LENGTH = 50;
    public static final int POSTCODE_LENGTH = 15;
    public static final int COUNTRY_LENGTH = 50;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    
    @Column(name = "FULLNAME", nullable = false, unique = true)
    private String fullName;//TODO: to be split as first and last name
    
    @Temporal(TemporalType.DATE)
    private Date dob;
    
    private String streetline1;
    private String streetline2;
    
    @Column(length = STATE_LENGTH)
    private String state;
    
    @Column(length = POSTCODE_LENGTH)
    private String postcode;
    
    @Column(length = COUNTRY_LENGTH)
    private String country;
    
    @Column(nullable = true, unique = true)
    private String email;

    Double annualSalary;
    
    @OneToMany
    @JoinColumn(name = "EMPLOYEEID")
    private Set<Payslip> payslips;    
    
    public Employee()
    {
    }
    
    public Employee(String inFullName)
    {
        fullName = inFullName;
    }

}


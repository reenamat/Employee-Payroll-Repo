package com.employee.payroll.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class PayslipDto implements Serializable{

private static final long serialVersionUID = -1842585447485522945L;
private String fullName;
private double grossIncome;
private double netIncome;
private double tax;



}


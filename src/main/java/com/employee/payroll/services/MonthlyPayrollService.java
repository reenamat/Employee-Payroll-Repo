package com.employee.payroll.services;

import java.util.Calendar;
import java.util.Date;

import com.employee.payroll.IPayrollService;
import com.employee.payroll.ITaxService;
import com.employee.payroll.data.PayrollSchedule;
import com.employee.payroll.data.Payslip;
import com.employee.payroll.repo.TaxRateRepository;

/**
 * Service that generates payslip on monthly schedule.
 * @author Reena
 *
 */
public class MonthlyPayrollService implements IPayrollService
{
    
    ITaxService taxService;
    @Override    
    public void setTaxService(ITaxService inTax)
    {
        taxService = inTax;
    }

    @Override
    public Payslip generatePayslip(String finYear, 
        Double annualSalary,Date startPeriod,TaxRateRepository taxRateRepository)
    {
        double monthlyTax = taxService.calculateTax(finYear, annualSalary,taxRateRepository)/12;
        double monthlyIncome = annualSalary/12;
        Payslip payslip = new Payslip();
        payslip.setAnnualSalary(annualSalary);
        payslip.setSchedule(PayrollSchedule.MONTHLY);
        payslip.setGrossIncome(monthlyIncome);
        payslip.setTax(monthlyTax);
        payslip.setNetIncome(monthlyIncome - monthlyTax);
        payslip.setPeriodStartDate(startPeriod);
        
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(startPeriod);  
        calendar.add(Calendar.MONTH, 1);  
        calendar.set(Calendar.DAY_OF_MONTH, 1);  
        calendar.add(Calendar.DATE, -1); 
        payslip.setPeriodEndDate(calendar.getTime());
        return payslip;
    }
}

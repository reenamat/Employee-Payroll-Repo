package com.employee.payroll.services;

import java.util.Date;

import com.employee.payroll.IPayrollService;
import com.employee.payroll.ITaxService;
import com.employee.payroll.data.PayrollSchedule;
import com.employee.payroll.data.Payslip;
import com.employee.payroll.repo.TaxRateRepository;
/**
 * Service that generates payslip on fortnightly schedule.
 * @author Reena
 *
 */
public class FortnightlyPayrollService  implements IPayrollService
{
    
    ITaxService taxService;
    @Override
    public void setTaxService(ITaxService inTax)
    {
        taxService = inTax;
    }
    @Override
    public Payslip generatePayslip(String finYear, Double annualSalary,
        Date startPeriod,TaxRateRepository taxRateRepository)
    {
        double forntightlyTax = taxService.calculateTax(finYear, annualSalary,taxRateRepository)/24;
        double forntightlyIncome = annualSalary/24;
        Payslip payslip = new Payslip();
        payslip.setSchedule(PayrollSchedule.FORTNIGHTLY);
        payslip.setGrossIncome(forntightlyIncome);
        payslip.setTax(forntightlyTax);
        payslip.setNetIncome(forntightlyIncome - forntightlyTax);
        return payslip;
    }
}

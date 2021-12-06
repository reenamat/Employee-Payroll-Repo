package com.employee.payroll;

import java.util.Date;

import com.employee.payroll.data.Payslip;
import com.employee.payroll.repo.TaxRateRepository;

/**
 * Service Interface that generates payslip based on schedule.
 * @author Reena
 *
 */
public interface IPayrollService
{
    /**
     * Sets the tax service to calculate tax for the payslip 
     * @param inTax
     */
    public void setTaxService(ITaxService inTax);
    
    /**
     * Generate paylsip for a given year and start period
     * @param finYear
     * @param annualSalary
     * @param startPeriod
     * @param taxRateRepository
     * @return Payslip
     */
    public Payslip generatePayslip(String finYear, Double annualSalary,
        Date startPeriod,TaxRateRepository taxRateRepository);
}

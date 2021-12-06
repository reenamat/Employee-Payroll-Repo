package com.employee.payroll.services;

import java.util.List;

import com.employee.payroll.ITaxService;
import com.employee.payroll.data.IncomeType;
import com.employee.payroll.data.TaxRate;
import com.employee.payroll.repo.TaxRateRepository;

/**
 * Service that calculates for Salary income
 * @author Reena
 *
 */
public class SalaryTaxService implements ITaxService
{


    /**
     * Returns the income type for which this class calculates tax
     * @return IncomeType
     */
    public IncomeType getIncomeType()
    {
        return IncomeType.SALARY;
    }
    
    /**
     * This method calculates tax given annual salary, fetching the different 
     * taxSlabs and respective tax rates from TaxRate data model.
     * TaxRate entity holds upper and lower limit of each tax slab.
     * @param finYear financial Year 
     * @Double annualSalary Annual Salary of employee
     * TaxRateRepository jpa repository to fetch Tax Rates
     */
    public double calculateTax(String finYear, Double annualSalary,TaxRateRepository taxRateRepository)
    {
        List<TaxRate> taxRates =  taxRateRepository.findByFinYearOrderByOrdinal(finYear);
        double remSalary = annualSalary;
        double tax = 0.0;
        for(TaxRate taxRate: taxRates)
        {
            if(remSalary <= 0)
                break;
            if(taxRate.getUpperLimit() == null)
            {
                tax = tax + remSalary *taxRate.getTax(); 
                break;
            }
            double taxSlab = taxRate.getUpperLimit() - taxRate.getLowerLimit();
            if(remSalary < taxSlab)//Remaining Salary to calculate tax is less than current tax slab amount
                tax = tax + remSalary*taxRate.getTax();
            else
             // Remaining Salary to calculate tax is more than current tax slab
                //so calculate tax for whole amount of this tax slab
                tax = tax + taxSlab *taxRate.getTax();
            remSalary = remSalary - taxSlab;
        }
        return tax;
    }
}

package com.employee.payroll;

import com.employee.payroll.repo.TaxRateRepository;

/**
 * Service Interface that calculates tax based on Income Type.
 * @author Reena
 *
 */
public interface ITaxService
{
    /**
     * Calculates tax for given year and salary.
     * @param finYear financial year
     * @param annualSalary 
     * @param taxRateRepository
     * @return double tax
     */
    double calculateTax(String finYear, Double annualSalary,TaxRateRepository taxRateRepository);
}

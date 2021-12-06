package com.employee.payroll;

import com.employee.payroll.data.IncomeType;
import com.employee.payroll.data.PayrollSchedule;
import com.employee.payroll.services.FortnightlyPayrollService;
import com.employee.payroll.services.MonthlyPayrollService;
import com.employee.payroll.services.SalaryTaxService;

/**
 * Simple Factory class to fetch Payroll and Tax Services based on type.
 * @author Reena
 *
 */
public class PayrollApplicationFactory {

	public IPayrollService getPayrollService(PayrollSchedule schedule) {
	 if(PayrollSchedule.FORTNIGHTLY.equals(schedule))
		return new FortnightlyPayrollService();
	 return new MonthlyPayrollService();  
	}

    public ITaxService getTaxService(IncomeType incomeType) {
	  if(IncomeType.SALARY.equals(incomeType))
	     return new SalaryTaxService();
	  return null;
	}
}

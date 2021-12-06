package com.employee.payroll.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.payroll.IPayrollService;
import com.employee.payroll.ITaxService;
import com.employee.payroll.PayrollApplicationFactory;
import com.employee.payroll.data.Employee;
import com.employee.payroll.data.IncomeType;
import com.employee.payroll.data.PayrollSchedule;
import com.employee.payroll.data.Payslip;
import com.employee.payroll.dto.PayslipDto;
import com.employee.payroll.exception.EmployeeNotFoundException;
import com.employee.payroll.repo.EmployeeRepository;
import com.employee.payroll.repo.PayslipRepository;
import com.employee.payroll.repo.TaxRateRepository;

import lombok.RequiredArgsConstructor;
@RestController
@RequiredArgsConstructor
public class PayrollRestController {
    private final EmployeeRepository employeeRepository;
    private final TaxRateRepository taxRateRepository;
    private final PayslipRepository payslipRepository;

    
    /**
     * This API returns all payslips of an employee given his name,
     * which is enforced unique. TODO: To be identified by employee code 
     * or email.
     * @param employeeName full name of employee     
     */
    @GetMapping("/getPayslipsByEmployeename")
    public Set<Payslip> getPayslipsByEmployeename(
        @RequestParam(value = "uniqueName", required = true) String employeeName) {
        //TODO: Assuming name as unique for now, 
        //to be replaced by email or employee Id later
        Employee employee = employeeRepository.findByFullName(employeeName).orElseThrow(EmployeeNotFoundException::new);
        return employee.getPayslips();

    }
    
    /**
     * This API will generate payslip with gross and net monthly tax and salary.
     * @param employeeName  full name of employee     
     * @param annualSalary
     * @return PayslipDto with details of employee name, monthly salary and tax 
     */
    @PostMapping("/generatePayslip")
    public PayslipDto generatePayslip(  
        @RequestParam(value = "employeeName", required = true) String employeeName,
        @RequestParam(value = "annualSalary", required = true) String annualSalary) 
    {

        Employee employee = employeeRepository.findByFullName(employeeName).orElse(new Employee(employeeName));
        PayrollApplicationFactory factory = new PayrollApplicationFactory();
        
        //Based on Payroll Schedule and Income Type, relevant service picked
        //from factory does the service
        IPayrollService payrollService = factory.getPayrollService(PayrollSchedule.MONTHLY);
        ITaxService taxService = factory.getTaxService(IncomeType.SALARY);
        payrollService.setTaxService(taxService);
        Payslip payslip = payrollService.generatePayslip("2020-21", Double.parseDouble(annualSalary),
            new Date(),taxRateRepository);
        payslipRepository.save(payslip);
        if(employee.getPayslips() == null)
        {
            employee.setPayslips(new HashSet<Payslip>());
        }
        employee.getPayslips().add(payslip);
        employeeRepository.save(employee);
        PayslipDto payslipDto = new PayslipDto();
        payslipDto.setFullName(employeeName);
        payslipDto.setGrossIncome(payslip.getGrossIncome());
        payslipDto.setTax(payslip.getTax());
        payslipDto.setNetIncome(payslip.getNetIncome());
        return payslipDto;
    }

}


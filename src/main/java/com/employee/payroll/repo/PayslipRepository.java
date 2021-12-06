package com.employee.payroll.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.payroll.data.Payslip;

@Repository
public interface PayslipRepository extends JpaRepository<Payslip, Long> {
}
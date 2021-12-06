package com.employee.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.payroll.data.TaxRate;

@Repository
public interface TaxRateRepository extends JpaRepository<TaxRate, Long> {
    
    List<TaxRate> findByFinYearOrderByOrdinal(String finYear);
    
  
}
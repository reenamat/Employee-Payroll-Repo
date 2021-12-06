package com.employee.payroll;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * All Test Cases work on data pre-loaded from data.sql
 * @author Reena
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc

class EmployeePayrollApplicationTests {

    @Autowired
    private MockMvc mockMvc;



   
   @Test
   public void testGeneratePayroll() throws JsonProcessingException, Exception
   {
   
        mockMvc.perform(post("/generatePayslip")
               .param("employeeName", "Reena")
               .param("annualSalary", "60000")
               .contentType("application/json"))
               .andExpect(jsonPath("$.tax", is(500.0)))
               .andExpect(jsonPath("$.grossIncome", is(5000.0)))
               .andExpect(jsonPath("$.netIncome", is(4500.0)));
  
        mockMvc.perform(post("/generatePayslip")
             .param("employeeName", "Reena")
             .param("annualSalary", "45000")
             .contentType("application/json"))
             .andExpect(jsonPath("$.tax", is(250.0)))
             .andExpect(jsonPath("$.grossIncome", is(3750.0)))
             .andExpect(jsonPath("$.netIncome", is(3500.0)));
 
         mockMvc.perform(post("/generatePayslip")
             .param("employeeName", "Reena")
             .param("annualSalary", "90000")
             .contentType("application/json"))
             .andExpect(jsonPath("$.tax", is(1083.3333333333333)))
             .andExpect(jsonPath("$.grossIncome", is(7500.0)))
             .andExpect(jsonPath("$.netIncome", is(6416.666666666667)));
  
         mockMvc.perform(post("/generatePayslip")
             .param("employeeName", "Reena")
             .param("annualSalary", "182000")
             .contentType("application/json"))
             .andExpect(jsonPath("$.tax", is(3400.0)))
             .andExpect(jsonPath("$.grossIncome", is(15166.666666666666)))
             .andExpect(jsonPath("$.netIncome", is(11766.666666666666)));
      
         //testing boundary conditions
         mockMvc.perform(post("/generatePayslip")
             .param("employeeName", "Reena")
             .param("annualSalary", "20000")
             .contentType("application/json"))
             .andExpect(jsonPath("$.tax", is(0.0)));
         
         mockMvc.perform(post("/generatePayslip")
             .param("employeeName", "Reena")
             .param("annualSalary", "40000")
             .contentType("application/json"))
             .andExpect(jsonPath("$.tax", is(166.66666666666666)));



   }   

}

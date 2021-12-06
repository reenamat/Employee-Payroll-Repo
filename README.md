## Employee Payroll Layer RestfulAPI
---
   - Run EmployeePayrollApplication.java to start Spring-Boot Application using embedded tomcat server	

   - Check Interface Specifications and Run APIs at      	 	http://localhost:8080/swagger-ui.html and select payroll-rest-controller
			OR
   - Run below CURL commands to check APIs
---
## EXAMPLES


## Generate Payslip
curl -X POST "http://localhost:8080/generatePayslip?annualSalary=130000&employeeName=Reena" 

## Get All Payslips of an employee
curl -X GET "http://localhost:8080/getPayslipsByEmployeename?uniqueName=Reena" 

## DESIGN
Application is designed as a Spring Boot based microservice to be able to be reused by any service. Design in extensible to calculalte tax on different types of income to generate payslip at different schedules
   - Tax Rates are added for a financial Year in data.sql which gets loaded to in memory H2 database on application start
   - Domain models/entities include Employee which holds one to Many relationship with Payslip. IncomeType and PayrollSchedule are defined as enums to cater multiple income types and different payroll frequencies respectively.
   - PayrollRestController is the entry point which maps the request with request handlers
   - Repository classes manages JPA and ORM mappings
   - PayrollApplicationFactory generates the services based on IncomeType and if payroll schedule id monthly/fortnightly. PayrollApplicationFactory need to be changed into Spring bean factory
   - Decorator pattern is used for PayrollService as Monthly/Fortnightly PayrollService wraps any TaxService object and divide it monthly/fornightly. This can be extended to any other IncomeType and Schedule
   - SalaryTaxService holds the core tax calculation logic of IncomeType Salary
   - Constants are separated into separate file
   - Swagger is used to generate interface specifications
   - Junits are added using MockMvc and scenarios added for each tax slab and boundary conditions

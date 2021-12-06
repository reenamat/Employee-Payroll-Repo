
package com.employee.payroll.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No such Employee Account Exists")
public class EmployeeNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 7684993015933972537L;
    public EmployeeNotFoundException() {
        super();
    }
}





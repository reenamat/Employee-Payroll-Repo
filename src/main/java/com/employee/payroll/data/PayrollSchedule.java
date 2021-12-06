package com.employee.payroll.data;

import com.employee.payroll.Constants;

public enum PayrollSchedule
{
    MONTHLY(Constants.PAYROLL_SCHEDULE_MONTHLY), 
    FORTNIGHTLY(Constants.PAYROLL_SCHEDULE_FORTNIGHTLY);
 

    private String label;

    private PayrollSchedule(final String l)
    {
        label = l;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString()
    {
        return label;
    }
}

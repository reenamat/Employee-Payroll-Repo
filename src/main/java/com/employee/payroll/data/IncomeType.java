package com.employee.payroll.data;

import com.employee.payroll.Constants;

public enum IncomeType
{
    SALARY(Constants.INCOMETYPE_SALARY), 
    BONUS(Constants.INCOMETYPE_BONUS),
    SUPER(Constants.INCOMETYPE_SUPER);

    private String label;

    private IncomeType(final String l)
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

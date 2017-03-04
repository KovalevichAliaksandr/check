package com.departments.dao.exception.employee;

import java.util.Date;

/**
 * Created by ALex on 04.03.2017.
 */
public class EmptyResultEmployeeWithFilterException extends EmployeeException {

    private Date startDate;
    private Date endDate;

    public EmptyResultEmployeeWithFilterException(Date startDate, Date endDate)  {
        super("Find employees with  filter date between "+startDate+" and "+endDate+ " error");
        this.startDate=startDate;
        this.endDate=endDate;
    }
    public Date getStartDate() {
        return startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
}

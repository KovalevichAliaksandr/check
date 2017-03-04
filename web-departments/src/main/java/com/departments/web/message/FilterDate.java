package com.departments.web.message;

import java.util.Date;

/**
 * Created by ALex on 04.03.2017.
 */
public class FilterDate {
    private Date startDate;
    private Date endDate;

    public FilterDate() {
    }
    public FilterDate(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


}

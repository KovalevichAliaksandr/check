package com.departments.web.message;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * Created by ALex on 04.03.2017.
 */
public class FilterDate {
    @AssertTrue(message = "should by start day < end day")
    public boolean isShouldStartDateOrEndData() {
        Boolean result = true;
        if (startDate != null && endDate != null) {
            if (startDate.after(endDate)) {
                result = false;
            }
        }
        return result;
    }

    @Past(message = " Start day of birth  should  be  less than the current date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = " Start day of birth  should  be  not null")
    private Date startDate;

    @Past(message = " End day of birth  should  be  less than the current date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = " Day of birth  should  be  not null")
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

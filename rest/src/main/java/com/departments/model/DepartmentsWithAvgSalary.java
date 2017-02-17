package com.departments.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by alex on 14.2.17.
 */
public class DepartmentsWithAvgSalary implements Serializable {
    private Long id;
    private String nameDepartment;
    private Integer avgSalary;

    public DepartmentsWithAvgSalary() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    public Integer getAvgSalary() {
        return avgSalary;
    }

    public void setAvgSalary(Integer avgSalary) {
        this.avgSalary = avgSalary;
    }

    @Override
    public String toString() {
        return "DepartmentsWithAvgSalary{" +
                "id=" + id +
                ", name='" + nameDepartment + '\'' +
                ", avgSalary=" + avgSalary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentsWithAvgSalary that = (DepartmentsWithAvgSalary) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(nameDepartment, that.nameDepartment) &&
                Objects.equals(avgSalary, that.avgSalary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameDepartment, avgSalary);
    }
}

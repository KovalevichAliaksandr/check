package com.departments.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * The class (DTO) is used to pass the name of the department and the average salary on it between
 * the layers of the program.
 */
public class DepartmentsWithAvgSalary extends Department {
    private Integer avgSalary;

    public DepartmentsWithAvgSalary() {
    }

    public DepartmentsWithAvgSalary(String nameDepartment, Integer avgSalary) {
        super(nameDepartment);
        this.avgSalary = avgSalary;
    }

    public DepartmentsWithAvgSalary(long id, String nameDepartment, Integer avgSalary) {
        super(id, nameDepartment);
        this.avgSalary = avgSalary;
    }

    public Integer getAvgSalary() {
        return avgSalary;
    }

    public void setAvgSalary(Integer avgSalary) {
        this.avgSalary = avgSalary;
    }
//
//    public void setAvgSalary(Integer avgSalary) {
//        this.avgSalary = avgSalary;
//    }

//    private Long id;
//    private String nameDepartment;
//    private Integer avgSalary;
//
//    public DepartmentsWithAvgSalary() {
//    }
//
//    public DepartmentsWithAvgSalary(Long id, String nameDepartment, Integer avgSalary) {
//        this.id = id;
//        this.nameDepartment = nameDepartment;
//        this.avgSalary = avgSalary;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getNameDepartment() {
//        return nameDepartment;
//    }
//
//    public void setNameDepartment(String nameDepartment) {
//        this.nameDepartment = nameDepartment;
//    }
//
//    public Integer getAvgSalary() {
//        return avgSalary;
//    }
//
//    public void setAvgSalary(Integer avgSalary) {
//        this.avgSalary = avgSalary;
//    }
//
//    @Override
//    public String toString() {
//        return "DepartmentsWithAvgSalary{" +
//                "id=" + id +
//                ", name='" + nameDepartment + '\'' +
//                ", avgSalary=" + avgSalary +
//                '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        DepartmentsWithAvgSalary that = (DepartmentsWithAvgSalary) o;
//        return Objects.equals(id, that.id) &&
//                Objects.equals(nameDepartment, that.nameDepartment) &&
//                Objects.equals(avgSalary, that.avgSalary);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, nameDepartment, avgSalary);
//    }
}

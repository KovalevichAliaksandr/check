package com.departments.service;

import com.departments.dao.DepartmentDao;
import com.departments.dao.EmployeeDao;
import com.departments.model.Department;
import com.departments.model.DepartmentsWithAvgSalary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Created by alex on 8.2.17.
 */

@Transactional
@Service(value = "departmentService")
public class DepartmentServiceImpl implements DepartmentService {
    private static final Logger log= LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private DepartmentDao departmentDao;

    public  DepartmentServiceImpl(DepartmentDao departmentDao){
        this.departmentDao=departmentDao;
    }



    @Override
    @Transactional(readOnly = true)
    public Department findDepartmentById(Long id) {
        Assert.notNull(id,"id must not be null");
        Assert.isTrue(id>0,"id must greater than 0 ");
        return departmentDao.findDepartmentById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Department> findAllDepartments() {
        return departmentDao.findAllDepartments();
    }

    @Override
    public List<DepartmentsWithAvgSalary> findDepartmentsWithAvgSalary() {
        return departmentDao.findDepartmentsWithAvgSalary();
    }


    @Override
    public Long save(Department department) {
        Assert.notNull(department.getNameDepartment(),"Name must be not null");
        Long id=departmentDao.save(department);
        return id;
    }

    @Override
    public void delete(Long id) {
        Assert.notNull(id,"id must not be null");
        Assert.isTrue(id>0,"id must greater than 0 ");
        departmentDao.delete(id);
    }

    @Override
    public void update(Department department) {
        Assert.notNull(department.getId(),"id must not be null");
        Assert.isTrue(department.getId()>0,"id must greater than 0 ");
        Assert.notNull(department.getNameDepartment(),"Name must be not null");
        departmentDao.update(department);
    }
}

package com.departments.dao;

import com.departments.model.Department;
import com.departments.model.DepartmentsWithAvgSalary;
import com.departments.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alex on 8.2.17.
 */
public class DepartmentDaoImpl implements DepartmentDao, InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(DepartmentDaoImpl.class);
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String SQL_FIND_DEPARTMENT_BY_ID = "SELECT * FROM department WHERE id =:id";
    private static final String SQL_FIND_ALL_DEPARTMENTS = "SELECT * FROM department";
    private static final String SQL_SAVE_DEPARTMENT = "INSERT into department(name_department) VALUES (:name_department)";
    private static final String SQL_UPDATE_DEPARTMENT = "UPDATE department SET name_department=:name_department WHERE id=:id ";
    private static final String SQL_DELETE_DEPARTMENT = "DELETE FROM  department WHERE id =:id";
    private static final String SQL_GET_DEPARTMENTS_WITH_AVG_SALARY = "SELECT department.id,department.name_department,avg(salary) as avg_salary" +
            " FROM department LEFT JOIN employee on (department.id=employee.id_department)GROUP BY department.id";

    public DepartmentDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        if (dataSource == null) {
            throw new BeanCreationException("Must set dataSource !!!");
        }
        if (jdbcTemplate == null) {
            throw new BeanCreationException("jdbcTemplate is null!!!");
        }
        if (namedParameterJdbcTemplate == null) {
            throw new BeanCreationException("namedParameterJdbcTemplate is null!!!");
        }
    }

    @Override
    public Department findDepartmentById(Long id) {
        log.debug("Find contact by id={} ", id);
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("id", id);
        return namedParameterJdbcTemplate.queryForObject(SQL_FIND_DEPARTMENT_BY_ID, namedParameters, new DepartmentRowMapper());
    }

    @Override
    public List<Department> findAllDepartments() {
        log.debug("Find all contacts ");
        return namedParameterJdbcTemplate.query(SQL_FIND_ALL_DEPARTMENTS, new DepartmentRowMapper());
    }


    @Override
    public Long save(Department department) {
        log.debug("Save new employee ={} ", department);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            namedParameterJdbcTemplate.update(SQL_SAVE_DEPARTMENT, mapSqlParameterSource(department), keyHolder, new String[]{"id"});
        } catch (DuplicateKeyException exception) {
            throw new DuplicateKeyException(department.getNameDepartment());
        }
        Long id = keyHolder.getKey().longValue();
        department.setId(id);
        return id;
    }

    @Override
    public void delete(Long id) {
        log.debug("Delete  department with id ={} ", id);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);
        namedParameterJdbcTemplate.update(SQL_DELETE_DEPARTMENT, parameters);
    }

    @Override
    public void update(Department department) {
        log.debug("Update  department ={} ", department);
        namedParameterJdbcTemplate.update(SQL_UPDATE_DEPARTMENT, mapSqlParameterSource(department));
    }

    @Override
    public List<DepartmentsWithAvgSalary> findDepartmentsWithAvgSalary() {
        log.debug("select departments with average salary  ");
        return namedParameterJdbcTemplate.query(SQL_GET_DEPARTMENTS_WITH_AVG_SALARY, new DepartmentWithSAvgSalaryRowMapper());
    }

    private static final class DepartmentWithSAvgSalaryRowMapper implements RowMapper<DepartmentsWithAvgSalary> {
        @Override
        public DepartmentsWithAvgSalary mapRow(ResultSet resultSet, int i) throws SQLException {
            DepartmentsWithAvgSalary departmentsWithAvgSalary = new DepartmentsWithAvgSalary();
            departmentsWithAvgSalary.setId(resultSet.getLong("id"));
            departmentsWithAvgSalary.setNameDepartment(resultSet.getString("name_department"));
            departmentsWithAvgSalary.setAvgSalary(resultSet.getInt("avg_salary"));
            return departmentsWithAvgSalary;
        }
    }

    private static final class DepartmentRowMapper implements RowMapper<Department> {
        @Override
        public Department mapRow(ResultSet resultSet, int i) throws SQLException {
            Department department = new Department();
            department.setId(resultSet.getLong("id"));
            department.setNameDepartment(resultSet.getString("name_department"));
            return department;
        }
    }

    private MapSqlParameterSource mapSqlParameterSource(Department department) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", department.getId());
        parameterSource.addValue("name_department", department.getNameDepartment());
        return parameterSource;
    }

}




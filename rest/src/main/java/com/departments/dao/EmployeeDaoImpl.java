package com.departments.dao;

import com.departments.dao.exception.department.DuplicateNameDepartmentException;
import com.departments.dao.exception.employee.DeleteEmployeeException;
import com.departments.dao.exception.employee.EmptyResultEmployeeException;
import com.departments.dao.exception.employee.UpdateEmployeeException;
import com.departments.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alex on 8.2.17.
 */
public class EmployeeDaoImpl implements EmployeeDao,InitializingBean {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String SQL_FIND_EMPLOYEE_BY_ID="SELECT * FROM employee WHERE id =:id";
    private static final String SQL_FIND_ALL_EMPLOYEES="SELECT * FROM employee";
    private static final String SQL_SAVE_EMPLOYEE="INSERT into employee(first_name,last_name,dob,salary,id_department) " +
            "VALUES (:first_name,:last_name,:dob,:salary,:id_department)";
    private static final String SQL_UPDATE_EMPLOYEE="UPDATE employee SET first_name=:first_name,last_name=:last_name," +
            "dob=:dob,salary=:salary, id_department=:id_department WHERE id=:id ";
    private static final String SQL_DELETE_EMPLOYEE="DELETE FROM  employee WHERE id =:id";

    private static final Logger log= LoggerFactory.getLogger(EmployeeDaoImpl.class);

    public EmployeeDaoImpl(DataSource dataSource) {
        this.dataSource=dataSource;
        jdbcTemplate=new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (dataSource==null){
            throw  new BeanCreationException("Must set dataSource !!!");
        }
        if (jdbcTemplate==null){
            throw new BeanCreationException("jdbcTemplate is null!!!");
        }
        if (namedParameterJdbcTemplate==null){
            throw new BeanCreationException("namedParameterJdbcTemplate is null!!!");
        }
    }
    @Override
    public Employee findEmployeeById(Long id) {
        log.info("Find employee by id={} ",id);
        Map<String,Object> namedParameters=new HashMap<>();
        namedParameters.put("id",id);
        try {
            return namedParameterJdbcTemplate.queryForObject(SQL_FIND_EMPLOYEE_BY_ID,namedParameters,new EmployeeRowMapper());
        }catch (EmptyResultDataAccessException e){
            throw new EmptyResultEmployeeException(id);
        }

    }

    @Override
    public List<Employee> findAllEmployees() {
        log.info("Find all employees ");
        return namedParameterJdbcTemplate.query(SQL_FIND_ALL_EMPLOYEES,new EmployeeRowMapper());
    }

    @Override
    @Transactional
    public Long save(Employee employee)  throws RuntimeException {
        log.info("Save new employee ={} ",employee);
        KeyHolder keyHolder=new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(SQL_SAVE_EMPLOYEE, mapSqlParameterSource(employee), keyHolder, new String[]{"id"});
        Long id=keyHolder.getKey().longValue();
        employee.setId(id);
        return id;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Delete  employee with id ={} ",id);
        Map<String,Object> parameters=new HashMap<>();
        parameters.put("id",id);
        try {
            namedParameterJdbcTemplate.update(SQL_DELETE_EMPLOYEE,parameters);
        }catch (DataAccessException e){
            throw new DeleteEmployeeException(id);
        }

    }
    @Override
    @Transactional
    public void update(Employee employee) {
        log.info("Update  employee ={} ",employee);
        try {
            namedParameterJdbcTemplate.update(SQL_UPDATE_EMPLOYEE,mapSqlParameterSource(employee));
        }catch (DataAccessException e){
            throw new UpdateEmployeeException(employee.getId());
        }
    }

    private static final class EmployeeRowMapper implements RowMapper<Employee> {
        @Override
        public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
            Employee employee=new Employee();
            employee.setId(resultSet.getLong("id"));
            employee.setFirstName(resultSet.getString("first_name"));
            employee.setLastName(resultSet.getString("last_Name"));
            employee.setDob(resultSet.getTimestamp("dob"));
            employee.setSalary(resultSet.getInt("salary"));
            employee.setIdDepartment(resultSet.getLong("id_department"));
            return employee;
        }
    }
    private MapSqlParameterSource mapSqlParameterSource(Employee employee) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", employee.getId());
        parameterSource.addValue("first_name",employee.getFirstName());
        parameterSource.addValue("last_name",employee.getLastName());
        parameterSource.addValue("dob",employee.getDob());
        parameterSource.addValue("salary",employee.getSalary());
        parameterSource.addValue("id_department",employee.getIdDepartment());
        return parameterSource;
    }
}

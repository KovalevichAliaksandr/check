package com.departments.dao;

import com.departments.dao.exception.department.DuplicateNameDepartmentException;
import com.departments.dao.exception.department.DeleteDepartmentException;
import com.departments.dao.exception.department.FindDepartmentByIdException;
import com.departments.dao.exception.department.UpdateDepartmentException;
import com.departments.model.Department;
import com.departments.model.DepartmentsWithAvgSalary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class DepartmentDaoImpl implements DepartmentDao, InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(DepartmentDaoImpl.class);

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${department.selectDepartmentById}")
    String SQL_FIND_DEPARTMENT_BY_ID ;
    @Value("${department.selectListDepartments}")
    String SQL_FIND_ALL_DEPARTMENTS ;
    @Value("${department.saveDepartment}")
    String SQL_SAVE_DEPARTMENT ;
    @Value("${department.updateDepartment}")
    String SQL_UPDATE_DEPARTMENT;
    @Value("${department.deleteDepartment}")
    String SQL_DELETE_DEPARTMENT;
    @Value("${department.selectListDepartmentsWithAvgSalary}")
    String SQL_GET_DEPARTMENTS_WITH_AVG_SALARY;

    public DepartmentDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
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
    public Department findDepartmentById(Long id) throws DataAccessException {
        log.debug("Find contact by id={} ", id);
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("id", id);
        try {
            return namedParameterJdbcTemplate.queryForObject(SQL_FIND_DEPARTMENT_BY_ID, namedParameters, new DepartmentRowMapper());
        } catch (EmptyResultDataAccessException e){
            throw new FindDepartmentByIdException(id);
        }
    }

    @Override
    public List<Department> findAllDepartments() throws DataAccessException{
        log.debug("Find all contacts ");
        return namedParameterJdbcTemplate.query(SQL_FIND_ALL_DEPARTMENTS, new DepartmentRowMapper());
    }

    @Override
    @Transactional
    public Long save(Department department) throws DuplicateNameDepartmentException {
        log.debug("Save new employee ={} ", department);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            namedParameterJdbcTemplate.update(SQL_SAVE_DEPARTMENT, mapSqlParameterSource(department), keyHolder, new String[]{"id"});
        } catch (DuplicateKeyException e) {
            throw new DuplicateNameDepartmentException(department.getNameDepartment());
        }
        Long id = keyHolder.getKey().longValue();
        department.setId(id);
        return id;
    }

    @Override
    @Transactional
    public void delete(Long id) throws DeleteDepartmentException {
        log.debug("Delete  department with id ={} ", id);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);
        try {
            namedParameterJdbcTemplate.update(SQL_DELETE_DEPARTMENT, parameters);
        }catch (DataAccessException e){
            throw new DeleteDepartmentException(id);
        }
    }

    @Override
    @Transactional
    public void update(Department department) throws UpdateDepartmentException {
        log.debug("Update  department ={} ", department);
        try{
            namedParameterJdbcTemplate.update(SQL_UPDATE_DEPARTMENT, mapSqlParameterSource(department));
        }catch (DataAccessException e){
            throw new UpdateDepartmentException(department.getNameDepartment());
        }
    }

    @Override
    public List<DepartmentsWithAvgSalary> findDepartmentsWithAvgSalary() throws DataAccessException{
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




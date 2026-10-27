package com.example.ems.repository;

import com.example.ems.model.Employee;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class JdbcEmployeeRepository implements EmployeeRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcEmployeeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(final Employee employee) {
        final String sql = "INSERT INTO employees (name, email, department, salary) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, employee.getName().trim());
            ps.setString(2, employee.getEmail().trim());
            ps.setString(3, employee.getDepartment().trim());
            ps.setDouble(4, employee.getSalary());
            return ps;
        }, keyHolder);
        if (keyHolder.getKey() != null) {
            employee.setId(keyHolder.getKey().longValue());
        }
    }

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query(
            "SELECT id, name, email, department, salary FROM employees ORDER BY id DESC",
            new RowMapper<Employee>() {
                @Override
                public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new Employee(rs.getLong("id"), rs.getString("name"),
                        rs.getString("email"), rs.getString("department"), rs.getDouble("salary"));
                }
            });
    }
}

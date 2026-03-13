package com.hotel.repository;

import com.hotel.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 员工数据访问接口
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    /**
     * 根据工号查找员工
     */
    Employee findByEmployeeNumber(String employeeNumber);
    
    /**
     * 查找在职员工
     */
    List<Employee> findByIsActiveTrue();
    
    /**
     * 根据职位查找员工
     */
    List<Employee> findByPosition(String position);
    
    /**
     * 根据姓名模糊查询
     */
    List<Employee> findByNameContaining(String name);
}

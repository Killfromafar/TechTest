package com.duffy.repo;

import com.duffy.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    List<Employee> findByDepartment_DepartmentDescriptionContaining(String departmentDescription);

    List<Employee> findByPosition_PayTypeContaining(String payType);

    List<Employee> findByEducationLevelContaining(String educationLevel);

    List<Employee> findByEducationLevelContainingAndDepartmentDepartmentDescriptionContainingAndPositionPayTypeContaining(String educationLevel, String departmentDescription, String payType);
}
package com.duffy;

import com.duffy.model.Department;
import com.duffy.model.Employee;
import com.duffy.model.Position;
import com.duffy.repo.EmployeeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class TestEmployeeRepository {

    @Autowired
    private TestEntityManager entityManager;

    @Resource
    private EmployeeRepository employeeRepository;

    @Test
    public void whenFindByEducationLevel() {
        // given
        Department testDepartment = new Department();
        Position testPosition = new Position();
        Employee testEmployee = new Employee();
        testDepartment.setDepartmentDescription("NotSearchedOn");
        testPosition.setPayType("NotSearchedOn");
        testEmployee.setEducationLevel("testEducationLevel");
        testEmployee.setDepartment(testDepartment);
        testEmployee.setPosition(testPosition);

        Department testDepartment2 = new Department();
        Position testPosition2 = new Position();
        Employee testEmployee2 = new Employee();
        testDepartment2.setDepartmentDescription("ShouldNotBeFound");
        testPosition2.setPayType("ShouldNotBeFound");
        testEmployee2.setEducationLevel("ShouldNotBeFound");
        testEmployee2.setDepartment(testDepartment2);
        testEmployee2.setPosition(testPosition2);

        employeeRepository.save(testEmployee);
        employeeRepository.save(testEmployee2);

        // when
        List<Employee> results = employeeRepository.findByEducationLevelContainingAndDepartmentDepartmentDescriptionContainingAndPositionPayTypeContaining(
                testEmployee.getEducationLevel(), "", "");

        // then
        Assert.assertEquals(1, results.size());
        Assert.assertEquals(testEmployee.getEducationLevel(), results.get(0).getEducationLevel());
        Assert.assertEquals(testEmployee.getDepartment().getDepartmentDescription(), results.get(0).getDepartment().getDepartmentDescription());
        Assert.assertEquals(testEmployee.getPosition().getPayType(), results.get(0).getPosition().getPayType());
    }
    @Test
    public void whenFindByDepartmentDescription() {
        // given
        Department testDepartment = new Department();
        Position testPosition = new Position();
        Employee testEmployee = new Employee();
        testDepartment.setDepartmentDescription("testDepartmentDescription");
        testPosition.setPayType("NotSearchedOn");
        testEmployee.setEducationLevel("NotSearchedOn");
        testEmployee.setDepartment(testDepartment);
        testEmployee.setPosition(testPosition);

        Department testDepartment2 = new Department();
        Position testPosition2 = new Position();
        Employee testEmployee2 = new Employee();
        testDepartment2.setDepartmentDescription("ShouldNotBeFound");
        testPosition2.setPayType("ShouldNotBeFound");
        testEmployee2.setEducationLevel("ShouldNotBeFound");
        testEmployee2.setDepartment(testDepartment2);
        testEmployee2.setPosition(testPosition2);

        employeeRepository.save(testEmployee);
        employeeRepository.save(testEmployee2);

        // when
        List<Employee> results = employeeRepository.findByEducationLevelContainingAndDepartmentDepartmentDescriptionContainingAndPositionPayTypeContaining(
                "", testEmployee.getDepartment().getDepartmentDescription(), "");

        // then
        Assert.assertEquals(1, results.size());
        Assert.assertEquals(testEmployee.getEducationLevel(), results.get(0).getEducationLevel());
        Assert.assertEquals(testEmployee.getDepartment().getDepartmentDescription(), results.get(0).getDepartment().getDepartmentDescription());
        Assert.assertEquals(testEmployee.getPosition().getPayType(), results.get(0).getPosition().getPayType());
    }
    @Test
    public void whenFindByPayType() {
        // given
        Department testDepartment = new Department();
        Position testPosition = new Position();
        Employee testEmployee = new Employee();
        testDepartment.setDepartmentDescription("NotSearchedOn");
        testPosition.setPayType("testPayType");
        testEmployee.setEducationLevel("NotSearchedOn");
        testEmployee.setDepartment(testDepartment);
        testEmployee.setPosition(testPosition);

        Department testDepartment2 = new Department();
        Position testPosition2 = new Position();
        Employee testEmployee2 = new Employee();
        testDepartment2.setDepartmentDescription("ShouldNotBeFound");
        testPosition2.setPayType("ShouldNotBeFound");
        testEmployee2.setEducationLevel("ShouldNotBeFound");
        testEmployee2.setDepartment(testDepartment2);
        testEmployee2.setPosition(testPosition2);

        employeeRepository.save(testEmployee);
        employeeRepository.save(testEmployee2);

        // when
        List<Employee> results = employeeRepository.findByEducationLevelContainingAndDepartmentDepartmentDescriptionContainingAndPositionPayTypeContaining(
                "", "", testEmployee.getPosition().getPayType());

        // then
        Assert.assertEquals(1, results.size());
        Assert.assertEquals(testEmployee.getEducationLevel(), results.get(0).getEducationLevel());
        Assert.assertEquals(testEmployee.getDepartment().getDepartmentDescription(), results.get(0).getDepartment().getDepartmentDescription());
        Assert.assertEquals(testEmployee.getPosition().getPayType(), results.get(0).getPosition().getPayType());
    }

    @Test
    public void whenFindByAll() {
        // given
        Department testDepartment = new Department();
        Position testPosition = new Position();
        Employee testEmployee = new Employee();
        testDepartment.setDepartmentDescription("testDepartmentDescription");
        testPosition.setPayType("testPayType");
        testEmployee.setEducationLevel("testEductionLevel");
        testEmployee.setDepartment(testDepartment);
        testEmployee.setPosition(testPosition);

        Department testDepartment2 = new Department();
        Position testPosition2 = new Position();
        Employee testEmployee2 = new Employee();
        testDepartment2.setDepartmentDescription("ShouldNotBeFound");
        testPosition2.setPayType("ShouldNotBeFound");
        testEmployee2.setEducationLevel("ShouldNotBeFound");
        testEmployee2.setDepartment(testDepartment2);
        testEmployee2.setPosition(testPosition2);

        employeeRepository.save(testEmployee);
        employeeRepository.save(testEmployee2);

        // when
        List<Employee> results = employeeRepository.findByEducationLevelContainingAndDepartmentDepartmentDescriptionContainingAndPositionPayTypeContaining(
                testEmployee.getEducationLevel(), testEmployee.getDepartment().getDepartmentDescription(), testEmployee.getPosition().getPayType());

        // then
        Assert.assertEquals(1, results.size());
        Assert.assertEquals(testEmployee.getEducationLevel(), results.get(0).getEducationLevel());
        Assert.assertEquals(testEmployee.getDepartment().getDepartmentDescription(), results.get(0).getDepartment().getDepartmentDescription());
        Assert.assertEquals(testEmployee.getPosition().getPayType(), results.get(0).getPosition().getPayType());
    }
}

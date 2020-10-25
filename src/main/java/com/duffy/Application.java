package com.duffy;

import com.duffy.repo.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Value("${department}")
    String departmentDescription;
    @Value("${pay}")
    String payType;
    @Value("${education}")
    String educationLevel;

    @Autowired
    private EmployeeRepository employeeRepository;

    private static Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        LOG.info("APPLICATION STARTED");
        SpringApplication.run(Application.class, args);
        LOG.info("APPLICATION FINISHED");
        System.exit(0);
    }

    @Override
    public void run(String... args) {
        LOG.info("EXECUTING");
        validateQueryParams();
        LOG.info("QUERYING FOR EMPLOYEES WHERE: " + "education_level=" + educationLevel + " AND " + "department_description=" + departmentDescription + " AND " + "pay_type=" + payType);
        employeeRepository.findByEducationLevelContainingAndDepartmentDepartmentDescriptionContainingAndPositionPayTypeContaining(
                educationLevel, departmentDescription, payType).forEach(System.out::println);
    }

    private void validateQueryParams() {
        validateDepartment();
        validatePayType();
        validateEducation();
    }


    private void validateDepartment() {
        if (departmentDescription == null || departmentDescription.isBlank()) {
            departmentDescription = "";
        }
    }

    private void validatePayType() {
        if (payType == null || payType.isBlank()) {
            payType = "";
        }
    }

    private void validateEducation() {
        if (educationLevel == null || educationLevel.isBlank()) {
            educationLevel = "";
        }
    }

}




/*
Write a CLI java program that:
1. Allows the user to specify a department, pay-type and education level.
3. Display the results of that query

DB Details:
MySQL DB with 3 Tables
JDBC URL: jdbc:mysql://35.187.11.81:3306/foodmart
Username: technical_test
Password: HopefullyProspectiveDevsDontBreakMe

connector jar: https://mvnrepository.com/artifact/mysql/mysql-connector-java/5.1.21
//todo: DB table entity models https://spring.io/guides/gs/accessing-data-mysql/
    departmentId = SELECT department_id FROM department WHERE department_description = "User Input"
    positionId = SELECT position_id FROM position WHERE pay_type = "User Input"
    result = SELECT * FROM employee WHERE department_id = departmentId && position_id = positionId && education_level = "user Input"
     */
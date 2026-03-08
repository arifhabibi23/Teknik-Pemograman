package id.ac.polban.employee.main;

import id.ac.polban.employee.model.*;
import id.ac.polban.employee.service.EmployeeService;

public class EmployeeMain {

    public static void main(String[] args) {

        Department deptIT = new Department("IT");
        Department deptHR = new Department("HR");
        Department deptFinance = new Department("Finance");
        
        
        EmploymentType permanent = new EmploymentType("Permanent");
        EmploymentType contract = new EmploymentType("Contract");

        Employee emp196 = new Employee(196, "Arif", deptIT, permanent, 10000000);
        Employee emp26 = new Employee(26, "Habibi", deptIT, permanent, 12000000);
        Employee emp238 = new Employee(238, "Simamora", deptIT, permanent, 13000000);
        Employee emp003 = new Employee(003, "Rahayu", deptHR, permanent, 15000000);
        Employee emp026 = new Employee(026, "Raia", deptFinance, contract, 7000000);

        EmployeeService service = new EmployeeService();

        service.addEmployee(emp196);
        service.addEmployee(emp26);
        service.addEmployee(emp238);
        service.addEmployee(emp003);
        service.addEmployee(emp026);

        service.displayAllEmployees();
    }
}
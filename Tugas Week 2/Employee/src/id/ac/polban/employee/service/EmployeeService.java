package id.ac.polban.employee.service;

import java.util.HashMap;
import java.util.Map;

import id.ac.polban.employee.model.Employee;

public class EmployeeService {

    private final Map<Integer, Employee> employees = new HashMap<>();

    public void addEmployee(Employee emp) {
        employees.put(emp.getId(), emp);
    }

    public Employee getEmployee(int id) {
        return employees.get(id);
    }

    public void raiseSalary(int id, double percent) {
        Employee emp = employees.get(id);

        if (emp != null) {
            double newSalary = emp.getSalary() * (1 + percent / 100);
            emp.setSalary(newSalary);
        }
    }
    public void displayAllEmployees() {

    System.out.println("=== DAFTAR KARYAWAN ===");

    for (Employee emp : employees.values()) {
        System.out.println("                                ");
        System.out.printf("ID      : %d%n", emp.getId());
        System.out.printf("Nama    : %s%n", emp.getName());
        System.out.printf("Dept    : %s%n", emp.getDepartment().getName());
        System.out.printf("Type    : %s%n", emp.getType().getType());
        System.out.printf("Salary  : %,.0f%n", emp.getSalary());
    }

}
}
 
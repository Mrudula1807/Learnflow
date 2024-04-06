import java.util.*;

class Employee {
    private int id;
    private String name;
    private double hourlyRate;
    private double totalHoursWorked;

    public Employee(int id, String name, double hourlyRate) {
        this.id = id;
        this.name = name;
        this.hourlyRate = hourlyRate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public double getTotalHoursWorked() {
        return totalHoursWorked;
    }

    public void setTotalHoursWorked(double totalHoursWorked) {
        this.totalHoursWorked = totalHoursWorked;
    }
}

class PayrollSystem {
    private List<Employee> employees = new ArrayList<>();
    private double taxRate = 0.1; // 10% tax rate

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void recordHoursWorked(int employeeId, double hoursWorked) {
        for (Employee employee : employees) {
            if (employee.getId() == employeeId) {
                employee.setTotalHoursWorked(hoursWorked);
                break;
            }
        }
    }

    public double calculateSalary(Employee employee) {
        return employee.getHourlyRate() * employee.getTotalHoursWorked();
    }

    public double calculateTax(double salary) {
        return salary * taxRate;
    }

    public double calculateNetSalary(double salary, double tax) {
        return salary - tax;
    }

    public void generatePayslip(Employee employee, double salary, double tax, double netSalary) {
        System.out.println("Payslip for Employee ID: " + employee.getId());
        System.out.println("Name: " + employee.getName());
        System.out.println("Hourly Rate: $" + employee.getHourlyRate());
        System.out.println("Total Hours Worked: " + employee.getTotalHoursWorked());
        System.out.println("Gross Salary: $" + salary);
        System.out.println("Tax Deduction: $" + tax);
        System.out.println("Net Salary: $" + netSalary);
    }
}

public class Main {
    public static void main(String[] args) {
        Employee employee1 = new Employee(1, "Mrudula", 20); // Employee ID, Name, Hourly Rate
        Employee employee2 = new Employee(2, "Jayanth", 25);

        PayrollSystem payrollSystem = new PayrollSystem();
        payrollSystem.addEmployee(employee1);
        payrollSystem.addEmployee(employee2);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter hours worked for Employee ID " + employee1.getId() + ": ");
        double hoursWorked1 = scanner.nextDouble();
        payrollSystem.recordHoursWorked(employee1.getId(), hoursWorked1);

        System.out.print("Enter hours worked for Employee ID " + employee2.getId() + ": ");
        double hoursWorked2 = scanner.nextDouble();
        payrollSystem.recordHoursWorked(employee2.getId(), hoursWorked2);

        double salary1 = payrollSystem.calculateSalary(employee1);
        double tax1 = payrollSystem.calculateTax(salary1);
        double netSalary1 = payrollSystem.calculateNetSalary(salary1, tax1);
        payrollSystem.generatePayslip(employee1, salary1, tax1, netSalary1);

        double salary2 = payrollSystem.calculateSalary(employee2);
        double tax2 = payrollSystem.calculateTax(salary2);
        double netSalary2 = payrollSystem.calculateNetSalary(salary2, tax2);
        payrollSystem.generatePayslip(employee2, salary2, tax2, netSalary2);

        scanner.close();
    }
}

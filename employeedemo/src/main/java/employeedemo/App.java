package employeedemo;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        EmployeeDAO dao = new EmployeeDAO();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Employee MENU =====");
            System.out.println("1. Insert Employee");
            System.out.println("2. Read All Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    sc.nextLine();
                    System.out.println("Enter Employee ID");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Employee name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Department: ");
                    String department = sc.nextLine();
                    System.out.println("Enter Employee Salary");
                    double salary = sc.nextDouble();
                    sc.nextLine();
                    System.out.println("Enter Employee Joining Date(YYYY/MM/DD");
                    String date = sc.nextLine();
                    LocalDate joiningDate = LocalDate.parse(date);
                    dao.saveEmployee(new Employee(id,name,department,salary,joiningDate));
                    System.out.println("Employee inserted successfully!");
                    break;

                case 2:
                    List<Employee> Employees = dao.getAllEmployees();
                    if (Employees.isEmpty()) {
                        System.out.println("No Employees found.");
                    } else {
                        for (Employee p : Employees) {
                            System.out.println(p);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter id of Employee to update: ");
                    int updateId = sc.nextInt();
                    Employee existing = dao.getEmployee(updateId);
                    if (existing == null) {
                        System.out.println("Employee not found.");
                        break;
                    }
                    sc.nextLine();
                    System.out.print("Enter new name: ");
                    existing.setName(sc.nextLine());
                    System.out.println("Enter new Department");
                    existing.setDepartment(sc.nextLine());
                    System.out.println("Enter new Salary");
                    existing.setSalary(sc.nextDouble());
                    sc.nextLine();
                    dao.updateEmployee(existing);
                    System.out.println("Employee updated successfully!");
                    break;

                case 4:
                    System.out.print("Enter id of Employee to delete: ");
                    int deleteId = sc.nextInt();
                    sc.nextLine();
                    dao.deleteEmployee(deleteId);
                    System.out.println("Employee deleted (if it existed).");
                    break;

                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
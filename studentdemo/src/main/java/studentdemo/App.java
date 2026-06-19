package studentdemo;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Student MENU =====");
            System.out.println("1. Insert Student");
            System.out.println("2. Read All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    sc.nextLine();
                    System.out.println("Enter Student ID");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Student name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Student Email: ");
                    String email = sc.nextLine();
                    System.out.println("Enter Student Course");
                    String course = sc.nextLine();
                    System.out.println("Enter Student Age");
                    int age = sc.nextInt();
                    sc.nextLine();
                    dao.saveStudent(new Student(id,name,email,course,age));
                    System.out.println("Student inserted successfully!");
                    break;

                case 2:
                    List<Student> Students = dao.getAllStudents();
                    if (Students.isEmpty()) {
                        System.out.println("No Students found.");
                    } else {
                        for (Student p : Students) {
                            System.out.println(p);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter id of Student to update: ");
                    int updateId = sc.nextInt();
                    Student existing = dao.getStudent(updateId);
                    if (existing == null) {
                        System.out.println("Student not found.");
                        break;
                    }
                    sc.nextLine();
                    System.out.print("Enter new name: ");
                    existing.setName(sc.nextLine());
                    System.out.print("Enter new email: ");
                    existing.setEmail(sc.nextLine());
                    System.out.println("Enter new Course");
                    existing.setCourse(sc.nextLine());
                    System.out.println("Enter new Age");
                    existing.setAge(sc.nextInt());
                    sc.nextLine();
                    dao.updateStudent(existing);
                    System.out.println("Student updated successfully!");
                    break;

                case 4:
                    System.out.print("Enter id of Student to delete: ");
                    int deleteId = sc.nextInt();
                    sc.nextLine();
                    dao.deleteStudent(deleteId);
                    System.out.println("Student deleted (if it existed).");
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
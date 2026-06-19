package com.example.productdemo;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== PRODUCT MENU =====");
            System.out.println("1. Insert Product");
            System.out.println("2. Read All Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    sc.nextLine();
                    System.out.print("Enter product name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter product price: ");
                    double price = sc.nextDouble();
                    dao.saveProduct(new Product(name, price));
                    System.out.println("Product inserted successfully!");
                    break;

                case 2:
                    List<Product> products = dao.getAllProducts();
                    if (products.isEmpty()) {
                        System.out.println("No products found.");
                    } else {
                        for (Product p : products) {
                            System.out.println(p);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter id of product to update: ");
                    int updateId = sc.nextInt();
                    Product existing = dao.getProduct(updateId);
                    if (existing == null) {
                        System.out.println("Product not found.");
                        break;
                    }
                    sc.nextLine();
                    System.out.print("Enter new name: ");
                    existing.setName(sc.nextLine());
                    System.out.print("Enter new price: ");
                    existing.setPrice(sc.nextDouble());
                    dao.updateProduct(existing);
                    System.out.println("Product updated successfully!");
                    break;

                case 4:
                    System.out.print("Enter id of product to delete: ");
                    int deleteId = sc.nextInt();
                    dao.deleteProduct(deleteId);
                    System.out.println("Product deleted (if it existed).");
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
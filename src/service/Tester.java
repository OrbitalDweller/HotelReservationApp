package service;

import model.Customer;

public class Tester {
    public static void main(String[] args) {
        // Create an instance of CustomerService
        CustomerService customerService = CustomerService.getInstance();

        // Test adding a customer
        customerService.addCustomer("John", "Doe", "john@example.com");

        // Test getting a customer
        Customer customer = customerService.getCustomer("john@example.com");
        System.out.println("Customer: " + customer);

        // Test getting all customers
        System.out.println("All Customers: " + customerService.getAllCustomers());

        // Test adding another customer
        customerService.addCustomer("Jane", "Smith", "jane@example.com");

        // Test getting all customers again
        System.out.println("All Customers: " + customerService.getAllCustomers());

        // Test getting a non-existent customer
        Customer nonExistentCustomer = customerService.getCustomer("nonexistent@example.com");
        System.out.println("Non-existent Customer: " + nonExistentCustomer);

        // Test adding a customer with a bad email
        try {
            customerService.addCustomer("Isaac", "Newton", "bad_email");
        } catch (IllegalArgumentException ex) {
            System.out.println("Exception caught: " + ex.getMessage());
        }
    }
}

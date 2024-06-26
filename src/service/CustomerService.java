package service;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {
    private static CustomerService reference = new CustomerService();
    private Map<String, Customer> customers;

    private CustomerService() {
        customers = new HashMap<String, Customer>();
    }

    public static CustomerService getReference() {
        return reference;
    }

    public void addCustomer(String firstName, String lastName, String email) {
        Customer customer = new Customer(firstName, lastName, email);
        customers.put(email, customer);
    }

    public Customer getCustomer(String customerEmail) {
        return customers.getOrDefault(customerEmail, null);
    }

    public Collection<Customer> getAllCustomers() {
        return customers.values();
    }
}

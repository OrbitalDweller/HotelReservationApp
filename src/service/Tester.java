package service;

import model.*;

import java.util.Collection;
import java.util.Date;

public class Tester {
    public static void main(String[] args) {
        // Create an instance of CustomerService
        CustomerService customerService = CustomerService.getReference();

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

        // Get the singleton instance of ReservationService
        ReservationService reservationService = ReservationService.getReference();

        // Create some rooms
        IRoom room101 = new Room("101", 100.0, RoomType.SINGLE);
        IRoom room102 = new Room("102", 150.0, RoomType.DOUBLE);

        // Add rooms to the reservation service
        reservationService.addRoom(room101);
        reservationService.addRoom(room102);

        // Create some customers
        Customer customer1 = new Customer("John", "Doe", "john.doe@example.com");
        Customer customer2 = new Customer("Jane", "Smith", "jane.smith@example.com");

        // Reserve rooms
        Date checkInDate = new Date(); // Use current date for simplicity
        Date checkOutDate = new Date(checkInDate.getTime() + (1000 * 60 * 60 * 24)); // 1 day later

        Reservation reservation1 = reservationService.reserveARoom(customer1, room101, checkInDate, checkOutDate);
        Reservation reservation2 = reservationService.reserveARoom(customer2, room102, checkInDate, checkOutDate);

        // Find available rooms
        Date newCheckInDate = new Date(checkOutDate.getTime() + (1000 * 60 * 60 * 24)); // 1 day after checkOutDate
        Date newCheckOutDate = new Date(newCheckInDate.getTime() + (1000 * 60 * 60 * 24)); // 1 day later

        Collection<IRoom> availableRooms = reservationService.findRooms(newCheckInDate, newCheckOutDate);
        System.out.println("Available rooms:");
        for (IRoom room : availableRooms) {
            System.out.println(room);
        }

        // Get reservations for a customer
        Collection<Reservation> johnReservations = reservationService.getCustomersReservation(customer1);
        System.out.println("John Doe's reservations:");
        for (Reservation reservation : johnReservations) {
            System.out.println(reservation);
        }

        // Print all reservations
        System.out.println("All reservations:");
        reservationService.printAllReservations();
    }
}

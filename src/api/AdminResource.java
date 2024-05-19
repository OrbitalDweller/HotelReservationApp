package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    private static AdminResource reference = new AdminResource();
    private CustomerService customerService = CustomerService.getReference();
    private ReservationService reservationService = ReservationService.getReference();

    public static AdminResource getReference() {
        return reference;
    }

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public void addRoom(List<IRoom> rooms) {
        for (IRoom room : rooms) {
            reservationService.addRoom(room);
        }
    }

    public Collection<IRoom> getAllRooms() {
        return reservationService.getAllRooms();
    }

    public Collection<Customer> getAllCustomer() {
        return customerService.getAllCustomers();
    }

    public void displayAllReservations() {
        reservationService.getAllReservations();
    }
}

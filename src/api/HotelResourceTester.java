package api;

import model.*;

import java.util.*;

public class HotelResourceTester {
    public static void main(String[] args) {
        // Initialize HotelResource
        HotelResource hotelResource = HotelResource.getReference();

        // Create a customer
        hotelResource.createACustomer("john.doe@example.com", "John", "Doe");
        Customer customer = hotelResource.getCustomer("john.doe@example.com");
        System.out.println("Customer created: " + customer);
        System.out.println();

        // Create multiple rooms
        hotelResource.addRoom(new Room("101", 150.0, RoomType.SINGLE));
        hotelResource.addRoom(new Room("102", 200.0, RoomType.DOUBLE));
        hotelResource.addRoom(new Room("103", 250.0, RoomType.SINGLE));
        hotelResource.addRoom(new Room("104", 300.0, RoomType.DOUBLE));

        // Book rooms for the customer
        Calendar calendar = Calendar.getInstance();
        Date checkInDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 3);
        Date checkOutDate = calendar.getTime();

        IRoom firstRoom = hotelResource.findARoom(checkInDate, checkOutDate).iterator().next();
        Reservation reservation = hotelResource.bookARoom("john.doe@example.com", firstRoom, checkInDate, checkOutDate);
        System.out.println("Reservation: " + reservation);
        System.out.println();


        // Find available rooms (after booking one)
        Collection<IRoom> availableRooms = hotelResource.findARoom(checkInDate, checkOutDate);
        System.out.println("Available rooms: " + availableRooms);
        System.out.println();

        // Get customer's reservations
        Collection<Reservation> reservations = hotelResource.getCustomersReservations("john.doe@example.com");
        System.out.println("Customer's reservations: " + reservations);
        System.out.println();
    }
}

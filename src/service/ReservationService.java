package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;


public class ReservationService {
    private static ReservationService reference = new ReservationService();
    private Collection<Reservation> reservations;
    private Set<IRoom> rooms;

    private ReservationService() {
        reservations = new ArrayList<Reservation>();
        rooms = new HashSet<IRoom>();
    }

    public static ReservationService getReference() {
        return reference;
    }

    public void addRoom(IRoom room) {
        rooms.add(room);
    }

    public IRoom getARoom(String roomId) {
        for (IRoom room : rooms) {
            if (roomId.equals(room.getRoomNumber())) {
                return room;
            }
        }
        return null;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate,
                                    Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservation);
        return reservation;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        Collection<IRoom> availableRooms = new HashSet<IRoom>();
        Collection<IRoom> occupiedRooms = new HashSet<IRoom>();

        for (Reservation reservation : reservations) {
            if (checkOutDate.before(reservation.getCheckInDate()) || checkInDate.after(reservation.getCheckOutDate())) {
                // The room is available if the check-out date is before the reservation's check-in date
                // or the check-in date is after the reservation's check-out date.
                availableRooms.add(reservation.getRoom());
            }
            else {
                // Otherwise, the room is occupied.
                occupiedRooms.add(reservation.getRoom());
            }
        }

        for (IRoom room : rooms) {
            if (!occupiedRooms.contains(room)) {
                availableRooms.add(room);
            }
        }

        return availableRooms;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        Collection<Reservation> customerReservations = new HashSet<Reservation>();

        for (Reservation reservation : reservations) {
            if (reservation.getCustomer().equals(customer)) {
                customerReservations.add(reservation);
            }
        }

        return customerReservations;
    }

    public void printAllReservations() {
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }

    public Collection<IRoom> getAllRooms() {
        return rooms;
    }

    public Collection<Reservation> getAllReservations() {
        return reservations;
    }
}

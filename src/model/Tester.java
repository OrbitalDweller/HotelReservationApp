package model;

import java.util.Calendar;
import java.util.Date;

public class Tester {
    public static void main(String[] args) {
        Room room = new Room("123", 100.0, RoomType.DOUBLE);
        System.out.println(room);

        FreeRoom freeRoom = new FreeRoom("222", RoomType.SINGLE);
        System.out.println(freeRoom);

        Customer customer1 = new Customer("Bob", "Fisher", "bobfisher@gmail.com");
        System.out.println(customer1);

        try {
            Customer customer2 = new Customer("Isaac", "Newton", "isaac.com");
            System.out.println(customer2);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        Calendar calendar = Calendar.getInstance();
        Date date = new java.util.Date();
        calendar.setTime(date);
        Date checkIn = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        Date checkOut = calendar.getTime();
        Reservation reservation = new Reservation(customer1, room, checkIn, checkOut);
        System.out.println(reservation);
    }
}

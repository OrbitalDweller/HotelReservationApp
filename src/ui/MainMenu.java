package ui;

import api.HotelResource;
import model.IRoom;
import model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainMenu {
    private String customerEmail;
    private HotelResource hotelResource;
    private Scanner scanner;
    private SimpleDateFormat dateFormat;

    public MainMenu() {
        customerEmail = null;
        hotelResource = HotelResource.getReference();
        scanner = new Scanner(System.in);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    public void displayMenu() {
        System.out.println("Welcome to the Main Menu!");
        System.out.println("Please select an option:");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");

        int choice = getUserChoice();

        switch (choice) {
            case 1:
                reserveARoom();
                break;
            case 2:
                seeReservations();
                break;
            case 3:
                createAccount();
                break;
            case 4:
                AdminMenu adminMenu = new AdminMenu();
                System.out.println();
                adminMenu.displayMenu();
                break;
            case 5:
                System.out.println("Exiting...");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
        System.out.println();

        displayMenu();
    }

    private int getUserChoice() {
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    private Date getValidDate(String prompt) {
        Date date = null;
        while (date == null) {
            System.out.print(prompt);
            String input = scanner.next();
            try {
                date = dateFormat.parse(input);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please try again.");
            }
        }
        return date;
    }

    private Date getCheckInDate() {
        return getValidDate("Enter the check-in date (YYYY-MM-DD): ");
    }

    private Date getCheckOutDate() {
        return getValidDate("Enter the check-out date (YYYY-MM-DD): ");
    }

    private IRoom findARoom(Date checkInDate, Date checkOutDate) {
        IRoom selectedRoom = null;

        Collection<IRoom> availableRooms = hotelResource.findARoom(checkInDate, checkOutDate);

        if (availableRooms == null || availableRooms.isEmpty()) {
            System.out.println("No available rooms for the selected dates.");
            return selectedRoom;
        }

        System.out.println("Available rooms:");
        int index = 1;
        for (IRoom room : availableRooms) {
            System.out.println(index + ". Room Number: " + room.getRoomNumber() + ", Price: " + room.getRoomPrice() + ", Type: " + room.getRoomType());
            index++;
        }

        System.out.print("Enter the number of the room you want to select: ");
        int roomChoice = getUserChoice();

        if (roomChoice > 0 && roomChoice <= availableRooms.size()) {
            Iterator<IRoom> iterator = availableRooms.iterator();
            for (int i = 1; i < roomChoice; i++) {
                iterator.next();
            }
            selectedRoom = iterator.next();
            System.out.println("You have selected room number: " + selectedRoom.getRoomNumber());
        } else {
            System.out.println("Invalid choice. Please try again.");
        }

        return selectedRoom;
    }

    private void reserveARoom() {
        if (customerEmail == null) {
            System.out.println("Please create an account.");
            return;
        }
        Date checkInDate = getCheckInDate();
        Date checkOutDate = getCheckOutDate();

        IRoom room = findARoom(checkInDate, checkOutDate);
        if (room == null) {
            return;
        }
        hotelResource.bookARoom(customerEmail, room, checkInDate, checkOutDate);
    }

    private void createAccount() {

        if (customerEmail != null) {
            System.out.println("Account already created.");
            return;
        }

        System.out.print("Enter your email: ");
        customerEmail = scanner.next();
        System.out.print("Enter your first name: ");
        String firstName = scanner.next();
        System.out.print("Enter your last name: ");
        String lastName = scanner.next();

        try {
            hotelResource.createACustomer(customerEmail, firstName, lastName);
            System.out.println("Account successfully created.");
        } catch (Exception ex) {
            customerEmail = null;
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void seeReservations() {
        if (customerEmail == null) {
            System.out.println("Please create an account.");
            return;
        }
        Collection<Reservation> reservations = hotelResource.getCustomersReservations(customerEmail);
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }

    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        mainMenu.displayMenu();
    }
}

package ui;

import api.AdminResource;
import model.*;

import java.util.*;

public class AdminMenu {
    private AdminResource adminResource;
    private Scanner scanner;

    public AdminMenu() {
        adminResource = AdminResource.getReference();
        scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("Welcome to the Admin Menu!");
        System.out.println("Please select an option:");
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Back to Main Menu");

        int choice = getUserChoice();

        switch (choice) {
            case 1:
                seeAllCustomers();
                break;
            case 2:
                seeAllRooms();
                break;
            case 3:
                seeAllReservations();
                break;
            case 4:
                addARoom();
                break;
            case 5:
                System.out.println("Returning to Main Menu...");
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

    private void seeAllCustomers() {
        Collection<Customer> customers = adminResource.getAllCustomer();
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    private void seeAllRooms() {
        Collection<IRoom> rooms = adminResource.getAllRooms();
        for (IRoom room : rooms) {
            System.out.println(room);
        }
    }

    private void seeAllReservations() {
        adminResource.displayAllReservations();
    }

    private void addARoom() {
        try {
            System.out.print("Enter the room number: ");
            String roomNumber = scanner.next();

            System.out.print("Enter the price: ");
            double price = scanner.nextDouble();

            System.out.print("Enter the room type (SINGLE, DOUBLE): ");
            String roomTypeInput = scanner.next();
            RoomType roomType = RoomType.valueOf(roomTypeInput.toUpperCase());

            IRoom room = new Room(roomNumber, price, roomType);
            List<IRoom> rooms = new ArrayList<IRoom>();
            rooms.add(room);
            adminResource.addRoom(rooms);
            System.out.println("Room added successfully!");

        }
        catch (IllegalArgumentException e) {
            System.out.println("Invalid room type. Please enter either SINGLE or DOUBLE.");
        }
        catch (InputMismatchException e) {
            System.out.println("Invalid input for price. Please enter a numeric value.");
        }
        catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

}

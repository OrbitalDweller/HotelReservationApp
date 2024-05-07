package model;

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
    }
}

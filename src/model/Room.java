package model;

public class Room implements IRoom {
    private String roomNumber;
    private Double price;
    private RoomType roomType;

    public Room(String roomNumber, Double price, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
    }

    public final String getRoomNumber() {
        return roomNumber;
    }

    public final Double getRoomPrice() {
        return price;
    }

    public final RoomType getRoomType() {
        return roomType;
    }

    public final boolean isFree() {
        return false;
    }

    @Override
    public String toString() {
        return "Room number " + this.getRoomNumber() + " is $" + this.getRoomPrice() + " per night and is " + this.getRoomType() + " type";
    }
}

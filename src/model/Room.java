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

    public String getRoomNumber() {
        return roomNumber;
    }

    public Double getRoomPrice() {
        return price;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public boolean isFree() {
        return false;
    }

    @Override
    public String toString() {
        return "Room number " + this.getRoomNumber() + " is $" + this.getRoomPrice() + " per night and is " + this.getRoomType() + " type";
    }
}

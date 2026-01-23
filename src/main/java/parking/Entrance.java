package main.java.parking;

public class Entrance {
    private final int id;

    public Entrance(int id) {
        this.id = id;
    }

    public ParkingTicket getTicket(Vehicle v) {
        return ParkingLot.getInstance().parkVehicle(v);
    }
}

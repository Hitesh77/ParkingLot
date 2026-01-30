package main.java.parking2;

public class Entry {

    private int id;

    public Entry(int id) {
        this.id = id;
    }

    public ParkingTicket getTicket(Vehicle vehicle) {
        return ParkingLot.getInstance().getTicket(vehicle);
    }

}

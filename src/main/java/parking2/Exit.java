package main.java.parking2;

public class Exit {
    private int id;

    public Exit(int id) {
        this.id = id;
    }

    public boolean validate(ParkingTicket parkingTicket) {
        return ParkingLot.getInstance().validateTicket(parkingTicket);
    }
}

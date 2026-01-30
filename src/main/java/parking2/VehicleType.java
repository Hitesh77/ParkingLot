package main.java.parking2;

import java.util.List;

import static main.java.parking2.ParkingSpotType.*;

public enum VehicleType {
    CAR(COMPACT, ACCESSIBLE),
    TRUCK(LARGE),
    LARGE_VEHICLE(LARGE),
    MOTERCYCLE(MOTORCYCLE);

    private final List<ParkingSpotType> parkingSpot;

    VehicleType(ParkingSpotType... parkingSpot) {
        this.parkingSpot = List.of(parkingSpot);
    }

    public List<ParkingSpotType> getParkingSpot() {
        return parkingSpot;
    }


}

package main.java.parking2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Floor {
    public Floor(int id) {
        this.id = id;
    }

    int id;
    Map<ParkingSpotType, List<ParkingSpot>> vehicleTypeToParkingSpot = new HashMap<>();

    public void addSpot(ParkingSpot parkingSpot) {
        vehicleTypeToParkingSpot.putIfAbsent(parkingSpot.getParkingSpotType(), new ArrayList<>());
        vehicleTypeToParkingSpot.get(parkingSpot.getParkingSpotType()).add(parkingSpot);
    }

}

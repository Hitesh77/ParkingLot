package main.java.parking2;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisplayBoard {

    private final int id;
    private final Map<String, Integer> freeCount = new HashMap<>();

    public DisplayBoard(int id) {
        this.id = id;
    }

    public void showFreeSlot() {
        System.out.println("\nFree slots by type:");
        System.out.printf("%-15s %s%n", "Type", "Count");
        Map<ParkingSpotType, List<ParkingSpot>> vehicleTypeToParkingSpot = ParkingLot.getInstance().vehicleTypeToParkingSpot;
        for (ParkingSpotType parkingSpotType : vehicleTypeToParkingSpot.keySet())
            System.out.printf("%-15s %d%n", parkingSpotType, vehicleTypeToParkingSpot.get(parkingSpotType).size());
    }
}

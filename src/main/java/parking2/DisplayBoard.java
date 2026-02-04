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

    public void showFloorFreeSlot() {
        System.out.println("\nFloorNo Free slots by type:");
        System.out.printf("%-10s %-15s %-10s%n ", "Floor No","Type", "Count");
        List<Floor> floors = FloorParkingLot.getInstance().floors;
        for(Floor floor: floors) {
            for (ParkingSpotType parkingSpotType : floor.vehicleTypeToParkingSpot.keySet())
                System.out.printf("%-10s %-15s %-10s%n ", floor.id, parkingSpotType, floor.vehicleTypeToParkingSpot.get(parkingSpotType).size());
        }
    }
}

package main.java.parking;

import java.util.*;

public class ParkingLot {
    private static ParkingLot instance;

    ParkingRate rate = new ParkingRate();
    Map<Integer, ParkingSpot> spots = new LinkedHashMap<>();
    Map<Integer, ParkingTicket> tickets = new HashMap<>();
    List<DisplayBoard> boards = new ArrayList<>();

    public static ParkingLot getInstance() {
        if (instance == null) instance = new ParkingLot();
        return instance;
    }

    public void addSpot(ParkingSpot s) {
        spots.put(s.getId(), s);
    }

    public void addDisplayBoard(DisplayBoard b) {
        boards.add(b);
    }

    public ParkingSpot getSpot(int id) {
        return spots.get(id);
    }

    public void freeSlot(int id) {
        ParkingSpot s = spots.get(id);
        if (s != null) s.removeVehicle();
    }

    public Collection<ParkingSpot> getAllSpots() {
        return spots.values();
    }

    public ParkingTicket parkVehicle(Vehicle v) {
        for (ParkingSpot s : spots.values()) {
            if (s.isFree() && canFit(v, s)) {
                s.assignVehicle(v);
                ParkingTicket t = new ParkingTicket(s.getId(), v);
                tickets.put(t.getTicketNo(), t);
                return t;
            }
        }
        System.out.println("Sorry, parking lot is full. New vehicle cannot be parked.");
        return null;
    }

    private boolean canFit(Vehicle v, ParkingSpot s) {
        if (v instanceof Motorcycle && s instanceof MotorcycleSpot) return true;
        if ((v instanceof Truck || v instanceof Van) && s instanceof Large) return true;
        return v instanceof Car && (s instanceof Compact || s instanceof Accessible);
    }
}

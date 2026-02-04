package main.java.parking2;

import main.java.parking.Cash;
import main.java.parking.CreditCard;

import java.util.*;

public class FloorParkingLot {
    private static FloorParkingLot parkingLot;

    public static FloorParkingLot getInstance() {
        if (parkingLot == null) {
            synchronized (FloorParkingLot.class) {
                if(parkingLot == null) {
                    parkingLot = new FloorParkingLot();
                }
            }
        }
        return parkingLot;
    }

    List<Floor> floors= new ArrayList<>();
    
    Payment payment;

    ParkingRate parkingRate = new ParkingRate();

    DisplayBoard displayBoard;


    public ParkingTicket getTicket(Vehicle vehicle) {
        List<ParkingSpotType> parkingSpots = vehicle.getVehicleType().getParkingSpot();
        ParkingSpot assignedParkingSpot = null;
        ParkingSpotType assignParkingSpotType = null;
        List<ParkingSpot> availableParkingSpots;
        int floorId = -1;
        for(Floor floor: floors) {
            for (ParkingSpotType parkingSpotType : parkingSpots) {
                availableParkingSpots = floor.vehicleTypeToParkingSpot.get(parkingSpotType);
                if (availableParkingSpots != null && !availableParkingSpots.isEmpty()) {
                    assignedParkingSpot = availableParkingSpots.get(0);
                    availableParkingSpots.remove(0);
                    assignParkingSpotType = parkingSpotType;
                    floorId = floor.id;
                    break;
                }
            }
            if(floorId > 0)
                break;
        }

        if (assignedParkingSpot == null) {
            System.out.printf("Parking are full for {%s} type.", vehicle.getVehicleType());
            System.out.println("");
            return null;
        }
        System.out.printf("Assigned Parking at {%d} floor, {%s} type.", floorId, vehicle.getVehicleType());
        return new ParkingTicket(vehicle, assignedParkingSpot, assignParkingSpotType, floorId);
    }

    public boolean validateTicket(ParkingTicket parkingTicket) {
        parkingTicket.setExit(new Date());
        double hrs = (parkingTicket.getExit().getTime() - parkingTicket.getEntry().getTime()) / 3600000.0;
        hrs = (int) Math.ceil(hrs);
        double fee = parkingRate.calculate(hrs, parkingTicket.getVehicle());
        parkingTicket.setFees(fee);
        System.out.printf("Ticket %d | Parked: %.2f hrs | Fee: $%.2f\n", parkingTicket.getId(), hrs, fee);
        main.java.parking.Payment p = (fee > 10) ? new CreditCard(fee) : new Cash(fee);
        p.initiateTransaction();
        parkingTicket.setStatus(TicketStatus.PAID);
        Optional<Floor> first = floors.stream().filter(f -> f.id == parkingTicket.floorId).findFirst();
        first.get().vehicleTypeToParkingSpot.get(parkingTicket.getParkingSpotType()).add(parkingTicket.getParkingSpot());
        return true;
    }





    public void addDisplayBoard(DisplayBoard board) {
        this.displayBoard = board;
    }

    public void addFloor(Floor floor) {
        floors.add(floor);
    }

//    public List<ParkingSpot> getAllSpots() {
//    }
}

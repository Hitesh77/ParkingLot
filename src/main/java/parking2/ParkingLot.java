package main.java.parking2;

import main.java.parking.Cash;
import main.java.parking.CreditCard;

import java.util.*;

public class ParkingLot {
    private static ParkingLot parkingLot;

    public static ParkingLot getInstance() {
        if (parkingLot == null) {
            synchronized (ParkingLot.class) {
                if(parkingLot == null) {
                    parkingLot = new ParkingLot();
                }
            }
        }
        return parkingLot;
    }

    Map<ParkingSpotType, List<ParkingSpot>> vehicleTypeToParkingSpot = new HashMap<>();

    Payment payment;

    ParkingRate parkingRate = new ParkingRate();

    DisplayBoard displayBoard;


    public ParkingTicket getTicket(Vehicle vehicle) {
        List<ParkingSpotType> parkingSpots = vehicle.getVehicleType().getParkingSpot();
        ParkingSpot assignedParkingSpot = null;
        ParkingSpotType assignParkingSpotType = null;
        List<ParkingSpot> availableParkingSpots;
        for (ParkingSpotType parkingSpotType : parkingSpots) {
            availableParkingSpots = vehicleTypeToParkingSpot.get(parkingSpotType);
            if (availableParkingSpots != null && !availableParkingSpots.isEmpty()) {
                assignedParkingSpot = availableParkingSpots.get(0);
                availableParkingSpots.remove(0);
                assignParkingSpotType = parkingSpotType;
                break;
            }
        }

        if (assignedParkingSpot == null) {
            System.out.printf("Parking are full for {%s} type.", vehicle.getVehicleType());
            System.out.println("");
        }

        return new ParkingTicket(vehicle, assignedParkingSpot, assignParkingSpotType);
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
        vehicleTypeToParkingSpot.get(parkingTicket.getParkingSpotType()).add(parkingTicket.getParkingSpot());
        return true;
    }


    public void addSpot(ParkingSpot parkingSpot) {
        vehicleTypeToParkingSpot.putIfAbsent(parkingSpot.getParkingSpotType(), new ArrayList<>());
        vehicleTypeToParkingSpot.get(parkingSpot.getParkingSpotType()).add(parkingSpot);
    }


    public void addDisplayBoard(DisplayBoard board) {
        this.displayBoard = board;
    }

//    public List<ParkingSpot> getAllSpots() {
//    }
}

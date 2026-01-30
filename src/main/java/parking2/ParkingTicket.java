package main.java.parking2;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class ParkingTicket {
    private static final AtomicInteger ticketNo = new AtomicInteger(1);
    private final int id;
    private final Vehicle vehicle;
    private final Date entry;
    private TicketStatus status;
    private Date exit;
    private double fees;
    private PaymentType paymentType;

    private ParkingSpot parkingSpot;
    private ParkingSpotType parkingSpotType;

    public ParkingTicket(Vehicle vehicle, ParkingSpot parkingSpot, ParkingSpotType parkingSpotType) {
        this.vehicle = vehicle;
        this.entry = new Date();
        this.status = TicketStatus.ISSUED;
        this.id = ticketNo.getAndIncrement();
        this.parkingSpot = parkingSpot;
        this.parkingSpotType = parkingSpotType;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Date getEntry() {
        return entry;
    }

    public Date getExit() {
        return exit;
    }

    public void setExit(Date exit) {
        this.exit = exit;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public int getId() {
        return id;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public ParkingSpotType getParkingSpotType() {
        return parkingSpotType;
    }

    public void setParkingSpotType(ParkingSpotType parkingSpotType) {
        this.parkingSpotType = parkingSpotType;
    }
}

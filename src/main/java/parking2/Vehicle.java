package main.java.parking2;

public class Vehicle {
    private VehicleType vehicleType;
    private String no;

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getNo() {
        return no;
    }

    public Vehicle(VehicleType vehicleType, String no) {
        this.vehicleType = vehicleType;
        this.no = no;
    }
}

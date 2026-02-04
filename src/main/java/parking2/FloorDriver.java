package main.java.parking2;


public class FloorDriver {

    public static void main(String[] args) throws InterruptedException {
        // -------------- SYSTEM INITIALIZATION --------------
        System.out.println("\n====================== PARKING LOT SYSTEM DEMO ======================\n");

        FloorParkingLot lot = FloorParkingLot.getInstance();
        Floor floor1 = new Floor(1);
        lot.addFloor(floor1);

        Floor floor2 = new Floor(2);
        lot.addFloor(floor2);

        floor1.addSpot(new ParkingSpot(1, ParkingSpotType.ACCESSIBLE));
        floor2.addSpot(new ParkingSpot(2, ParkingSpotType.COMPACT));
        floor1.addSpot(new ParkingSpot(3, ParkingSpotType.LARGE));
        floor2.addSpot(new ParkingSpot(5, ParkingSpotType.LARGE));
        floor1.addSpot(new ParkingSpot(4, ParkingSpotType.MOTORCYCLE));

        DisplayBoard board = new DisplayBoard(1);
        lot.addDisplayBoard(board);

        Entry entrance = new Entry(1);
        Exit exit = new Exit(1);

        // ----------------- SCENARIO 1: CUSTOMER ENTERS, PARKS -----------------
        System.out.println("\n→→→ SCENARIO 1: Customer enters and parks a car\n");

        Vehicle car = new Vehicle(VehicleType.CAR, "KA-01-HH-1234");
        System.out.println("-> Car " + car.getNo() + " arrives at entrance");
        ParkingTicket ticket1 = entrance.getTicket(car);

        System.out.println("-> Updating display board after parking:");
//        board.update(lot.getAllSpots());
        board.showFloorFreeSlot();

        // ----------------- SCENARIO 2: CUSTOMER EXITS AND PAYS -----------------
        System.out.println("\n→→→ SCENARIO 2: Customer exits and pays\n");

        System.out.println("-> Car " + car.getNo() + " proceeds to exit panel");
        Thread.sleep(1500); // Simulate parking duration (1.5 sec)
        exit.validate(ticket1);

        System.out.println("-> Updating display board after exit:");
//        board.update(lot.getAllSpots());
        board.showFloorFreeSlot();

        // --------- SCENARIO 3: FILLING LOT AND REJECTING ENTRY IF FULL ---------
        System.out.println("\n→→→ SCENARIO 3: Multiple customers attempt to enter; lot may become full\n");

        // Vehicles arriving
        Vehicle van = new Vehicle(VehicleType.TRUCK, "KA-01-HH-9999");
        Vehicle motorcycle = new Vehicle(VehicleType.MOTERCYCLE, "KA-02-XX-3333");
        Vehicle truck = new Vehicle(VehicleType.LARGE_VEHICLE, "KA-04-AA-9998");
        Vehicle car2 = new Vehicle(VehicleType.CAR, "DL-09-YY-1234");

        System.out.println("-> Van " + van.getNo() + " arrives at entrance");
        ParkingTicket ticket2 = entrance.getTicket(van);

        System.out.println("-> Motorcycle " + motorcycle.getNo() + " arrives at entrance");
        ParkingTicket ticket3 = entrance.getTicket(motorcycle);

        System.out.println("-> Truck " + truck.getNo() + " arrives at entrance");
        ParkingTicket ticket4 = entrance.getTicket(truck);

        System.out.println("-> Car " + car2.getNo() + " arrives at entrance");
        ParkingTicket ticket5 = entrance.getTicket(car2);

        System.out.println("-> Updating display board after several parkings:");
//        board.update(lot.getAllSpots());
        board.showFloorFreeSlot();

        // Try to park another car (lot may now be full)
        Vehicle car3 = new Vehicle(VehicleType.CAR, "UP-01-CC-1001");
        System.out.println("-> Car " + car3.getNo() + " attempts to park (should be denied if lot is full):");
        ParkingTicket ticket6 = entrance.getTicket(car3);

//        board.update(lot.getAllSpots());
        board.showFloorFreeSlot();

        exit.validate(ticket2);
        exit.validate(ticket3);
        exit.validate(ticket4);
        exit.validate(ticket5);
        exit.validate(ticket6);

        board.showFloorFreeSlot();


        System.out.println("\n====================== END OF DEMONSTRATION ======================\n");
    }
}



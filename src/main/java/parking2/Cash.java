package main.java.parking2;

import main.java.parking.PaymentStatus;

public class Cash extends Payment {

    public Cash(double amt) {
        super(amt);
    }

    public boolean initiateTransaction() {
        status = PaymentStatus.COMPLETED;
        System.out.println("Cash payment of $" + amount + " completed.");
        return true;
    }
}

package Pricing;

import Parking_Spot.ParkingSpot;
import Ticket.Ticket;

public class Minutewise extends PricingStrategy {
    @Override

    public int GetPrice(ParkingSpot ps, Ticket tc, long exitTime) {
        long durationMillis = exitTime - tc.entryTime;
        long minutes = (durationMillis + 59999) / 60000; // round up to next minute
        return (int) (minutes * 2); // e.g., ₹2 per minute
    }
}

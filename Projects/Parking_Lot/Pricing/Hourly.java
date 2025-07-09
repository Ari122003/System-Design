package Pricing;

import Parking_Spot.ParkingSpot;
import Ticket.Ticket;

public class Hourly extends PricingStrategy {
    @Override
    public int GetPrice(ParkingSpot ps, Ticket tc, long exitTime) {
        long durationMillis = exitTime - tc.entryTime;
        long hours = (durationMillis + 3599999) / 3600000; // round up to next hour
        return (int) (hours * 50); // e.g., ₹50 per hour
    }
}

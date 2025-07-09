package Pricing;

import Parking_Spot.ParkingSpot;
import Ticket.Ticket;

public abstract class PricingStrategy {
    public abstract int GetPrice(ParkingSpot ps, Ticket tc, long exitTime);
}
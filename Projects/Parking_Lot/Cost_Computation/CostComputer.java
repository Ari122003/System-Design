package Cost_Computation;

import Parking_Spot.ParkingSpot;
import Pricing.PricingStrategy;
import Ticket.Ticket;

public interface CostComputer {
    int compute(PricingStrategy strategy, ParkingSpot spot, Ticket ticket, long exitTime);
}

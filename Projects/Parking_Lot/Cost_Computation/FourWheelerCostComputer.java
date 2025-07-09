package Cost_Computation;

import Parking_Spot.ParkingSpot;
import Pricing.PricingStrategy;
import Ticket.Ticket;

public class FourWheelerCostComputer implements CostComputer {
    @Override
    public int compute(PricingStrategy strategy, ParkingSpot spot, Ticket ticket, long exitTime) {
        return strategy.GetPrice(spot, ticket, exitTime);
    }
}

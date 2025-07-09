package Gates;

import Parking_Spot.Spot_Manager.SpotManager;
import Ticket.Ticket;
import Payment_System.Payment;
import Pricing.PricingStrategy;
import Cost_Computation.CostComputer;
import Cost_Computation.CostComputationFactory;

public class ExitGate {
    private final SpotManager spotManager;
    private final Payment paymentProcessor;

    public ExitGate(SpotManager spotManager, Payment paymentProcessor) {
        this.spotManager = spotManager;
        this.paymentProcessor = paymentProcessor;
    }

    public boolean processExit(Ticket ticket, PricingStrategy pricingStrategy, String vehicleType, long exitTime) {
        CostComputer costComputer = CostComputationFactory.getCostComputer(vehicleType);
        int amount = costComputer.compute(pricingStrategy, ticket.spot, ticket, exitTime);
        boolean paid = paymentProcessor.pay(amount);
        if (paid) {
            spotManager.removeVehicle(ticket.spot);
        }
        return paid;
    }
}

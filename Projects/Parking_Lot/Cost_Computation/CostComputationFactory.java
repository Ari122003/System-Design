package Cost_Computation;

public class CostComputationFactory {

    public static CostComputer getCostComputer(String vehicleType) {
        if (vehicleType.equals("TwoWheeler")) {
            return new TwoWheelerCostComputer();
        } else if (vehicleType.equals("FourWheeler")) {
            return new FourWheelerCostComputer();
        }

        throw new IllegalArgumentException("Unknown vehicle type: " + vehicleType);
    }
}

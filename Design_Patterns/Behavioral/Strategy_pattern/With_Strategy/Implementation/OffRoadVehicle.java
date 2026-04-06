package Design_Patterns.Behavioral.Strategy_pattern.With_Strategy.Implementation;

import Design_Patterns.Behavioral.Strategy_pattern.With_Strategy.Strategy.SportDriveStrategy;

public class OffRoadVehicle extends Vehicle {

    public OffRoadVehicle() {
        super(new SportDriveStrategy());
    }
}

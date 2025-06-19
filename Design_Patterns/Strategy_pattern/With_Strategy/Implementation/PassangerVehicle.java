package Design_Patterns.Strategy_pattern.With_Strategy.Implementation;

import Design_Patterns.Strategy_pattern.With_Strategy.Strategy.NormalDriveStrategy;

public class PassangerVehicle extends Vehicle {

    public PassangerVehicle() {
        super(new NormalDriveStrategy());
    }

}

package Design_Patterns.Behavioral.Strategy_pattern.With_Strategy.Implementation;

import Design_Patterns.Behavioral.Strategy_pattern.With_Strategy.Strategy.NormalDriveStrategy;

public class PassangerVehicle extends Vehicle {

    public PassangerVehicle() {
        super(new NormalDriveStrategy());
    }

}

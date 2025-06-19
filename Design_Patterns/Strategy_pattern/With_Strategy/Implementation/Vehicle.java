package Design_Patterns.Strategy_pattern.With_Strategy.Implementation;

import Design_Patterns.Strategy_pattern.With_Strategy.Strategy.DriveStrategy;

public class Vehicle {
    private DriveStrategy driveObj;

    public Vehicle(DriveStrategy driveObj) {
        this.driveObj = driveObj;
    }

    public void drive() {
        driveObj.drive();
    }
}

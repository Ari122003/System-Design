package Design_Patterns.Strategy_pattern.With_Strategy.Strategy;

public class NormalDriveStrategy implements DriveStrategy {

    public void drive() {
        System.out.println("Driving normally");
    }
}

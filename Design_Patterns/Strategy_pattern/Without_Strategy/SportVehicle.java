package Design_Patterns.Strategy_pattern.Without_Strategy;

public class SportVehicle extends Vehicle {

    // we have to copy same drive method code for sport , offRoad and Super which is
    // not effcient coding

    public void drive() {
        System.out.println("Special drive capability");
    }

}

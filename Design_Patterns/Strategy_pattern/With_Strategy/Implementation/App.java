package Design_Patterns.Strategy_pattern.With_Strategy.Implementation;

public class App {
    public static void main(String[] a) {
        SportVehicle f1 = new SportVehicle();
        PassangerVehicle p1 = new PassangerVehicle();
        p1.drive();
        f1.drive();
    }

}

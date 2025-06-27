package Design_Patterns.Abstract_Fcatory_Pattern.Factories;

import Design_Patterns.Abstract_Fcatory_Pattern.Vehicles.Bus1;
import Design_Patterns.Abstract_Fcatory_Pattern.Vehicles.Bus2;
import Design_Patterns.Abstract_Fcatory_Pattern.Vehicles.Vehicle;

public class BusFactory {

    public Vehicle getBus(String v) {
        switch (v) {
            case "Public":
                return new Bus1();
            case "Travel":

                return new Bus2();

            default:
                return null;
        }
    }
}

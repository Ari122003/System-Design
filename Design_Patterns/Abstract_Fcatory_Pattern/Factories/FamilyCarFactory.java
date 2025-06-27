package Design_Patterns.Abstract_Fcatory_Pattern.Factories;

import Design_Patterns.Abstract_Fcatory_Pattern.Vehicles.FamilyCar1;
import Design_Patterns.Abstract_Fcatory_Pattern.Vehicles.FamilyCar2;
import Design_Patterns.Abstract_Fcatory_Pattern.Vehicles.Vehicle;

public class FamilyCarFactory implements VehicleFactory {

    public Vehicle getFamilyCar(String v) {
        switch (v) {
            case "Swift":
                return new FamilyCar1();
            case "Vitara":

                return new FamilyCar2();

            default:
                return null;
        }

    }
}

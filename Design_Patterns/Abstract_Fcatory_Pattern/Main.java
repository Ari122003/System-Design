package Design_Patterns.Abstract_Fcatory_Pattern;

import Design_Patterns.Abstract_Fcatory_Pattern.Factories.BusFactory;
import Design_Patterns.Abstract_Fcatory_Pattern.Factories.FamilyCarFactory;
import Design_Patterns.Abstract_Fcatory_Pattern.Vehicles.Vehicle;

public class Main {
    public static void main(String[] args) {
        FamilyCarFactory fcf = new FamilyCarFactory();

        Vehicle familyCar = fcf.getFamilyCar("Swift");

        System.out.println(familyCar.average());

        BusFactory bf = new BusFactory();

        Vehicle bus = bf.getBus("Travel");

        System.out.println(bus.average());
    }

}

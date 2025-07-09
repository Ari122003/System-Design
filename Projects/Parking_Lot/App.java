import Parking_Spot.ParkingSpot;
import Parking_Spot.FourWheelerSpot;
import Parking_Spot.TwoWheelerSpot;
import Parking_Spot.Spot_Manager.TwoWheelerManager;
import Parking_Spot.Spot_Manager.FourWheelerSpotManager;
import Parking_Spot.Spot_Manager.ParkingSpotFactory;
import Gates.EntranceGate;
import Gates.ExitGate;
import Gates.ParkingLotManager;
import Payment_System.CashPayment;
import Ticket.Ticket;
import Vehicles.VT;
import Vehicles.Vehicle;
import java.util.*;
import Pricing.Minutewise;
import Pricing.Hourly;
import Parking_Spot.Parking_Strategies.ParkingStrategy;
import Parking_Spot.Parking_Strategies.NearEntrance;

public class App {
    public static void main(String[] args) {
        // 1. Create parking spots
        List<ParkingSpot> twoWheelerSpots = new ArrayList<>();
        List<ParkingSpot> fourWheelerSpots = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            twoWheelerSpots.add(new TwoWheelerSpot(i, 10 + i * 5));
            fourWheelerSpots.add(new FourWheelerSpot(i, 15 + i * 5));
        }

        // 2. Create parking strategies
        ParkingStrategy nearEntranceStrategy = new NearEntrance();

        // 3. Create managers with strategies
        TwoWheelerManager twoWheelerManager = new TwoWheelerManager(twoWheelerSpots, nearEntranceStrategy);
        FourWheelerSpotManager fourWheelerManager = new FourWheelerSpotManager(fourWheelerSpots, nearEntranceStrategy);

        // 4. Create gates and factory
        ParkingSpotFactory spotFactory = new ParkingSpotFactory();
        EntranceGate twoWheelerEntrance = new EntranceGate(twoWheelerManager, spotFactory);
        EntranceGate fourWheelerEntrance = new EntranceGate(fourWheelerManager, spotFactory);
        CashPayment paymentProcessor = new CashPayment();

        // Use Minutewise for two-wheelers, Hourly for four-wheelers
        Minutewise minutePricing = new Minutewise();
        Hourly hourlyPricing = new Hourly();

        ExitGate twoWheelerExit = new ExitGate(twoWheelerManager, paymentProcessor);
        ExitGate fourWheelerExit = new ExitGate(fourWheelerManager, paymentProcessor);

        // 5. Create lot manager
        ParkingLotManager lotManager = new ParkingLotManager(twoWheelerManager, fourWheelerManager);

        // 6. Simulate two-wheeler entry/exit
        Vehicle bike = new Vehicle(1234, VT.Two_Wheeler);
        ParkingSpot bikeSpot = twoWheelerEntrance.findSpot("TwoWheeler");
        if (bikeSpot != null) {
            twoWheelerEntrance.book(bikeSpot, "10:00");
            Ticket bikeTicket = new Ticket(System.currentTimeMillis(), bike, bikeSpot);
            System.out.println("Two-wheeler parked at spot: " + bikeSpot.id);
            // Simulate exit
            long bikeExitTime = System.currentTimeMillis();
            boolean bikePaid = twoWheelerExit.processExit(bikeTicket, minutePricing, "TwoWheeler", bikeExitTime);
            if (bikePaid) {
                System.out.println("Two-wheeler exited and spot freed!");
            }
        } else {
            System.out.println("No two-wheeler spot available!");
        }

        // 7. Simulate four-wheeler entry/exit
        Vehicle car = new Vehicle(5678, VT.Four_Wheeler);
        ParkingSpot carSpot = fourWheelerEntrance.findSpot("FourWheeler");
        if (carSpot != null) {
            fourWheelerEntrance.book(carSpot, "11:00");
            Ticket carTicket = new Ticket(System.currentTimeMillis(), car, carSpot);
            System.out.println("Four-wheeler parked at spot: " + carSpot.id);
            // Simulate exit
            long carExitTime = System.currentTimeMillis();
            boolean carPaid = fourWheelerExit.processExit(carTicket, hourlyPricing, "FourWheeler", carExitTime);
            if (carPaid) {
                System.out.println("Four-wheeler exited and spot freed!");
            }
        } else {
            System.out.println("No four-wheeler spot available!");
        }

        // 8. Print lot status
        System.out.println("Available two-wheeler spots: " + lotManager.getAvailableTwoWheelerSpots());
        System.out.println("Available four-wheeler spots: " + lotManager.getAvailableFourWheelerSpots());
    }
}
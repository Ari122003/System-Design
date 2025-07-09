package Gates;

import Parking_Spot.ParkingSpot;
import Parking_Spot.Spot_Manager.ParkingSpotFactory;
import Parking_Spot.Spot_Manager.SpotManager;
import Ticket.Ticket;
import Vehicles.Vehicle;

public class EntranceGate {

    private final SpotManager spotManager;
    private final ParkingSpotFactory spotFactory;

    public EntranceGate(SpotManager spotManager, ParkingSpotFactory spotFactory) {
        this.spotManager = spotManager;
        this.spotFactory = spotFactory;
    }

    public ParkingSpot findSpot(String vehicleType) {
        return spotManager.findParkingSpace(vehicleType);
    }

    public boolean book(ParkingSpot spot, String entryTime) {
        spotManager.parkVehicle(spot, entryTime);
        return true;
    }

    public Ticket generateTicket(long entryTime, ParkingSpot spot, Vehicle vehicle) {
        // Example ticket generation logic
        return new Ticket(entryTime, vehicle, spot);
    }
}
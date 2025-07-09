package Ticket;

import Parking_Spot.ParkingSpot;
import Vehicles.Vehicle;

public class Ticket {

    public long entryTime;
    protected Vehicle vehicle;
    public ParkingSpot spot;
    protected long exitTime;

    public Ticket(long entryTime, Vehicle vehicle, ParkingSpot spot) {
        this.entryTime = entryTime;
        this.vehicle = vehicle;
        this.spot = spot;
    }
}

package Parking_Spot.Spot_Manager;

import java.util.List;
import java.util.ArrayList;
import Parking_Spot.ParkingSpot;
import Parking_Spot.Parking_Strategies.ParkingStrategy;

public class SpotManager {

    protected List<ParkingSpot> availableSpots;
    protected ParkingStrategy parkingStrategy;

    public SpotManager(List<ParkingSpot> list, ParkingStrategy strategy) {
        this.availableSpots = new ArrayList<>(list);
        this.parkingStrategy = strategy;
    }

    public ParkingSpot findParkingSpace(String type) {
        // Use the parking strategy to find a spot
        ParkingSpot spot = parkingStrategy.findSpot(availableSpots);
        if (spot != null) {
            availableSpots.remove(spot);
        }
        return spot;
    }

    public void addParkingSpace(ParkingSpot spot) {
        availableSpots.add(spot);
    }

    public void removeParkingspace(ParkingSpot spot) {
        availableSpots.remove(spot);
    }

    public void parkVehicle(ParkingSpot spot, String time) {
        spot.isEmpty = false;
    }

    public void removeVehicle(ParkingSpot spot) {
        spot.isEmpty = true;
        availableSpots.add(spot);
    }

    public int getAvailableSpots() {
        return availableSpots.size();
    }
}

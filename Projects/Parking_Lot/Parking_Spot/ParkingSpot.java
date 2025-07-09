package Parking_Spot;

import Vehicles.Vehicle;

public abstract class ParkingSpot implements Comparable<ParkingSpot> {

    public int id;
    public boolean isEmpty;
    protected Vehicle vehicle;
    public int distanceFromEntrance; // Add this field

    public abstract void park(Vehicle vehicle);

    public abstract void remove();

    // Implement Comparable to prioritize by distance
    @Override
    public int compareTo(ParkingSpot other) {
        return Integer.compare(this.distanceFromEntrance, other.distanceFromEntrance);
    }
}
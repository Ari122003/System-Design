package Parking_Spot;

import Vehicles.Vehicle;

public class FourWheelerSpot extends ParkingSpot {

    public FourWheelerSpot(int id, int distanceFromEntrance) {
        this.id = id;
        this.distanceFromEntrance = distanceFromEntrance;
        this.isEmpty = true;
    }

    @Override
    public void park(Vehicle vehicle) {

        this.vehicle = vehicle;
        this.isEmpty = false;
    }

    @Override
    public void remove() {
        this.vehicle = null;
        this.isEmpty = true;
    }
}

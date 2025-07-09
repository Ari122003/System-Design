package Parking_Spot.Spot_Manager;

import Parking_Spot.FourWheelerSpot;
import Parking_Spot.ParkingSpot;
import Parking_Spot.TwoWheelerSpot;

public class ParkingSpotFactory {

    public ParkingSpot getParkingSpot(String vehicleType, int id, int distanceFromEntrance) {
        if ("TwoWheeler".equalsIgnoreCase(vehicleType)) {
            return new TwoWheelerSpot(id, distanceFromEntrance);
        } else if ("FourWheeler".equalsIgnoreCase(vehicleType)) {
            return new FourWheelerSpot(id, distanceFromEntrance);
        }
        return null;
    }
}

package Parking_Spot.Spot_Manager;

import java.util.List;

import Parking_Spot.ParkingSpot;
import Parking_Spot.Parking_Strategies.ParkingStrategy;

public class TwoWheelerManager extends SpotManager {

    public TwoWheelerManager(List<ParkingSpot> twoWheelerSpot, ParkingStrategy strategy) {
        super(twoWheelerSpot, strategy);
    }
}

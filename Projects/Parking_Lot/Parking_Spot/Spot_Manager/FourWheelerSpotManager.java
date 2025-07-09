package Parking_Spot.Spot_Manager;

import java.util.List;
import Parking_Spot.ParkingSpot;
import Parking_Spot.Parking_Strategies.ParkingStrategy;

public class FourWheelerSpotManager extends SpotManager {

    public FourWheelerSpotManager(List<ParkingSpot> fourWheelerSpots, ParkingStrategy strategy) {
        super(fourWheelerSpots, strategy);
    }
}
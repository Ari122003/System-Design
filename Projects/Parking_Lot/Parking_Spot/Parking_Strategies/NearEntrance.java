package Parking_Spot.Parking_Strategies;

import java.util.Comparator;
import java.util.List;

import Parking_Spot.ParkingSpot;

public class NearEntrance implements ParkingStrategy {

    @Override

    public ParkingSpot findSpot(List<ParkingSpot> avaialableSpots) {
        return avaialableSpots.stream().filter(spot -> spot.isEmpty)
                .min(Comparator.comparingInt(s -> s.distanceFromEntrance)).orElse(null);
    }

}

package Parking_Spot.Parking_Strategies;

import java.util.List;

import Parking_Spot.ParkingSpot;

public interface ParkingStrategy {

    ParkingSpot findSpot(List<ParkingSpot> avaialableSpots);
}

package Gates;

import Parking_Spot.Spot_Manager.SpotManager;

public class ParkingLotManager {
    private final SpotManager twoWheelerManager;
    private final SpotManager fourWheelerManager;

    public ParkingLotManager(SpotManager twoWheelerManager, SpotManager fourWheelerManager) {
        this.twoWheelerManager = twoWheelerManager;
        this.fourWheelerManager = fourWheelerManager;
    }

    public int getAvailableTwoWheelerSpots() {
        return twoWheelerManager.getAvailableSpots();
    }

    public int getAvailableFourWheelerSpots() {
        return fourWheelerManager.getAvailableSpots();
    }
}

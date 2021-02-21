package com.cogent.example;


public class Level {
	private int floor;
	private ParkingSpot[] spots;
	private int availableSpots = 0; // number of free spots
	private static final int SPOTS_PER_ROW = 10;
	
	public Level(int floor, int numberSpots) {
		this.floor = floor;
		spots = new ParkingSpot[numberSpots];
		int largeSpots = numberSpots / 4;
		int bikeSpots = numberSpots / 4;
		int compactSpots = numberSpots - largeSpots - bikeSpots;
		for (int i = 0; i < numberSpots; i++) {
			VehicleSize size = VehicleSize.Motorcycle;
			if (i < largeSpots) {
				size = VehicleSize.Large;
			} else if (i < largeSpots + compactSpots) {
				size = VehicleSize.Compact;
			}
			int row = i / SPOTS_PER_ROW;
			spots[i] = new ParkingSpot(this, row, i, size);
		}
		availableSpots = numberSpots;
	}
	
	public int availableSpots() {
		return availableSpots;
	}
	
	// check if there is a parking place
	public boolean parkVehicle(Vehicle vehicle) {
		if (availableSpots() < vehicle.getSpotsNeeded()) {
			return false;
		}
		int spotNumber = findAvailableSpots(vehicle);
		if (spotNumber < 0) {
			return false;
		}
		return parkStartingAtSpot(spotNumber, vehicle);
	}
	
	// Park a vehicle starting at the spot until spots needed
	private boolean parkStartingAtSpot(int spotNumber, Vehicle vehicle) {
		vehicle.clearSpots();
		boolean success = true;
		for (int i = spotNumber; i < spotNumber + vehicle.spotsNeeded; i++) {
			 success &= spots[i].park(vehicle);
		}
		availableSpots -= vehicle.spotsNeeded;
		return success;
	}
	
	//checking if there is a parking spot
	private int findAvailableSpots(Vehicle vehicle) {
		int spotsNeeded = vehicle.getSpotsNeeded();
		int lastRow = -1;
		int spotsFound = 0;
		for (int i = 0; i < spots.length; i++) {
			ParkingSpot spot = spots[i];
			if (lastRow != spot.getRow()) {
				spotsFound = 0;
				lastRow = spot.getRow();
			}
			if (spot.canFitVehicle(vehicle)) {
				spotsFound++;
			} else {
				spotsFound = 0;
			}
			if (spotsFound == spotsNeeded) {
				return i - (spotsNeeded - 1);
			}
		}
		return -1;
	}
	
	public void print() {
		int lastRow = -1;
		for (int i = 0; i < spots.length; i++) {
			ParkingSpot spot = spots[i];
			if (spot.getRow() != lastRow) {
				System.out.print("  ");
				lastRow = spot.getRow();
			}
			spot.print();
		}
	}
	
	//checking to add into available spot if a vehicle leaves
	public void spotFreed() {
		availableSpots++;
	}
}
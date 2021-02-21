package com.cogent.example;

public class ParkingSpot {
	private Vehicle vehicle;
	private VehicleSize spotSize;
	private int row;
	private int spotNumber;
	private Level level;
	
	public ParkingSpot(Level level, int row, int spotNumber, VehicleSize spotSize) {
		this.level = level;
		this.row = row;
		this.spotNumber = spotNumber;
		this.spotSize = spotSize;
	}
	
	public boolean isAvailable() {
		return vehicle == null;
	}
	
	// Checks if the spot is big enough for the vehicle 
	public boolean canFitVehicle(Vehicle vehicle) {
		return isAvailable() && vehicle.canFitInSpot(this);
	}
	
	// Park vehicle in this spot
	public boolean park(Vehicle vehicle) {
		if (!canFitVehicle(vehicle)) {
			return false;
		}
		this.vehicle = vehicle;
		this.vehicle.parkInSpot(this);
		return true;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getSpotNumber() {
		return spotNumber;
	}
	
	public VehicleSize getSize() {
		return spotSize;
	}
	
	// Remove vehicle from spot, and notify level & spot as available 
	public void removeVehicle() {
		level.spotFreed();
		vehicle = null;
	}
	
	public void print() {
		if (vehicle == null) {
			if (spotSize == VehicleSize.Compact) {
				System.out.print("c");
			} else if (spotSize == VehicleSize.Large) {
				System.out.print("l");
			} else if (spotSize == VehicleSize.Motorcycle) {
				System.out.print("m");
			}
		} else {
			vehicle.print();
		}
	}
}
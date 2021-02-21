package com.cogent.example;



public class Main {
	public static void main(String[] args) {
		ParkingLot lot = new ParkingLot();
		
		Vehicle vehicle = null;
		while (vehicle == null || lot.parkVehicle(vehicle)) {
			lot.print();
			int randomInt = AssortedMethods.randomIntInRange(0, 10);
			if (randomInt < 2) {
				vehicle = new Bus();
			} else if (randomInt < 4) {
				vehicle = new Motorcycle();
			} else {
				vehicle = new Car();
			}
			System.out.print("\nParking a ");
			vehicle.print();
			System.out.println("");
		}
		System.out.println("Parking Failed. Final state: ");
		lot.print();
	}

}

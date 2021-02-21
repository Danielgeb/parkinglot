package com.cogent.example;

public class AssortedMethods {	
		
		public static int randomInt(int n) {
			return (int) (Math.random() * n);
		}

		public static int randomIntInRange(int min, int max) {
			return randomInt(max + 1 - min) + min;
			
		}
}

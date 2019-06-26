package com.revature;

import java.util.Random;

import com.revature.controller.BankController;

/**
 * Creates an instance of  controller and launches application.
 * 
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BankController BC = new BankController();
		BC.start();

	}

	public static void random(long seed) {

		// create random object
		Random r = new Random();

		// setting seed
		r.setSeed(seed);

		// value after setting seed
		System.out.println(r.nextInt(1000000000));
	}

}

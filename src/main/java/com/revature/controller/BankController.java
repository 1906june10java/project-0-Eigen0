package com.revature.controller;

import java.util.Scanner;

import com.revature.exception.CancelException;
import com.revature.exception.PinException;
import com.revature.service.BusinessLogic;
/**
 * Calls Service class functions
 * Catches exceptions thrown by said functions
 * 
 */
public class BankController {
	// start-bank/atm
	public void start() throws Exception {
		while (true) {
			// start session with new user
			session();
		}
	}

	public void session() throws Exception {

		Scanner myObj = new Scanner(System.in);
		BusinessLogic BL = new BusinessLogic(myObj);

		try {
			BL.getLogInInfo(myObj);
		} catch (PinException pe) {
			System.out.println("Too many PIN trys. Locked out.\n");
			Thread.sleep(5000);
			// do some admin alert thing
			BL.getLogInInfo(myObj);
		} catch (CancelException ce) {
			System.out.println("Session cancelled.\n");
			BL.getLogInInfo(myObj);
		}
		BL.interactions(myObj);
	}

	boolean validLogin() {
		// authenticate user info
		return true;
	}

	
	boolean validCommand() {

		return true;
	}


}

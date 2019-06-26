package com.revature.service;

import java.util.Scanner;

import com.revature.exception.CancelException;
import com.revature.exception.PinException;
import com.revature.model.Account;
import com.revature.repository.AccountRepository;
import com.revature.repository.AccountRepositoryJDBC;

public class BusinessLogic {

	static Scanner input;
	static Account account = new Account();
	static AccountRepository repository = new AccountRepositoryJDBC();

	static int count = 0;
	static String commands = " DEPOSIT WITHDRAWAL LOGOUT VIEW EXIT";
	static boolean validCommand = false;
	static boolean validUpdate = false;

	final static int ZERO = 0;
	final static int OVERDRAFT_COVERAGE = 10000;
	final static int ACCOUNT_LENGTH = 9;
	final static int PIN_LENGTH = 4;
	final static String nonNumeric = "[^0-9]+";
	final static String bankName = "Goliath National Bank";// "MiMoni Bank"

	public BusinessLogic(Scanner input) {
		super();
		this.input = input;
	}

	public static void interactions(Scanner input) {
		System.out.println("Welcome " + " ");// Generic user
		do {
			displayInteractionOptions();
			String command = input.nextLine();
			command = command.toUpperCase();
			if (commands.contains(command)) {

				switch (command) {
				case "DEPOSIT":
					depositSubroutine(input);
					break;
				case "WITHDRAWAL":
					withdrawalSubroutine(input);
					break;
				case "VIEW":
					viewSubroutine();
					break;
				case "LOGOUT":
					logoutSubroutine();
					break;
				case "EXIT":
					exitSubroutine();
					break;
				default:
					System.out.println("Invalid command. Try again");
					break;
				}
			} else {
				System.out.println("Invalid command. Try again");
				validCommand = false;
			}
		} while (!validCommand);
	}

	public static void exitSubroutine() {
		// Terminate JVM
		System.out.println("\nJikan desu. Sayonara.");
		System.exit(0);
	}

	public static void logoutSubroutine() {
		System.out.println("Service charges may apply to this session.\n");
		System.out.println("Session over. Thank you and Goodbye.\n");
		validCommand = true;
	}

	public static long viewSubroutine() {
		account = repository.findByAccountNumber(account.getAccountNumber());
		System.out.println("\n$" + account.getBalance() + "\n");
		return account.getBalance();
	}

	public static long withdrawalSubroutine(Scanner input) throws IllegalArgumentException {
		String amount = null;
		try {
			System.out.println("How much do you want to withdraw? ");
			System.out.print("$");
			amount = input.nextLine();
			long longAmount = Long.parseLong(amount);

			if (account.getBalance() < longAmount)
				System.out.println("Overdraft charges may apply.");

			if ((amount.contains(nonNumeric)) || longAmount < ZERO
					|| account.getBalance() + OVERDRAFT_COVERAGE < longAmount)
				throw new IllegalArgumentException("Not valid withdrawal amount.");

			validUpdate = repository.withdrawFromBalance(account.getAccountNumber(), longAmount);
			if (validUpdate)
				System.out.println("\nWithdraw Successful\n");
			return account.getBalance();
		} catch (IllegalArgumentException e) {
			System.out.println("Cannot withdraw '" + amount + "'\n");
			withdrawalSubroutine(input);
		}
		return -1;
	}

	public static long depositSubroutine(Scanner input) throws IllegalArgumentException {
		String amount = null;
		try {
			System.out.println("How much do you want to deposit? ");
			System.out.print("$");
			amount = input.nextLine();
			long longAmount = Long.parseLong(amount);

			if ((amount.contains(nonNumeric)) || longAmount < ZERO)
				throw new IllegalArgumentException("Not valid deposit amount.");

			validUpdate = repository.depositToBalance(account.getAccountNumber(), longAmount);
			if (validUpdate) {
				System.out.println("\nDeposit Successful\n");
				return account.getBalance();
			}
		} catch (IllegalArgumentException e) {
			System.out.println("\nCannot deposit '" + amount + "'\n");
			depositSubroutine(input);
		}
		return -1;
	}

	public static void displayInteractionOptions() {
		System.out.println(" Would like to: ");
		System.out.println("1. Make a DEPOSIT");
		System.out.println("2. Make a WITHDRAWAL");
		System.out.println("3. VIEW balance");
		System.out.println("4. LOGOUT");
	}

	public static String getLogInInfo(Scanner input) throws CancelException, PinException {
		System.out.println("Welcome to " + bankName);

		validateUserAccNumInput(input);

		validateUserPINInput(0, input);
		return null;
	}

	public static long validateUserPINInput(int count, Scanner input) throws CancelException, PinException {
		String userInput = null;
		try {
			System.out.print("\nPIN (cancel): ");

			userInput = input.nextLine();
			long pin = Long.parseLong(userInput);
			System.out.println();

			if (userInput.length() != PIN_LENGTH || (userInput.contains(nonNumeric)) || account.getPin() != pin)
				throw new IllegalArgumentException("Not valid pin");
		} catch (IllegalArgumentException e) {

			if (count == 3)
				throw new PinException("Too many PIN verification trys");
			else if (userInput.contentEquals("cancel"))
				throw new CancelException("Cancel current login process");
			else
				System.out.println("\nEither account number or pin is invalid\n ");
			validateUserPINInput(++count, input);
		}
		return account.getPin();
	}

	public static boolean validateUserAccNumInput(Scanner input) throws NullPointerException {
		try {
			System.out.println("Please enter valid login information");
			System.out.print("Account Number: ");

			String userInput = input.nextLine();
			if (userInput.length() != ACCOUNT_LENGTH || (userInput.contains(nonNumeric)))
				throw new IllegalArgumentException("Not valid account");
			else {
				long accountNumber = Long.parseLong(userInput);

				account = repository.findByAccountNumber(accountNumber);
				System.out.println("\nAccount " + account.getAccountNumber() + " ");
			}
		} catch (IllegalArgumentException e) {
			System.out.println("\nInvalid Input.\n ");
			validateUserAccNumInput(input);
		} catch (NullPointerException e) {
			System.out.println("\nAccount Number does not exist.\n ");
			validateUserAccNumInput(input);
		}
		if (account.equals(null))
			return false;
		else
			return true;

	}

}

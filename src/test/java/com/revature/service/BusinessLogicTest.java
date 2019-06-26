package com.revature.service;

import static org.junit.Assert.assertEquals;

import java.util.Scanner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.revature.exception.CancelException;
import com.revature.exception.PinException;


public class BusinessLogicTest {
	static Scanner input = new Scanner(System.in);
	private static final BusinessLogic businessLogicWorkerBee = new BusinessLogic( input);

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	/*******************************************************************
	 * Test 1
	 * @throws PinException 
	 * @throws CancelException 
	 ******************************************************************/
	@Test
	public void invalidPINThrice() throws CancelException, PinException {
		BusinessLogic.validateUserAccNumInput(new Scanner("570126108\n"));
		String input="password\n";
		expectedException.expect(PinException.class);
		BusinessLogic.validateUserPINInput(3,new Scanner(input)); 
	}


	/*******************************************************************
	 * Test2
	 * @throws PinException 
	 * @throws CancelException 
	 ******************************************************************/
	@Test
	public void validPIN() throws CancelException, PinException  {
      BusinessLogic.validateUserAccNumInput(new Scanner("570126108\n"));
		String input="0002\n";
		BusinessLogic.validateUserAccNumInput(new Scanner("570126108\n"));
		long pin=0002;
		assertEquals(pin,BusinessLogic.validateUserPINInput(0,new Scanner("0002\n"))); 
		
		
	} 
	
	/*******************************************************************
	 * Test3
	 ******************************************************************/
	
	@Test
	public void validAccount()   {

		String input="570126108\n";	
		assertEquals(true,BusinessLogic.validateUserAccNumInput(new Scanner(input))); 	
		
	}
	
	/*******************************************************************
	 * Test4
	 ******************************************************************/
	@Test
	public void validDeposit()   {
		
		BusinessLogic.validateUserAccNumInput(new Scanner("570126108\n"));
		String input="80\n";
		long balance=BusinessLogic.depositSubroutine(new Scanner(input));
		long balanceAfterDeposit=BusinessLogic.viewSubroutine();
		assertEquals(balance+80,balanceAfterDeposit); 

	}

}

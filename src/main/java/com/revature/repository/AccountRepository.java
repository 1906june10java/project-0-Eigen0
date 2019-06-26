package com.revature.repository;
/**
 * Interface: Has methods that are implemented by the DAO
 * 
 */
import com.revature.model.Account;

public interface AccountRepository {

	public Account findByAccountNumber(long accountNumber);
	
	boolean depositToBalance(long accountNumber, long amount);
	
	boolean withdrawFromBalance(long accountNumber, long amount);
}

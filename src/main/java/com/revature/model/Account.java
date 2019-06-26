package com.revature.model;
/**
 * Custom: Is able to hold  the value of the an account table record in an instance of this object
 * 
 */
public class Account implements Comparable<Account>{

//	A_ID             NOT NULL NUMBER        
//	A_BALANCE        NOT NULL VARCHAR2(100) 
//	A_ACCOUNT_NUMBER NOT NULL NUMBER        
//	A_PIN            NOT NULL NUMBER(4)     
	
	private long id;
	private long balance;
	private long accountNumber;
	private long pin;
	
	public Account(){
		
	}
	
	
	public Account(long id, long balance, long accountNumber, long pin) {
		super();
		this.id = id;
		this.balance = balance;
		this.accountNumber = accountNumber;
		this.pin = pin;
	}


	@Override
	public String toString() {
		return "Account [id=" + id + ", balance=" + balance + ", accountNumber=" + accountNumber + ", pin=" + pin + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (accountNumber ^ (accountNumber >>> 32));
		result = prime * result + (int) (balance ^ (balance >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (pin ^ (pin >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountNumber != other.accountNumber)
			return false;
		if (balance != other.balance)
			return false;
		if (id != other.id)
			return false;
		if (pin != other.pin)
			return false;
		return true;
	}


	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public long getBalance() {
		return balance;
	}



	public void setBalance(long balance) {
		this.balance = balance;
	}



	public long getAccountNumber() {
		return accountNumber;
	}



	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}



	public long getPin() {
		return pin;
	}



	public void setPin(long pin) {
		this.pin = pin;
	}



	@Override
	public int compareTo(Account o) {
		return new Long(this.id).compareTo(o.id);
	}
	
	

}

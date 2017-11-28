package com.ahmed.testing.stuff.test.stuff;

/* ephemeral accounts... no persistence! */

public class Account {

	private int accountNumber;
	private String name;
	private double balance;
	private AccountHolder accountHolder;
	private Auditor auditor;
	
	private static int initialAccountNumber = 1000; 
	
	public Account(String name, double balance) {
		super();
		this.accountNumber = ++initialAccountNumber;
		this.name = name;
		this.balance = balance; // opening balance
	}
	
	public void deposit (double amount){
		// if amount negative, throw an exception
		if (amount < 0){
			throw new ArithmeticException("Negative deposit amount");
		}
		if (accountHolder == null){
			return; // account not yet ready...
		}
		balance += amount; // we don't yet have a Transaction class...
		if (auditor != null){
			auditor.reportTransaction();
		}
	}
	
	public void withdraw (double amount){
		if (accountHolder == null){
			return; // account not yet ready...
		}
		if (accountHolder.isActive()){
			balance -= amount; // we don't yet have a Transaction class...
		}
		if (auditor != null){
			auditor.reportTransaction();
		}
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public String getName() {
		return name;
	}
	
	public void changeName(String name) {
		// requires auditor agreement 
		if (auditor != null && auditor.proposedNameChangeIsOK(name)){
			this.name = name;
		}
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public AccountHolder getAccountHolder() {
		return accountHolder;
	}
	
	public void setAccountHolder(AccountHolder accountHolder) {
		this.accountHolder = accountHolder;
	}
	
	public Auditor getAuditor() {
		return auditor;
	}
	
	public void setAuditor(Auditor auditor) {
		this.auditor = auditor;
	}
	
}

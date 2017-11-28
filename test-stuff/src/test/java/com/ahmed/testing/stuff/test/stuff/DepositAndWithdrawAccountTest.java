package com.ahmed.testing.stuff.test.stuff;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.*;

import com.ahmed.testing.stuff.test.stuff.Account;
import com.ahmed.testing.stuff.test.stuff.AccountHolder;
import com.ahmed.testing.stuff.test.stuff.Auditor;

import static org.mockito.Mockito.*;

public class DepositAndWithdrawAccountTest {

	@Test
	public void canDepositMoney() {
		// given   - the account holder simply needs to exist...
		double openingBalance = 9000.00;
		Account sut = new Account("Mike",openingBalance);
		AccountHolder dummyAccountHolder = mock(AccountHolder.class);
		sut.setAccountHolder(dummyAccountHolder);
		// when 
		double amount = 500;
		sut.deposit(amount);
		// then
		assertEquals(openingBalance + amount, sut.getBalance(), 0.0);
	}

	@Test
	public void canWithdrawMoney() {
		// given   - the account holder needs to exist and return true when the SUT calls its isActive() method.
		double openingBalance = 9000.00;
		Account sut = new Account("Any Name",openingBalance);
		AccountHolder dummyAccountHolder = mock(AccountHolder.class);
		sut.setAccountHolder(dummyAccountHolder);
		when(dummyAccountHolder.isActive()).thenReturn(true);
		// when 
		double amount = 500;
		sut.withdraw(amount);
		// then
		assertEquals(openingBalance - amount, sut.getBalance(), 0.0);
	}
	

	@Test
	public void withdrawMoneyFailsWithoutExceptionWhenAccountHolderIsInactive() {
		// given   - the account holder needs to exist and return true when the SUT calls its isActive() method.
		double openingBalance = 9000.00;
		Account sut = new Account("Ahmed",openingBalance);
		AccountHolder dummyAccountHolder = mock(AccountHolder.class);
		sut.setAccountHolder(dummyAccountHolder);
		// when 
		double amount = 500;
		sut.withdraw(amount);
		// then
		assertEquals(openingBalance, sut.getBalance(), 0.0);
	}

	@Test
	public void accountHolderStatusIsCheckedWhenWithdrawMoneyIsAttempted() {
		// given   - the account holder needs to exist and return true when the SUT calls its isActive() method.
		double openingBalance = 9000.00;
		Account sut = new Account("Anna",openingBalance);
		AccountHolder dummyAccountHolder = mock(AccountHolder.class);
		sut.setAccountHolder(dummyAccountHolder);
		// when 
		double amount = 500;
		sut.withdraw(amount);
		// then
		verify(dummyAccountHolder).isActive();
	}

	@Test
	public void auditorReceivesTransactionReportsFor3TransactionsWhenAuditingIsEnabled() {
		// given   - the account holder needs to exist and return true when the SUT calls its isActive() method.
		double openingBalance = 9000.00;
		Account sut = new Account("Nina",openingBalance);
		AccountHolder dummyAccountHolder = mock(AccountHolder.class);
		sut.setAccountHolder(dummyAccountHolder);
		Auditor dummyAuditor = mock(Auditor.class);
		sut.setAuditor(dummyAuditor); //enable auditing
		// when 
		double amount = 500;
		sut.withdraw(amount);
		sut.withdraw(amount);
		sut.deposit(amount);
		// then
		verify(dummyAuditor, times(3)).reportTransaction();;
	}


	@Test
	public void nameChangeWorksIfAuditorApprovesOfProposedNameChange() {
		// given   - the account holder needs to exist and return true when the SUT calls its isActive() method.
		double openingBalance = 9000.00;
		Account sut = new Account("Freda",openingBalance);
		AccountHolder dummyAccountHolder = mock(AccountHolder.class);
		sut.setAccountHolder(dummyAccountHolder);
		Auditor dummyAuditor = mock(Auditor.class);
		sut.setAuditor(dummyAuditor); //enable auditing
		
		// force the auditor to agree
		// note use of a Matcher to force the test dummy to return true
		when(dummyAuditor.proposedNameChangeIsOK(anyString())).thenReturn(true);
		
		// when 
		sut.changeName("Lauren");
		// then
		assertEquals("Lauren", sut.getName());
	}
	

	@Test
	public void nameChangeDoesNotWorkIfAuditorDoesnNotApproveOfProposedNameChange() {
		// given   - the account holder needs to exist and return true when the SUT calls its isActive() method.
		double openingBalance = 9000.00;
		Account sut = new Account("Freda",openingBalance);
		AccountHolder dummyAccountHolder = mock(AccountHolder.class);
		sut.setAccountHolder(dummyAccountHolder);
		Auditor dummyAuditor = mock(Auditor.class);
		sut.setAuditor(dummyAuditor); //enable auditing
		
		// by not forcing the auditor to agree, we'll get the default test dummy behavior
		// which is to return false from any method with a boolean return,
		// just as we get back zero from a method that returns a number
		// when 
		sut.changeName("Lauren");
		// then
		assertEquals("Freda", sut.getName());
	}
	
	
	
}

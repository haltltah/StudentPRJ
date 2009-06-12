/**
 * 
 */
package sis.studentinfo;

import java.math.BigDecimal;

import junit.framework.TestCase;


/**
 * @author hirian
 *
 */
public class AccountTest extends TestCase {
	
	/*
	 * The student information system must maintain an account of charges and 
	 * credits for each student. The first test demonstrates the ability of an 
	 * Account to track a balance based on applied charges and credits.
	 */
	public void testTransactions() {
		Account account =  new Account();
		account.credit(new BigDecimal("0.10"));
		account.credit(new BigDecimal("11.00"));
		assertEquals(new BigDecimal("11.10"), account.getBalance());
	}
	//test scale
	public void testScale() {
		assertEquals(new BigDecimal("5.300"),
				   new BigDecimal("5.000").add(new BigDecimal("0.3")));
	}
	
	public void testTransactionAverage() {
		Account account = new Account();
		account.credit(new BigDecimal("0.10"));
		account.credit(new BigDecimal(11.00));
		account.credit(new BigDecimal(2.99));
		assertEquals(new BigDecimal("4.70"), account.transactionAverage());
	}
}

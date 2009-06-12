/**
 * 
 */
package sis.studentinfo;

import java.math.BigDecimal;

/**
 * @author hirian
 *
 */

/*
 * Using BigDecimal - lesson 10
 */
public class Account {
	private BigDecimal balance = new BigDecimal("0.00");
	private int transactionCount = 0;
	/**
	 * <p>
	 * Calculate credit
	 * </p>
	 * 
	 * @param amount
	 */
	public void credit(BigDecimal amount) {
		balance = balance.add(amount);
		transactionCount++;
	}
	
	/**
	 * <p>
	 * Return account balance
	 * </p>
	 * @return balance - BigDecimal
	 */
	public BigDecimal getBalance() {
		return balance;
	}

	/**
	 * <p>
	 * Get transaction average for account
	 * </p>
	 * @return transactionAverage - BigDecimal
	 */
	public BigDecimal transactionAverage() {
		/*BigDecimal average = new BigDecimal("0.00");
		System.out.println(average.scale() + " - 1st scale");
		average = balance.divide(new BigDecimal(transactionCount), BigDecimal.ROUND_HALF_UP);
		System.out.println(average.scale() + " - 2nd scale");
		average = average.setScale(2, BigDecimal.ROUND_HALF_UP);
		System.out.println(average.scale() + " -3rd scale");
		return average;*/
		
		return balance.divide(new BigDecimal(transactionCount), 2, BigDecimal.ROUND_HALF_UP);
		
	}
}

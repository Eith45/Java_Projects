/* Name: Course: CNT 4714 Spring 2015 Assignment title: Program 2 – Synchronized, Cooperating Threads Under Locking
Due Date: February 8, 2015
*/

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {

	/**
	 * Bank account has a balance that can be changed by both deposits and withdrawals
	 */

	private int balance;//current balance
	private final Lock balanceChangeLock;//Lock for providing synchronization
	private Condition sufficientFundsCondition;//Condition for providing synchronization

	//Bank account constructor 
	public BankAccount() {
		balance = 0;//starting balance is equal to 0
		balanceChangeLock = new ReentrantLock();//creating lock
		sufficientFundsCondition = balanceChangeLock.newCondition();//creation condition
	}

	public void deposit(int amount, int anID) throws InterruptedException {

		balanceChangeLock.lock();//closing our data from other threads
		try {
			//working with amount
			System.out.print("Thread " + anID + " deposits $" + amount);
			int newBalance = balance + amount;
			System.out.println("                               Balance is $" + newBalance);
			balance = newBalance;
			
			sufficientFundsCondition.signalAll();//telling other threads that are waiting in pool, that the money has been loaded 
		} finally {
			balanceChangeLock.unlock();//open our lock that other thread can work with balance
		}
	}

	public void withdraw(int amount, int anID) throws InterruptedException {
		balanceChangeLock.lock();//closing lock
		try {
			//if a withdrawal thread attempts to withdraw an 
			//amount greater than the current balance 
			if(balance < amount) {				
				System.out.print("                         Thread " + anID + " withdraws $" + amount);
				System.out.println("     Thread Withdrawal - Blocked - Insufficient Funds");
				 //– then it must block and wait until
			     //a deposit has occurred before it can try again.
				while (balance < amount){
					sufficientFundsCondition.await();	
				}
			}
			//else working with balance
			else { 
				System.out.print("                         Thread " + anID + " withdraws $" + amount);
				int newBalance = balance - amount;
				System.out.println("      Balance is $" + newBalance);
				balance = newBalance;
			}            	
		} finally {
			//telling threads than withdraw has been done
			sufficientFundsCondition.signalAll();
			//unlocking data
			balanceChangeLock.unlock();
			
		}
	}
}


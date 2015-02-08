/* Name: Course: CNT 4714 Spring 2015 Assignment title: Program 2 – Synchronized, Cooperating Threads Under Locking
Due Date: February 8, 2015
*/

import java.util.Random;

public class WithdrawRunnable implements Runnable{
	
	private BankAccount account;//account object
	private int amount;//withdraw sum
	private int id;//Thread id
	Random random = new Random();
	
	public WithdrawRunnable(BankAccount anAccount, int anID) {
		account = anAccount;
		id = anID;
	}
	
	public void run() {
		
		while(true){
			amount = 1 + random.nextInt(49);			
			try {				
				account.withdraw(amount, id);
				//Once a withdrawal thread has executed, have it yield to another thread
				Thread.yield();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}
		
	}
}


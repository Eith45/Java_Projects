/* Name: Course: CNT 4714 Spring 2015 Assignment title: Program 2 – Synchronized, Cooperating Threads Under Locking
Due Date: February 8, 2015
*/

import java.util.Random;

public class DepositRunnable implements Runnable{
	
	
	private BankAccount account;//Current bank account object
	private int amount;//money that we are going download to deposit 
	private int id;//Thread id
	Random random = new Random();//Random for choosing the sum of payment
	
	//Deposit constructor
	public DepositRunnable(BankAccount anAccount, int anID) {
		//Setting data
		account = anAccount;
		id = anID;
	}
	
	public void run() {
		
		while(true){
			//sum of payment
			amount = 1 + random.nextInt(200);
			try {
				//new deposit
				account.deposit(amount, id);
				//making thread to sleep
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			
		}
	}
}


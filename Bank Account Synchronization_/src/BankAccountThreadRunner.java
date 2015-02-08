/* Name: Course: CNT 4714 Spring 2015 Assignment title: Program 2 – Synchronized, Cooperating Threads Under Locking
Due Date: February 8, 2015
*/

import java.util.Random;


public class BankAccountThreadRunner {
	
	public static void main(String[] args) {
		
		System.out.println("Deposit Threads          Withdrawal Threads          Balance");
		System.out.println("---------------          ------------------          -------");
		
		
		//creating new account
		BankAccount account = new BankAccount();
		//Creating new three deposit threads
		Thread d1 = new Thread(new DepositRunnable(account, 1));
		Thread d2 = new Thread(new DepositRunnable(account, 2));
		Thread d3 = new Thread(new DepositRunnable(account, 3));
		
		//Creating new four withdrawal threads
		Thread w4 = new Thread(new WithdrawRunnable(account, 4));
		Thread w5 = new Thread(new WithdrawRunnable(account, 5));
		Thread w6 = new Thread(new WithdrawRunnable(account, 6));
		Thread w7 = new Thread(new WithdrawRunnable(account, 7));
		
		//starting deposits and withdraws
		d1.start();
		w4.start();
		w6.start();
		d2.start();
		d3.start();				
		w5.start();		
		w7.start();
	}
}




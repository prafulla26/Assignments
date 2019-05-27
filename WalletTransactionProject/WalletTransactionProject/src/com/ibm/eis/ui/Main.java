/**
 * 
 */
/**
 * @author PrafullaKumarTripath
 *
 */
package com.ibm.eis.ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.ibm.eis.bean.CustomerDetails;
import com.ibm.eis.service.ServiceClass;
import com.ibm.eis.service.ServiceInterface;



public class Main {
	
	
	public static void main(String[] args) throws SQLException {
			ServiceInterface service = new ServiceClass();
			Scanner sc =new Scanner(System.in);
			int accountno = 0;
			int amount =0;
			while(true) {
			System.out.println("Welcome to App");
			System.out.println("Enter 1. If you are new user \n Enter 2. If existing  user");
			int choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1 :String password;
					System.out.println("Enter Customer Name");
					String name = sc.nextLine();
					while(true) {
					System.out.println("Enter Password(must contain UpperCase,LowerCase character,digit,length should be 8 digit");
					password = sc.nextLine();
					if(!(service.validatePassword(password))){
						System.out.println("Entered password is wrong");
					}
					else {
						System.out.println("Password pattern correct");
						break;
					}
					}
					while(true) {
					System.out.println("Enter Customer Mobile No(only digits)");
					String mobileno =sc.nextLine();
					
					if(!(service.checkMobileNo(mobileno))){
						System.out.println("Mobile no is wrong please enter correct mobile no");
						
					}
					else {
						
						CustomerDetails cust =new CustomerDetails(name,mobileno,password);
						accountno = service.createAccount(cust);
						System.out.println("Your account no is: "+accountno);
						break;
					}
					}
					
				break;
			
			case 2 : 	System.out.println("Enter account no for login");
			            accountno = sc.nextInt();
			            sc.nextLine();
			            while(true) {
						System.out.println("Enter password for login");
						String password1 = sc.nextLine();
						if(!(service.checkUser(password1,accountno))){
							System.out.println("Entered password is wrong");
						}
						else {
						System.out.println("Successfully Login");
						while(true) {
						System.out.println("Enter your choice: \n 1. Viewing balance \n 2. Deposit money \n 3. Withdraw money \n 4.Fund Transfer \n 5.Printing Transaction \n 6.Closing Connection");
						int ch = sc.nextInt();
						
						switch(ch) {
			
							case 1 :service.showBalance(accountno);
									break;
									
							case 2 :while(true) {
									System.out.println("Enter amount to be deposit");
									try {
									amount = sc.nextInt();
									break;
									}catch(InputMismatchException e) {
										sc.next();
									}
									
									}
									service.deposit(accountno,amount);
									break;
							case 3 :while(true) {
									System.out.println("Enter amount to be withdraw");
									try {
									amount = sc.nextInt();
									break;
									}catch(InputMismatchException e) {
										sc.next();
									}
									}
									service.withdraw(accountno,amount);
									break;
							case 4 :
									System.out.println("Enter account no to which you want to transfer fund");
									int targetAccountNo = sc.nextInt();
									while(true) {
									try {
									System.out.println("Enter amount to transfer");
									amount = sc.nextInt();
									break;
									}catch(InputMismatchException e) {
										sc.next();
									}
									}
									service.fundTransfer(accountno,targetAccountNo,amount);
									break;
							case 5 : service.PrintTransaction(accountno);
									break;
							case 6 : System.out.println("Connection Closed");
									service.closeConnection();
									System.exit(0);
									break;
							default: 
								System.out.println("Entered a wrong choice");
								break;
							}
							}
						}}
					
			default:
				System.out.println("Entered a wrong choice");
			}
			
		
			}	
	}
}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			

		
		
		
		
		
		
		
		
		
		
		
	

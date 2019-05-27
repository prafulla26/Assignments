/**
 * 
 */
/**
 * @author PrafullaKumarTripath
 *
 */
package com.ibm.eis.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ibm.eis.bean.CustomerDetails;

public interface ServiceInterface {

	int createAccount(CustomerDetails cust) throws SQLException;

	void showBalance(int accountno);

	void deposit(int accountno1, int amount);

	void fundTransfer(int sourceAccountNo, int targetAccountNo, int amount2);

	void withdraw(int accountno2, int amount);

	void PrintTransaction(int accountno4);

	boolean checkMobileNo(String mobileno);

	void closeConnection();

	boolean validatePassword(String password);

	boolean checkUser(String password1, int accountno)throws SQLException;

	
	

	
}

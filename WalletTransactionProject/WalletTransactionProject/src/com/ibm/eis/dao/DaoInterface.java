/**
 * 
 */
/**
 * @author PrafullaKumarTripath
 *
 */
package com.ibm.eis.dao;

import java.sql.SQLException;




public interface DaoInterface {



	void showBalance(int accountno);

	void deposit(int accountno, int amount);

	void fundTransfer(int sourceAccountNo, int targetAccountNo, int amount);

	void withdraw(int accountno, int amount);

	void PrintTransaction(int accountno);

	void closeConnection();

	boolean checkUser(String password, int accountno) throws SQLException;

	int createAccount(com.ibm.eis.bean.CustomerDetails cust) throws SQLException;



}

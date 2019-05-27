/**
 * 
 */
/**
 * @author PrafullaKumarTripath
 *
 */
package com.ibm.eis.service;

import java.sql.SQLException;

import com.ibm.eis.bean.CustomerDetails;
import com.ibm.eis.dao.DaoClass;
import com.ibm.eis.dao.DaoInterface;

public class ServiceClass implements ServiceInterface {
	
	DaoInterface dao =new DaoClass();
	@Override
	public int createAccount(CustomerDetails cust) throws SQLException {
		return dao.createAccount(cust);
		
	}

	@Override
	public void showBalance(int accountno) {
		dao.showBalance(accountno);
		
	}

	@Override
	public void deposit(int accountno, int amount) {
		dao.deposit(accountno,amount);
		
	}

	@Override
	public void fundTransfer(int sourceAccountNo, int targetAccountNo, int amount) {
		dao.fundTransfer(sourceAccountNo,targetAccountNo,amount);
		
	}

	@Override
	public void withdraw(int accountno, int amount) {
		dao.withdraw(accountno,amount);
		
	}

	@Override
	public void PrintTransaction(int accountno) {
		dao.PrintTransaction(accountno);
		
	}

	@Override
	public boolean checkMobileNo(String mobileno) {
		String mobilepattern ="[0-9]{10}";
		if(mobileno.matches(mobilepattern)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void closeConnection() {
		dao.closeConnection();
		
	}

	@Override
	public boolean validatePassword(String password) {
		
		String passwordPattern ="[A-Z a-z 0-9]{8}";
		if(password.matches(passwordPattern)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean checkUser(String password,int accountno) throws SQLException {
		return dao.checkUser(password,accountno);
	}

	

}





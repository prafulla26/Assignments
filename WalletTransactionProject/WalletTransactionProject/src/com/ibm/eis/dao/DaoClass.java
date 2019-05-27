/**
 * 
 */
/**
 * @author PrafullaKumarTripath
 *
 */
package com.ibm.eis.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.ibm.eis.bean.CustomerDetails;

public class DaoClass implements DaoInterface {
	Connection dbCon;
	Statement s;
	PreparedStatement stmt;
	public DaoClass(){
	
			try {
				Class.forName("com.mysql.jdbc.Driver");
				//Create the Connection
				 dbCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking","root","");
				 s = dbCon.createStatement();
			} catch (ClassNotFoundException | SQLException e) 
			{
			System.out.println("Some issue occur : "+e.getMessage());
			}
	
		
		}

	@Override
	public int createAccount(CustomerDetails cust) throws SQLException {
		System.out.println(cust.getMobileno());
			try {
				
				
				String insertQry = "insert into userdetails(UserName,MobileNo,Balance,AccountCreationDate,Password) values("
				+ "'"+ cust.getName() +"',"
				+ "'"+ cust.getMobileno() +"',"
				+ "'"+ 1000 +"',"
				+ "'"+ new java.sql.Date(System.currentTimeMillis()) +"',"
				+ "'"+ cust.getPassword() +"'"
				+")";
				
				if(s.executeUpdate(insertQry)>0) {
					System.out.println("Data added");
				}
				else {
					System.out.println("No data inserted"+ "");
				}}catch (SQLException e) {
						System.out.println("Dublicate mobile no is not possible:"+e);
				}
				String findAccountNo ="select AccountNo from UserDetails";
				ResultSet rs = s.executeQuery(findAccountNo);
				if(rs.next()) {
					return rs.getInt("AccountNo");
				}
				else {
					return 0;
				}
					
	}

	@Override
	public void showBalance(int accountno) {
	String showQry ="select Balance from userdetails where AccountNo='"+ accountno +"'";
		try {
			ResultSet rs = s.executeQuery(showQry);
			while(rs.next()) {
					System.out.println("Balance : "+ rs.getInt("Balance"));
					if(rs.getInt("Balance")<0){
						System.out.println("Low Balance");
					}
			}	
			
			
			
		} catch (SQLException e) {
			System.out.println("Some error occur: "+ e);
		}
		
		
	}

	@Override
	public void deposit(int accountno,int amount) {
		String insertQry =" insert into transactiondetails(AccountNo,TransactionDate,TransactionType,Amount) values("
				+ "'"+ accountno +"',"
				+"'"+ new java.sql.Date(System.currentTimeMillis()) +"',"
				+ "'"+ "Deposit" +"',"
				+ "'"+ amount +"'"
				+")";
		int count;
		try {
			count = s.executeUpdate(insertQry);
			
		} catch (SQLException e1) {
			System.out.println("Error: "+ e1);
			}
		

		
		
				
		String updateQry = "update userdetails set Balance = Balance + "
				+ "(select amount from transactiondetails where AccountNo='"+ accountno +"' "
						+ "order by TransactionDate desc limit 1) where AccountNo='"+accountno+"'";
		try {
			
			int n = s.executeUpdate(updateQry);

		} catch (SQLException e) {
			System.out.println("Some issue occur :" +e);
		}
		
		String showQry = "select Balance from userdetails where AccountNo='"+ accountno +"'";
		ResultSet rs;
		try {
			rs = s.executeQuery(showQry);
		
		if(rs.next()) {
		System.out.println("Balance after deposit: "+rs.getInt("Balance"));
		}} catch (SQLException e1) {
			System.out.println(e1);
		}
		
		
	}

	@Override
	public void fundTransfer(int sourceAccountNo, int targetAccountNo, int amount) {
		String insertSourceQry =" insert into transactiondetails(AccountNo,TransactionDate,TransactionType,Amount,TargetAccountNo) values("
				+ "'"+ sourceAccountNo +"',"
				+"'"+new java.sql.Date(System.currentTimeMillis())+"',"
				+ "'"+ "Fund Transfer" +"',"
				+ "'"+ amount +"',"
				+ "'"+ targetAccountNo +"'"
				+")";
		String updateSourceQry = "update userdetails set Balance = "
				+ "Balance - (select amount from transactiondetails where AccountNo='"+sourceAccountNo+"' "
				+ "order by TransactionDate desc limit 1)where AccountNo='"+sourceAccountNo+"' ";

		String updateTargetQry = "update userdetails set Balance = "
				+ "Balance + (select amount from transactiondetails where AccountNo='"+ targetAccountNo+"' "
				+ "order by TransactionDate desc limit 1)where AccountNo='"+ targetAccountNo +"'";
		try {
			int sourceInsert=s.executeUpdate(insertSourceQry);

			int sourceUpdate= s.executeUpdate(updateSourceQry);
			
		    int targetUpdate= s.executeUpdate(updateTargetQry);
		    
		} catch (SQLException e) {
			System.out.println("Some issue occur :" +e);
		}
		String showQry = "select Balance from userdetails where AccountNo='"+ sourceAccountNo +"'";
		ResultSet rs;
		try {
			rs = s.executeQuery(showQry);
		
		if(rs.next()) {
		System.out.println("Balance after deposit: "+rs.getInt("Balance"));
		}} catch (SQLException e1) {
			System.out.println(e1);
		}
	
	}

	@Override
	public void withdraw(int accountno, int amount) {
		ResultSet rs=null;
		try {
			String fetchQry = "Select Balance from userdetails where AccountNo='"+accountno+"'";
			 rs = s.executeQuery(fetchQry);
		} catch (SQLException e2) {
			System.out.println("some issue: "+ e2.getMessage());
		}
			try {
				rs.next();
				
					if(rs.getInt("Balance")<amount){
					System.out.println("Low Balance Can't withdraw amount");
					
					}
					else {
						String insertQry =" insert into transactiondetails(AccountNo,TransactionDate,TransactionType,Amount) values("
								+ "'"+ accountno +"',"
								+"'"+new java.sql.Date(System.currentTimeMillis())+"',"
								+ "'"+ "Withdraw" +"',"
								+ "'"+ amount +"'"
								+")";
						int count;
						try {
							count = s.executeUpdate(insertQry);
								} catch (SQLException e1) {
							System.out.println("Error: "+ e1);
						}
						
								
						String updateQry = "update userdetails set Balance = "
								+ "Balance - (select amount from transactiondetails where AccountNo=accountno "
								+ "order by TransactionDate desc limit 1)where AccountNo='"+ accountno +"'";
						try {
							
							int n = s.executeUpdate(updateQry);
							
					} catch (SQLException e) {
							System.out.println("Some error occur: "+e);
					}


					}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();}
				String showQry = "select Balance from userdetails where AccountNo='"+ accountno +"'";
				ResultSet rs1;
				try {
					rs1 = s.executeQuery(showQry);
				
				if(rs1.next()) {
				System.out.println("Balance after withdrawl: "+rs1.getInt("Balance"));
				}} catch (SQLException e1) {
					System.out.println(e1);
				}
			}
			
			
						
		
				
	

	@Override
	public void PrintTransaction(int accountno) {
		String fetchQry = "select * from transactiondetails where AccountNo = '"+ accountno +"'";
		try {
			ResultSet rs = s.executeQuery(fetchQry);
			while(rs.next()) {
				System.out.print("Account No :"+rs.getInt("AccountNo"));
				System.out.print(", Transaction Date :"+rs.getTimestamp("TransactionDate"));
				System.out.print(", Transaction Type : "+rs.getString("TransactionType"));
				System.out.println(", Amount :"+rs.getInt("Amount"));
			}
		} catch (SQLException e) {
			System.out.println("Some problem in Query Execution : "+e);		}
		
	}

	@Override
	public void closeConnection() {
		try {
			dbCon.close();
			//System.exit(0);
		} catch (SQLException e) {
			System.out.println("Problem in closing connection");
		}
		
	}

	@Override
	public boolean checkUser(String password,int accountno) throws SQLException {
		ResultSet rs=null;
		String fetchQry = "Select password from userDetails where AccountNo = '"+accountno+"'";
		rs =s.executeQuery(fetchQry);
		if(rs.next()) {
		return true;
		}
		else {
			return false;
		}
		} 
		
	}

	


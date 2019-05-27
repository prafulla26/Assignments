/**
 * 
 */
/**
 * @author PrafullaKumarTripath
 *
 */
package com.ibm.eis.bean;

public class CustomerDetails {
String name;
String mobileno;
String password;

public CustomerDetails(String name, String mobileno, String password) {
	this.name = name;
	this.mobileno = mobileno;
	this.password = password;
}
public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}
public void setPassword(String password) {
	this.password = password;
}
public String getMobileno() {
	return mobileno;
}
public void setMobileno(String mobileno) {
	this.mobileno = mobileno;
}
public String getPassword() {
	return password;
}
}

package BankProjectNYP;

import java.util.HashMap;

abstract class User implements Person,UserFunctions{
	int type;
	double takeForeign;
	String name;
	String surname;
	String id;
	String username;
	String password;
	double balance;
	double dolarBalance;
	double euroBalance;
	double sterlinBalance;
	boolean waitingCreditProcess = false;
	boolean waitingCreditCardProcess = false;
	double desiredCredit = 0;
	double desiredCreditCardLimit = 0;
	CreditCard newCard;
	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}
	@Override
	public void setSurname(String surname) {
		// TODO Auto-generated method stub
		this.surname = surname;
	}
	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.id = id;
	}
	@Override
	public void setUsername(String username) {
		// TODO Auto-generated method stub
		this.username = username;
	}
	@Override
	public void setPassword(String newPassword) {
		// TODO Auto-generated method stub
		this.password = newPassword;
	}
	@Override
	public void changePassword(String newPassword) {
		// TODO Auto-generated method stub
		this.password = newPassword;
	}
	@Override
	public double balanceView() {
		// TODO Auto-generated method stub
		return balance;
	}
	@Override
	public void withdrawMoney(int money) {
		// TODO Auto-generated method stub
		balance = balance - money;
	}
	@Override
	public void depositMoney(int money) {
		// TODO Auto-generated method stub
		balance = balance + money;
	}
	@Override
	public void transferMoney(HashMap<String,User> users, String id, int money) {
		// TODO Auto-generated method stub
		users.get(id).balance = money + users.get(id).balance;
		balance = balance -money;
	}
	@Override
	public void buyForeignCurrency(int type, double money) {
		// TODO Auto-generated method stub
		if(type == 1) {
			takeForeign = money/dolar;
			this.dolarBalance += takeForeign;
		}
		else if(type == 2) {
			takeForeign = money/euro;
			this.euroBalance += takeForeign;
		}
		else if(type == 3) {
			takeForeign = money/sterlin;
			this.sterlinBalance += takeForeign;
		}
		balance = balance - money;
	}
	public void takeCredit(double money) {
		this.waitingCreditProcess = true;
		this.desiredCredit = money;
	}
	public void takeCreditCard(double limit) {
		this.waitingCreditCardProcess = true;
		this.desiredCreditCardLimit = limit;
		newCard = new CreditCard(limit);
	}
}

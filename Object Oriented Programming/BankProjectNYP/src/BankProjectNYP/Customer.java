package BankProjectNYP;

import java.util.HashMap;

public class Customer extends User implements UserFunctions{
	int debt;
	public Customer(String name, String surname, String id, String username, String password) {
		// TODO Auto-generated constructor stub
		this.type = 1;
		this.name = name;
		this.surname = surname;
		this.id = id;
		this.username = username;
		this.password = password;
		this.balance = 0;
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
}

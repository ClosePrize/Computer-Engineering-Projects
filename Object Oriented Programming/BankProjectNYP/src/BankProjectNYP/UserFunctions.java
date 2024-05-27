package BankProjectNYP;

import java.util.HashMap;

public interface UserFunctions {
	double dolar = 29.76;
	double euro = 32.68;
	double sterlin = 37.74;
	double balanceView();
	void depositMoney(int money);
	void withdrawMoney(int money);
	void transferMoney(HashMap<String,User> users,String id, int money);
	void buyForeignCurrency(int type, double money);
}

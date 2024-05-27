package BankProjectNYP;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
        HashMap <String,User> Users = new HashMap<String,User>();
		try {
			FileInputStream fileInputStream = new FileInputStream(new File("users.txt"));
	        Scanner scanner = new Scanner(fileInputStream);
	        while(scanner.hasNext()) {
	        	String userData = scanner.nextLine();
	        	String[] satir_bilgisi = userData.split(", ");
	        	if(satir_bilgisi[0].equals("1")) {
	        		Customer user = new Customer(satir_bilgisi[1], satir_bilgisi[2], satir_bilgisi[3], satir_bilgisi[4], satir_bilgisi[5]);
	        		user.balance = Double.parseDouble(satir_bilgisi[6]);
	        		user.dolarBalance = Double.parseDouble(satir_bilgisi[7]);
	        		user.euroBalance = Double.parseDouble(satir_bilgisi[8]);
	        		user.sterlinBalance = Double.parseDouble(satir_bilgisi[9]);
	        		user.waitingCreditCardProcess = Boolean.parseBoolean(satir_bilgisi[10]);
	        		user.waitingCreditProcess = Boolean.parseBoolean(satir_bilgisi[11]);
	        		user.desiredCreditCardLimit = Double.parseDouble(satir_bilgisi[12]);
	        		user.desiredCredit = Double.parseDouble(satir_bilgisi[13]);
	        		Users.put(satir_bilgisi[3], user);
	        	}
	        	else if(satir_bilgisi[0].equals("2")) {
	        		BankStaff user = new BankStaff(satir_bilgisi[1], satir_bilgisi[2], satir_bilgisi[3], satir_bilgisi[4], satir_bilgisi[5]);
	        		user.isApproved = Boolean.parseBoolean(satir_bilgisi[6]);
	        		user.isWaiting = Boolean.parseBoolean(satir_bilgisi[7]);
	        		Users.put(satir_bilgisi[3], user);
	        	}
	        	else if(satir_bilgisi[0].equals("3")) {
	        		BankManager user = new BankManager(satir_bilgisi[1], satir_bilgisi[2], satir_bilgisi[3], satir_bilgisi[4], satir_bilgisi[5]);
	        		user.balance = Double.parseDouble(satir_bilgisi[6]);
	        		user.dolarBalance = Double.parseDouble(satir_bilgisi[7]);
	        		user.euroBalance = Double.parseDouble(satir_bilgisi[8]);
	        		user.sterlinBalance = Double.parseDouble(satir_bilgisi[9]);
	        		user.waitingCreditCardProcess = Boolean.parseBoolean(satir_bilgisi[10]);
	        		user.waitingCreditProcess = Boolean.parseBoolean(satir_bilgisi[11]);
	        		user.desiredCreditCardLimit = Double.parseDouble(satir_bilgisi[12]);
	        		user.desiredCredit = Double.parseDouble(satir_bilgisi[13]);
	        		Users.put(satir_bilgisi[3], user);
	        	}
			}
	        scanner.close();
		}
		catch (Exception e) {
		}
		Login.users = Users;
		SignUp.users = Users;
		Login login = new Login(Users);
		login.setVisible(true);
	}

}

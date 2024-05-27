package BankProjectNYP;

public class BankStaff extends User implements Employee{
	boolean isApproved = false;
	boolean isWaiting = true;
	public BankStaff(String name, String surname, String id, String username, String password) {
		// TODO Auto-generated constructor stub
		this.type = 2;
		this.name = name;
		this.surname = surname;
		this.id = id;
		this.username = username;
		this.password = password;
	}
	@Override
	public void approve(User user, boolean x) {
		// TODO Auto-generated method stub
		user.waitingCreditCardProcess = x;
	}
}

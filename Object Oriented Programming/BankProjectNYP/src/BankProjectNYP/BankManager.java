package BankProjectNYP;

public class BankManager extends User implements Employee{
	public BankManager(String name, String surname, String id, String username, String password) {
		// TODO Auto-generated constructor stub
		this.type = 3;
		this.name = name;
		this.surname = surname;
		this.id = id;
		this.username = username;
		this.password = password;
	}
	void approveBankStaff(BankStaff staff, Boolean x){
		staff.isApproved = x;
	}
	@Override
	public void approve(User user, boolean x) {
		// TODO Auto-generated method stub
		user.waitingCreditProcess = x;
	}
}

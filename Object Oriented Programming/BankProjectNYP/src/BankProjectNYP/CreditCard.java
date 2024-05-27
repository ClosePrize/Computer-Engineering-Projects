package BankProjectNYP;

public class CreditCard extends Card{
	double limit;
	double debt;
	CreditCard(double limit){
		this.limit = limit;
	}
	@Override
	public void debtPayment(double money) {
		// TODO Auto-generated method stub
		debt = money + debt;
	}
	@Override
	public void arrangeLimit(double limit) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void cardRequest() {
		// TODO Auto-generated method stub
		
	}
}

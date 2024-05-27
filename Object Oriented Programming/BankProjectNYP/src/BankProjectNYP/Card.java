package BankProjectNYP;

abstract class Card {
	abstract void cardRequest();
	abstract void arrangeLimit(double limit);
	abstract void debtPayment(double money);
}

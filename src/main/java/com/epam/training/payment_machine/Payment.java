package com.epam.training.payment_machine;

public class Payment {
	private int amountToPay;
	private int paidAmount;

	public Payment(int amountToPay) {
		paidAmount = 0;
		this.amountToPay = amountToPay;
	}

	public int getPaidAmount() {
		return paidAmount;
	}

	public int getRemainedAmount() {
		return amountToPay - paidAmount;
	}

	public int getReturnAmount() {
		if (getRemainedAmount() >= 0) {
			throw new IllegalStateException();
		}
		return getRemainedAmount() * -1;
	}

	public boolean isMoneyEnough() {
		return paidAmount >= amountToPay;
	}

	public void addCoinValue(int amount) {
		paidAmount += amount;
	}
}

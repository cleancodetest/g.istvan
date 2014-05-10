package com.epam.training.payment_machine;

public class Payment {
	private Ticket ticket;
	private int paidAmount;

	public Payment(Ticket selectedTicket) {
		paidAmount = 0;
		ticket = selectedTicket;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public int getPaidAmount() {
		return paidAmount;
	}

	public int getRemainedAmount() {
		return ticket.getPrice() - paidAmount;
	}

	public int getReturnAmount() {
		if (getRemainedAmount() >= 0) {
			throw new IllegalStateException();
		}
		return getRemainedAmount() * -1;
	}

	public boolean isMoneyEnough() {
		return paidAmount >= ticket.getPrice();
	}

	public void addCoinValue(int amount) {
		paidAmount += amount;
		//ticket.setPaid(isPaymentPayed());
	}

	public boolean isPaid() {
		if (ticket != null) {
			return ticket.isPaid();
		}
		return false;
	}
}

package com.epam.training.payment_machine;

import java.util.Map;

import com.epam.training.payment_machine.exception.IllegalMachineStateException;
import com.epam.training.payment_machine.exception.TicketIsNotSelectedException;
import com.epam.training.payment_machine.exception.TicketNotFoundException;
import com.epam.training.payment_machine.payment.Coin;

public interface PaymentMachine {
	void selectTicket(int ticketId) throws IllegalMachineStateException, TicketNotFoundException;

	boolean hasSelectedTicket();

	Map<Coin, Integer> putCoin(Coin coin);

	int getPaidAmount();

	String getTicketDetails() throws TicketIsNotSelectedException;

	boolean isSelectedTicketPaid() throws TicketIsNotSelectedException;

	int getSelectedTicketPrice() throws TicketIsNotSelectedException;
}

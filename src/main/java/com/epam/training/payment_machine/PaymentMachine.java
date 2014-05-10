package com.epam.training.payment_machine;

import java.util.List;
import java.util.Map;

import com.epam.training.payment_machine.exception.IllegalMachineStateException;
import com.epam.training.payment_machine.exception.TicketIsNotSelectedException;
import com.epam.training.payment_machine.exception.TicketNotFoundException;
import com.epam.training.payment_machine.payment.Coin;
import com.epam.training.payment_machine.ticket.Ticket;

public interface PaymentMachine {
	List<Ticket> getKnownTickets();

	void selectTicket(int ticketId) throws IllegalMachineStateException, TicketNotFoundException;

	boolean hasSelectedTicket();

	Map<Coin, Integer> putCoin(Coin coin);

	int getPaidAmount();

	String getTicketDetails() throws TicketIsNotSelectedException;

	boolean isSelectedTicketPaid() throws TicketIsNotSelectedException;

	int getSelectedTicketPrice() throws TicketIsNotSelectedException;
}

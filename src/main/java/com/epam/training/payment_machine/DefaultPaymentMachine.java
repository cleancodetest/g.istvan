package com.epam.training.payment_machine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epam.training.payment_machine.exception.IllegalMachineStateException;
import com.epam.training.payment_machine.exception.NotEnoughChangeException;
import com.epam.training.payment_machine.exception.TicketIsNotSelectedException;
import com.epam.training.payment_machine.exception.TicketNotFoundException;

public class DefaultPaymentMachine implements PaymentMachine {
	private Map<Integer, Ticket> tickets;
	private CoinContainer availableCoins;
	private PaymentMachineState state;
	private Payment payment;
	private Ticket selectedTicket;

	private DefaultPaymentMachine() {
		tickets = new HashMap<>();
		state = PaymentMachineState.IDLE;
	}

	public static DefaultPaymentMachine createTestPaymentMachine() {
		DefaultPaymentMachine machine = new DefaultPaymentMachine();

		for (int i = 0; i < 1000; i++) {
			Ticket t = new Ticket();
			t = TicketPriceGenerator.generateTicketPrice(t);
			machine.tickets.put(t.getId(), t);
		}

		machine.availableCoins = CoinContainer.createTestCoinContainer();

		return machine;
	}

	@Override
	public List<Ticket> getKnownTickets() {
		return new ArrayList<>(tickets.values());
	}

	private Ticket getTicketById(int ticketId) throws TicketNotFoundException {
		Ticket t = tickets.get(ticketId);
		if (t == null)
		{
			throw new TicketNotFoundException();
		}
		return t;
	}

	@Override
	public void selectTicket(int ticketId) throws IllegalMachineStateException, TicketNotFoundException {
		if (state == PaymentMachineState.IDLE) {
			selectedTicket = getTicketById(ticketId);
			if (!selectedTicket.isPaid()) {
				state = PaymentMachineState.PAYING;
				payment = new Payment(selectedTicket.getPrice());
			}
		} else {
			throw new IllegalMachineStateException(PaymentMachineState.IDLE, state);
		}
	}

	@Override
	public Map<Coin, Integer> putCoin(Coin coin) {
		Map<Coin, Integer> dropBack = new HashMap<>();
		if (state == PaymentMachineState.PAYING) {
			payment.addCoinValue(coin.getValue());
			availableCoins.incrementCoinInTray(coin);

			if (payment.isMoneyEnough()) {

				int coinToReturn = payment.getReturnAmount();
				try {
					dropBack.putAll(availableCoins.doReturnCoins(coinToReturn));
					selectedTicket.setPaid(true);
				} catch (NotEnoughChangeException e) {
					// give back all money, the ticket is not paid
				}
				state = PaymentMachineState.IDLE;
			}
		}
		else
		{
			dropBack.put(coin, 1);
		}
		return dropBack;
	}

	@Override
	public int getPaidAmount() {
		return payment.getPaidAmount();
	}

	@Override
	public boolean hasSelectedTicket() {
		return (selectedTicket != null);
	}

	@Override
	public String getTicketDetails() throws TicketIsNotSelectedException {
		if (!hasSelectedTicket()) {
			throw new TicketIsNotSelectedException();
		}
		return selectedTicket.toString();
	}

	@Override
	public boolean isSelectedTicketPaid() throws TicketIsNotSelectedException {
		if (!hasSelectedTicket()) {
			throw new TicketIsNotSelectedException();
		}
		return selectedTicket.isPaid();
	}

	@Override
	public int getSelectedTicketPrice() throws TicketIsNotSelectedException {
		if (!hasSelectedTicket()) {
			throw new TicketIsNotSelectedException();
		}
		return selectedTicket.getPrice();
	}

}
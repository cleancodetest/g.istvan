package com.epam.training.payment_machine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentMachine {
	private Map<Integer, Ticket> tickets;
	private CoinContainer availableCoins;
	private PaymentMachineState state;
	private Payment payment;

	private PaymentMachine() {
		tickets = new HashMap<>();
		state = PaymentMachineState.IDLE;
	}

	public static PaymentMachine createTestPaymentMachine() {
		PaymentMachine machine = new PaymentMachine();

		for (int i = 0; i < 1000; i++) {
			Ticket t = new Ticket();
			t = TicketPriceGenerator.generateTicketPrice(t);
			machine.tickets.put(t.getId(), t);
		}

		machine.availableCoins = CoinContainer.createTestCoinContainer();

		return machine;
	}

	public List<Ticket> getKnownTickets() {
		return new ArrayList<>(tickets.values());
	}

	protected Ticket getTicketById(int ticketId) {
		return tickets.get(ticketId);
	}

	public void selectTicket(int ticketId) throws IllegalMachineStateTransitionException{
		if (state == PaymentMachineState.IDLE) {
			payment = new Payment(getTicketById(ticketId));
			if (!payment.isPaid()) {
				state = PaymentMachineState.PAYING;
			}
		}else{
			throw new IllegalMachineStateTransitionException();
		}
	}

	public Map<Coin, Integer> putCoin(Coin coin) {
		Map<Coin, Integer> dropBack = new HashMap<>();
		if (state == PaymentMachineState.PAYING) {
			payment.addCoinValue(coin.getValue());
			availableCoins.incrementCoin(coin);

			if (payment.isPaymentPayed()) {
				state = PaymentMachineState.IDLE;
				int coinToReturn = payment.getReturnAmount();
				dropBack.putAll(calculateReturnCoins(coinToReturn));
			}
		}
		else
		{
			dropBack.put(coin, 1);
		}
		return dropBack;
	}

	protected Map<Coin, Integer> calculateReturnCoins(int coinAmount) {
		Map<Coin, Integer> dropBack = new HashMap<>();
		while (coinAmount >= 5) {
			Coin coin = availableCoins.getBiggestCoinInAmount(coinAmount);
			if (coin != null) {
				coinAmount -= coin.getValue();
				availableCoins.decrementCoin(coin);
				Integer prevValue = dropBack.get(coin);
				prevValue = prevValue == null ? new Integer(0) : prevValue;
				dropBack.put(coin, 1 + prevValue);
			} else {
				break;
			}
		}
		return dropBack;
	}

	public Ticket getSelectedTicket() {
		return payment.getTicket();
	}

	public int getPaidAmount() {
		return payment.getPaidAmount();
	}

}

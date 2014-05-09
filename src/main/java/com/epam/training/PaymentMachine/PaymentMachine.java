package com.epam.training.PaymentMachine;

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
			machine.tickets.put(t.getId(), TicketPriceGenerator.generateTicketPrice(t));
		}

		machine.availableCoins = CoinContainer.createTestCoinContainer();

		return machine;
	}

	public List<Ticket> getKnownTickets() {
		return new ArrayList<Ticket>(tickets.values());
	}

	protected Ticket getTicketById(int ticketId) {
		return tickets.get(ticketId);
	}

	public void selectTicket(int ticketId) {
		if (state == PaymentMachineState.IDLE) {
			payment = new Payment(getTicketById(ticketId));
			if (!payment.isPaid()) {
				state = PaymentMachineState.PAYING;
			}
		}
	}

	public Map<Integer, Integer> putCoin(int coinType) {
		Map<Integer, Integer> dropBack = new HashMap<>();
		if (state == PaymentMachineState.PAYING) {
			payment.addPayment(coinType);
			availableCoins.incrementCoin(coinType);

			if (payment.isPaymentPayed()) {
				state = PaymentMachineState.IDLE;
				int coinToReturn = payment.getReturnAmount();
				dropBack.putAll(calculateReturnCoins(coinToReturn));
			}
		}
		else
		{
			dropBack.put(coinType, 1);
		}
		return dropBack;
	}

	protected Map<Integer, Integer> calculateReturnCoins(int coinAmount) {
		Map<Integer, Integer> dropBack = new HashMap<>();
		while (coinAmount >= 5) {
			int type = availableCoins.getBiggestCoinTypeInAmount(coinAmount);
			if (type != 0) {
				coinAmount -= type;
				availableCoins.decrementCoin(type);
				Integer prevValue = dropBack.get(type);
				prevValue = prevValue == null ? new Integer(0) : prevValue;
				dropBack.put(type, 1 + prevValue);
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

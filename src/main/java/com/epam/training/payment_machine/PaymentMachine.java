package com.epam.training.payment_machine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epam.training.payment_machine.exception.IllegalMachineStateException;
import com.epam.training.payment_machine.exception.NotEnoughChangeException;
import com.epam.training.payment_machine.exception.TicketNotFoundException;

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

	protected Ticket getTicketById(int ticketId) throws TicketNotFoundException {
		Ticket t = tickets.get(ticketId);
		if (t == null)
		{
			throw new TicketNotFoundException();
		}
		return t;
	}

	public void selectTicket(int ticketId) throws IllegalMachineStateException, TicketNotFoundException {
		if (state == PaymentMachineState.IDLE) {
			payment = new Payment(getTicketById(ticketId));
			if (!payment.isPaid()) {
				state = PaymentMachineState.PAYING;
			}
		} else {
			throw new IllegalMachineStateException(PaymentMachineState.IDLE, state);
		}
	}

	public Map<Coin, Integer> putCoin(Coin coin) {
		Map<Coin, Integer> dropBack = new HashMap<>();
		if (state == PaymentMachineState.PAYING) {
			payment.addCoinValue(coin.getValue());
			availableCoins.incrementCoin(coin);

			if (payment.isMoneyEnough()) {
				
				if (availableCoins.canGiveChangeBack(payment.getRemainedAmount())){
					int coinToReturn = payment.getReturnAmount();
					try {
						dropBack.putAll(availableCoins.doReturnCoins(coinToReturn));
					} catch (NotEnoughChangeException e) {
						assert false : "Can't be thrown";
					}	
				}else{
					// give back all money
				}
				
				state = PaymentMachineState.IDLE;
				payment = null;
			}
		}
		else
		{
			dropBack.put(coin, 1);
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

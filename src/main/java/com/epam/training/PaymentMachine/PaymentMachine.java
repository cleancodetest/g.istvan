package com.epam.training.PaymentMachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentMachine {
	private Map<Integer, Ticket> tickets;
	private CoinContainer availableCoins;
	private Ticket selectedTicket;
	private PaymentMachineState state;
	private int payedAmount;

	private PaymentMachine() {
		tickets = new HashMap<>();
		selectedTicket = null;
		state = PaymentMachineState.IDLE;
		payedAmount = 0;
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

	public Ticket getTicketById(int ticketId) {
		Ticket t = tickets.get(ticketId);
		return t;
	}

	public void selectTicket(int ticketId) {
		if (state == PaymentMachineState.IDLE) {
			this.selectedTicket = getTicketById(ticketId);
			if (selectedTicket != null && !selectedTicket.isPaid()) {
				state = PaymentMachineState.PAYING;
			}
		}
	}

	public Map<Integer, Integer> putCoin(int coinType) {
		Map<Integer, Integer> dropBack = new HashMap<>();
		if (state == PaymentMachineState.PAYING) {
			payedAmount += coinType;
			availableCoins.incrementCoin(coinType);

			if (selectedTicket.getPrice() <= payedAmount) {
				state = PaymentMachineState.IDLE;
				selectedTicket.setPaid(true);
				int coinToReturn = payedAmount - selectedTicket.getPrice();
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
				Integer prevValue = dropBack.get(type) ;
				prevValue = prevValue == null? new Integer(0) : prevValue ;
				dropBack.put(type, 1 + prevValue);
			} else {
				break;
			}
		}
		return dropBack;
	}

}

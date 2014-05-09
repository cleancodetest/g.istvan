package com.epam.training.payment_machine;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

public class PaymentMachineTest {

	@Test
	public void getKnownTicketsTest() {
		PaymentMachine pm = PaymentMachine.createTestPaymentMachine();
		List<Ticket> tickets = pm.getKnownTickets();

		assertEquals(tickets.size(), 1000);
	}

	@Test
	public void getTicketByIdWhenTicketIsContained() {
		PaymentMachine pm = PaymentMachine.createTestPaymentMachine();
		List<Ticket> tickets = pm.getKnownTickets();
		Ticket expected = tickets.get(0);

		Ticket actual = pm.getTicketById(expected.getId());

		assertEquals(actual, expected);
	}

	@Test
	public void getTicketByIdWhenTicketIsNotContained() {
		PaymentMachine pm = PaymentMachine.createTestPaymentMachine();

		Ticket actual = pm.getTicketById(9999999);

		assertNull(actual);
	}

	@Test
	public void calculateReturnCoinsTest() {
		PaymentMachine pm = PaymentMachine.createTestPaymentMachine();
		Map<Coin, Integer> expected = new HashMap<>();
		expected.put(Coin.BANKNOTE_5000, 1);
		Map<Coin, Integer> actual = pm.calculateReturnCoins(5000);

		assertEquals(actual, expected);
	}

}

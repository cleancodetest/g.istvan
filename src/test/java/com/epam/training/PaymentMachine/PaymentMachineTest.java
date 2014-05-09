package com.epam.training.PaymentMachine;

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
		List<Ticket> tickets = pm.getKnownTickets();

		Ticket actual = pm.getTicketById(9999999);

		assertNull(actual);
	}

	@Test
	public void calculateReturnCoinsTest() {
		PaymentMachine pm = PaymentMachine.createTestPaymentMachine();
		Map<Integer, Integer> expected = new HashMap<>();
		expected.put(5000, 1);
		Map<Integer, Integer> actual = pm.calculateReturnCoins(5000);

	}

}

package com.epam.training.payment_machine;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import java.util.List;

import org.testng.annotations.Test;

import com.epam.training.payment_machine.exception.TicketNotFoundException;

public class PaymentMachineTest {

	@Test
	public void getKnownTicketsTest() {
		PaymentMachine pm = PaymentMachine.createTestPaymentMachine();
		List<Ticket> tickets = pm.getKnownTickets();

		assertEquals(tickets.size(), 1000);
	}

	@Test
	public void getTicketByIdWhenTicketIsContained() throws TicketNotFoundException {
		PaymentMachine pm = PaymentMachine.createTestPaymentMachine();
		List<Ticket> tickets = pm.getKnownTickets();
		Ticket expected = tickets.get(0);

		Ticket actual = pm.getTicketById(expected.getId());

		assertEquals(actual, expected);
	}

	@Test
	public void getTicketByIdWhenTicketIsNotContained() throws TicketNotFoundException {
		PaymentMachine pm = PaymentMachine.createTestPaymentMachine();

		Ticket actual = pm.getTicketById(9999999);

		assertNull(actual);
	}
}

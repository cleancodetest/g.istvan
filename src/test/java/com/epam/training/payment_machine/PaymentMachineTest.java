package com.epam.training.payment_machine;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.epam.training.payment_machine.exception.IllegalMachineStateException;
import com.epam.training.payment_machine.exception.TicketIsNotSelectedException;
import com.epam.training.payment_machine.exception.TicketNotFoundException;
import com.epam.training.payment_machine.ticket.Ticket;

public class PaymentMachineTest {

	private PaymentMachine pm;

	@BeforeTest
	public void ceateTestPaymentMachine() {
		pm = DefaultPaymentMachine.createTestPaymentMachine();
	}

	@Test
	public void getKnownTicketsTest() {
		//given
		//when
		List<Ticket> tickets = pm.getKnownTickets();

		//then
		assertEquals(tickets.size(), 1000);
	}

	@Test
	public void selectTicketWhenTicketIsContainedShouldEqualPrice() throws TicketNotFoundException, TicketIsNotSelectedException, IllegalMachineStateException {
		//given
		List<Ticket> tickets = pm.getKnownTickets();
		Ticket t = tickets.get(0);
		int expected = t.getPrice();

		//when
		pm.selectTicket(t.getId());
		int actual = pm.getSelectedTicketPrice();

		//then
		assertEquals(actual, expected);
	}

	@Test(expectedExceptions = TicketNotFoundException.class)
	public void selectTicketWhenIsNotContainedShouldException() throws TicketNotFoundException, IllegalMachineStateException {
		//given
		pm.selectTicket(0);
	}
}

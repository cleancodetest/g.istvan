package com.epam.training.payment_machine.ticket;

import org.testng.annotations.Test;

import com.epam.training.payment_machine.ticket.Ticket;
import com.epam.training.payment_machine.ticket.TicketPriceGenerator;

import static org.testng.Assert.*;

public class TicketPriceGeneratorTest {

	@Test
	public void priceGeneratorTest() {

		for (int i = 0; i <= 10000; i++) {
			Ticket ticket = new Ticket();

			TicketPriceGenerator.generateTicketPrice(ticket);

			assertTrue(ticket.getPrice() >= 200, "greater or equals than 200");
			assertTrue(ticket.getPrice() < 5000, "less than 5000");
		}
	}
}

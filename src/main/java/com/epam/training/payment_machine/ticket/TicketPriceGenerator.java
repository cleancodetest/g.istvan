package com.epam.training.payment_machine.ticket;

import java.util.Random;

public class TicketPriceGenerator {
	public static Ticket generateTicketPrice(Ticket ticket) {
		Random random = new Random();

		ticket.setPrice(random.nextInt(4800) + 200);
		
		return ticket;
	}
}

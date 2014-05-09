package com.epam.training.PaymentMachine;

import java.util.Random;

public class TicketPriceGenerator {
	public static Ticket generateTicketPrice(Ticket ticket) {
		Random random = new Random();

		ticket.setPrice(random.nextInt(5000) + 200);
		
		return ticket;
	}
}

package com.epam.training.PaymentMachine;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TicketPriceGeneratorTest {
	
	@Test
	public void priceGeneratorTest(){
		
		for (int i=0;i<=10000;i++){
			Ticket ticket = new Ticket();
			
			TicketPriceGenerator.generateTicketPrice(ticket);
			
			assertTrue(ticket.getPrice()>=200, "greater or equals than 200");
			assertTrue(ticket.getPrice()<5200, "less than 5000");
		}
	}
}

package com.epam.training.payment_machine;

import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static org.mockito.Mockito.*;

public class PaymentTest {
	
	@Test
	public void isMoneyEnoughTest(){
		//given
		Ticket t = mock(Ticket.class);
		when(t.getPrice()).thenReturn(50);
		Payment p =  new Payment(t);
		p.addCoinValue(50);
		boolean expected = true;
		
		//when
		boolean actual = p.isMoneyEnough();
		
		//then
		assertEquals(actual, expected);
		
	}
	
}

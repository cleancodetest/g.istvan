package com.epam.training.payment_machine;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.epam.training.payment_machine.payment.Payment;

public class PaymentTest {
	
	@Test
	public void isMoneyEnoughTestWhenEnoughShouldTrue(){
		//given
		Payment p =  new Payment(50);
		p.addCoinValue(50);
		boolean expected = true;
		
		//when
		boolean actual = p.isMoneyEnough();
		
		//then
		assertEquals(actual, expected);
		
	}
	
	@Test
	public void isMoneyEnoughTestWhenNotEnoughShouldFalse(){
		//given
		Payment p =  new Payment(50);
		p.addCoinValue(20);
		boolean expected = false;
		
		//when
		boolean actual = p.isMoneyEnough();
		
		//then
		assertEquals(actual, expected);
		
	}
	
}

package com.epam.training.payment_machine;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class CoinContainerTest {

	@Test
	public void getBiggestCoinTypeAmountTestWhenCoinTypeShouldCoinType(){
		CoinContainer cc = CoinContainer.createTestCoinContainer();
		
		int expected = 5000;
		int actual = cc.getBiggestCoinTypeInAmount(5000);
		
		assertEquals(actual, expected);
	}
	
	@Test
	public void getBiggestCoinTypeAmountTestWhenBiggerCoinTypeShouldCoinType(){
		CoinContainer cc = CoinContainer.createTestCoinContainer();
		
		int expected = 5000;
		int actual = cc.getBiggestCoinTypeInAmount(6000);
		
		assertEquals(actual, expected);
	}
}

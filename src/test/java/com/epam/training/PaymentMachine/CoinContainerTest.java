package com.epam.training.PaymentMachine;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

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

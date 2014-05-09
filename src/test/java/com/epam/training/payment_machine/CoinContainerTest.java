package com.epam.training.payment_machine;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class CoinContainerTest {

	@Test
	public void getBiggestCoinTypeAmountTestWhenCoinTypeShouldCoinType(){
		CoinContainer cc = CoinContainer.createTestCoinContainer();
		
		Coin expected = Coin.BANKNOTE_5000;
		Coin actual = cc.getBiggestCoinInAmount(5000);
		
		assertEquals(actual, expected);
	}
	
	@Test
	public void getBiggestCoinTypeAmountTestWhenBiggerCoinTypeShouldCoinType(){
		CoinContainer cc = CoinContainer.createTestCoinContainer();
		
		Coin expected = Coin.BANKNOTE_5000;
		Coin actual = cc.getBiggestCoinInAmount(6000);
		
		assertEquals(actual, expected);
	}
}

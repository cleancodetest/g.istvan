package com.epam.training.payment_machine;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.epam.training.payment_machine.exception.NotEnoughChangeException;
import com.epam.training.payment_machine.payment.Coin;
import com.epam.training.payment_machine.payment.CoinContainer;

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
	
	@Test
	public void calculateReturnCoinsTest() throws NotEnoughChangeException {
		CoinContainer cc = CoinContainer.createTestCoinContainer();
		
		Map<Coin, Integer> expected = new HashMap<>();
		expected.put(Coin.BANKNOTE_5000, 1);
		Map<Coin, Integer> actual = cc.doReturnCoins(5000);

		assertEquals(actual, expected);
	}
}

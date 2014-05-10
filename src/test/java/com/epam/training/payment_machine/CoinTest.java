package com.epam.training.payment_machine;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class CoinTest {

	@Test
	public void isTheSmallestValueCoinTestWhen5ShouldTrue() {
		// given
		Coin coin = Coin.BANKNOTE_5;
		boolean expected = true;

		// when
		boolean actual = coin.isTheSmallestValueCoin();

		// then
		assertEquals(actual, expected);

	}

	@Test
	public void isTheSmallestValueCoinTestWhenNot5ShouldFalse() {
		// given
		Coin coin = Coin.BANKNOTE_10;
		boolean expected = false;

		// when
		boolean actual = coin.isTheSmallestValueCoin();

		// then
		assertEquals(actual, expected);

	}

	@Test
	public void valueTestWhenBanknote50Should50() {
		// given
		int expected = 50;
		// when
		int actual = Coin.BANKNOTE_50.getValue();
		// then
		assertEquals(actual, expected);
	}

}

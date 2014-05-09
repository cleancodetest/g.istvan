package com.epam.training.payment_machine;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class CoinContainer {
	private Map<Coin, Integer> coinTray;

	private CoinContainer() {
		coinTray = new TreeMap<>(Collections.reverseOrder());
	}

	public void incrementCoin(Coin coin) {
		Integer coinsCount = coinTray.get(coin);
		coinsCount += 1;
		coinTray.put(coin, coinsCount);
	}

	public void decrementCoin(Coin coin) {
		Integer coinsCount = coinTray.get(coin);
		coinsCount -= 1;
		coinTray.put(coin, coinsCount);
	}

	public static CoinContainer createTestCoinContainer() {
		CoinContainer container = new CoinContainer();

		for (Coin c : Coin.values()){
			container.coinTray.put(c, 50);	
		}

		return container;
	}

	public Coin getBiggestCoinInAmount(int amount) {
		for (Entry<Coin, Integer> coinAndCount : coinTray.entrySet()) {
			Coin coin = coinAndCount.getKey();
			int coinCount = coinAndCount.getValue();
			if (coin.getValue() <= amount && coinCount > 0) {
				return coin;
			}
		}
		return null;
	}
}

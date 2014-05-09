package com.epam.training.PaymentMachine;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class CoinContainer {
	private Map<Integer, Integer> coinTray;

	private CoinContainer() {
		coinTray = new TreeMap<>(Collections.reverseOrder());
	}

	public void incrementCoin(int coinType) {
		Integer coinsCount = coinTray.get(coinType);
		coinsCount = coinsCount == null ? new Integer(0) : coinsCount;
		coinsCount += 1;
		coinTray.put(coinType, coinsCount);
	}

	public void decrementCoin(int coinType) {
		Integer coinsCount = coinTray.get(coinType);
		coinsCount -= 1;
		coinTray.put(coinType, coinsCount);
	}

	public static CoinContainer createTestCoinContainer() {
		CoinContainer container = new CoinContainer();

		container.coinTray.put(5, 50);
		container.coinTray.put(10, 50);
		container.coinTray.put(20, 50);
		container.coinTray.put(50, 50);
		container.coinTray.put(100, 50);
		container.coinTray.put(500, 50);
		container.coinTray.put(1000, 50);
		container.coinTray.put(2000, 50);
		container.coinTray.put(5000, 50);
		container.coinTray.put(10000, 50);
		container.coinTray.put(20000, 50);

		return container;
	}

	public int getBiggestCoinTypeInAmount(int amount) {
		for (Entry<Integer, Integer> coinAndCount : coinTray.entrySet()) {
			int coinType = coinAndCount.getKey();
			int coinCount = coinAndCount.getValue();
			if (coinType <= amount && coinCount > 0) {
				return coinType;
			}
		}
		return 0;
	}
}

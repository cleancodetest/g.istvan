package com.epam.training.payment_machine.payment;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.epam.training.payment_machine.exception.NotEnoughChangeException;

public class CoinContainer {
	private Map<Coin, Integer> coinTray;

	private CoinContainer() {
		coinTray = new TreeMap<>(Collections.reverseOrder());
	}

	public void incrementCoinInTray(Coin coin) {
		Integer coinsCount = coinTray.get(coin);
		coinsCount += 1;
		coinTray.put(coin, coinsCount);
	}

	public void decrementCoinInTray(Coin coin) {
		Integer coinsCount = coinTray.get(coin);
		coinsCount -= 1;
		coinTray.put(coin, coinsCount);
	}

	public static CoinContainer createTestCoinContainer() {
		CoinContainer container = new CoinContainer();

		for (Coin c : Coin.values()) {
			container.coinTray.put(c, 50);
		}

		return container;
	}

	protected Coin getBiggestCoinInAmount(int amount) {
		for (Entry<Coin, Integer> coinAndCount : coinTray.entrySet()) {
			Coin coin = coinAndCount.getKey();
			int coinCount = coinAndCount.getValue();
			if (coin.getValue() <= amount && coinCount > 0) {
				return coin;
			}
		}
		return null;
	}

	protected boolean hasMore(Coin coin) {
		Integer value = coinTray.get(coin);
		return value > 0;
	}

	public Map<Coin, Integer> doReturnCoins(int coinAmount) throws NotEnoughChangeException {
		Map<Coin, Integer> dropBack = new HashMap<>();
		Map<Coin, Integer> coinTrayOriginal = new HashMap<>(coinTray);
		
		while (coinAmount >= 5) {
			Coin coin = getBiggestCoinInAmount(coinAmount);
			if (coin != null && hasMore(coin)) {
				coinAmount -= coin.getValue();
				decrementCoinInTray(coin);
				Integer prevValue = dropBack.get(coin);
				prevValue = prevValue == null ? new Integer(0) : prevValue;
				dropBack.put(coin, 1 + prevValue);
			} else {
				if (coin.isTheSmallestValueCoin()) {
					coinTray = coinTrayOriginal;
					throw new NotEnoughChangeException();
				}
			}
		}
		return dropBack;
	}
}

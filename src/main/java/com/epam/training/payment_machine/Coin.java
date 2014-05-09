package com.epam.training.payment_machine;

public enum Coin {
	BANKNOTE_5(5),
	BANKNOTE_10(10),
	BANKNOTE_20(20),
	BANKNOTE_50(20),
	BANKNOTE_100(100),
	BANKNOTE_200(200),
	BANKNOTE_500(500),
	BANKNOTE_1000(1000),
	BANKNOTE_2000(2000),
	BANKNOTE_5000(5000),
	BANKNOTE_10000(10000),
	BANKNOTE_20000(20000);

	private int value;

	public int getValue() {
		return value;
	}

	private Coin(int value) {
		this.value = value;
	}

	public boolean compare(int i) {
		return value == i;
	}

	public static Coin getCoinByValue(int value) throws InvalidCoinException
	{
		Coin[] values = Coin.values();
		for (int i = 0; i < values.length; i++)
		{
			if (values[i].compare(value))
				return values[i];
		}
		throw new InvalidCoinException();
	}

	@Override
	public String toString() {
		return "Banknote " + value;
	}

}
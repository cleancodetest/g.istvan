package com.epam.training.payment_machine;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.epam.training.payment_machine.exception.IllegalMachineStateException;
import com.epam.training.payment_machine.exception.TicketIsNotSelectedException;
import com.epam.training.payment_machine.exception.TicketNotFoundException;

public class Main {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static PaymentMachine pm = DefaultPaymentMachine.createTestPaymentMachine();

	public static void main(String[] args) throws IllegalMachineStateException, TicketIsNotSelectedException {
		System.out.println("----------------------");
		System.out.println("PaymentMachine created");
		System.out.println();
		boolean readNext = true;

		do {
			int parkingTicketId = 0;
			while (parkingTicketId == 0) {
				parkingTicketId = readParkingTicketId();
				try {
					System.out.printf("Entered parking ticket id is %d", parkingTicketId);
					System.out.println();

					pm.selectTicket(parkingTicketId);
				} catch (TicketNotFoundException e) {
					System.out.println("This id is not exist, please enter other id!");
					parkingTicketId = 0;
				}
			}

			System.out.print("Parking ticket is: ");
			System.out.println(pm.getTicketDetails());

			Map<Coin, Integer> cashBack = new HashMap<>();
			while (!pm.isSelectedTicketPaid()) {
				Coin insertedCoin = readCoin();
				cashBack = pm.putCoin(insertedCoin);
				if (pm.hasSelectedTicket()){
					System.out.printf("payment: %d / %d %n", pm.getPaidAmount(), pm.getSelectedTicketPrice());
				}
			}
			System.out.println("This ticket is paid");
			if (cashBack.size() > 0) {
				System.out.println("Change: ");
				for (Entry<Coin, Integer> entry : cashBack.entrySet()) {
					System.out.printf("banknote \"%d\"  =  %d %n", entry.getKey().getValue(), entry.getValue());
				}
			}
			readNext = readBool("Would you like to pay for an other ticket? (true/false)");
		} while (readNext);
	}

	private static int readParkingTicketId() {
		int parkingTicketId = 0;
		while (parkingTicketId == 0)
		{
			try {
				System.out.println("Please enter your valid parking ticket id!");
				String s = br.readLine();
				parkingTicketId = Integer.parseInt(s);
			} catch (Exception e) {
				System.out.println("Invalid ticket id.");
			}
		}
		return parkingTicketId;
	}

	private static Coin readCoin() {
		Coin nextCoin = null;
		while (nextCoin == null)
		{
			try {
				System.out.println("Please enter a coin");
				String s = br.readLine();
				nextCoin = Coin.getCoinByValue(Integer.parseInt(s));
			} catch (Exception e) {
				System.out.println("Invalid coin.");
			}
		}
		return nextCoin;
	}

	private static boolean readBool(String message) {
		System.out.println(message);
		Boolean b = null;
		while (b == null)
		{
			try {
				String s = br.readLine();
				b = Boolean.parseBoolean(s);
			} catch (Exception e) {
				System.out.println("Invalid input.");
			}
		}
		return b;
	}

}

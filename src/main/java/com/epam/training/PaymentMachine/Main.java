package com.epam.training.PaymentMachine;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static PaymentMachine pm = PaymentMachine.createTestPaymentMachine();
	
	public static void main(String[] args) {

		System.out.println("----------------------");
		System.out.println("PaymentMachine created");
		System.out.println();

		int parkingTicketId = readParkingTicketId() ;
		System.out.printf("Entered parking ticket id is %d", parkingTicketId);
		System.out.println();

		Ticket t = pm.getTicketById(parkingTicketId);
		System.out.print("Parking ticket is: ");
		System.out.println(t);
		
		while (!t.isPaid()){
			//pay
		}
		System.out.println("This ticket is paid");
		
		System.out.print("");
		
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
			if (pm.getTicketById(parkingTicketId) == null){
				System.out.println("This id is not exist, please enter other id!");
				parkingTicketId = 0;
			}
			
		}
		return parkingTicketId;
	}

}

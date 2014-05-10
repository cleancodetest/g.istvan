package com.epam.training.payment_machine;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.epam.training.payment_machine.exception.IllegalMachineStateException;
import com.epam.training.payment_machine.exception.TicketNotFoundException;

public class PaymentMachineTest {

	private PaymentMachine pm;

	@BeforeTest
	public void ceateTestPaymentMachine() {
		pm = DefaultPaymentMachine.createTestPaymentMachine();
	}

	@Test(expectedExceptions = TicketNotFoundException.class)
	public void selectTicketWhenIsNotContainedShouldException() throws TicketNotFoundException, IllegalMachineStateException {
		//given
		pm.selectTicket(0);
	}
}

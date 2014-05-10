package com.epam.training.payment_machine.exception;

import com.epam.training.payment_machine.PaymentMachineState;

public class IllegalMachineStateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7559070351657640251L;
	
	public IllegalMachineStateException(PaymentMachineState shouldBeIn, PaymentMachineState butIn){
		super(String.format("The machine is in %s state but it should be in %s state", butIn, shouldBeIn));
	}
}

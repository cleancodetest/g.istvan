package com.epam.training.payment_machine;

import static org.testng.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.epam.training.payment_machine.exception.IllegalMachineStateException;
import com.epam.training.payment_machine.exception.NotEnoughChangeException;
import com.epam.training.payment_machine.exception.TicketNotFoundException;
import com.epam.training.payment_machine.payment.Coin;
import com.epam.training.payment_machine.payment.CoinContainer;
import com.epam.training.payment_machine.ticket.Ticket;

public class DefaultPaymentMachineTest {

	@Test(expectedExceptions = TicketNotFoundException.class)
	public void selectTicketTestWhenIsNotContainedShouldException() throws TicketNotFoundException, IllegalMachineStateException {
		// given
		PaymentMachine pm = DefaultPaymentMachine.createTestPaymentMachine();
		pm.selectTicket(0);
	}

	@Test
	public void selectTicketTestWhenIsContainedAndNotPaid() throws IllegalMachineStateException, TicketNotFoundException {
		// given
		DefaultPaymentMachine pm = new DefaultPaymentMachine();
		final Ticket ticket = Mockito.mock(Ticket.class);
		Map<Integer, Ticket> tickets = new HashMap<>();
		tickets.put(1, ticket);
		pm.setTickets(tickets);

		// when
		pm.selectTicket(1);

		// then
		assertTrue(pm.hasSelectedTicket());
	}

	@Test
	public void selectTicketTestWhenIsContainedAndPaid() throws IllegalMachineStateException, TicketNotFoundException {
		// given
		DefaultPaymentMachine pm = new DefaultPaymentMachine();
		final Ticket ticket = Mockito.mock(Ticket.class);
		when(ticket.isPaid()).thenReturn(true);
		Map<Integer, Ticket> tickets = new HashMap<>();
		tickets.put(1, ticket);
		pm.setTickets(tickets);

		// when
		pm.selectTicket(1);

		// then
		assertTrue(pm.hasSelectedTicket());
	}

	@Test(expectedExceptions = IllegalMachineStateException.class)
	public void selectTicketTestWhenIncorrectStateShouldException() throws IllegalMachineStateException, TicketNotFoundException {
		// given
		DefaultPaymentMachine pm = new DefaultPaymentMachine();
		final Ticket ticket = Mockito.mock(Ticket.class);
		Map<Integer, Ticket> tickets = new HashMap<>();
		tickets.put(1, ticket);
		pm.setTickets(tickets);

		// when
		pm.selectTicket(1);
		pm.selectTicket(1);

		// then
		Assert.fail();
	}

	@Test
	public void putCoinTestWhenMachineIsIDLEShouldThrowBackMoney() {
		// given
		DefaultPaymentMachine pm = new DefaultPaymentMachine();
		Map<Coin, Integer> expected = new HashMap<>();
		expected.put(Coin.BANKNOTE_10, 1);

		Map<Coin, Integer> actual = pm.putCoin(Coin.BANKNOTE_10);

		assertEquals(actual, expected);
	}

	@Test
	public void putCoinWhenPaymentLessThanPrice() throws IllegalMachineStateException, TicketNotFoundException {
		// given
		DefaultPaymentMachine pm = new DefaultPaymentMachine();
		pm.setAvalilableCoins(Mockito.mock(CoinContainer.class));
		final Ticket ticket = Mockito.mock(Ticket.class);
		when(ticket.getId()).thenReturn(1);
		when(ticket.getPrice()).thenReturn(50);
		when(ticket.isPaid()).thenReturn(false);
		Map<Integer, Ticket> tickets = new HashMap<>();
		tickets.put(1, ticket);
		pm.setTickets(tickets);
		pm.selectTicket(1);

		// when
		Map<Coin, Integer> actual = pm.putCoin(Coin.BANKNOTE_5);

		// then
		assertEquals(actual.size(), 0);
	}

	@Test
	public void putCoinWhenPaymentExactlyThePrice() throws IllegalMachineStateException, TicketNotFoundException {
		// given
		DefaultPaymentMachine pm = new DefaultPaymentMachine();
		pm.setAvalilableCoins(Mockito.mock(CoinContainer.class));
		final Ticket ticket = Mockito.mock(Ticket.class);
		when(ticket.getId()).thenReturn(1);
		when(ticket.getPrice()).thenReturn(50);
		when(ticket.isPaid()).thenReturn(false);
		Map<Integer, Ticket> tickets = new HashMap<>();
		tickets.put(1, ticket);
		pm.setTickets(tickets);
		pm.selectTicket(1);

		// when
		Map<Coin, Integer> actual = pm.putCoin(Coin.BANKNOTE_50);

		// then
		assertEquals(actual.size(), 0);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void putCoinWhenPaymentMoreThePrice() throws IllegalMachineStateException, TicketNotFoundException, NotEnoughChangeException {
		// given
		DefaultPaymentMachine pm = new DefaultPaymentMachine();

		final Ticket ticket = Mockito.mock(Ticket.class);
		when(ticket.getId()).thenReturn(1);
		when(ticket.getPrice()).thenReturn(50);
		when(ticket.isPaid()).thenReturn(false);
		Map<Integer, Ticket> tickets = new HashMap<>();
		tickets.put(1, ticket);

		Map<Coin, Integer> expected = new HashMap<>();

		CoinContainer coinContainer = Mockito.mock(CoinContainer.class);
		when(coinContainer.doReturnCoins(anyInt())).thenThrow(NotEnoughChangeException.class).thenReturn(expected);
		pm.setAvalilableCoins(coinContainer);

		pm.setTickets(tickets);
		pm.selectTicket(1);

		// when
		Map<Coin, Integer> actual = pm.putCoin(Coin.BANKNOTE_100);

		// then
		assertEquals(actual, expected);
	}
	

	
	
	

}

package com.epam.training.payment_machine.ticket;

import java.util.concurrent.atomic.AtomicInteger;

public class Ticket {
	private static AtomicInteger nextId = new AtomicInteger(1);
	private int id;
	private int price;
	private boolean isPaid;
	
	public Ticket() {
		this.id = nextId.getAndIncrement();
		this.isPaid = false;
	}
	
	public Ticket(int price) {
		this();
		this.price = price;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public boolean isPaid() {
		return isPaid;
	}
	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}
	
	@Override
	public String toString() {
		return "Ticket [id=" + id + ", price=" + price + ", isPaid=" + isPaid + "]";
	}
}

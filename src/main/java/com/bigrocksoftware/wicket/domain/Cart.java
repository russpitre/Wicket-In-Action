package com.bigrocksoftware.wicket.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Cheese> cheeses = new ArrayList<Cheese>();
	private Address billingAddress = new Address();
	
	public List<Cheese> getCheeses() {
		return cheeses;
	}

	public void setCheeses(List<Cheese> cheeses) {
		this.cheeses = cheeses;
	}
	
	public Address getBillingAddress() {
		return billingAddress;
	}
	
	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}
	
	public double getTotal() {
		double total = 0.0;
		for(Cheese ch : cheeses){
			total += ch.getPrice();
		}
		return total;
	}
}

package com.bigrocksoftware.wicket.session;

import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebSession;

import com.bigrocksoftware.wicket.domain.Cart;

public class CheesrSession extends WebSession {

	private static final long serialVersionUID = 1L;

	private Cart cart = new Cart();
	
	public CheesrSession(Request request){
		super(request);
	}
	
	public Cart getCart(){
		return cart;
	}
}

package com.bigrocksoftware.wicket.page;

import java.util.List;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;

import com.bigrocksoftware.wicket.CheesrApplication;
import com.bigrocksoftware.wicket.domain.Cart;
import com.bigrocksoftware.wicket.domain.Cheese;
import com.bigrocksoftware.wicket.session.CheesrSession;

public abstract class CheesrPage extends WebPage {

	public CheesrPage() {}
	
	public CheesrPage(PageParameters pageParameters){
		super(pageParameters);
	}
	
	public CheesrSession getCheesrSession(){
		return (CheesrSession)getSession();
	}
	
	public Cart getCart(){
		return getCheesrSession().getCart();
	}
	
	public List<Cheese> getCheeses() {
		return CheesrApplication.get().getCheese();
	}
	
}

package com.bigrocksoftware.wicket.page;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.link.Link;


public class CheesrDetails extends CheesrPage {

	public CheesrDetails(PageParameters pageParameters){
		
		
        add(new ShoppingCartPanel("shoppingcart", getCart()));
        add(new Link("checkout") {
			
			@Override
			public void onClick() {
				setResponsePage(new CheckOut());				
			}
			
			@Override
			public boolean isVisible(){
				return !getCart().getCheeses().isEmpty();
			}
		});
	}
}

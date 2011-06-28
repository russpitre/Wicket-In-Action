package com.bigrocksoftware.wicket.page;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;

import com.bigrocksoftware.wicket.domain.Cheese;
import com.bigrocksoftware.wicket.service.CheesrService;
import com.bigrocksoftware.wicket.service.CheesrServiceDefaultImpl;


public class CheesrDetails extends CheesrPage {

	private CheesrService cheesrService = new CheesrServiceDefaultImpl(getCheeses());
	
	/**
	 * Bookmarkable constructor
	 * 
	 */
	public CheesrDetails(PageParameters pageParameters){
		super(pageParameters);
		
		Cheese cheese = null;
		
		if(pageParameters.containsKey("name")){
			String name = pageParameters.getString("name");
			cheese = cheesrService.findByName(name);
		}
		createComponents(cheese);
	}
	
	/**
	 * Non-bookmarkable constructor
	 */
	public CheesrDetails(Cheese cheese){
		createComponents(cheese);
	}
	
	/**
	 * Create components for the details page.
	 * 
	 * @param cheese
	 */
	@SuppressWarnings({ "serial"})
	private void createComponents(Cheese cheese) {
		
		/**
		 * Detail components
		 */
		CompoundPropertyModel<Cheese> cheeseModel = new CompoundPropertyModel<Cheese>(cheese);
		setDefaultModel(cheeseModel);
		
		add(new Label("name"));
		add(new Label("description"));
		add(new Label("price"));
		
		
		
		/**
		 * Show shopping cart
		 */
        add(new ShoppingCartPanel("shoppingcart", getCart()));
        
        /**
         * Checkout button
         */
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

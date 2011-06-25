package com.bigrocksoftware.wicket.page;

import java.text.NumberFormat;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import com.bigrocksoftware.wicket.domain.Cart;
import com.bigrocksoftware.wicket.domain.Cheese;

/**
 * Panel for displaying the contents of a shopping cart. The cart
 * shows the entries and the total value of the cart. Each item
 * can be removed by the user.
 */
public class ShoppingCartPanel extends Panel {

	private static final long serialVersionUID = 1L;
	
	private Cart cart;
	
	@SuppressWarnings("serial")
	public ShoppingCartPanel(String id, Cart cart) {
		super(id);
		this.cart = cart;
		
        /**
         * Add shopping cart component
         */
        add(new ListView("cart", new PropertyModel(this, "cart.cheeses")) {
			
			@Override
			protected void populateItem(ListItem item) {
				Cheese cheese = (Cheese)item.getModelObject();				
				item.add(new Label("name", cheese.getName()));
				item.add(new Label("price", "$" + cheese.getPrice()));
				item.add(new Link("remove", item.getModel()) {
					
					@Override
					public void onClick() {
						Cheese selected = (Cheese)getModelObject();
						getCart().getCheeses().remove(selected);
					}
				});
			}
		});
        
        /**
         * Add total label
         */
        add(new Label("total", new Model(){
        	
        	@Override
        	public Object getObject(){
        		 NumberFormat nf = NumberFormat.getCurrencyInstance();
        	     return nf.format(getCart().getTotal());
        	}
        }));
	}

	public Cart getCart() {
		return cart;
	}
}

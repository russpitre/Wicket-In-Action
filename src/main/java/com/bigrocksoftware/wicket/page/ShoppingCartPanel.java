package com.bigrocksoftware.wicket.page;

import java.text.NumberFormat;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
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
	
	@SuppressWarnings({ "serial", "unchecked", "rawtypes" })
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
				final ShoppingCartPanel panel = item.findParent(ShoppingCartPanel.class);
				
				item.add(new Label("name", cheese.getName()));
				item.add(new Label("price", "$" + cheese.getPrice()));
				item.add(new AjaxFallbackLink("remove", item.getModel()) {
					
					@Override
					public void onClick(AjaxRequestTarget target) {
						Cheese selected = (Cheese)getModelObject();
						getCart().getCheeses().remove(selected);
												
						if(target != null){
							target.addComponent(target.getPage().get("shoppingcart"));
						}
					}
				});
			}
		});
        
        /**
         * Add total label
         */
        add(new Label("total", new Model<String>(){
        	
        	@Override
        	public String getObject(){
        		 NumberFormat nf = NumberFormat.getCurrencyInstance();
        	     return nf.format(getCart().getTotal());
        	}
        }));
	}

	public Cart getCart() {
		return cart;
	}
}

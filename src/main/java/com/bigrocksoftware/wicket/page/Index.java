package com.bigrocksoftware.wicket.page;

import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;

import com.bigrocksoftware.wicket.domain.Cheese;

/**
 * Homepage
 */
public class Index extends CheesrPage {

	private static final long serialVersionUID = 1L;

	private ShoppingCartPanel shoppingCartPanel;
	
    /**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameters
	 */
	@SuppressWarnings({ "serial", "rawtypes" })
    public Index(final PageParameters parameters) {
	
		/**
		 * Setup a pageable component for viewing cheeses
		 */
		@SuppressWarnings("unchecked")
		PageableListView cheeses = new PageableListView("cheeses", getCheeses(), 5) {
			
			@Override
			protected void populateItem(ListItem item) {
				Cheese cheese = (Cheese)item.getModelObject();
				item.add(new Label("name", cheese.getName()));
				item.add(new Label("description", cheese.getDescription()));
				item.add(new Label("price", "$" + cheese.getPrice()));
				
				/**
				 * Link to add cheese to cart
				 */
				item.add(new AjaxFallbackLink("add", item.getModel()) {
					
					@Override
					public void onClick(AjaxRequestTarget target) {
						Cheese selected = (Cheese)getModelObject();
						getCart().getCheeses().add(selected);
						if(target != null){
							target.addComponent(shoppingCartPanel);
						}
					}
				});
		        
		        /**
		         * Add a link to cheese details
		         */
				PageParameters params = new PageParameters();
				params.add("name", cheese.getName().toLowerCase());
				item.add(new BookmarkablePageLink("details", CheesrDetails.class, params));
			}
		};
		
		/**
		 * Add cheeses component
		 */
        add(cheeses);
        add(new PagingNavigator("navigator", cheeses));
        
        /**
         * Add the custom shopping cart panel
         */
        shoppingCartPanel = new ShoppingCartPanel("shoppingcart",  getCart());
        shoppingCartPanel.setOutputMarkupId(true); // <--- Required to handle AjaxFallbackLink
        add(shoppingCartPanel);
        
        /**
         * A "Checkout" button, clicking it will bring
         * user to the Checkout page.  Only visible if
         * the cart is not empty.
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

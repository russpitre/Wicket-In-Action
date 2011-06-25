package com.bigrocksoftware.wicket.page;

import org.apache.wicket.PageParameters;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.markup.html.basic.Label;
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

    /**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameters
	 */
	@SuppressWarnings("serial")
    public Index(final PageParameters parameters) {

		/**
		 * Showcase link
		 */
		add(new Link("showcase") {
			
			@Override
			public void onClick() {
				setResponsePage(Showcase.class);
				
			}
		});
		
		PageableListView cheeses = new PageableListView("cheeses", getCheeses(), 5) {
			
			@Override
			protected void populateItem(ListItem item) {
				Cheese cheese = (Cheese)item.getModelObject();
				item.add(new Label("name", cheese.getName()));
				item.add(new Label("description", cheese.getDescription()));
				item.add(new Label("price", "$" + cheese.getPrice()));
				item.add(new Link("add", item.getModel()) {
					
					@Override
					public void onClick() {
						Cheese selected = (Cheese)getModelObject();
						getCart().getCheeses().add(selected);
					}
				});
			}
		};
		
		/**
		 * Add cheeses component
		 */
        add(cheeses);
        add(new PagingNavigator("navigator", cheeses));
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

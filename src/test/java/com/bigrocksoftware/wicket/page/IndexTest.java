package com.bigrocksoftware.wicket.page;

import org.apache.wicket.Page;
import org.apache.wicket.PageParameters;
import org.apache.wicket.util.tester.ITestPageSource;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.bigrocksoftware.wicket.CheesrApplication;


public class IndexTest {

	private WicketTester tester;
	
	@Before
	@SuppressWarnings("serial")
	public void setup() {
		tester = new WicketTester(new CheesrApplication());

		tester.startPage(new ITestPageSource(){
			public Page getTestPage() {
				return new Index(new PageParameters());
			}	
		});
	}
	
	/**
	 * Test link to Showcase page
	 */
	@Test
	public void testLinkToShowcase() {
		tester.clickLink("showcase");
		tester.assertRenderedPage(Showcase.class);
	}
	
	/**
	 * Test checkout button is not visible until
	 * adding an item to the cart.  Also test that
	 * clicking the checkout button brings you to
	 * the CheckOut page.
	 */
	@Test
	public void testLinkToCart(){
		
		// Checkout button not visible
		tester.assertInvisible("checkout");

		// Add item to the cart
		tester.clickLink("cheeses:1:add");

		// Checkout button should be visible once
		// an item is put into cart
		tester.assertVisible("checkout");
		
		// Click checkout
		tester.clickLink("checkout");
		
		// Assert current page is CheckOut
		tester.assertRenderedPage(CheckOut.class);
		
	}
}

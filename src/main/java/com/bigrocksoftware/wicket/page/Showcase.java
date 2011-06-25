package com.bigrocksoftware.wicket.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public class Showcase extends WebPage {

	public Showcase() {
		
		/**
		 * Simple label
		 */
		add(new Label("simplelabel", "Just a very simple label."));
		
		
		/**
		 *  Multiline label
		 */
		add(new MultiLineLabel("multilinelabel", "Hello\nMy name is\nRussell P. Pitre"));
		
		/**
		 * Label with formatting 
		 */
		add(new Label("formattedlabel", "A <b><i>formatted</i></b> label.").setEscapeModelStrings(false));
		
		/**
		 * External link
		 */
		add(new ExternalLink("externallink", "http://wicket.apache.org", "Apache Wicket Website"));
		
		/**
		 *  BookmarkablePageLink
		 */
		add(new BookmarkablePageLink("bookmarkablelink", CheckOut.class));
	}
}

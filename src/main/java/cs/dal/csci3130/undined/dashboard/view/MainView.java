package cs.dal.csci3130.undined.dashboard.view;

import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;

import cs.dal.csci3130.undined.dashboard.DashboardNavigator;

@SuppressWarnings("serial")
public class MainView extends HorizontalLayout {
	
	public MainView() {
		setSizeFull();
		addStyleName("mainview");
		
		addComponent(new DashboardMenu());
		ComponentContainer content = new CssLayout();
		content.addStyleName("view-content");
		content.setSizeFull();
		addComponent(content);
		setExpandRatio(content, 1.0f);
		
		new DashboardNavigator(content);
	}
}

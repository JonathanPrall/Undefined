package cs.dal.csci3130.undined.dashboard;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;

public class ManagePage extends HorizontalLayout {

	public Button manageInfo = new Button("Manage Info");
	public Button addMenuItem = new Button("Add Menu");
	public Button requestRegist = new Button("Register Restaurants");
	public Button orderRequests = new Button("View orders");

	public ManagePage() {
		this.manageInfo.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {

				getUI().changePage(getUI().manageView);
			}
		});
		this.addMenuItem.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				
				getUI().changePage(getUI().addMenuItemView);
			}
		});
		this.requestRegist.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {

				getUI().changePage(getUI().restaurantRegisterView);
			}
		});
		this.orderRequests.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {

				getUI().setContent(getUI().manageOrder);
			}
		});
		

//		this.setSizeUndefined();
		this.setSizeFull();
		this.addComponents(manageInfo, addMenuItem, requestRegist, orderRequests);
		this.setComponentAlignment(manageInfo, Alignment.MIDDLE_CENTER);
		this.setComponentAlignment(addMenuItem, Alignment.MIDDLE_CENTER);
		this.setComponentAlignment(requestRegist, Alignment.MIDDLE_CENTER);
		this.setComponentAlignment(orderRequests, Alignment.MIDDLE_CENTER);
		
	}

	public IndexUI getUI() {
		return (IndexUI) super.getUI();
	}
}

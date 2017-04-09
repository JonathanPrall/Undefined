package cs.dal.csci3130.undined.dashboard;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.Grid;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.TextField;

import cs.dal.csci3130.undined.backend.Menu;
import cs.dal.csci3130.undined.backend.MenuItem;

public class ManageOrder extends VerticalLayout {
	Button logout = new Button("Log out");
	
	TextField orderSearchBar = new TextField();
	Grid orderRequestList = new Grid();
	Grid orderAcceptList = new Grid();
	Grid orderRejectList = new Grid();
	
	// Add whatever that service is here
	
	public ManageOrder() {
		configureComponent();
		buildLayout();
	}


	private void configureComponent() {
		
		logout.setIcon(FontAwesome.SIGN_OUT);
    	logout.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				getUI().setContent(getUI().loginView);
			}
    		
    	});
    	
    	orderSearchBar.setInputPrompt("Search...");
//    	orderSearchBar.addTextChangeListener(e -> refreshAll());
    	
    	orderRequestList.setContainerDataSource(new BeanItemContainer<>(MenuItem.class));
		orderRequestList.setColumnOrder("name","description","price");
		orderRequestList.removeColumn("id");
		orderRequestList.setSelectionMode(Grid.SelectionMode.SINGLE);
//		refreshAll();
		
		orderAcceptList.setContainerDataSource(new BeanItemContainer<>(MenuItem.class));
		orderAcceptList.setColumnOrder("name","description","price");
		orderAcceptList.removeColumn("id");
		orderAcceptList.setSelectionMode(Grid.SelectionMode.SINGLE);
//		refreshAll();
		
		orderRejectList.setContainerDataSource(new BeanItemContainer<>(MenuItem.class));
		orderRejectList.setColumnOrder("name","description","price");
		orderRejectList.removeColumn("id");
		orderRejectList.setSelectionMode(Grid.SelectionMode.SINGLE);
//		refreshAll();
		
		
	}
	
	private void buildLayout() {
		
		HorizontalLayout logoutBar = buildLogout();
		
		
		HorizontalLayout searchBar = new HorizontalLayout(orderSearchBar);
		searchBar.setWidth("100%");
		orderSearchBar.setWidth("100%");
		searchBar.setExpandRatio(orderSearchBar, 1);
		
		HorizontalLayout navigation = new HorizontalLayout();
		TabSheet navigationTab = new TabSheet();
		navigationTab.setHeight(100.0f, Unit.PERCENTAGE);
		navigationTab.addStyleName(ValoTheme.TABSHEET_FRAMED);
		navigationTab.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
		
		VerticalLayout tab1 = generateTab(orderRequestList);
		VerticalLayout tab2 = generateTab(orderAcceptList);
		VerticalLayout tab3 = generateTab(orderRejectList);
		
		navigationTab.addTab(tab1, "Order Requests");
		navigationTab.addTab(tab2, "Order Accepted");
		navigationTab.addTab(tab3, "Order Rejected");
		
		navigation.addComponent(navigationTab);
		navigation.setSizeFull();
		
		this.addComponents(logoutBar, searchBar, navigation);
	}
	
	private HorizontalLayout buildLogout() {
		HorizontalLayout logoutBar = new HorizontalLayout();
		logoutBar.setSizeFull();
		logoutBar.addComponent(this.logout);
		logoutBar.setComponentAlignment(logout, Alignment.MIDDLE_RIGHT);
		
		return logoutBar; 
	}

	private VerticalLayout generateTab(Grid t) {
		final Label label = new Label();
		label.setWidth(100.0f, Unit.PERCENTAGE);
		
		t.setSizeFull();
		final VerticalLayout layout = new VerticalLayout(label);
		layout.setMargin(true);
		layout.addComponent(t);
		return layout;
	}
	
	void refreshAll() {
		refreshOrders(orderSearchBar.getValue());
	}
	
	private void refreshOrders(String value) {
		this.orderRequestList.setContainerDataSource(new BeanItemContainer<>(
				MenuItem.class, getUI().customerView.viewForm.menu.findAll(value)));
		
	}


	public IndexUI getUI() {
		return (IndexUI) super.getUI();
	}
}

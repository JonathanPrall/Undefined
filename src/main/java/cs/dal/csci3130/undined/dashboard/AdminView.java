package cs.dal.csci3130.undined.dashboard;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.Grid;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.TextField;

import cs.dal.csci3130.undined.backend.Admin;
import cs.dal.csci3130.undined.backend.Manager;
import cs.dal.csci3130.undined.backend.Restaurant;
import cs.dal.csci3130.undined.backend.User;
import cs.dal.csci3130.undined.backend.services.AdminService;
import cs.dal.csci3130.undined.backend.services.ManagerService;
import cs.dal.csci3130.undined.backend.services.RestaurantService;
import cs.dal.csci3130.undined.backend.services.UserService;


public class AdminView extends VerticalLayout {

	Button logout = new Button("Log out");
	
	TextField restaurantSearchBar = new TextField();
	Grid restaurantRequestList = new Grid();
	Grid restaurantAcceptedList = new Grid();
	Grid restaurantRejectedList = new Grid();
	
	TextField userSearchBar = new TextField();
	Grid userUserList = new Grid();
	Grid userManagerList = new Grid();
	Grid userAdminList = new Grid();
	
	RestaurantService restaurantService = RestaurantService.createService();
	RestaurantRequestForm restaurantRequestForm = new RestaurantRequestForm();
	RestaurantEditForm restaurantEditForm = new RestaurantEditForm();
	
	UserService userService = UserService.createService();
	ManagerService managerService = ManagerService.createService();
	AdminService adminService =  AdminService.createService();
	
	UserEditForm userEditForm = new UserEditForm();
	
	public AdminView() {
		configureComponents();
		buildLayout();
	}

    private void configureComponents() {
		
    	//
    	logout.setIcon(FontAwesome.SIGN_OUT);
    	logout.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				getUI().setContent(getUI().loginView);
			}
    		
    	});
    	//
    	
    	restaurantSearchBar.setInputPrompt("Search Restaurants...");
    	restaurantSearchBar.addTextChangeListener(e -> refreshAll());
		
		restaurantRequestList.setContainerDataSource(new BeanItemContainer<>(Restaurant.class));
		restaurantRequestList.setColumnOrder("id", "restaurantName","foodType","location","hoursOfBusiness");
		restaurantRequestList.removeColumn("status");
		restaurantRequestList.setSelectionMode(Grid.SelectionMode.SINGLE);
		restaurantRequestList.addSelectionListener(
				e -> restaurantRequestForm.edit((Restaurant) restaurantRequestList.getSelectedRow()));
		refreshAll();
		
		restaurantAcceptedList.setContainerDataSource(new BeanItemContainer<>(Restaurant.class));
		restaurantAcceptedList.setColumnOrder("id", "restaurantName","foodType","location","hoursOfBusiness");
		restaurantAcceptedList.removeColumn("status");
		restaurantAcceptedList.setSelectionMode(Grid.SelectionMode.SINGLE);
		restaurantAcceptedList.addSelectionListener(
				e -> restaurantEditForm.edit((Restaurant) restaurantAcceptedList.getSelectedRow()));
		refreshAll();
		
		restaurantRejectedList.setContainerDataSource(new BeanItemContainer<>(Restaurant.class));
		restaurantRejectedList.setColumnOrder("id", "restaurantName","foodType","location","hoursOfBusiness");
		restaurantRejectedList.removeColumn("status");
		restaurantRejectedList.setSelectionMode(Grid.SelectionMode.SINGLE);
		restaurantRejectedList.addSelectionListener(
				e -> restaurantRequestForm.edit((Restaurant) restaurantRejectedList.getSelectedRow()));
		refreshAll();
		
		
		userSearchBar.setInputPrompt("Search...");
		userSearchBar.addTextChangeListener(e -> refreshAll());
		
		userUserList.setContainerDataSource(new BeanItemContainer<>(User.class));
		userUserList.setColumnOrder("id", "firstName","lastName","email","phone");
		userUserList.removeColumn("password");
		userUserList.removeColumn("role");
		userUserList.setSelectionMode(Grid.SelectionMode.SINGLE);
		userUserList.addSelectionListener(
				e -> userEditForm.edit((User) userUserList.getSelectedRow()));
		refreshAll();
		
		userManagerList.setContainerDataSource(new BeanItemContainer<>(Manager.class));
		userManagerList.setColumnOrder("id", "firstName","lastName","email","phone");
		userManagerList.removeColumn("password");
		userManagerList.removeColumn("role");
		userManagerList.setSelectionMode(Grid.SelectionMode.SINGLE);
		userManagerList.addSelectionListener(
				e -> userEditForm.edit((Manager) userManagerList.getSelectedRow()));
		refreshAll();
		
		userAdminList.setContainerDataSource(new BeanItemContainer<>(Admin.class));
		userAdminList.setColumnOrder("id", "firstName","lastName","email","phone");
		userAdminList.removeColumn("password");
		userAdminList.removeColumn("role");
		userAdminList.setSelectionMode(Grid.SelectionMode.SINGLE);
		refreshAll();
	}
    

	private void buildLayout() {
		
		TabSheet navigationTab = new TabSheet();
		navigationTab.setHeight(100.0f, Unit.PERCENTAGE);
		navigationTab.addStyleName(ValoTheme.TABSHEET_FRAMED);
		navigationTab.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
		
		
		//Build pages
		HorizontalLayout logoutBar = buildLogout();
		HorizontalLayout restaurantPage = buildRestaurantPage();
		HorizontalLayout userPage = buildUserPage();
		
		navigationTab.addTab(restaurantPage, "Restaurant");
		navigationTab.addTab(userPage, "User");
		
		this.addComponents(logoutBar, navigationTab);
		
//		
//		VerticalLayout mainLayout = new VerticalLayout(navigationTab);
//		setContent(mainLayout);
	}

	HorizontalLayout buildLogout() {
		HorizontalLayout logoutBar = new HorizontalLayout();
		logoutBar.setSizeFull();
		logoutBar.addComponent(this.logout);
		logoutBar.setComponentAlignment(logout, Alignment.MIDDLE_RIGHT);
		
		return logoutBar; 
	}
	
	HorizontalLayout buildRestaurantPage(){
		HorizontalLayout searchBar = new HorizontalLayout(restaurantSearchBar);
		searchBar.setWidth("100%");
		restaurantSearchBar.setWidth("100%");
		searchBar.setExpandRatio(restaurantSearchBar, 1);
		
		TabSheet restaurantTabs = new TabSheet();
		restaurantTabs.setHeight(100.0f, Unit.PERCENTAGE);
		restaurantTabs.addStyleName(ValoTheme.TABSHEET_FRAMED);
		restaurantTabs.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);

		VerticalLayout tab1 = generateTab(restaurantRequestList);
		VerticalLayout tab2 = generateTab(restaurantAcceptedList);
		VerticalLayout tab3 = generateTab(restaurantRejectedList);
		
		restaurantTabs.addTab(tab1, "Requests");
		restaurantTabs.addTab(tab2, "Accepts");
		restaurantTabs.addTab(tab3, "Rejects");
		
		 
		VerticalLayout restaurantPage = new VerticalLayout(searchBar, restaurantTabs);
		restaurantPage.setSizeFull();
		restaurantTabs.setSizeFull();
		restaurantPage.setExpandRatio(restaurantTabs, 1);
		
		HorizontalLayout mainLayout = new HorizontalLayout(restaurantPage, restaurantRequestForm, restaurantEditForm);
		mainLayout.setSizeFull();
		mainLayout.setExpandRatio(restaurantPage, 1);
		
		return mainLayout;
	}
	
	HorizontalLayout buildUserPage(){
		HorizontalLayout searchBar = new HorizontalLayout(userSearchBar);
		searchBar.setWidth("100%");
		userSearchBar.setWidth("100%");
		searchBar.setExpandRatio(userSearchBar, 1);
		
		TabSheet userTabs = new TabSheet();
		userTabs.setHeight(100.0f, Unit.PERCENTAGE);
		userTabs.addStyleName(ValoTheme.TABSHEET_FRAMED);
		userTabs.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);

		VerticalLayout tab1 = generateTab(userUserList);
		VerticalLayout tab2 = generateTab(userManagerList);
		VerticalLayout tab3 = generateTab(userAdminList);
		
		userTabs.addTab(tab1, "Users");
		userTabs.addTab(tab2, "Managers");
		userTabs.addTab(tab3, "Admins");
		
		
		VerticalLayout userPage = new VerticalLayout(searchBar, userTabs);
		userPage.setSizeFull();
		userTabs.setSizeFull();
		userPage.setExpandRatio(userTabs, 1);
		
		HorizontalLayout mainLayout = new HorizontalLayout(userPage, userEditForm);
		mainLayout.setSizeFull();
		mainLayout.setExpandRatio(userPage, 1);
		
		return mainLayout;
	}
	
	void refreshAll() {
		refreshRestaurants(restaurantSearchBar.getValue());
		refreshUsers(userSearchBar.getValue());
	}
	
	private void refreshRestaurants(String stringFilter) {
		restaurantRequestList.setContainerDataSource(new BeanItemContainer<>(
				Restaurant.class, restaurantService.findAll(stringFilter, 0)));
		restaurantAcceptedList.setContainerDataSource(new BeanItemContainer<>(
				Restaurant.class, restaurantService.findAll(stringFilter, 1)));
		restaurantRejectedList.setContainerDataSource(new BeanItemContainer<>(
				Restaurant.class, restaurantService.findAll(stringFilter, -1)));
		restaurantRequestForm.setVisible(false);
		restaurantEditForm.setVisible(false);	
	}
	private void refreshUsers(String stringFilter) {
		userUserList.setContainerDataSource(new BeanItemContainer<>(
				User.class, userService.findAll(stringFilter)));
		
		userManagerList.setContainerDataSource(new BeanItemContainer<>(
				Manager.class, managerService.findAll(stringFilter)));
		
		
		userAdminList.setContainerDataSource(new BeanItemContainer<>(
				Admin.class, adminService.findAll(stringFilter)));
		
		
		userEditForm.setVisible(false);
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

	public IndexUI getUI() {
		return (IndexUI) super.getUI();
	}
}
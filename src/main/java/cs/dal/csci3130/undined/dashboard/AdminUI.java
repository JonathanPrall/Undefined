package cs.dal.csci3130.undined.dashboard;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.Grid;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.TextField;

import cs.dal.csci3130.undined.newbackend.Admin;
import cs.dal.csci3130.undined.newbackend.Manager;
import cs.dal.csci3130.undined.newbackend.Restaurant;
import cs.dal.csci3130.undined.newbackend.User;
import cs.dal.csci3130.undined.newbackend.services.AdminService;
import cs.dal.csci3130.undined.newbackend.services.ManagerService;
import cs.dal.csci3130.undined.newbackend.services.RestaurantService;
import cs.dal.csci3130.undined.newbackend.services.UserService;




/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Title("Admin page")
@Theme("valo")
@Widgetset("com.vaadin.v7.Vaadin7WidgetSet")
public class AdminUI extends UI {

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
	
	UserService userService = new UserService();
	ManagerService managerService = new ManagerService();
	AdminService adminService =  new AdminService();
	
	UserEditForm userEditForm = new UserEditForm();
	
	
    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	configureComponents();
    	buildLayout();
    }

    private void configureComponents() {
		
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
		
	}
    

	private void buildLayout() {
		
		TabSheet navigationTab = new TabSheet();
		navigationTab.setHeight(100.0f, Unit.PERCENTAGE);
		navigationTab.addStyleName(ValoTheme.TABSHEET_FRAMED);
		navigationTab.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
		
		
		//Build pages
		HorizontalLayout restaurantPage = buildRestaurantPage();
		HorizontalLayout userPage = buildUserPage();
		
		navigationTab.addTab(restaurantPage, "Restaurant");
		navigationTab.addTab(userPage, "User");
		
		
		VerticalLayout mainLayout = new VerticalLayout(navigationTab);
		setContent(mainLayout);
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
		
		/*
		userAdminList.setContainerDataSource(new BeanItemContainer<>(
				Admin.class, adminService.findAll(stringFilter)));
		*/
		
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

	@WebServlet(urlPatterns = "/Admin/*", name = "AdminServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = AdminUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
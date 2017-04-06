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


public class CustomerView extends VerticalLayout {

	Button logout = new Button("Log out");
	
	TextField restaurantSearchBar = new TextField();
	Grid restaurantAcceptedList = new Grid();

	RestaurantService restaurantService = RestaurantService.createService();
	
	RestaurantViewForm viewForm = new RestaurantViewForm();
	
	public CustomerView() {
		configureComponents();
		buildLayout();
	}
	
//    @Override
//    protected void init(VaadinRequest vaadinRequest) {
//    	configureComponents();
//    	buildLayout();
//    }

    private void configureComponents() {
		
    	logout.setIcon(FontAwesome.SIGN_OUT);
    	logout.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				getUI().setContent(getUI().loginView);
			}
    		
    	});
    	
    	restaurantSearchBar.setInputPrompt("Search Restaurants...");
    	restaurantSearchBar.addTextChangeListener(e -> refreshAll());
		
		restaurantAcceptedList.setContainerDataSource(new BeanItemContainer<>(Restaurant.class));
		restaurantAcceptedList.setColumnOrder("id", "restaurantName","foodType","location","hoursOfBusiness");
		restaurantAcceptedList.removeColumn("status");
		restaurantAcceptedList.setSelectionMode(Grid.SelectionMode.SINGLE);
		restaurantAcceptedList.addSelectionListener(
				e -> viewForm.edit((Restaurant) restaurantAcceptedList.getSelectedRow()));
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
		
		navigationTab.addTab(restaurantPage, "Restaurant");
		
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

		VerticalLayout restaurants = generateTab(restaurantAcceptedList);

		VerticalLayout restaurantPage = new VerticalLayout(searchBar, restaurants);
		restaurantPage.setSizeFull();
		restaurantPage.setExpandRatio(restaurants, 1);
		
		HorizontalLayout mainLayout = new HorizontalLayout(restaurantPage, viewForm);
		mainLayout.setSizeFull();
		mainLayout.setExpandRatio(restaurantPage, 1);
		
		return mainLayout;
	}
	
	void refreshAll() {
		refreshRestaurants(restaurantSearchBar.getValue());
	}
	
	private void refreshRestaurants(String stringFilter) {
		restaurantAcceptedList.setContainerDataSource(new BeanItemContainer<>(
				Restaurant.class, restaurantService.findAll(stringFilter, 1)));
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
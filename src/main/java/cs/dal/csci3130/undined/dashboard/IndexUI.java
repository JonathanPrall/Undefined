package cs.dal.csci3130.undined.dashboard;

import javax.servlet.annotation.WebServlet;
import cs.dal.csci3130.undined.backend.services.*;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@Title("Index page")
@Theme("valo")
@Widgetset("com.vaadin.v7.Vaadin7WidgetSet")
public class IndexUI extends UI {


	public LoginView loginView;
	public AdminView adminView;
	public ManageView manageInformationUI;
	public ManagePage managePage;
	public ManageView manageView;
	public AddMenuItemView addMenuItemView;
	public RestaurantRegisterView restaurantRegisterView;
	public CustomerView customerView;

	@Override
	protected void init(VaadinRequest request) {
		initializeDatabase();
		initializeLayouts();
		this.setContent(loginView);
	}
	
	private void initializeDatabase(){
		RestaurantService.createService();
		//MenuService.createService();
		//Etc...
	}
	
	private void initializeLayouts(){
		loginView = new LoginView();
		adminView = new AdminView();
		manageInformationUI = new ManageView();
		managePage = new ManagePage();
		manageView = new ManageView();
		addMenuItemView = new AddMenuItemView();
		restaurantRegisterView = new RestaurantRegisterView();
		customerView = new CustomerView();
	}
	
	
	@WebServlet(urlPatterns = "/*", name = "IndexServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = IndexUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}

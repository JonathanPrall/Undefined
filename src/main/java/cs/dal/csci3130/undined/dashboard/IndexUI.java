package cs.dal.csci3130.undined.dashboard;

import javax.servlet.annotation.WebServlet;
import cs.dal.csci3130.undined.backend.services.*;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Layout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

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
	public ManageOrder manageOrder;
	public CustomerView customerView;
	private NavigationBar navbar;
	
	private Layout current;
	private Layout previous;
	
	@Override
	protected void init(VaadinRequest request) {
		initializeDatabase();
		initializeLayouts();
		this.changePage(loginView);
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
		manageOrder = new ManageOrder();
		customerView = new CustomerView();
		navbar = new NavigationBar();
	}
	
	public void changePage(){
		if(previous == null){
			navbar.hideBackButton();
		}
		else{
			changePage(previous);
			previous = null;
			navbar.hideBackButton();
		}
	}
	public void changePage(Layout newPage){
		if(newPage instanceof LoginView){
			this.setContent(newPage);
			current = newPage;
			previous = null;
			navbar.hideBackButton();
		}
		else{
			VerticalLayout page = new VerticalLayout();
			page.addComponent(navbar);
			page.addComponent(newPage);
			this.setContent(page);
			
			previous = current;
			current = newPage;
			
			//If there is a previous page and its not the login page
			//Show the back button
			if(previous != null){
				if(!(previous instanceof LoginView)){
					navbar.showBackButton();
				}
			}
			
		}
		
		
		
	}
	
	@WebServlet(urlPatterns = "/*", name = "IndexServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = IndexUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}

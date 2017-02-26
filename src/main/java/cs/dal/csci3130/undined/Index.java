package cs.dal.csci3130.Undined;

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

import cs.dal.csci3130.Undined.backend.Restaurant;
import cs.dal.csci3130.Undined.backend.RestaurantService;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Title("Undined - Index")
@Theme("valo")
@Widgetset("com.vaadin.v7.Vaadin7WidgetSet")
public class Index extends UI {
	
    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	Label lblSignUp = new Label("Sign up a Restaurant: ");
    	Label lblEdit = new Label("Edit Restaurant Information: ");
    	Label lblApproveDeny = new Label("Approve/Deny new Restaurants (Admins): ");
    	
    	Button btnSignUp = new Button("Sign Up");
    	Button btnEdit = new Button("Edit");
    	Button btnApproveDeny = new Button("Approve/Deny");
    	
    	//Add click listeners
    	
    	VerticalLayout layout0 = new VerticalLayout();
    	HorizontalLayout layout1 = new HorizontalLayout();
    	HorizontalLayout layout2 = new HorizontalLayout();
    	HorizontalLayout layout3 = new HorizontalLayout();
    	
    	layout1.setMargin(true);
    	layout1.addComponents(lblSignUp, btnSignUp);
    	layout1.setSpacing(true);
    	setContent(layout1);
    	
    	layout2.setMargin(true);
    	layout2.addComponents(lblEdit, btnEdit);
    	layout2.setSpacing(true);
    	setContent(layout2);
    	
    	layout3.setMargin(true);
    	layout3.addComponents(lblApproveDeny, btnApproveDeny);
    	layout3.setSpacing(true);
    	setContent(layout3);
    	
    	layout0.addComponents(layout1, layout2, layout3);
    	setContent(layout0);
    }


	

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = Index.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
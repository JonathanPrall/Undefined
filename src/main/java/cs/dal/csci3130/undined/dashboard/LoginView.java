package cs.dal.csci3130.undined.dashboard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.CheckBox;
import com.vaadin.v7.ui.Grid;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.PasswordField;
import com.vaadin.v7.ui.TextField;

import cs.dal.csci3130.undined.backend.Restaurant;
import cs.dal.csci3130.undined.backend.services.RestaurantService;


public class LoginView extends VerticalLayout {
	
	final Label title = new Label("Undined");
	final TextField userName = new TextField("Username");
	final PasswordField password = new PasswordField("Password");
	ComboBox select = null;
	final CheckBox rememberMe = new CheckBox("Remember me", true);
	final Button signIn = new Button("Sign In");
	
//	@Override
//    protected void init(VaadinRequest vaadinRequest) {
//    	configureComponents();
//    	buildLayout();
//    }

	public LoginView() {
		configureComponents();
    	buildLayout();
	}
	
	private void configureComponents() {
		
		// set Labels	
		
		title.setSizeUndefined();
		title.addStyleName(ValoTheme.LABEL_H2);
		title.addStyleName(ValoTheme.LABEL_LIGHT);
		
		userName.setWidth(100,Unit.PERCENTAGE);
		userName.setIcon(FontAwesome.USER);
		userName.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
		

		password.setWidth(200,Unit.PERCENTAGE);
		password.setIcon(FontAwesome.LOCK);
		password.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
		
		select = new ComboBox("User Type");
		select.setPlaceholder("Select One");
		select.setEmptySelectionAllowed(false);
		select.setItems("Customer", "Restaurant", "Admin");
		
		signIn.addStyleName(ValoTheme.BUTTON_PRIMARY);
		signIn.setClickShortcut(KeyCode.ENTER);
		signIn.focus();
		
		signIn.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
				if(select.getValue() == "Admin") {
					getUI().setContent(getUI().adminView);
				} else if (select.getValue() == "Restaurant") {
					getUI().setContent(getUI().managePage);
				} else if (select.getValue() == "Customer") {
					getUI().setContent(getUI().customerView);
				}
			}
		});
		
	}

	private void buildLayout() {
		
		// set fields layout
		VerticalLayout fields = new VerticalLayout();
		fields.setSpacing(true);
		
		fields.addComponents(title, userName, password, select, rememberMe, signIn);
		fields.setComponentAlignment(rememberMe, Alignment.MIDDLE_LEFT);
		fields.setComponentAlignment(signIn, Alignment.MIDDLE_LEFT);
		
		// set login form
		VerticalLayout loginPanel = new VerticalLayout();
		loginPanel.setSizeUndefined();
		loginPanel.setSpacing(true);
		Responsive.makeResponsive(loginPanel);
		loginPanel.addStyleName("login-panel");
		
		loginPanel.addComponent(fields);
		
		// set overall
		VerticalLayout mainLayout = new VerticalLayout();
		this.setSizeFull();
		this.addComponent(loginPanel);
		this.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
		
//		setContent(mainLayout);
	}

	public IndexUI getUI() {
		return (IndexUI) super.getUI();
	}
	

//	@WebServlet(urlPatterns = "/*", name = "IndexServlet", asyncSupported = true)
//    @VaadinServletConfiguration(ui = LoginView.class, productionMode = false)
//    public static class MyUIServlet extends VaadinServlet {
//    }
}

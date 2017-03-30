package cs.dal.csci3130.undined.dashboard;

import javax.servlet.annotation.WebServlet;

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

	public LoginView loginView = new LoginView();
	public AdminView adminView = new AdminView();
	public ManageView manageInformationUI = new ManageView();
	
	@Override
	protected void init(VaadinRequest request) {

		this.setContent(loginView);
//		this.setContent(adminView);
	}

	@WebServlet(urlPatterns = "/*", name = "IndexServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = IndexUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}

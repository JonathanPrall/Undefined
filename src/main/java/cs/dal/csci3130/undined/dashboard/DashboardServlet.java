package cs.dal.csci3130.undined.dashboard;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import com.vaadin.server.VaadinServlet;


@SuppressWarnings("serial")
public class DashboardServlet extends VaadinServlet {
	
	@Override
	protected final void servletInitialized() throws ServletException {
		super.servletInitialized();
		getService().addSessionInitListener(new DashboardSessionInitListener());
	}
}

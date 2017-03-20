package cs.dal.csci3130.undined.dashboard;

import cs.dal.csci3130.undined.backend.User;
import cs.dal.csci3130.undined.event.DashboardEventBus;
import com.vaadin.annotations.Theme;
import com.vaadin.server.Page;
import com.vaadin.server.Page.BrowserWindowResizeEvent;
import com.vaadin.server.Page.BrowserWindowResizeListener;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

import cs.dal.csci3130.undined.event.DashboardEventBus;

@Theme("dashboard")
@SuppressWarnings("serial")
public class DashboardUI extends UI {

	private final DashboardEventBus dashboardEventBus = new DashboardEventBus();
	
	@Override
	protected void init(VaadinRequest request) {
//		DashboardEventBus.register(this);
//		Responsive.makeResponsive(this);
//		addStyleName(ValoTheme.UI_WITH_MENU);
//		
//		updateContent();
	}
	
	private void updateContent() {
//		User user = (User) VaadinSession.getCurrent().getAttribute(User.class.getName());
//		
//		if(user != null && "admin".equals(user.getRole())) {
//			setContent()
//		}
	}
	
}

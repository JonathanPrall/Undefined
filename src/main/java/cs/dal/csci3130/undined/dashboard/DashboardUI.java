package cs.dal.csci3130.undined.dashboard;

import java.util.Locale;

import com.google.common.eventbus.Subscribe;
import com.vaadin.annotations.Theme;
import com.vaadin.server.Page;
import com.vaadin.server.Page.BrowserWindowResizeEvent;
import com.vaadin.server.Page.BrowserWindowResizeListener;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

import com.vaadin.server.Page;
import cs.dal.csci3130.undined.event.DashboardEventBus;
import cs.dal.csci3130.undined.newbackend.services.DataProvider;
import cs.dal.csci3130.undined.newbackend.services.DummyDataProvider;
import cs.dal.csci3130.undined.dashboard.view.LoginView;
import cs.dal.csci3130.undined.dashboard.view.MainView;
import cs.dal.csci3130.undined.domain.User;
import cs.dal.csci3130.undined.event.DashboardEvent;
import cs.dal.csci3130.undined.event.DashboardEvent.BrowserResizeEvent;
import cs.dal.csci3130.undined.event.DashboardEvent.CloseOpenWindowsEvent;
import cs.dal.csci3130.undined.event.DashboardEvent.UserLoggedOutEvent;
import cs.dal.csci3130.undined.event.DashboardEvent.UserLoginRequestedEvent;
@Theme("dashboard")
@SuppressWarnings("serial")
public class DashboardUI extends UI {

	private final DataProvider dataProvider = new DummyDataProvider();
	private final DashboardEventBus dashboardEventBus = new DashboardEventBus();
	
	@Override
	protected void init(VaadinRequest request) {
		setLocale(Locale.CANADA);
		
		DashboardEventBus.register(this);
		Responsive.makeResponsive(this);
		addStyleName(ValoTheme.UI_WITH_MENU);
		
		updateContent();
		
		Page.getCurrent().addBrowserWindowResizeListener(
				new BrowserWindowResizeListener() {
					
					@Override
					public void browserWindowResized(BrowserWindowResizeEvent event) {
						DashboardEventBus.post(new BrowserResizeEvent());
					}
				});
		
	}
	
	private void updateContent() {
		User user = (User) VaadinSession.getCurrent().getAttribute(User.class.getName());
		if(user != null && "admin".equals(user.getRole())) {
			setContent(new MainView());
			removeStyleName("loginview");
			getNavigator().navigateTo(getNavigator().getState());
		} else {
			setContent(new LoginView());
			addStyleName("loginview");
		}
	}
	
	@Subscribe
	public void userLoginRequested(final UserLoginRequestedEvent event) {
		// unimplemented
	}
	
	@Subscribe
	public void userLoggedOut(final UserLoggedOutEvent event) {
		VaadinSession.getCurrent().close();
		Page.getCurrent().reload();
	}
	
	@Subscribe
	public void closeOpenWindows(final CloseOpenWindowsEvent event) {
		for (Window window : getWindows()) {
			window.close();
		}
	}
	
	public static DataProvider getDataProvider() {
        return ((DashboardUI) getCurrent()).dataProvider;
    }
	
	public static DashboardEventBus getDashboardEventBus() {
		return ((DashboardUI) getCurrent()).dashboardEventBus;
	}
}

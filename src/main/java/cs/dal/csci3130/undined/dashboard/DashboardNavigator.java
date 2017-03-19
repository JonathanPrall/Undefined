package cs.dal.csci3130.undined.dashboard;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.navigator.ViewProvider;
import com.vaadin.navigator.Navigator.ClassBasedViewProvider;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;

import cs.dal.csci3130.undined.event.DashboardEvent.PostViewChangeEvent;
import cs.dal.csci3130.undined.event.DashboardEvent.BrowserResizeEvent;
import cs.dal.csci3130.undined.event.DashboardEvent.CloseOpenWindowsEvent;
import cs.dal.csci3130.undined.dashboard.view.DashboardViewType;
import cs.dal.csci3130.undined.event.DashboardEventBus;

import org.vaadin.googleanalytics.tracking.GoogleAnalyticsTracker;

public class DashboardNavigator extends Navigator {
	
	private static final String TRACKER_ID = null;
	private GoogleAnalyticsTracker tracker;
	
	private static final DashboardViewType ERROR_VIEW = DashboardViewType.DASHBOARD;
	private ViewProvider errorViewProvider;
	
	public DashboardNavigator(final ComponentContainer container) {
		super(UI.getCurrent(), container);
		
		String host = getUI().getPage().getLocation().getHost();
		if(TRACKER_ID != null && host.endsWith("undined.csci3130.dal.cs")) {
			initGATracker(TRACKER_ID);
		}
		initViewChangeListener();
		initViewProviders();
	}

	private void initViewProviders() {
		
		for (final DashboardViewType viewType : DashboardViewType.values()) {
            ViewProvider viewProvider = new ClassBasedViewProvider(
                    viewType.getViewName(), viewType.getViewClass()) {

                // This field caches an already initialized view instance if the
                // view should be cached (stateful views).
                private View cachedInstance;

                @Override
                public View getView(final String viewName) {
                    View result = null;
                    if (viewType.getViewName().equals(viewName)) {
                        if (viewType.isStateful()) {
                            // Stateful views get lazily instantiated
                            if (cachedInstance == null) {
                                cachedInstance = super.getView(viewType
                                        .getViewName());
                            }
                            result = cachedInstance;
                        } else {
                            // Non-stateful views get instantiated every time
                            // they're navigated to
                            result = super.getView(viewType.getViewName());
                        }
                    }
                    return result;
                }
            };

            if (viewType == ERROR_VIEW) {
                errorViewProvider = viewProvider;
            }

            addProvider(viewProvider);
        }

        setErrorProvider(new ViewProvider() {
            @Override
            public String getViewName(final String viewAndParameters) {
                return ERROR_VIEW.getViewName();
            }

            @Override
            public View getView(final String viewName) {
                return errorViewProvider.getView(ERROR_VIEW.getViewName());
            }
        });
    }
	private void initViewChangeListener() {
		addViewChangeListener(new ViewChangeListener() {

			@Override
			public boolean beforeViewChange(ViewChangeEvent event) {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public void afterViewChange(ViewChangeEvent event) {
				DashboardViewType view = DashboardViewType.getByViewName(event.getViewName());
				DashboardEventBus.post(new PostViewChangeEvent(view));
				DashboardEventBus.post(new BrowserResizeEvent());
				DashboardEventBus.post(new CloseOpenWindowsEvent());
				
				if(tracker != null) {
					tracker.trackPageview("/dashboard/" + event.getViewName());
				}
			}
		});
	}

	private void initGATracker(String trackerId) {
		tracker = new GoogleAnalyticsTracker(trackerId, "undined.csci3130.dal.cs");
		
		tracker.extend(UI.getCurrent());
	}
}

package cs.dal.csci3130.undined.event;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.eventbus.SubscriberExceptionHandler;

import cs.dal.csci3130.undined.dashboard.DashboardUI;

public class DashboardEventBus implements SubscriberExceptionHandler {

	private final EventBus eventBus = new EventBus(this);
	
	public static void post(final Object event) {
		DashboardUI.getDashboardEventBus().eventBus.post(event);
	}
	
	public static void register(final Object object) {
		DashboardUI.getDashboardEventBus().eventBus.register(object);
	}
	
	public static void unregister(final Object object) {
		DashboardUI.getDashboardEventBus().eventBus.unregister(object);
	}
	@Override
	public void handleException(Throwable exception, SubscriberExceptionContext context) {
		exception.printStackTrace();

	}

}

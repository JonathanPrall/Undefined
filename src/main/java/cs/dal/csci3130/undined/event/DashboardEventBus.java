package cs.dal.csci3130.undined.event;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.eventbus.SubscriberExceptionHandler;

public class DashboardEventBus implements SubscriberExceptionHandler {

	private final EventBus eventBus = new EventBus(this);
	
	public static void post(final Object event) {
		
	}
	
	@Override
	public void handleException(Throwable exception, SubscriberExceptionContext context) {
		// TODO Auto-generated method stub

	}

}

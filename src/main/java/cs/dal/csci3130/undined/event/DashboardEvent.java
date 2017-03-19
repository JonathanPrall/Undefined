package cs.dal.csci3130.undined.event;

import cs.dal.csci3130.undined.dashboard.view.DashboardViewType;

public abstract class DashboardEvent {
	
	public static final class UserLoginRequestedEvent {
		private final String userName;
		private final String password;
		private final String role;

		public UserLoginRequestedEvent(final String userName, final String password, final String role) {
			this.userName = userName;
			this.password = password;
			this.role = role;
		}

		public String getUserName() {
			return userName;
		}

		public String getPassword() {
			return password;
		}
		
		public String getRole() {
			return role;
		}
	}
	
	public static class PostViewChangeEvent {
		private final DashboardViewType view;
		
		public PostViewChangeEvent(DashboardViewType view) {
			this.view = view;
		}
		
		public DashboardViewType getView() {
			return view;
		}
	}
	public static class BrowserResizeEvent {

    }
	
	public static class CloseOpenWindowsEvent {
    }
	
	public static class UserLoggedOutEvent {
		
	}
}

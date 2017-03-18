package cs.dal.csci3130.undined.event;

public abstract class DashboardEvent {
	
	public static final class UserLoginRequestEvent {
		private final String userName;
		private final String password;
		
		public UserLoginRequestEvent(final String userName, final String password) {
			this.userName = userName;
			this.password = password;
		}

		public String getUserName() {
			return userName;
		}

		public String getPassword() {
			return password;
		}
	}
	
	public static class BrowserResizeEvent {

    }
	
	public static class UserLoggedOutEvent {
		
	}
}

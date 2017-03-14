package cs.dal.csci3130.undined.dashboard.view;

import com.vaadin.server.AbstractErrorMessage.ContentMode;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.themes.ValoTheme;

import cs.dal.csci3130.undined.backend.User;

@SuppressWarnings({ "serial", "unchecked"})
public final class DashboardMenu extends CustomComponent {
	
	public static final String ID = "dashboard-menu";
	public static final String REPORTS_BADGE_ID = "dashboard-menu-reports-badge";
    public static final String NOTIFICATIONS_BADGE_ID = "dashboard-menu-notifications-badge";
    private static final String STYLE_VISIBLE = "valo-menu-visible";
    private Label notificationsBadge;
    private Label reportsBadge;
	private MenuItem settingsItem;
	
	private Component buildContent() {
		final CssLayout menuContent = new CssLayout();
		menuContent.addStyleName("sidebar");
		menuContent.addStyleName(ValoTheme.MENU_PART);
		menuContent.addStyleName("no-vertical-drag-hints");
		menuContent.addStyleName("no-horizontal-drag-hints");
		menuContent.setWidth(null);
		menuContent.setHeight("100%");
		
		// addComponent()
		return menuContent;
	}
	
	private Component buildTitle() {
		Label logo = new Label("Undined Dashboard");
		logo.setSizeUndefined();
		HorizontalLayout logoWrapper = new HorizontalLayout(logo);
		logoWrapper.setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
		logoWrapper.addStyleName("valo-menu-title");
		return logoWrapper;
	}
	
	/*
	 * need to re-specify
	 */
	private User getCurrentUser() {
		return (User) VaadinSession.getCurrent().getAttribute(User.class.getName());
	}
	
	private Component buildUserMenu() {
		final MenuBar settings = new MenuBar();
		settings.addStyleName("user-menu");
		final User user = getCurrentUser();
		settingsItem = settings.addItem("", new ThemeResource("img/profile-pic-300px.jpg"), null);
		// updateUserName(null);
		settingsItem.addItem("Edit Profile", new Command() {
			@Override
			public void menuSelected(MenuItem selectedItem) {
//				ProfilePreferencesWindow.open(user,false);
			}
		});
		settingsItem.addSeparator();
		settingsItem.addItem("Sign Out", new Command() {
			@Override
			public void menuSelected(MenuItem selectedItem) {
//				DashboardEventBus.post(new UserLoggedOutEvent());
			}
		});	
		return settings;
	}
	private Component buildToggleButton() {
		Button valoMenuToggleButton = new Button("Menu", new ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
                if (getCompositionRoot().getStyleName().contains(STYLE_VISIBLE)) {
                    getCompositionRoot().removeStyleName(STYLE_VISIBLE);
                } else {
                    getCompositionRoot().addStyleName(STYLE_VISIBLE);
                }
            }
        });
		valoMenuToggleButton.setIcon(FontAwesome.LIST);
		valoMenuToggleButton.addStyleName("valo-menu-toggle");
		valoMenuToggleButton.addStyleName(ValoTheme.BUTTON_BORDERLESS);
		valoMenuToggleButton.addStyleName(ValoTheme.BUTTON_SMALL);
		return valoMenuToggleButton;
	}
	
	private Component buildMeneItems() {
		CssLayout menuItemsLayout = new CssLayout();
		menuItemsLayout.addStyleName("valo-menuitems");
		
		//
		return menuItemsLayout;
	}
	
	private Component buildBadgeWrapper(final Component menuItemButton, final Component badgeLabel) {
		CssLayout dashboardWrapper = new CssLayout(menuItemButton);
		dashboardWrapper.addStyleName("badgewrapper");
		dashboardWrapper.addStyleName(ValoTheme.MENU_ITEM);
		badgeLabel.addStyleName(ValoTheme.MENU_BADGE);
		badgeLabel.setWidthUndefined();
		badgeLabel.setVisible(false);
		dashboardWrapper.addComponent(badgeLabel);
		return badgeLabel;
	}
	
	@Override
	public void attach() {
		super.attach();
		//updateNotificationsCount(null);
	}
	
//	@Subscribe
//	public void postViewChange(final PostViewChangeEvent event) {
//		getCompositionRoot().removeStyleName(STYLE_VISIBLE);
//	}
	
	
}

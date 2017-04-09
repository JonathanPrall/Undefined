package cs.dal.csci3130.undined.dashboard;

import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.v7.data.fieldgroup.BeanFieldGroup;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.v7.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.DateField;
import com.vaadin.v7.ui.Grid;
import com.vaadin.v7.ui.TextField;

import cs.dal.csci3130.undined.backend.Restaurant;
import cs.dal.csci3130.undined.backend.services.MenuService;
import cs.dal.csci3130.undined.backend.services.RestaurantService;
import cs.dal.csci3130.undined.backend.Menu;
import cs.dal.csci3130.undined.backend.MenuItem;

public class RestaurantViewForm extends FormLayout{
	
	Button dine = new Button("Dine", this::dine);
	Button cancel = new Button("Cancel", this::cancel);
	
	Grid restaurantMenu = new Grid();
	
	Restaurant restaurant;
	
	@SuppressWarnings("deprecation")
	BeanFieldGroup<Restaurant> formFieldBindings;
	
	public RestaurantViewForm() {
		configureComponents();
		buildLayout();
	}

	private void configureComponents() {
		
		dine.setStyleName(ValoTheme.BUTTON_PRIMARY);
		//dine.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		
		restaurantMenu.setContainerDataSource(new BeanItemContainer<>(MenuItem.class));
		
		restaurantMenu.setColumnOrder("name","description","price");
		restaurantMenu.removeColumn("id");
		restaurantMenu.removeColumn("status");
		restaurantMenu.setSelectionMode(Grid.SelectionMode.SINGLE);
	}
	
	private void buildLayout() {
		setSizeUndefined();
		setMargin(true);
		
		HorizontalLayout actions = new HorizontalLayout(dine, cancel);
		actions.setSpacing(true);
		
		addComponents(actions, restaurantMenu);
		
	}
	
	public void dine(Button.ClickEvent event) {	
		// Commit the fields from UI to DAO
		String msg = String.format("You have been DINED.");
		Notification.show(msg, Type.TRAY_NOTIFICATION);
		getUI().customerView.refreshAll();
	}
	
	void getMenu(Restaurant restaurant) {
		this.restaurant = restaurant;
		if(restaurant != null) {
			restaurantMenu.setContainerDataSource(new BeanItemContainer<>(
					//Appearently we are hard coded to only allow one menu to exist for some reason
					//So this code will need to change when that gets fixed	
					MenuItem.class, Menu.createMenu().findAll(-1)));
		}
		setVisible(restaurant != null);
	}
	
	public void cancel(Button.ClickEvent event) {
		getUI().customerView.refreshAll();
	}
	
	
	public IndexUI getUI() {
		return (IndexUI) super.getUI();
	}
	
}

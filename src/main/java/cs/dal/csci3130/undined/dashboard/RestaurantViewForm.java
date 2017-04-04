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
import com.vaadin.v7.ui.DateField;
import com.vaadin.v7.ui.TextField;

import cs.dal.csci3130.undined.backend.Restaurant;

public class RestaurantViewForm extends FormLayout{
	
	Button dine = new Button("Dine", this::dine);
	Button cancel = new Button("Cancel", this::cancel);
	
	TextField restaurantName = new TextField("Restaurant");
	TextField foodType = new TextField("Food Type");
	TextField location = new TextField("Location");
	TextField hoursOfBusiness = new TextField("Working hours");
	
	Restaurant restaurant;
	
	@SuppressWarnings("deprecation")
	BeanFieldGroup<Restaurant> formFieldBindings;
	
	public RestaurantViewForm() {
		configureComponents();
		buildLayout();
	}

	private void configureComponents() {
		
		dine.setStyleName(ValoTheme.BUTTON_PRIMARY);
		dine.setClickShortcut(ShortcutAction.KeyCode.ENTER);
	}
	
	private void buildLayout() {
		setSizeUndefined();
		setMargin(true);
		
		HorizontalLayout actions = new HorizontalLayout(dine, cancel);
		actions.setSpacing(true);
		
		addComponents(actions, restaurantName, foodType, location, hoursOfBusiness);
		
	}
	
	public void dine(Button.ClickEvent event) {	
		// Commit the fields from UI to DAO
		String msg = String.format("You have been DINED.");
		Notification.show(msg, Type.TRAY_NOTIFICATION);
		getUI().customerView.refreshAll();
	}
	
	void edit(Restaurant restaurant) {
		this.restaurant = restaurant;
		if(restaurant != null) {
			formFieldBindings = BeanFieldGroup.bindFieldsBuffered(restaurant, this);
			restaurantName.focus();
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

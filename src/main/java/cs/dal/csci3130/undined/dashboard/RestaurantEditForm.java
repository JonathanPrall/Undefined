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

import cs.dal.csci3130.undined.newbackend.Restaurant;

public class RestaurantEditForm extends FormLayout{
	
	Button save = new Button("Save",this::save);
	Button reject = new Button("Move to Rejects",this::reject);
	Button cancel = new Button("Cancel", this::cancel);
	
	TextField id = new TextField("ID");
	TextField restaurantName = new TextField("Restaurant");
	TextField foodType = new TextField("Food Type");
	TextField location = new TextField("Location");
	TextField hoursOfBusiness = new TextField("Working hours");
	// should be a operating hours here
	
	Restaurant restaurant;
	
	@SuppressWarnings("deprecation")
	BeanFieldGroup<Restaurant> formFieldBindings;
	
	public RestaurantEditForm() {
		configureComponents();
		buildLayout();
	}

	private void configureComponents() {
		
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(ShortcutAction.KeyCode.ENTER);
	}
	
	private void buildLayout() {
		setSizeUndefined();
		setMargin(true);
		
		HorizontalLayout actions = new HorizontalLayout(save, reject, cancel);
		actions.setSpacing(true);
		
		addComponents(actions, restaurantName, foodType, location, hoursOfBusiness);
		
	}
	
	public void save(Button.ClickEvent event) {	
		// Commit the fields from UI to DAO
		try {
			formFieldBindings.commit();
			restaurant.setStatus(1);
			getUI().restaurantService.save(restaurant);
			String msg = String.format("Accepted '%s'", restaurant.getRestaurantName());
			Notification.show(msg, Type.TRAY_NOTIFICATION);
			getUI().refreshAll();
		} catch (CommitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void reject(Button.ClickEvent event) {
		try {
			formFieldBindings.commit();
			restaurant.setStatus(-1);
			getUI().restaurantService.delete(restaurant);
			String msg = String.format("Rejected '%s'", restaurant.getRestaurantName());
			Notification.show(msg, Type.TRAY_NOTIFICATION);
			getUI().refreshAll();
		} catch (CommitException e) {
			e.printStackTrace();
		}
	}
	
	public void cancel(Button.ClickEvent event) {
		getUI().refreshAll();
	}
	
	void edit(Restaurant restaurant) {
		this.restaurant = restaurant;
		if(restaurant != null) {
			formFieldBindings = BeanFieldGroup.bindFieldsBuffered(restaurant, this);
			restaurantName.focus();
		}
		setVisible(restaurant != null);
	}
	
	
	public AdminUI getUI() {
		return (AdminUI) super.getUI();
	}
	
}

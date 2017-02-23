package cs.dal.csci3130.Undined;

import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.v7.data.fieldgroup.BeanFieldGroup;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.v7.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.v7.ui.DateField;
import com.vaadin.v7.ui.TextField;

import cs.dal.csci3130.Undined.backend.Restaurant;

public class RequestForm extends FormLayout{
	
	Button accept = new Button("Accept",this::accept);
	Button reject = new Button("Reject",this::reject);
	
	TextField id = new TextField("ID");
	TextField restaurantName = new TextField("Restaurant");
	TextField foodType = new TextField("Food Type");
	TextField location = new TextField("Location");
	TextField hoursOfBusiness = new TextField("Working hours");
	// should be a operating hours here
	
	Restaurant restaurant;
	
	@SuppressWarnings("deprecation")
	BeanFieldGroup<Restaurant> formFieldBindings;
	
	public RequestForm() {
		configureComponents();
		buildLayout();
	}

	private void configureComponents() {
		accept.setStyleName(ValoTheme.BUTTON_PRIMARY);
		accept.setClickShortcut(ShortcutAction.KeyCode.ENTER);
	}
	
	private void buildLayout() {
		setSizeUndefined();
		setMargin(true);
		
		HorizontalLayout actions = new HorizontalLayout(accept, reject);
		actions.setSpacing(true);
		
		addComponents(actions, id, restaurantName, foodType, location, hoursOfBusiness);
		
	}
	
	public void accept(Button.ClickEvent event) {	
		// Commit the fields from UI to DAO
		try {
			formFieldBindings.commit();
			getUI().service.save(restaurant);
			
			String msg = String.format("Accepted '%s'", restaurant.getRestaurantName());
			Notification.show(msg, Type.TRAY_NOTIFICATION);
			getUI().refreshRequests();
		} catch (CommitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void reject(Button.ClickEvent event) {
		try {
			formFieldBindings.commit();
			getUI().service.delete(restaurant);
			
			String msg = String.format("Rejected '%s'", restaurant.getRestaurantName());
			Notification.show(msg, Type.TRAY_NOTIFICATION);
			getUI().refreshRequests();
		} catch (CommitException e) {
			e.printStackTrace();
		}
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

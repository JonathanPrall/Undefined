package cs.dal.csci3130.undined.dashboard;
//Manage restaurant information UI

import java.util.*;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.swing.*;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.v7.data.fieldgroup.BeanFieldGroup;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.Grid;
import com.vaadin.v7.ui.TextField;
import com.vaadin.ui.FormLayout;

import cs.dal.csci3130.undined.backend.Restaurant;
import cs.dal.csci3130.undined.backend.services.RestaurantService;

import java.awt.*;
import com.vaadin.ui.FormLayout;
import com.vaadin.v7.ui.DateField;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.v7.data.fieldgroup.BeanFieldGroup;

@Title("Manage Information Page")
@Theme("valo")
@Widgetset("com.vaadin.v7.Vaadin7WidgetSet")

public class ManageView extends VerticalLayout {
	
	Button save = new Button("Save", this::save);
	Button cancel = new Button("Cancel", this::cancel);
	TextField restaurantName = new TextField("Restaurant Name");
	TextField hoursOfBusiness = new TextField("Hours of Business");
	TextField foodType = new TextField("Type of Food");
	TextField location = new TextField("Location");

	Restaurant restaurant;

	BeanFieldGroup<Restaurant> formFieldBindings;

	private void configureComponents() {
		Random rand = new Random();
		
		List<Restaurant> restaurantList = RestaurantService.createService().findAll();
		
		restaurant = restaurantList.get(rand.nextInt(restaurantList.size()));
		
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		
		formFieldBindings = BeanFieldGroup.bindFieldsBuffered(restaurant, this);
		restaurantName.focus();
		
		setVisible(true);
	}

	public ManageView() {
		configureComponents();
		buildLayout();
	}

	private void buildLayout() {
		FormLayout layout = new FormLayout();
		setSizeUndefined();
		layout.setMargin(true);

		HorizontalLayout actions = new HorizontalLayout(save, cancel);
		actions.setSpacing(true);

		this.addComponents(actions, restaurantName, foodType, hoursOfBusiness, location);
		
	}

	public void save(Button.ClickEvent event) {
		try {
			formFieldBindings.commit();
			RestaurantService.createService().save(restaurant);

			String msg = String.format("Saved '%s'.", restaurant.getRestaurantName());
			Notification.show(msg, Type.TRAY_NOTIFICATION);
		} catch (FieldGroup.CommitException e) {
		}
	}

	public void cancel(Button.ClickEvent event) {
		Notification.show("Cancelled", Type.TRAY_NOTIFICATION);
		formFieldBindings = BeanFieldGroup.bindFieldsBuffered(restaurant, this);
	}
}

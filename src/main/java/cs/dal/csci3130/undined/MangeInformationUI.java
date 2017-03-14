package cs.dal.csci3130.undined;

import java.util.*;
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

import cs.dal.csci3130.undined.backend.Restaurant;

import java.awt.*;
import com.vaadin.ui.FormLayout;
import com.vaadin.v7.ui.DateField;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.v7.data.fieldgroup.BeanFieldGroup;

public class MangeInformationUI extends FormLayout
{
	Button save = new Button("Save", this::save);
	Button cancel = new Button("Cancel", this::cancel);
	TextField restaurantName = new TextField("RestaurantName");
	TextField op = new TextField("OpenHour");
	TextField cl = new TextField("CloseHour");
	TextField email = new TextField("Email");
	TextField phone = new TextField("Phone");
	TextField des = new TextField("Description");
	TextField available = new TextField("# of tables are available");
	Restaurant mi;
	
	BeanFieldGroup<Restaurant> formFieldBindings;

	public MangeInformationUI() {
        configureComponents();
        buildLayout();
    }

    private void configureComponents() {
    	save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        //setVisible(false);
    }
    private void buildLayout() {
        setSizeUndefined();
        setMargin(true);

        HorizontalLayout actions = new HorizontalLayout(save, cancel);
        actions.setSpacing(true);

        addComponents(actions, restaurantName, op, cl, email, phone, des, available);
    }
    public void save(Button.ClickEvent event) {
        try {
        	formFieldBindings.commit();
        	getUI().service.save(mi);

            String msg = String.format("Saved '%s %s'.", mi.getRestaurantName());
            Notification.show(msg, Type.TRAY_NOTIFICATION);
            getUI().refreshAll();
        } catch (FieldGroup.CommitException e) {
        }
    }
    public void cancel(Button.ClickEvent event) {
        Notification.show("Cancelled", Type.TRAY_NOTIFICATION);
        //getUI().select(null);
    }

    void edit(Restaurant mi) {
        this.mi = mi;
        if (mi != null) {
            formFieldBindings = BeanFieldGroup.bindFieldsBuffered(mi,
                    this);
            restaurantName.focus();
        }
        //setVisible(mi != null);
    }

    @Override
    public AdminUI getUI() {
        return (AdminUI) super.getUI();
    }

}


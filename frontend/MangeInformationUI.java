import java.util.*;
import javax.swing.*;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.tutorial.addressbook.backend.Contact;
import com.vaadin.tutorial.addressbook.backend.ContactService;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.Grid;
import com.vaadin.v7.ui.TextField;
import java.awt.*;

public class MangeInformationUI 
{
	Button save = new Button("Save", this::save);
	Button cancel = new Button("Cancel", this::cancel);
	TextField name = new TextField("RestaurantName");
	TextField op = new TextField("OpenHour");
	TextField cl = new TextField("CloseHour");
	TextField email = new TextField("Email");
	TextField phone = new TextField("Phone");
	TextField des = new TextField("Description");
	TextField available = new TextField("# of tables are available");
	ManageInfo mi;
	
	BeanFieldGroup<ManageInfo> formFieldBindings;

	public MangeInformationUI() {
        configureComponents();
        buildLayout();
    }

    private void configureComponents() {
    	save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        setVisible(false);
    }
    private void buildLayout() {
        setSizeUndefined();
        setMargin(true);

        HorizontalLayout actions = new HorizontalLayout(save, cancel);
        actions.setSpacing(true);

        addComponents(actions, name, op, cl, email, phone, des, available);
    }
    public void save(Button.ClickEvent event) {
        try {
        	formFieldBindings.commit();
        	getUI().service.save(mi);

            String msg = String.format("Saved '%s %s'.", mi.getRestName());
            Notification.show(msg, Type.TRAY_NOTIFICATION);
            getUI().refreshRestaurant();
        } catch (FieldGroup.CommitException e) {
        }
    }
    public void cancel(Button.ClickEvent event) {
        Notification.show("Cancelled", Type.TRAY_NOTIFICATION);
        getUI().MangeInformationUI.select(null);
    }

    void edit(ManageInfo mi) {
        this.mi = mi;
        if (mi != null) {
            formFieldBindings = BeanFieldGroup.bindFieldsBuffered(mi,
                    this);
            name.focus();
        }
        setVisible(mi != null);
    }

    @Override
    public RestaurantUI getUI() {
        return (RestaurantUI) super.getUI();
    }

}

}
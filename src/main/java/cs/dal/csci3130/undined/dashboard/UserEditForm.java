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

import cs.dal.csci3130.undined.dashboard.AdminUI;
import cs.dal.csci3130.undined.backend.Manager;
import cs.dal.csci3130.undined.backend.Restaurant;
import cs.dal.csci3130.undined.backend.User;

public class UserEditForm extends FormLayout{
	
	Button save = new Button("Save",this::save);
	Button cancel = new Button("Cancel", this::cancel);
	
	TextField firstName = new TextField("First Name");
	TextField lastName = new TextField("Last Name");
	TextField role = new TextField("Role");
	TextField email = new TextField("Email Address");
	TextField phone = new TextField("Phone Number");
	
	User user;
	
	@SuppressWarnings("deprecation")
	BeanFieldGroup<User> formFieldBindings;
	BeanFieldGroup<Manager> managerFormFieldBindings;
	
	public UserEditForm() {
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
		
		HorizontalLayout actions = new HorizontalLayout(save, cancel);
		actions.setSpacing(true);
		
		addComponents(actions, firstName, lastName, role, email, phone);
		
	}
	
	public void save(Button.ClickEvent event) {	
		// Commit the fields from UI to DAO
		try {
			formFieldBindings.commit();
			getUI().userService.save(user);
			getUI().refreshAll();
		} catch (CommitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void cancel(Button.ClickEvent event) {
		getUI().refreshAll();
	}
	
	void edit(User user) {
		this.user = user;
		if(user != null) {
			formFieldBindings = BeanFieldGroup.bindFieldsBuffered(user, this);
			firstName.focus();
		}
		setVisible(user != null);
	}
	
	void edit(Manager manager) {
		if(manager != null) {
			managerFormFieldBindings = BeanFieldGroup.bindFieldsBuffered(manager, this);
			firstName.focus();
		}
		setVisible(manager != null);
	}
	
	
	public AdminUI getUI() {
		return (AdminUI) super.getUI();
	}
	
}

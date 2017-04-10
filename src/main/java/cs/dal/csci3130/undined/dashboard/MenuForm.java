package cs.dal.csci3130.undined.dashboard;

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

import cs.dal.csci3130.undined.backend.MenuItem;

public class MenuForm extends FormLayout{
	
	Button save = new Button("Save",this::save);
	Button cancel = new Button("Cancel",this::cancel);
	
	TextField name = new TextField("Name");
	TextField description = new TextField("Description");
	TextField price = new TextField("Price");

	// should be a operating hours here
	
	MenuItem menuItem;
	
	@SuppressWarnings("deprecation")
	BeanFieldGroup<MenuItem> formFieldBindings;
	
	public MenuForm() {
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
		
		addComponents(actions, name, description, price);
		
	}
	
	public void save(Button.ClickEvent event) {	
		// Commit the fields from UI to DAO
		try {
			formFieldBindings.commit();
			getUI().addMenuItemView.service.save(menuItem);
			String msg = String.format("Accepted '%s'", menuItem.getName());
			Notification.show(msg, Type.TRAY_NOTIFICATION);
			getUI().addMenuItemView.refreshAll();
		} catch (CommitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void cancel(Button.ClickEvent event) {
		getUI().addMenuItemView.refreshAll();
	}
	
	void edit(MenuItem menuItem) {
		this.menuItem = menuItem;
		if(menuItem != null) {
			formFieldBindings = BeanFieldGroup.bindFieldsBuffered(menuItem, this);
			name.focus();
		}
		setVisible(menuItem != null);
	}
	
	
	public IndexUI getUI() {
		return (IndexUI) super.getUI();
	}
	
}
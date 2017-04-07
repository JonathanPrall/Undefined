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

import cs.dal.csci3130.undined.backend.Review;

public class ReviewView extends FormLayout{
	
	Button done = new Button("Done", this::done);
	Button cancel = new Button("Cancel", this::cancel);
	
	TextField reviewWords = new TextField("Review");
	TextField name = new TextField("Name");
	
	Review review;
	
	@SuppressWarnings("deprecation")
	BeanFieldGroup<Review> formFieldBindings;
	
	public ReviewView() {
		configureComponents();
		buildLayout();
	}

	private void configureComponents() {
		
		done.setStyleName(ValoTheme.BUTTON_PRIMARY);
		done.setClickShortcut(ShortcutAction.KeyCode.ENTER);
	}
	
	private void buildLayout() {
		setSizeUndefined();
		setMargin(true);
		
		HorizontalLayout actions = new HorizontalLayout(done, cancel);
		actions.setSpacing(true);
		
		addComponents(actions, reviewWords, name);
		
	}
	
	public void done(Button.ClickEvent event) {	
		// Commit the fields from UI to DAO
		String msg = String.format("You have been DINED.");
		Notification.show(msg, Type.TRAY_NOTIFICATION);
		getUI().customerView.refreshAll();
	}
	
	void edit(Review review) {
		this.review = review;
		if(review != null) {
			formFieldBindings = BeanFieldGroup.bindFieldsBuffered(review, this);
			reviewWords.focus();
		}
		setVisible(review != null);
	}
	
	public void cancel(Button.ClickEvent event) {
		getUI().customerView.refreshAll();
	}
	
	
	public IndexUI getUI() {
		return (IndexUI) super.getUI();
	}
	
}

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

import cs.dal.csci3130.undined.backend.Menu;
import cs.dal.csci3130.undined.backend.MenuItem;
import cs.dal.csci3130.undined.backend.Restaurant;
import cs.dal.csci3130.undined.backend.Review;

public class ReviewView extends FormLayout{


    TextField reviewWords = new TextField("Review");
    TextField name = new TextField("Name");



    Grid reviewThing = new Grid();

    Review review;

    @SuppressWarnings("deprecation")
    BeanFieldGroup<Review> formFieldBindings;

    public ReviewView() {
	configureComponents();
	buildLayout();
    }

    private void configureComponents() {

	reviewThing.setContainerDataSource(new BeanItemContainer<>(Review.class));
	reviewThing.setColumnOrder("name","review");
	reviewThing.removeColumn("id");
	reviewThing.setSelectionMode(Grid.SelectionMode.SINGLE);
    }

    private void buildLayout() {
	setSizeUndefined();
	setMargin(true);

	addComponents(reviewThing);

    }

    void edit(Review review) {
	this.review = review;
	if(review != null) {
	    formFieldBindings = BeanFieldGroup.bindFieldsBuffered(review, this);
	    reviewWords.focus();
	}
	setVisible(review != null);
    }
    
    public IndexUI getUI() {
	return (IndexUI) super.getUI();
    }

}
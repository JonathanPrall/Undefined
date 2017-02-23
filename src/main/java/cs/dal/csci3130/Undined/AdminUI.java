package cs.dal.csci3130.Undined;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.Grid;
import com.vaadin.v7.ui.TextField;

import cs.dal.csci3130.Undined.backend.Restaurant;
import cs.dal.csci3130.Undined.backend.RestaurantService;




/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Title("Admin page")
@Theme("valo")
@Widgetset("com.vaadin.v7.Vaadin7WidgetSet")
public class AdminUI extends UI {

	TextField filter = new TextField();
	Grid requestList = new Grid();
	Button edit = new Button("Edit");
	
	RequestForm requestForm = new RequestForm();
	
	
	RestaurantService service = RestaurantService.createService();
	
	
	
    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	configureComponents();
    	buildLayout();
    	
    }

    private void configureComponents() {
		edit.addClickListener(e -> requestForm.edit(new Restaurant()));
		
		filter.setInputPrompt("Filter Requests");
		filter.addTextChangeListener(e -> refreshRequests(e.getText()));
		
		requestList.setContainerDataSource(new BeanItemContainer<>(Restaurant.class));
		requestList.setColumnOrder("id", "restaurantName","location");
		requestList.removeColumn("foodType");
		requestList.setSelectionMode(Grid.SelectionMode.SINGLE);
		requestList.addSelectionListener(
				e -> requestForm.edit((Restaurant) requestList.getSelectedRow()));
		refreshRequests();
	}
    

	private void buildLayout() {
		HorizontalLayout actions = new HorizontalLayout(filter, edit);
		actions.setWidth("100%");
		filter.setWidth("100%");
		actions.setExpandRatio(filter, 1);
		
		VerticalLayout left = new VerticalLayout(actions, requestList);
		left.setSizeFull();
		requestList.setSizeFull();
		left.setExpandRatio(requestList, 1);
		
		HorizontalLayout mainLayout = new HorizontalLayout(left, requestForm);
		mainLayout.setSizeFull();
		mainLayout.setExpandRatio(left, 1);
		
		setContent(mainLayout);
	}

	void refreshRequests() {
		refreshRequests(filter.getValue());
	}
	
	private void refreshRequests(String stringFilter) {
		requestList.setContainerDataSource(new BeanItemContainer<>(
				Restaurant.class, service.findAll(stringFilter)));
		requestForm.setVisible(false);
		
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = AdminUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}

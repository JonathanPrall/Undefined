package cs.dal.csci3130.undined.dashboard.view;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.Grid;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.TextField;

import cs.dal.csci3130.undined.domain.Restaurant;
import cs.dal.csci3130.undined.newbackend.services.RestaurantService;




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
	Grid acceptedList = new Grid();
	Grid rejectedList = new Grid();
	
	RequestForm requestForm = new RequestForm();
	EditForm editForm = new EditForm();
	
	RestaurantService service = RestaurantService.createService();
	
	
	
    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	configureComponents();
    	buildLayout();
    }

    private void configureComponents() {
		
		filter.setInputPrompt("Filter Requests");
		filter.addTextChangeListener(e -> refreshAll(e.getText()));
		
		requestList.setContainerDataSource(new BeanItemContainer<>(Restaurant.class));
		requestList.setColumnOrder("id", "restaurantName","foodType","location","hoursOfBusiness");
		requestList.removeColumn("status");
		requestList.setSelectionMode(Grid.SelectionMode.SINGLE);
		requestList.addSelectionListener(
				e -> requestForm.edit((Restaurant) requestList.getSelectedRow()));
		refreshAll();
		
		acceptedList.setContainerDataSource(new BeanItemContainer<>(Restaurant.class));
		acceptedList.setColumnOrder("id", "restaurantName","foodType","location","hoursOfBusiness");
		acceptedList.removeColumn("status");
		acceptedList.setSelectionMode(Grid.SelectionMode.SINGLE);
		acceptedList.addSelectionListener(
				e -> editForm.edit((Restaurant) acceptedList.getSelectedRow()));
		refreshAll();
		
		rejectedList.setContainerDataSource(new BeanItemContainer<>(Restaurant.class));
		rejectedList.setColumnOrder("id", "restaurantName","foodType","location","hoursOfBusiness");
		rejectedList.removeColumn("status");
		rejectedList.setSelectionMode(Grid.SelectionMode.SINGLE);
		rejectedList.addSelectionListener(
				e -> requestForm.edit((Restaurant) rejectedList.getSelectedRow()));
		refreshAll();
	}
    

	private void buildLayout() {
		
		HorizontalLayout actions = new HorizontalLayout(filter);
		actions.setWidth("100%");
		filter.setWidth("100%");
		actions.setExpandRatio(filter, 1);
		
		TabSheet lists = new TabSheet();
		lists.setHeight(100.0f, Unit.PERCENTAGE);
		lists.addStyleName(ValoTheme.TABSHEET_FRAMED);
		lists.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);

		VerticalLayout tab1 = generateTab(requestList);
		VerticalLayout tab2 = generateTab(acceptedList);
		VerticalLayout tab3 = generateTab(rejectedList);
		
		lists.addTab(tab1, "Requests");
		lists.addTab(tab2, "Accepts");
		lists.addTab(tab3, "Rejects");
		
		
		VerticalLayout left = new VerticalLayout(actions, lists);
		left.setSizeFull();
		lists.setSizeFull();
		left.setExpandRatio(lists, 1);
		
		HorizontalLayout mainLayout = new HorizontalLayout(left, requestForm, editForm);
		mainLayout.setSizeFull();
		mainLayout.setExpandRatio(left, 1);
		
		setContent(mainLayout);
	}

	void refreshAll() {
		refreshAll(filter.getValue());
	}
	
	private void refreshAll(String stringFilter) {
		requestList.setContainerDataSource(new BeanItemContainer<>(
				Restaurant.class, service.findAll(stringFilter, 0)));
		acceptedList.setContainerDataSource(new BeanItemContainer<>(
				Restaurant.class, service.findAll(stringFilter, 1)));
		rejectedList.setContainerDataSource(new BeanItemContainer<>(
				Restaurant.class, service.findAll(stringFilter, -1)));
		requestForm.setVisible(false);
		editForm.setVisible(false);
		
	}
	
	private VerticalLayout generateTab(Grid t) {
		final Label label = new Label();
		label.setWidth(100.0f, Unit.PERCENTAGE);
		
		t.setSizeFull();
		final VerticalLayout layout = new VerticalLayout(label);
		layout.setMargin(true);
		layout.addComponent(t);
		return layout;
	}

//	@WebServlet(urlPatterns = "/Admin/*", name = "AdminServlet", asyncSupported = true)
//    @VaadinServletConfiguration(ui = AdminUI.class, productionMode = false)
//    public static class MyUIServlet extends VaadinServlet {
//    }
}

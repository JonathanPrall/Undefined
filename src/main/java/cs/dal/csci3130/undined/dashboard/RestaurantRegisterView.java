package cs.dal.csci3130.undined.dashboard;

import javax.servlet.annotation.WebServlet;

import java.util.*;
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

import cs.dal.csci3130.undined.backend.Restaurant;
import cs.dal.csci3130.undined.backend.services.RestaurantService;

/**
 * @author tony 
 *
 */
@Title("Restaurant Register page")
@Theme("valo")
@Widgetset("com.vaadin.v7.Vaadin7WidgetSet")
public class RestaurantRegisterView extends VerticalLayout{
	
	TextField restaurantName = new TextField("Restaurant Name");
	TextField foodType = new TextField("Food Type");
	TextField location = new TextField("Location");
	TextField hoursOfBusiness = new TextField("Hours Of Business");
	
	Button confirm = new Button("Confirm");
	
	RestaurantService service = RestaurantService.createService();
	Grid grid = new Grid();
	
	
	public RestaurantRegisterView() {
		configureComponents();
    	buildLayout();
	}
	
	private void configureComponents() {
		confirm.addClickListener(e -> {
			service.save(new Restaurant(service.nextID(), restaurantName.getValue(),foodType.getValue(),location.getValue(),hoursOfBusiness.getValue()));
			getList();
		});
		
		getList();
		grid.setColumnOrder("id", "restaurantName","foodType","location","hoursOfBusiness");
		grid.removeColumn("status");
		grid.setWidth("90%");
	}
	
	private void buildLayout() {
		VerticalLayout mainPage = new VerticalLayout();
		this.addComponents(restaurantName, foodType, location, hoursOfBusiness, confirm,grid);
		this.setMargin(true);
		this.setSpacing(true);
	}
	
	private void getList(){
		grid.setContainerDataSource(new BeanItemContainer<>(Restaurant.class, service.findAll()));
	}
	
	

}


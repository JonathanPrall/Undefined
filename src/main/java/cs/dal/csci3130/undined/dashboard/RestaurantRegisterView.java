package cs.dal.csci3130.undined.dashboard;

import javax.servlet.annotation.WebServlet;

import java.util.*;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
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
		VerticalLayout form = new VerticalLayout();
		VerticalLayout gridF = new VerticalLayout();
		HorizontalLayout main = new HorizontalLayout();
		
		form.addComponents(restaurantName, foodType, location, hoursOfBusiness, confirm);
		form.setMargin(true);
		form.setSpacing(true);
		gridF.addComponent(grid);
		gridF.setSizeFull();
		main.addComponents(gridF, form);
		main.setSizeFull();
		main.setExpandRatio(gridF, (float) 0.80);
		main.setExpandRatio(form, (float)0.20);
		
//		this.setExpandRatio(grid, 1);
		this.addComponents(main);
	}
	
	private void getList(){
		grid.setContainerDataSource(new BeanItemContainer<>(Restaurant.class, service.findAll()));
	}
	
	public IndexUI getUI() {
		return (IndexUI) super.getUI();
	}

}


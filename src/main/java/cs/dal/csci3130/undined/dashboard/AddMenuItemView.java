package cs.dal.csci3130.undined.dashboard;

import javax.servlet.annotation.WebServlet;

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

import cs.dal.csci3130.undined.backend.MenuItem;
import cs.dal.csci3130.undined.backend.Menu;


public class AddMenuItemView extends VerticalLayout {	
	
	TextField filter = new TextField();
	Grid menuList = new Grid();
	Button edit = new Button("Edit");
	
	MenuForm menuForm = new MenuForm();
	
	
	Menu service = Menu.createMenu();
	
	public AddMenuItemView() {
		configureComponents();
		buildLayout();
	}
	
//	
//    @Override
//    protected void init(VaadinRequest vaadinRequest) {
//    	configureComponents();
//    	buildLayout();
//    	
//    }

    private void configureComponents() {
    	
    	edit.addClickListener(e -> menuForm.edit(new MenuItem()));
		
		filter.setInputPrompt("Filter Menu Items");
		filter.addTextChangeListener(e -> refreshAll(e.getText()));
		
		menuList.setContainerDataSource(new BeanItemContainer<>(MenuItem.class));
		menuList.setColumnOrder("id", "name","description","price");
		menuList.setSelectionMode(Grid.SelectionMode.SINGLE);
		menuList.addSelectionListener(
				e -> menuForm.edit((MenuItem) menuList.getSelectedRow()));
		refreshAll();
	}
    

	private void buildLayout() {
		
		
		HorizontalLayout actions = new HorizontalLayout(filter, edit);
		actions.setWidth("100%");
		filter.setWidth("100%");
		actions.setExpandRatio(filter, 1);
		
		TabSheet lists = new TabSheet();
		lists.setHeight(100.0f, Unit.PERCENTAGE);
		lists.addStyleName(ValoTheme.TABSHEET_FRAMED);
		lists.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);

		VerticalLayout tab1 = generateTab(menuList);
		
		lists.addTab(tab1, "Menu Items");
		
		
		VerticalLayout left = new VerticalLayout(actions, lists);
		left.setSizeFull();
		lists.setSizeFull();
		left.setExpandRatio(lists, 1);
		
		HorizontalLayout main = new HorizontalLayout();
		
		
		main.addComponents(left, menuForm);
		main.setSizeFull();
		main.setExpandRatio(left, 1);
		
		this.addComponents(main);
	}

	void refreshAll() {
		refreshAll(filter.getValue());
	}
	
	private void refreshAll(String stringFilter) {
		menuList.setContainerDataSource(new BeanItemContainer<>(
				MenuItem.class, service.findAll(stringFilter)));
		menuForm.setVisible(false);
		
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
	public IndexUI getUI() {
		return (IndexUI) super.getUI();
	}
}
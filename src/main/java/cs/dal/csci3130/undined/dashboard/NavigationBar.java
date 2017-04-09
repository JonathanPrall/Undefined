package cs.dal.csci3130.undined.dashboard;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class NavigationBar extends HorizontalLayout
{
	Button logout = new Button("Log out");
	Button back = new Button("Back");
	
	public NavigationBar(){
		
		configureComponents();

	}
	
	private void configureComponents(){
		configureBack();
		configureLogout();
	}

	private void configureLogout(){
		logout.setIcon(FontAwesome.SIGN_OUT);
    	logout.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				getUI().changePage((getUI().loginView));
			}
    		
    	});
    	
    	this.addComponent(logout);
    	this.setComponentAlignment(logout, Alignment.MIDDLE_RIGHT);
    	this.setSizeFull();
	}
	
	private void configureBack(){
		back.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				getUI().changePage();
			}
    		
    	});
		
		back.setVisible(false);
		
		this.addComponent(back);
    	this.setComponentAlignment(back, Alignment.MIDDLE_LEFT);
	}

	public void showBackButton(){
		back.setVisible(true);
	}
	
	public void hideBackButton(){
		back.setVisible(false);
	}
	
	public IndexUI getUI() {
		return (IndexUI) super.getUI();
	}
}

package cs.dal.csci3130.undined.newbackend.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Menu database table.
 * 
 */
@Entity
@NamedQuery(name="Menu.findAll", query="SELECT m FROM Menu m")
public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MenuPK id;

	private String menuName;

	//bi-directional many-to-one association to Restaurant
	@ManyToOne
	private Restaurant restaurant;

	//bi-directional many-to-one association to MenuItem
	@OneToMany(mappedBy="menu")
	private List<MenuItem> menuItems;

	public Menu() {
	}

	public MenuPK getId() {
		return this.id;
	}

	public void setId(MenuPK id) {
		this.id = id;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public Restaurant getRestaurant() {
		return this.restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public List<MenuItem> getMenuItems() {
		return this.menuItems;
	}

	public void setMenuItems(List<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}

	public MenuItem addMenuItem(MenuItem menuItem) {
		getMenuItems().add(menuItem);
		menuItem.setMenu(this);

		return menuItem;
	}

	public MenuItem removeMenuItem(MenuItem menuItem) {
		getMenuItems().remove(menuItem);
		menuItem.setMenu(null);

		return menuItem;
	}

}
package cs.dal.csci3130.undined.newbackend.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MenuItem database table.
 * 
 */
@Entity
@NamedQuery(name="MenuItem.findAll", query="SELECT m FROM MenuItem m")
public class MenuItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idMenuItem;

	private String description;

	private String name;

	private float price;

	//bi-directional many-to-one association to Menu
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="Menu_idMenu", referencedColumnName="idMenu"),
		@JoinColumn(name="Menu_Restaurant_idRestaurant", referencedColumnName="Restaurant_idRestaurant")
		})
	private Menu menu;

	public MenuItem() {
	}

	public int getIdMenuItem() {
		return this.idMenuItem;
	}

	public void setIdMenuItem(int idMenuItem) {
		this.idMenuItem = idMenuItem;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

}
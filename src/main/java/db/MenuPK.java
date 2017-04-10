package db;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Menu database table.
 * 
 */
@Embeddable
public class MenuPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idMenu;

	@Column(insertable=false, updatable=false)
	private int restaurant_idRestaurant;

	public MenuPK() {
	}
	public int getIdMenu() {
		return this.idMenu;
	}
	public void setIdMenu(int idMenu) {
		this.idMenu = idMenu;
	}
	public int getRestaurant_idRestaurant() {
		return this.restaurant_idRestaurant;
	}
	public void setRestaurant_idRestaurant(int restaurant_idRestaurant) {
		this.restaurant_idRestaurant = restaurant_idRestaurant;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MenuPK)) {
			return false;
		}
		MenuPK castOther = (MenuPK)other;
		return 
			(this.idMenu == castOther.idMenu)
			&& (this.restaurant_idRestaurant == castOther.restaurant_idRestaurant);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idMenu;
		hash = hash * prime + this.restaurant_idRestaurant;
		
		return hash;
	}
}
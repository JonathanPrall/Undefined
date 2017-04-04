package cs.dal.csci3130.undined.newbackend.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Restaurant database table.
 * 
 */
@Entity
@NamedQuery(name="Restaurant.findAll", query="SELECT r FROM Restaurant r")
public class Restaurant implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idRestaurant;

	private String foodType;

	private String hoursOfBusiness;

	private String location;

	private String menuId;

	private String restaurantName;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="restaurants")
	private List<User> users1;

	//bi-directional many-to-one association to Menu
	@OneToMany(mappedBy="restaurant")
	private List<Menu> menus;

	//bi-directional many-to-one association to Review
	@OneToMany(mappedBy="restaurant")
	private List<Review> reviews;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="restaurant")
	private List<User> users2;

	public Restaurant() {
	}

	public int getIdRestaurant() {
		return this.idRestaurant;
	}

	public void setIdRestaurant(int idRestaurant) {
		this.idRestaurant = idRestaurant;
	}

	public String getFoodType() {
		return this.foodType;
	}

	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}

	public String getHoursOfBusiness() {
		return this.hoursOfBusiness;
	}

	public void setHoursOfBusiness(String hoursOfBusiness) {
		this.hoursOfBusiness = hoursOfBusiness;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMenuId() {
		return this.menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getRestaurantName() {
		return this.restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public List<User> getUsers1() {
		return this.users1;
	}

	public void setUsers1(List<User> users1) {
		this.users1 = users1;
	}

	public List<Menu> getMenus() {
		return this.menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public Menu addMenus(Menu menus) {
		getMenus().add(menus);
		menus.setRestaurant(this);

		return menus;
	}

	public Menu removeMenus(Menu menus) {
		getMenus().remove(menus);
		menus.setRestaurant(null);

		return menus;
	}

	public List<Review> getReviews() {
		return this.reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public Review addReview(Review review) {
		getReviews().add(review);
		review.setRestaurant(this);

		return review;
	}

	public Review removeReview(Review review) {
		getReviews().remove(review);
		review.setRestaurant(null);

		return review;
	}

	public List<User> getUsers2() {
		return this.users2;
	}

	public void setUsers2(List<User> users2) {
		this.users2 = users2;
	}

	public User addUsers2(User users2) {
		getUsers2().add(users2);
		users2.setRestaurant(this);

		return users2;
	}

	public User removeUsers2(User users2) {
		getUsers2().remove(users2);
		users2.setRestaurant(null);

		return users2;
	}

}
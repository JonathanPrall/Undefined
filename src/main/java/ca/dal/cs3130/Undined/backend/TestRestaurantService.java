package my.vaadin.Undined.backend;

import java.util.List;

public class TestRestaurantService{
	public static void main(String[] args){
		
		
		RestaurantService restaurantService = RestaurantService.createDemoService();
		
		List<Restaurant> restaurants = restaurantService.findAll(null);
		
		for(int i =0 ; i < restaurants.size(); i++){
			System.out.println(restaurants.get(i).getRestaurantName());
			System.out.println(restaurants.get(i).getFoodType());
			System.out.println(restaurants.get(i).getLocation());
			System.out.println(restaurants.get(i).getHoursOfBusiness() + "\n");
		}
		
		
	}
}
package my.vaadin.Undined.backend;

import java.util.List;

public class TestUserService{
	public static void main(String[] args){
		
		
		UserService userService = UserService.createDemoService();
		
		List<User> peoples = userService.findAll(null);
		
		for(int i =0 ; i < peoples.size(); i++){
			System.out.println(peoples.get(i).getUserName());
			System.out.println(peoples.get(i).getEmailAddress());
			System.out.println(peoples.get(i).getPhoneNumber() + "\n");
		}
		
		
	}
}
package my.vaadin.Undined.backend;

import java.util.ArrayList;
import java.util.List;

public class TestMenuService{
	public static void main(String[] args){
		
		boolean fail = false;
		
		MenuService menuService = MenuService.createDemoService();
		
		List<Menu> menus = menuService.findAll(null);
		
		for(int i =0 ; i < menus.size(); i++){
			System.out.println(menus.get(i).getMenuName());
			
			ArrayList<MenuItem> list = menus.get(i).getMenu();
			
			if(list.size() == 0){
				fail = true;
			}
			
			
			for(int item = 0; item < list.size(); item++){
				System.out.println(list.get(item) + "\n");
			}
			

		}
		
		
		if(fail){
			System.out.println("Test failed. Menu Items aren't saving for some reason.");
		}
		
	}
}
package com.flp.ems.view;

import java.util.Scanner;

public class BootClass {

	public static void main(String[] args) {
		
		selectMenu();
	}
	
	private static void selectMenu(){
		UserInteraction user = new UserInteraction();
		while(true){
			System.out.println("Please select one of the following options :\n1. Add new employee \n2. Modify an existing employee\n3. Remove an existing employee\n4. Search employee\n5. get all employee\n6. Exit");
			Scanner option = new Scanner(System.in);
			int choice = option.nextInt();
			switch(choice){
				
			case 1:
				user.addEmployee();
				break;
				
			case 2:
				user.ModifyEmployee();
				break;
			
			case 3:
				user.RemoveEmployee();
				break;
				
			case 4:
				user.SearchEmployee();
				break;
				
			case 5:
				user.getAllEmployee();
				break;
			
			case 6:
				System.exit(0);
				break;
			default :
				System.out.println("Please Enter valid option ");
				selectMenu();
			
			
			
			
			}
			
		
		}
		
		
		
		
	}
	
}

package com.flp.ems.view;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.flp.ems.service.EmployeeServiceImpl;
import com.flp.ems.util.Validate;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

public class UserInteraction {

	
	static int id;
	EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
	public void addEmployee() {
		String name, kinId, emailId, phoneNumber, address, birthDate, joiningDate, departmentId, projectId, roleId;
		HashMap<String, String> employee= new HashMap<String, String>();
		System.out.println("Enter Your personal information:");
		Scanner information = new Scanner(System.in);
		System.out.println("Name:");
		name = information.nextLine();
		try {
			Validate.validateName(name);
		} catch (Exception e2) {
			e2.printStackTrace();
			addEmployee();
		}
		System.out.println("Phone Number:");
		phoneNumber = information.nextLine();
		try {
			Validate.validatePhoneNumber(phoneNumber);
		} catch (Exception e2) {
			e2.printStackTrace();
			addEmployee();
		}
		System.out.println("Address:");
		address = information.nextLine();
		System.out.println("Date of birth(dd/mm/yyyy):");
		birthDate = information.nextLine();
		try {
			Validate.validateDate(birthDate);
		} catch (Exception e1) {
			
			e1.printStackTrace();
			System.out.println("Date of birth(dd/mm/yyyy):");
			birthDate = information.nextLine();
		}
		System.out.println("Date of joining(dd/mm/yyyy):");
		joiningDate = information.nextLine();
		try {
			Validate.validateDate(joiningDate);
		} catch (Exception e1) {
			
			e1.printStackTrace();
			System.out.println("Date of joining(dd/mm/yyyy):");
			joiningDate = information.nextLine();
		}
		try {
			id = employeeService.getNumberOfEmployees();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		id++;
		kinId = id + "_FS";
		emailId = kinId + "." + name.replaceAll("\\s", "") + "@barclays.com";
		System.out.println("Please select one of the following departments :\n1. Barclaycard\n2. PCB");
		departmentId = information.nextLine();
		System.out.println("Please select one of the following Projects :\n1. CWS\n2. BAPI");
		projectId = information.nextLine();
		System.out.println("Please select one of the following Roles :\n1. BA3\n2. BA4");
		roleId = information.nextLine();
		employee.put("name", name);
		employee.put("kinId", kinId);
		employee.put("emailId", emailId.toLowerCase());
		employee.put("phoneNumber", phoneNumber);
		employee.put("address", address);
		employee.put("birthDate", birthDate);
		employee.put("joiningDate", joiningDate);
		employee.put("departmentId", departmentId);
		employee.put("projectId", projectId);
		employee.put("roleId", roleId);
		try {
			
			employeeService.addEmployee(employee);
		} catch (Exception e) {
			
			e.printStackTrace();
			addEmployee();
		}
	}

	public void ModifyEmployee() {
		
		getAllEmployee();
		System.out.println("Please enter the Kin Id of the employee you want to modify :\nKin Id\n");
		String kinId = new Scanner(System.in).nextLine();
		System.out.println("Plese enter the field name that you want to modify address, phone number or exit:");
		HashMap<String, String> modifyEmp = new HashMap<String, String>();
		modifyEmp.put("kinId", kinId);
		switch(new Scanner(System.in).nextLine().toLowerCase()){
		
		case "phone number" :
			System.out.println("Please enter your phone number:");
			modifyEmp.put("phoneNumber", new Scanner(System.in).nextLine());
			break;
			
		case "address" :
			System.out.println("Please enter your new address:");
			modifyEmp.put("address", new Scanner(System.in).nextLine());
			break;
		
		default :
			System.out.println("You can modify your phone number or address only");
			ModifyEmployee();
			break;
		}
		try{
			if(employeeService.modifyEmployee(modifyEmp)){
				System.out.println("Entry modified successfully");
			}
			else{
				System.err.println("Something went wrong..Please try again");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
	}

	public void RemoveEmployee() {
		getAllEmployee();
		System.out.println("Please choose the criteria to delete the employee:\n1.Kin Id\n2.Email Id\n3.Name");
		HashMap<String, String> remEmployeeMap = new HashMap<String, String>();
		Scanner input = new Scanner(System.in);
		switch(input.nextInt()){
		
		case 1: 
			System.out.println("Please enter the kinId of the employee you want to remove:\nKin Id: ");
			remEmployeeMap.put("kinId", new Scanner(System.in).nextLine());
			
			break;
		case 2:
			System.out.println("Please enter the Email Id of the employee you want to remove:\nEmail Id: ");
			remEmployeeMap.put("emailId", new Scanner(System.in).nextLine());
			break;
			
		case 3:
			System.out.println("Please enter the name of the employee you want to remove:\nName : ");
			remEmployeeMap.put("name", new Scanner(System.in).nextLine());
			break;
			
		default :
				System.out.println("Please enter a valid option");
				RemoveEmployee();
				break;
			
		
		
		}
		try{
			if(employeeService.removeEmployee(remEmployeeMap)){
				System.out.println("removed successfully");
			}
			else{
				System.err.println("Something went wrong..Please try again");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public void SearchEmployee() {
		
		System.out.println("Please select a search criteria from the following menu:\n1.Only name\n2.Only Kin Id\n3.Only Email  Id\n4. name and kin Id\n5. name and email id\n6. email and kin id\n7. all three\n");
		Scanner search = new Scanner(System.in);
		int choice = search.nextInt();
		HashMap<String,String> searchEmp = new HashMap<String, String>();
		switch(choice){
		case 1:
			System.out.println("Name:");
			searchEmp.put("name", new Scanner(System.in).nextLine());
			break;
		case 2:
			System.out.println("Kin Id:");
			searchEmp.put("kinId", new Scanner(System.in).nextLine());
			break;
			
		case 3:
			System.out.println("Email Id:");
			searchEmp.put("emailId",new Scanner(System.in).nextLine());
			break;
		case 4:
			System.out.println("Kin Id:");
			searchEmp.put("kinId", new Scanner(System.in).nextLine());
			System.out.println("Name:");
			searchEmp.put("name", new Scanner(System.in).nextLine());
			break;
		case 5:
			System.out.println("Email Id:");
			searchEmp.put("emailId", search.nextLine());
			System.out.println("Name:");
			searchEmp.put("name", new Scanner(System.in).nextLine());
			break;
		case 6:
			System.out.println("Kin Id:");
			searchEmp.put("kinId",new Scanner(System.in).nextLine());
			System.out.println("Email Id:");
			searchEmp.put("emailId", new Scanner(System.in).nextLine());
			break;
		case 7:
			System.out.println("Kin Id:");
			searchEmp.put("kinId",new Scanner(System.in).nextLine());
			System.out.println("Email Id:");
			searchEmp.put("emailId", new Scanner(System.in).nextLine());
			System.out.println("Name:");
			searchEmp.put("name", new Scanner(System.in).nextLine());
			break;
		
		default :
				System.out.println("Please enter valid option");
				SearchEmployee();
		}
		ArrayList<HashMap<String, String>> employeeMap = null;
		try {
			employeeMap = this.employeeService.searchEmployee(searchEmp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(employeeMap == null){
			System.out.println("The required employee does not exist");
			
		}
		else{
			for (HashMap<String, String> hashMap : employeeMap) {
				System.out.println(hashMap.entrySet().toString());
			}
		}
	}

	public void getAllEmployee() {
	
		ArrayList<HashMap<String, String>> employeesList;
		try {
			employeesList = employeeService.getAllEmployee();
			for (HashMap<String, String> hashMap : employeesList) {
				System.out.println(hashMap.entrySet().toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
	}

}

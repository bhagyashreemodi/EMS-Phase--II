package com.flp.ems.service;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import com.flp.ems.dao.EmployeeDaoImplForDB;
import com.flp.ems.domain.Employee;

public class EmployeeServiceImpl implements IEmployeeService{

	
	EmployeeDaoImplForDB employees = new EmployeeDaoImplForDB();
	
	@Override
	public void addEmployee(HashMap<String, String> employeeInfo) throws Exception {
		
		Employee employee = new Employee();
		employee.setName(employeeInfo.get("name"));
		employee.setKinId(employeeInfo.get("kinId"));
		employee.setEmailId(employeeInfo.get("emailId"));
		employee.setPhoneNumber(Long.valueOf(employeeInfo.get("phoneNumber")));
		employee.setAddres(employeeInfo.get("address"));
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date birthDate = format.parse(employeeInfo.get("birthDate"));
		employee.setBirthDate(birthDate);
		Date joiningDate = format.parse(employeeInfo.get("joiningDate"));
		employee.setJoiningDate(joiningDate);
		employee.setDepartmentId(Integer.valueOf(employeeInfo.get("departmentId")));
		employee.setProjectId(Integer.valueOf(employeeInfo.get("projectId")));
		employee.setRoleId(Integer.valueOf(employeeInfo.get("roleId")));
		employees.addEmployee(employee);
	
	}
	

	@Override
	public boolean modifyEmployee(HashMap<String, String> modifyEmp) throws Exception {
		
		//ArrayList<Employee> employees = this.employees.getAllEmployee();
		Employee modifyEmployee = this.employees.getEmpForModification(modifyEmp.get("kinId"));
		Employee employee =null;
		
		if(modifyEmployee == null){
			return false;
		}
		try {
			employee = (Employee) modifyEmployee.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		/*if(modifyEmp.containsKey("phoneNumber")){
			employee.setPhoneNumber(Long.parseLong(modifyEmp.get("phoneNumber")));
		}
		if(modifyEmp.containsKey("address")){
			employee.setAddres(modifyEmp.get("address"));
		}*/
		
		/*if(!(modifyEmp.get("name").isEmpty()))
			employee.setName(modifyEmp.get("name"));*/
		if(!(modifyEmp.get("phoneNumber").isEmpty()))
			employee.setPhoneNumber(Long.parseLong(modifyEmp.get("phoneNumber")));
		if(!(modifyEmp.get("address").isEmpty()))
			employee.setAddres(modifyEmp.get("address"));
		if(!(modifyEmp.get("departmentId").isEmpty()))
			employee.setDepartmentId(Integer.parseInt(modifyEmp.get("departmentId")));
		if(!(modifyEmp.get("projectId").isEmpty()))
			employee.setProjectId(Integer.parseInt(modifyEmp.get("projectId")));
		if(!(modifyEmp.get("roleId").isEmpty()))
			employee.setRoleId(Integer.parseInt(modifyEmp.get("roleId")));
		
		
		
		return this.employees.modifyEmployee(employee);
	}

	@Override
	public boolean removeEmployee(HashMap<String, String> remEmployeeMap) throws Exception{
		
		Employee employee = new Employee();
		employee.setName(remEmployeeMap.get("name"));
		employee.setKinId(remEmployeeMap.get("kinId"));
		employee.setEmailId(remEmployeeMap.get("emailId"));
		ArrayList<Employee> employeesArr = this.employees.searchEmployee(employee);
		if(employeesArr.size() == 0){
			System.out.println("No such employee found");
			return false;
		}
		else{
			System.out.println("Do u want to delete?\n1.yes\n2. no");
			if(new Scanner(System.in).nextInt() == 1){
				
				for (Employee employee2 : employeesArr) {
					boolean flag = this.employees.removeEmployee(employee2.getKinId());
					if(flag == false){
						return false;
					}
					else{
						continue;
					}
				}
			}
			else{
				return false;
				
			}
		}
		return true;
	}

	@Override
	public ArrayList<HashMap<String, String>> searchEmployee(HashMap<String, String> employeeInfo) throws Exception{
		Employee employee = new Employee();
		employee.setName(employeeInfo.get("name"));
		employee.setKinId(employeeInfo.get("kinId"));
		employee.setEmailId(employeeInfo.get("emailId"));
		ArrayList<Employee> employeesArr = this.employees.searchEmployee(employee);
		ArrayList	<HashMap<String, String>> employeesMap = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < employeesArr.size(); i++) {
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("kinId", (employeesArr.get(i).getKinId()));
			map.put("emailId", (employeesArr.get(i).getEmailId()));
			map.put("name", (employeesArr.get(i).getName()));
			map.put("address", (employeesArr.get(i).getAddres()));
			map.put("phoneNumber", (String.valueOf(employeesArr.get(i).getPhoneNumber())));
			employeesMap.add(map);
		}
		return employeesMap;
	}

	@Override
	public ArrayList<HashMap<String, String>> getAllEmployee() throws Exception {
		ArrayList<Employee> employeesArr = this.employees.getAllEmployee();
	ArrayList	<HashMap<String, String>> employeesMap = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < employeesArr.size(); i++) {
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("kinId", (employeesArr.get(i).getKinId()));
			map.put("emailId", (employeesArr.get(i).getEmailId()));
			map.put("name", (employeesArr.get(i).getName()));
			map.put("address", (employeesArr.get(i).getAddres()));
			map.put("phoneNumber", (String.valueOf(employeesArr.get(i).getPhoneNumber())));
			employeesMap.add(map);
		}
		return employeesMap;
	}

	public int getNumberOfEmployees()throws Exception{
		return employees.getNumberOfEmployees();		
	}
	
	
}

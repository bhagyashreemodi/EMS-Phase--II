package com.flp.ems.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public interface IEmployeeService {

	void addEmployee(HashMap<String, String> employee) throws Exception;
	boolean modifyEmployee(HashMap<String, String> modifyEmp) throws Exception;
	boolean removeEmployee(HashMap<String, String> remEmployeeMap) throws Exception;
	ArrayList<HashMap<String, String>> searchEmployee(HashMap<String, String> searchEmp) throws Exception;
	ArrayList<HashMap<String, String>> getAllEmployee() throws Exception;
	
	
	
	
	
	
	
}

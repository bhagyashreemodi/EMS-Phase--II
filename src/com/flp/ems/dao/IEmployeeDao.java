package com.flp.ems.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import com.flp.ems.domain.Employee;

public interface IEmployeeDao {

	void addEmployee(Employee employee) throws Exception;
	boolean modifyEmployee(Employee modifyEmployee) throws Exception;
	boolean removeEmployee(String kinId) throws Exception;
	ArrayList<Employee> searchEmployee(Employee employee) throws Exception;
	ArrayList<Employee> getAllEmployee() throws Exception;
	Employee getEmpForModification(String kinId) throws Exception;
	
	
	
}

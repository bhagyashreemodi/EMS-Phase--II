package com.flp.ems.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import com.flp.ems.domain.Employee;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;



public class EmployeeDaoImplForDB implements IEmployeeDao{

	
	Properties props = new Properties();
	FileInputStream propsFile;
	Connection dbConnection;
	@Override
	public void addEmployee(Employee employee) throws Exception {
		
		propsFile = new FileInputStream("ems.properties");
		props.load(propsFile);
		PreparedStatement insertStatement=null;
		try{
		
			dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test");
			insertStatement = dbConnection.prepareStatement(props.getProperty("jdbc.query.insert.employee"));
			insertStatement.setString(1, employee.getName());
			insertStatement.setString(2, employee.getKinId());
			System.out.println(employee.getKinId());
			insertStatement.setString(3, employee.getEmailId());
			insertStatement.setLong(4, employee.getPhoneNumber());
			insertStatement.setDate(5, new Date(employee.getBirthDate().getTime()));
			insertStatement.setDate(6,new Date(employee.getJoiningDate().getTime()));
			insertStatement.setString(7, employee.getAddres());
			insertStatement.setInt(8, employee.getDepartmentId());
			insertStatement.setInt(9, employee.getProjectId());
			insertStatement.setInt(10, employee.getRoleId());
			insertStatement.executeUpdate();
			
		}finally{
			dbConnection.close();
			insertStatement.close();
		}
		
		
		
		
		
	}

	@Override
	public boolean modifyEmployee(Employee modifyEmployee) throws Exception{
		
		if(modifyEmployee != null){
			propsFile = new FileInputStream("ems.properties");
			props.load(propsFile);
			PreparedStatement selectStatement = null;
			ResultSet employee = null;
			try{
				dbConnection = DriverManager.getConnection(props.getProperty("jdbc.url"));
				selectStatement = dbConnection.prepareStatement(props.getProperty("jdbc.query.select.modify"), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
				selectStatement.setString(1, modifyEmployee.getKinId());
				employee = selectStatement.executeQuery();
				employee.next();
				employee.updateLong("phone_number", modifyEmployee.getPhoneNumber());
				employee.updateDate("birth_date", (Date) modifyEmployee.getBirthDate());
				employee.updateDate("joining_date", (Date) modifyEmployee.getJoiningDate());
				employee.updateString("address", modifyEmployee.getAddres());
				employee.updateInt("department_id", modifyEmployee.getDepartmentId());
				employee.updateInt("project_id", modifyEmployee.getProjectId());
				employee.updateInt("role_id", modifyEmployee.getRoleId());
				employee.updateRow();
			}finally{
				dbConnection.close();
				selectStatement.close();
			}
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public boolean removeEmployee(String kinId) throws Exception {
		
		if(kinId != null){
			
			propsFile = new FileInputStream("ems.properties");
			props.load(propsFile);
			PreparedStatement deleteStatement = null;
			try{
				dbConnection = DriverManager.getConnection(props.getProperty("jdbc.url"));
				deleteStatement = dbConnection.prepareStatement(props.getProperty("jdbc.query.delete.employee"));
				deleteStatement.setString(1, kinId);
				deleteStatement.executeUpdate();
				
				
				
			}finally{
				deleteStatement.close();
				dbConnection.close();
			}
			return true;
		}
		else{
			return false;
		}
		
		
		
	}

	@Override
	public ArrayList<Employee> searchEmployee(Employee employee) throws Exception {
		
		ArrayList<Employee> empl = new ArrayList<Employee>();
		propsFile = new FileInputStream("ems.properties");
		props.load(propsFile);
		PreparedStatement selectStatement = null;
		dbConnection = DriverManager.getConnection(props.getProperty("jdbc.url"));
		ResultSet selectResult = null;
		try{
			if(employee.getKinId() != null){
				
				selectStatement = dbConnection.prepareStatement("select * from employee where kin_id=?");
				selectStatement.setString(1, employee.getKinId());
				selectResult = selectStatement.executeQuery();
				selectResult.next();
				Employee tempEmployee = new Employee();
				tempEmployee.setName(selectResult.getString("name"));
				tempEmployee.setKinId(selectResult.getString("kin_id"));
				tempEmployee.setEmailId(selectResult.getString("email_id"));
				tempEmployee.setEmailId(selectResult.getString("email_id"));
				tempEmployee.setBirthDate(selectResult.getDate("birth_date"));
				tempEmployee.setJoiningDate(selectResult.getDate("joining_date"));
				tempEmployee.setAddres(selectResult.getString("address"));
				tempEmployee.setDepartmentId(selectResult.getInt("department_id"));
				tempEmployee.setProjectId(selectResult.getInt("project_id"));
				tempEmployee.setRoleId(selectResult.getInt("role_id"));
				empl.add(tempEmployee);
			}
			else if(employee.getEmailId() != null){
				selectStatement = dbConnection.prepareStatement("select * from employee where email_id=?");
				selectStatement.setString(1, employee.getEmailId());
				selectResult = selectStatement.executeQuery();
				selectResult.next();
				Employee tempEmployee = new Employee();
				tempEmployee.setName(selectResult.getString("name"));
				tempEmployee.setKinId(selectResult.getString("kin_id"));
				tempEmployee.setEmailId(selectResult.getString("email_id"));
				tempEmployee.setEmailId(selectResult.getString("email_id"));
				tempEmployee.setBirthDate(selectResult.getDate("birth_date"));
				tempEmployee.setJoiningDate(selectResult.getDate("joining_date"));
				tempEmployee.setAddres(selectResult.getString("address"));
				tempEmployee.setDepartmentId(selectResult.getInt("department_id"));
				tempEmployee.setProjectId(selectResult.getInt("project_id"));
				tempEmployee.setRoleId(selectResult.getInt("role_id"));
				empl.add(tempEmployee);
			}
			else{
				selectStatement = dbConnection.prepareStatement("select * from employee where name=?");
				selectStatement.setString(1, employee.getName());
				selectResult = selectStatement.executeQuery();
				while(selectResult.next()){
					Employee tempEmployee = new Employee();
					tempEmployee.setName(selectResult.getString("name"));
					tempEmployee.setKinId(selectResult.getString("kin_id"));
					tempEmployee.setEmailId(selectResult.getString("email_id"));
					tempEmployee.setEmailId(selectResult.getString("email_id"));
					tempEmployee.setBirthDate(selectResult.getDate("birth_date"));
					tempEmployee.setJoiningDate(selectResult.getDate("joining_date"));
					tempEmployee.setAddres(selectResult.getString("address"));
					tempEmployee.setDepartmentId(selectResult.getInt("department_id"));
					tempEmployee.setProjectId(selectResult.getInt("project_id"));
					tempEmployee.setRoleId(selectResult.getInt("role_id"));
					empl.add(tempEmployee);
				}
				
				
				
			}
		}finally{
			selectResult.close();
			selectStatement.close();
			dbConnection.close();
		}
		return empl;
		
	}

	@Override
	public ArrayList getAllEmployee() throws Exception{
		
		ArrayList<Employee> employees = new ArrayList<Employee>();
		propsFile = new FileInputStream("ems.properties");
		props.load(propsFile);
		PreparedStatement selectStatement = null;
		ResultSet selectResult = null;
		try{
			dbConnection = DriverManager.getConnection(props.getProperty("jdbc.url"));
			selectStatement=dbConnection.prepareStatement(props.getProperty("jdbc.query.select.all.employee"));
			selectResult=selectStatement.executeQuery();
			while(selectResult.next()){
				Employee tempEmployee = new Employee();
				tempEmployee.setName(selectResult.getString("name"));
				tempEmployee.setKinId(selectResult.getString("kin_id"));
				tempEmployee.setEmailId(selectResult.getString("email_id"));
				tempEmployee.setPhoneNumber(selectResult.getLong("phone_number"));
				tempEmployee.setBirthDate(selectResult.getDate("birth_date"));
				tempEmployee.setJoiningDate(selectResult.getDate("joining_date"));
				tempEmployee.setAddres(selectResult.getString("address"));
				tempEmployee.setDepartmentId(selectResult.getInt("department_id"));
				tempEmployee.setProjectId(selectResult.getInt("project_id"));
				tempEmployee.setRoleId(selectResult.getInt("role_id"));
				employees.add(tempEmployee);
			}
			
			
			
		}finally{
			selectResult.close();
			selectStatement.close();
			dbConnection.close();
		}
		return employees;
		
		
		
	}

	@Override
	public Employee getEmpForModification(String kinId) throws Exception{
		
		
		propsFile = new FileInputStream("ems.properties");
		props.load(propsFile);
		PreparedStatement selectStatement = null;
		
		ResultSet selectResult = null;
		Employee tempEmployee = new Employee();
		try{
			dbConnection = DriverManager.getConnection(props.getProperty("jdbc.url"));
			selectStatement = dbConnection.prepareStatement("select * from employee where kin_id=?");
			selectStatement.setString(1, kinId);
			selectResult = selectStatement.executeQuery();
			selectResult.next();
			tempEmployee.setName(selectResult.getString("name"));
			tempEmployee.setKinId(selectResult.getString("kin_id"));
			tempEmployee.setEmailId(selectResult.getString("email_id"));
			tempEmployee.setPhoneNumber(selectResult.getLong("phone_number"));
			tempEmployee.setBirthDate(selectResult.getDate("birth_date"));
			tempEmployee.setJoiningDate(selectResult.getDate("joining_date"));
			tempEmployee.setAddres(selectResult.getString("address"));
			tempEmployee.setDepartmentId(selectResult.getInt("department_id"));
			tempEmployee.setProjectId(selectResult.getInt("project_id"));
			tempEmployee.setRoleId(selectResult.getInt("role_id"));
		}finally{
			selectResult.close();
			selectStatement.close();
			dbConnection.close();
		}
		return tempEmployee;
		
	}

	
	public int getNumberOfEmployees()throws Exception{
		dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test");
		PreparedStatement selectStatement = dbConnection.prepareStatement("select * from employee");
		ResultSet result = selectStatement.executeQuery();
		int count = 0;
		while(result.next()){
			count++;
		}
		return count;		
	}
	

	
	
	
	
}

package com.flp.ems.domain;

public class Department {

	private static long departmentId;
	private String name, description;
	
	{
		departmentId++;
	}
	
	public static long getDepartmentId() {
		return departmentId;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}

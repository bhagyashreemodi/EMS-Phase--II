package com.flp.ems.domain;

public class Role {

	private static long roleId;
	private String name, description;
	
	{
		roleId++;
	}
	
	public static long getRoleId() {
		return roleId;
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

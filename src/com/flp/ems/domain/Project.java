package com.flp.ems.domain;

public class Project {

	private static long projectId;
	private String name, description;
	private long departmentId;
	
	{
		projectId++;
	}
	
	public static long getProjectId() {
		return projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}
	
}

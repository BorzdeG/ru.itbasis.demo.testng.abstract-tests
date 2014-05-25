package ru.itbasis.demo.testng.abstract_tests.model;

import java.util.List;

public class Organization {
	private List<Department> departments;

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}
}

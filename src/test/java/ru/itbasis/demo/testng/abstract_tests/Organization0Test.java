package ru.itbasis.demo.testng.abstract_tests;

import org.testng.annotations.DataProvider;

public class Organization0Test extends AbstractTests {

	@Override
	protected String getOrgName() {
		return "org0";
	}

	@DataProvider
	@Override
	public Object[][] dataDepartmentCount() {
		return new Object[][]{{2}};
	}

	@DataProvider
	@Override
	public Object[][] dataEmployeeCount() {
		return new Object[][]{{"dep1", 2}};
	}

	@DataProvider
	@Override
	public Object[][] dataEmployeeLastName() {
		return new Object[][]{{"dep0", 0, "empLastName0_0"}};
	}
}
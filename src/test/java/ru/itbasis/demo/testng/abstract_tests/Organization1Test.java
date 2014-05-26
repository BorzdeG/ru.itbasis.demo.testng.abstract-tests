package ru.itbasis.demo.testng.abstract_tests;

import org.testng.annotations.DataProvider;

public class Organization1Test extends AbstractTests {

	@Override
	protected String getOrgName() {
		return "org1";
	}

	@DataProvider
	@Override
	public Object[][] dataEmployeeCount() {
		return new Object[][]{{"dep1", 2}};
	}

	@DataProvider
	@Override
	public Object[][] dataEmployeeLastName() {
		return new Object[][]{{"dep2", 1, "empLastName2_1"}};
	}

}
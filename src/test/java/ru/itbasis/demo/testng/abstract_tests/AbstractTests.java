package ru.itbasis.demo.testng.abstract_tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.itbasis.demo.testng.abstract_tests.model.Department;
import ru.itbasis.demo.testng.abstract_tests.model.Employee;
import ru.itbasis.demo.testng.abstract_tests.model.Organization;

import java.io.File;
import java.util.List;

abstract public class AbstractTests {
	protected Organization organization;

	abstract protected String getOrgName();

	@DataProvider
	public Object[][] dataEmployeeCount() {
		return new Object[][]{};
	}

	@DataProvider
	public Object[][] dataEmployeeLastName() {
		return new Object[][]{};
	}

	@BeforeMethod
	public void setUp() throws Exception {
		File fRoot = new File(".");
		File orgDir = new File(fRoot, "src/test/resources/" + getOrgName());
		Assert.assertTrue(orgDir.exists());
		Assert.assertTrue(orgDir.isDirectory());

		organization = OrganizationLoader.loader(orgDir);
		Assert.assertNotNull(organization);
	}

	@Test(dataProvider = "dataDepartmentCount")
	public void testDepartmentCount(int count) throws Exception {
		Assert.assertEquals(organization.getDepartments().size(), count);
	}

	@Test(dependsOnMethods = {"testDepartmentCount"}, dataProvider = "dataEmployeeCount")
	public void testEmployeesCount(String depName, int countEmployee) throws Exception {
		final List<Department> departments = organization.getDepartments();
		int i = 0;
		Department department;
		do {
			department = departments.get(i++);
		} while (!department.getName().equals(depName));
		Assert.assertEquals(department.getName(), depName);
		Assert.assertEquals(department.getEmployees().size(), countEmployee);
	}

	@Test(dependsOnMethods = {"testDepartmentCount"}, dataProvider = "dataEmployeeLastName")
	public void testEmployeeLastName(String depName, int employeeIndex, String lastName) throws Exception {
		final List<Department> departments = organization.getDepartments();
		int i = 0;
		Department department;
		do {
			department = departments.get(i++);
		} while (!department.getName().equals(depName));
		Assert.assertEquals(department.getName(), depName);
		Employee employee = department.getEmployees().get(employeeIndex);
		Assert.assertEquals(employee.getLastName(), lastName);
	}

}

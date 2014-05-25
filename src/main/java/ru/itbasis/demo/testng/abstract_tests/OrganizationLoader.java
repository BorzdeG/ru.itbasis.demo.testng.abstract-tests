package ru.itbasis.demo.testng.abstract_tests;

import com.thoughtworks.xstream.XStream;
import org.apache.commons.io.FileUtils;
import ru.itbasis.demo.testng.abstract_tests.model.Department;
import ru.itbasis.demo.testng.abstract_tests.model.Organization;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrganizationLoader {
	private Organization organization;

	public OrganizationLoader(File orgDirectory) {
		organization = null;

		if (orgDirectory == null || !orgDirectory.exists() || !orgDirectory.isDirectory()) {
			return;
		}

		final File[] files = orgDirectory.listFiles();
		if (files == null || files.length < 1) {
			return;
		}

		XStream xStream = new XStream();
		xStream.autodetectAnnotations(true);

		organization = new Organization();

		List<Department> departments = new ArrayList<>();
		for (File file : files) {
			try {
				String xml = FileUtils.readFileToString(file, "UTF-8");
				Department department = (Department) xStream.fromXML(xml);
				departments.add(department);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		organization.setDepartments(departments);
	}

	public Organization getOrganization() {
		return organization;
	}
}

package com.app.xml;

import com.app.xml.model.Employee;
import com.app.xml.service.EmployeeDOM;

public class Test {

	public static void main(String[] args) {
		EmployeeDOM dom = new EmployeeDOM();
		dom.update(new Employee("Pankaj", 30.00, "Manager", "MALE"));
		dom.add(new Employee("John", 20.00, "Intern", "MALE"));
	}

}

package com.app.xml.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.app.xml.helper.DomHelper;
import com.app.xml.model.Employee;

public class EmployeeDOM {
	
	public void add(Employee emp) {
		Document document = null;
		try {
			document = DomHelper.getDocument(new FileInputStream(new File("E:\\employee.xml")));
			Element employees = document.getDocumentElement();
			
			//Create Employee Tag
			Element employee = document.createElement("Employee");
			
			//Create Name Tag
			Element name = document.createElement("name");
			name.appendChild(document.createTextNode(emp.getName()));
			employee.appendChild(name);//Add name to Emp
			
			Element age = document.createElement("age");
			age.appendChild(document.createTextNode(emp.getAge().toString()));
			employee.appendChild(age);
			
			Element role = document.createElement("role");
			role.appendChild(document.createTextNode(emp.getRole()));
			employee.appendChild(role);
			
			Element gender = document.createElement("gender");
			gender.appendChild(document.createTextNode(emp.getGender()));
			employee.appendChild(gender);
			
			employees.appendChild(employee);
			
			//Write To File
			DomHelper.saveXML(document, Paths.get("E:\\employee.xml"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Employee employee){

		Document document = null;
		try {
			document = DomHelper.getDocument(new FileInputStream(new File("E:\\employee.xml")));
			NodeList nodeList = document.getElementsByTagName("Employee");
			for(int i = 0; i< nodeList.getLength();i++) {
				Element eleEmployee = (Element) nodeList.item(i);
				if(eleEmployee.getElementsByTagName("name").item(0).getTextContent().equals(employee.getName())) {
					eleEmployee.getElementsByTagName("role").item(0).setTextContent(employee.getRole());
				}
			}
			DomHelper.saveXML(document, Paths.get("E:\\Files\\empoyee.xml"));
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}

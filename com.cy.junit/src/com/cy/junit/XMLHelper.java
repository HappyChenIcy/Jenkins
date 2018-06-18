package com.cy.junit;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.XPath;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XMLHelper {

	public static boolean createXmlDB(String path) {
		Document doc = DocumentHelper.createDocument();
		doc.setXMLEncoding("UTF-8");
		Element root = doc.addElement("db", "http://www.nuaa.edu.cn/db");
		// students元素
		root.addElement("students");

		// 写入指定文件
		try {
			FileOutputStream fos = new FileOutputStream(new File(path));
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");
			XMLWriter writer = new XMLWriter(fos, format);
			writer.write(doc);
			writer.close();
			fos.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean deleteXmlDB(String path) {
		File file = new File(path);
		return file.delete();
	}

	public static boolean addStudent(String path, Student s) {
		SAXReader reader = new SAXReader();
		Document doc = null;
		try {
			doc = reader.read(new File(path));
		} catch (DocumentException e) {

			e.printStackTrace();
		}
		Element students = doc.getRootElement().element("students");
		// 添加
		Element stu = students.addElement("stu");
		stu.addAttribute("id", s.getId());
		stu.addAttribute("name", s.getName());
		stu.addAttribute("age", s.getAge());
		try {
			FileOutputStream fos = new FileOutputStream(new File(path));
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");
			XMLWriter writer = new XMLWriter(fos, format);
			writer.write(doc);
			writer.close();
			fos.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean delStudent(String path, String id) {

		Node stu = null;
		SAXReader reader = new SAXReader();

		Map<String, String> nsMap = new HashMap<>();
		nsMap.put("db", "http://www.nuaa.edu.cn/db");
		reader.getDocumentFactory().setXPathNamespaceURIs(nsMap);
		Document doc = null;
		try {
			doc = reader.read(new File(path));
			XPath xpath = doc.createXPath("db:db/db:students/db:stu[@id='" + id + "']");
			xpath.setNamespaceURIs(nsMap);
			stu = xpath.selectSingleNode(doc);
			if (stu == null)
				return false;
			stu.getParent().remove(stu);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		try {
			FileOutputStream fos = new FileOutputStream(new File(path));
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");
			XMLWriter writer = new XMLWriter(fos, format);
			writer.write(doc);
			writer.close();
			fos.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean alterStudent(String path, Student s) {
		Node stu = null;
		SAXReader reader = new SAXReader();

		Map<String, String> nsMap = new HashMap<>();
		nsMap.put("db", "http://www.nuaa.edu.cn/db");
		reader.getDocumentFactory().setXPathNamespaceURIs(nsMap);
		try {
			Document doc = reader.read(new File(path));
			XPath xpath = doc.createXPath("db:db/db:students/db:stu[@id='" + s.getId() + "']");
			xpath.setNamespaceURIs(nsMap);
			stu = xpath.selectSingleNode(doc);
			if (stu != null) {
				Element p = stu.getParent();
				p.remove(stu);
				Element stu1 = p.addElement("stu");
				stu1.addAttribute("id", s.getId());
				stu1.addAttribute("name", s.getName());
				stu1.addAttribute("age", s.getAge());
			}

			try {
				FileOutputStream fos = new FileOutputStream(new File(path));
				OutputFormat format = OutputFormat.createPrettyPrint();
				format.setEncoding("UTF-8");
				XMLWriter writer = new XMLWriter(fos, format);
				writer.write(doc);
				writer.close();
				fos.close();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		return false;
	}

	public static Student searchStudentById(String path, String id) {
		Node stu = null;
		SAXReader reader = new SAXReader();

		Map<String, String> nsMap = new HashMap<>();
		nsMap.put("db", "http://www.nuaa.edu.cn/db");
		reader.getDocumentFactory().setXPathNamespaceURIs(nsMap);
		try {
			Document doc = reader.read(new File(path));
			XPath xpath = doc.createXPath("db:db/db:students/db:stu[@id='" + id + "']");
			xpath.setNamespaceURIs(nsMap);
			stu = xpath.selectSingleNode(doc);
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		if (stu == null) {
			return null;
		} else {
			Student s = new Student(stu.valueOf("id"), stu.valueOf("name"), stu.valueOf("age"));
			return s;
		}
	}
}


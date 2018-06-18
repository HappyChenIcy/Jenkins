package com.cy.junit.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cy.junit.Student;
import com.cy.junit.XMLHelper;

public class XMLHelperTest {

	static String path = "C:\\\\Users\\\\asus-pc\\\\Desktop\\\\stu_db.xml";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		XMLHelper.createXmlDB(path);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		XMLHelper.deleteXmlDB(path);
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddStudent() {
		System.out.println("add");
		assertEquals(true, XMLHelper.addStudent(path, new Student("CY1716077", "ChenYue", "22")));
	}

	@Test
	public void testDelStudent() {
		System.out.println("del");
		XMLHelper.addStudent(path, new Student("SX1716077", "³ÂÔÃ", "23"));
		XMLHelper.addStudent(path, new Student("CY1716077", "ChenYue", "26"));
		assertEquals(true, XMLHelper.delStudent(path, "CY1716077"));
		assertEquals(false, XMLHelper.delStudent(path, "SX1716013"));
	}

	@Test
	public void testAlterStudent() {
		System.out.println("alter");
		XMLHelper.addStudent(path, new Student("SX1716077", "³ÂÔÃ", "23"));
		assertEquals(true, XMLHelper.alterStudent(path, new Student("SX1716077", "HappyChen", "20")));
	}

//	@Test
//	public void testSearchStudentById() {
//		System.out.println("search");
//		assertNotEquals(null, XMLHelper.searchStudentById(path, "sSX1716077"));
//	}

}

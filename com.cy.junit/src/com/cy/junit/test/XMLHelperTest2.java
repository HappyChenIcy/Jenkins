package com.cy.junit.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cy.junit.Student;
import com.cy.junit.XMLHelper;

public class XMLHelperTest2 {
	static String path = "C:\\\\Users\\\\asus-pc\\\\Desktop\\\\stu_db.xml";
	int count = 0;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		XMLHelper.createXmlDB(path);
		for (int i = 0; i < 10; i++) {
			XMLHelper.addStudent(path, new Student("SX17160" + i, "³ÂÔÃ" + i, "" + 15 + i));
		}
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
		count++;
	}

	@Test
	public void testDelStudent() {
		assertEquals(true, XMLHelper.delStudent(path, "SX17160" + count));
	}

}


package com.cy.junit.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cy.junit.Student;
import com.cy.junit.XMLHelper;

public class XMLHelperTest1 {

	static String path = "C:\\\\Users\\\\asus-pc\\\\Desktop\\\\stu_db.xml";
	int count;

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
		count = 0;
	}

	@After
	public void tearDown() throws Exception {
		count = 0;
	}

	@Test
	public void testAddStudent() {
		for (count = 0; count < 10; count++) {
			assertEquals(true,
					XMLHelper.addStudent(path, new Student("SX17160" + count, "³ÂÔÃ" + count, "" + 15 + count)));
		}
	}

}



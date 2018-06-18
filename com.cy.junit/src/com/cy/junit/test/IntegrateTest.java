package com.cy.junit.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ XMLHelperTest.class, XMLHelperTest1.class, XMLHelperTest2.class, XMLHelperTest3.class,
		XMLHelperTest4.class,

})
public class IntegrateTest {

	@Test
	public void test() {
	}

}

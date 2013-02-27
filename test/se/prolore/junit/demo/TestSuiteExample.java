package se.prolore.junit.demo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DatadrivenTest.class, SimpleTests.class })
public class TestSuiteExample {

}

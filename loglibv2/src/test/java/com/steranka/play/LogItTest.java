package com.steranka.play;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class LogItTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public LogItTest(String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( LogItTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testLogIt()
    {
        LogIt logit = new LogIt();
        assertEquals(logit.sayHello("Joe", "How's it going,"), "How's it going, Joe");
    }
}

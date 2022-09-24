package com.steranka.play;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class GoodFeatureTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public GoodFeatureTest(String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( GoodFeatureTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testLogIt()
    {
        GoodFeature goodFeature = new GoodFeature();
        assertEquals(goodFeature.sayGoodbye("Joe"), "Goodbye, Joe");
    }
}

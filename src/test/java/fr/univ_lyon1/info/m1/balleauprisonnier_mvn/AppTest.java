package fr.univ_lyon1.info.m1.balleauprisonnier_mvn;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import model.Ball;
import model.Human;
import model.Player;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	Ball b;
	Player p;
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName){
    	p = new Human(20, 20, 45, "Haut");
        b = new Ball(20, 20, 10, null);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite(){
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp(){
    	b.moveBall();
    	assertNotSame(b.getX(), 600);
    	assertNotSame(b.getX(), 0);
    	assertNotSame(b.getY(), 600);
    	assertNotSame(b.getY(), 0);
    }
}

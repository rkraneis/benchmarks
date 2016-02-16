package io.github.rkraneis.benchmarks;

import io.github.rkraneis.benchmarks.Fib;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author rnk
 */
@Ignore
public class FibIT {

    public FibIT() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of fibCorecursive method, of class Fib.
     */
    @Test
    public void testFibCorecursive() {
        System.out.println("fibCorecursive");
        int n = 0;
        long expResult = 0L;
        long result = Fib.fibCorecursive(n);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fibRecursive method, of class Fib.
     */
    @Test
    public void testFibRecursive() {
        System.out.println("fibRecursive");
        int n = 0;
        long expResult = 0L;
        long result = Fib.fibRecursive(n);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fibRecursive2 method, of class Fib.
     */
    @Test
    public void testFibRecursive2() {
        System.out.println("fibRecursive2");
        int n = 0;
        long expResult = 0L;
        long result = Fib.fibRecursive2(n);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fibIterative method, of class Fib.
     */
    @Test
    public void testFibIterative() {
        System.out.println("fibIterative");
        int n = 0;
        long expResult = 0L;
        long result = Fib.fibIterative(n);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

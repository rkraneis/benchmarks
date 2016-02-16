package io.github.rkraneis.benchmarks;

import io.github.rkraneis.benchmarks.Fib;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TestRule;

/**
 *
 * @author <a href="mailto:Rene.Kraneis@hays.de">René Kraneis</a>
 */
public class FibTest {

    private final static long[] results = {
        0, 1, 1, 2, 3, 5, 8, 13, 21, 34,
        55, 89, 144, 233, 377, 610, 987, 1_597, 2_584,
        4_181, 6_765, 10_946, 17_711, 28_657, 46_368, 75_025, 12_1393, 196_418, 317_811,
        514_229, 832_040, 1_346_269, 2_178_309, 3_524_578, 5_702_887, 9_227_465, 14_930_352, 24_157_817, 39_088_169,};

    public FibTest() {
    }

    @Rule
    public TestRule benchmarkRun = new BenchmarkRule();

    @Test
    public void testFibCorecursive() {
        for (int n = 0; n < results.length; n++) {
            long expResult = results[n];
            long result = Fib.fibCorecursive(n);
            assertEquals(expResult, result);
        }
    }

    @Test
    public void testFibCorecursive2() {
        for (int n = 0; n < results.length; n++) {
            long expResult = results[n];
            long result = Fib.fibCorecursive2(n);
            assertEquals(expResult, result);
        }
    }

    @Test
    public void testFibRecursive() {
        for (int n = 0; n < results.length; n++) {
            long expResult = results[n];
            long result = Fib.fibRecursive(n);
            assertEquals(expResult, result);
        }
    }

    @Test
    public void testFibRecursive2() {
        for (int n = 0; n < results.length; n++) {
            long expResult = results[n];
            long result = Fib.fibRecursive2(n);
            assertEquals(expResult, result);
        }
    }

    @Test
    public void testFibIterative() {
        for (int n = 0; n < results.length; n++) {
            long expResult = results[n];
            long result = Fib.fibIterative(n);
            assertEquals(expResult, result);
        }
    }

}

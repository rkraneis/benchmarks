package io.github.rkraneis.benchmarks;

import io.github.rkraneis.benchmarks.LoopUnrolling;
import java.util.Arrays;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author René Kraneis
 */
public class LoopUnrollingTest {

//    private final int[] is = new int[]{1, 2, 3, 4, 5, 6, 7};
//    private final int expResult = 28;
//    private final int[] is = new int[]{1};
//    private final int expResult = 1;
    private final int[] is = new Random().ints(17, 0, 100).toArray();
    private final int expResult = Arrays.stream(is).sum();

    public LoopUnrollingTest() {
    }

    @Test
    public void testSumFor() {
        System.out.println("sumFor");
        int result = LoopUnrolling.sumFor(is);
        assertEquals(expResult, result);
    }

    @Test
    public void testSumForF() {
        System.out.println("sumForF");
        int result = LoopUnrolling.sumForF(is);
        assertEquals(expResult, result);
    }

    @Test
    public void testSumFor2() {
        System.out.println("sumFor2");
        int result = LoopUnrolling.sumFor2(is);
        assertEquals(expResult, result);
    }

    @Test
    public void testSumFor4() {
        System.out.println("sumFor4");
        int result = LoopUnrolling.sumFor4(is);
        assertEquals(expResult, result);
    }

    @Test
    public void testSumFor4F() {
        System.out.println("sumFor4F");
        int result = LoopUnrolling.sumFor4F(is);
        assertEquals(expResult, result);
    }

    @Test
    public void testSumFor4F2() {
        System.out.println("sumFor4F2");
        int result = LoopUnrolling.sumFor4F2(is);
        assertEquals(expResult, result);
    }

    @Test
    public void testSumFor8() {
        System.out.println("sumFor8");
        int result = LoopUnrolling.sumFor8(is);
        assertEquals(expResult, result);
    }

    @Test
    public void testSumFor16() {
        System.out.println("sumFor16");
        int result = LoopUnrolling.sumFor16(is);
        assertEquals(expResult, result);
    }

    @Test
    public void testSumFor16B1() {
        System.out.println("sumFor16B1");
        int result = LoopUnrolling.sumFor16B1(is);
        assertEquals(expResult, result);
    }

    @Test
    public void testSumFor16B2() {
        System.out.println("sumFor16B2");
        int result = LoopUnrolling.sumFor16B2(is);
        assertEquals(expResult, result);
    }

    @Test
    public void testSumWhile4() {
        System.out.println("sumWhile4");
        int result = LoopUnrolling.sumWhile4(is);
        assertEquals(expResult, result);
    }

    @Test
    public void testSumWhile4F() {
        System.out.println("sumWhile4F");
        int result = LoopUnrolling.sumWhile4F(is);
        assertEquals(expResult, result);
    }

    @Test
    public void testSumWhile4F2() {
        System.out.println("sumWhile4F2");
        int result = LoopUnrolling.sumWhile4F2(is);
        assertEquals(expResult, result);
    }

    @Test
    public void testSumForEach() {
        System.out.println("sumForEach");
        int result = LoopUnrolling.sumForEach(is);
        assertEquals(expResult, result);
    }

    @Test
    public void testSumForEachF() {
        System.out.println("sumForEachF");
        int result = LoopUnrolling.sumForEachF(is);
        assertEquals(expResult, result);
    }

    @Test
    public void testSumStream() {
        System.out.println("sumStream");
        int result = LoopUnrolling.sumStream(is);
        assertEquals(expResult, result);
    }

    @Test
    public void testSumStreamF() {
        System.out.println("sumStreamF");
        int result = LoopUnrolling.sumStreamF(is);
        assertEquals(expResult, result);
    }

    @Test
    public void testSumParallelStream() {
        System.out.println("sumParallelStream");
        int result = LoopUnrolling.sumParallelStream(is);
        assertEquals(expResult, result);
    }

    @Test
    public void testSumParallelStreamF() {
        System.out.println("sumParallelStreamF");
        int result = LoopUnrolling.sumParallelStreamF(is);
        assertEquals(expResult, result);
    }

}

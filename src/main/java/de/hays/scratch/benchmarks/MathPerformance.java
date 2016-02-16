package de.hays.scratch.benchmarks;

import java.math.BigDecimal;
import java.math.MathContext;
import org.nevec.rjm.BigDecimalMath;

/**
 *
 * @author rnk
 */
public class MathPerformance {

    public MathPerformance(MathContext mc) {
        this.mc = mc;
        this.bPI = BigDecimalMath.pi(mc);
    }

    private final float fPI = (float) Math.PI;
    private final double dPI = Math.PI;
    private final MathContext mc;
    private final BigDecimal bPI;

    private final static MathContext DECIMAL256 = new MathContext(70);
    private final static MathContext DECIMAL512 = new MathContext(142);
    private final static MathContext DECIMAL1024 = new MathContext(286);

    private final static BigDecimal ZERO = BigDecimal.ZERO;
    private final static BigDecimal ONE = BigDecimal.ONE;
    private final static BigDecimal TWO = BigDecimal.valueOf(2);
    private final static BigDecimal THREE = BigDecimal.valueOf(3);
    private final static BigDecimal FOUR = BigDecimal.valueOf(4);
    private final static BigDecimal FIVE = BigDecimal.valueOf(5);
    private final static BigDecimal SIXTEEN = BigDecimal.valueOf(16);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
         float f1 = 1.0f;
         float f2 = 2.0f;
         float f3 = 3.0f;
         System.out.println("f:    " + addMul(f1, f2, f3));
         System.out.println("f:    " + addDiv(f1, f2, f3));

         double d1 = 1.0;
         double d2 = 2.0;
         double d3 = 3.0;
         System.out.println("d:    " + addMul(d1, d2, d3));
         System.out.println("d:    " + addDiv(d1, d2, d3));

         BigDecimal b1 = BigDecimal.ONE;
         BigDecimal b2 = BigDecimal.valueOf(2);
         BigDecimal b3 = BigDecimal.valueOf(3);
         System.out.println("b32:  " + addMul(b1, b2, b3, MathContext.DECIMAL32));
         System.out.println("b32:  " + addDiv(b1, b2, b3, MathContext.DECIMAL32));
         System.out.println("b64:  " + addMul(b1, b2, b3, MathContext.DECIMAL64));
         System.out.println("b64:  " + addDiv(b1, b2, b3, MathContext.DECIMAL64));
         System.out.println("b128: " + addMul(b1, b2, b3, MathContext.DECIMAL128));
         System.out.println("b128: " + addDiv(b1, b2, b3, MathContext.DECIMAL128));
         System.out.println("bUL:  " + addMul(b1, b2, b3, MathContext.UNLIMITED));
         //System.out.println("bUL:  " + addDiv(b1, b2, b3, MathContext.UNLIMITED));
         */

        System.out.println("Y00");
        MathPerformance mp = new MathPerformance(DECIMAL256);
        System.out.println(mp.y00(ZERO, ZERO));
        mp = new MathPerformance(MathContext.DECIMAL128);
        System.out.println(mp.y00(ZERO, ZERO));
        mp = new MathPerformance(MathContext.DECIMAL64);
        System.out.println(mp.y00(ZERO, ZERO));
        mp = new MathPerformance(MathContext.DECIMAL32);
        System.out.println(mp.y00(ZERO, ZERO));
        System.out.println(mp.y00(0d, 0d));
        System.out.println(mp.y00(0f, 0f));

        System.out.println("Y10");
        mp = new MathPerformance(DECIMAL256);
        System.out.println(mp.y10(mp.bPI, ZERO));
        mp = new MathPerformance(MathContext.DECIMAL128);
        System.out.println(mp.y10(mp.bPI, ZERO));
        mp = new MathPerformance(MathContext.DECIMAL64);
        System.out.println(mp.y10(mp.bPI, ZERO));
        mp = new MathPerformance(MathContext.DECIMAL32);
        System.out.println(mp.y10(mp.bPI, ZERO));
        System.out.println(mp.y10(mp.dPI, 0d));
        System.out.println(mp.y10(mp.fPI, 0f));

        System.out.println("Y20");
        mp = new MathPerformance(DECIMAL256);
        System.out.println(mp.y20(mp.bPI, ZERO));
        mp = new MathPerformance(MathContext.DECIMAL128);
        System.out.println(mp.y20(mp.bPI, ZERO));
        mp = new MathPerformance(MathContext.DECIMAL64);
        System.out.println(mp.y20(mp.bPI, ZERO));
        mp = new MathPerformance(MathContext.DECIMAL32);
        System.out.println(mp.y20(mp.bPI, ZERO));
        System.out.println(mp.y20(mp.dPI, 0d));
        System.out.println(mp.y20(mp.fPI, 0f));
    }

    public static float addMul(float f1, float f2, float f3) {
        return f1 + f2 * f3;
    }

    public static double addMul(double d1, double d2, double d3) {
        return d1 + d2 * d3;
    }

    public static BigDecimal addMul(BigDecimal b1, BigDecimal b2, BigDecimal b3, MathContext mc) {
        return b1.add(b2.multiply(b3, mc), mc);
    }

    public static float addDiv(float f1, float f2, float f3) {
        return f1 + f2 / f3;
    }

    public static double addDiv(double d1, double d2, double d3) {
        return d1 + d2 / d3;
    }

    public static BigDecimal addDiv(BigDecimal b1, BigDecimal b2, BigDecimal b3, MathContext mc) {
        return b1.add(b2.divide(b3, mc), mc);
    }

    ///// Y00 /////
    public float y00(float theta, float phi) {
        return (float) Math.sqrt(1f / 4f / fPI);
    }

    public double y00(double theta, double phi) {
        return Math.sqrt(1d / 4d / dPI);
    }

    public BigDecimal y00(BigDecimal theta, BigDecimal phi) {
        return BigDecimalMath.sqrt(ONE.divide(FOUR, mc).divide(bPI, mc), mc);
    }

    ///// Y10 /////
    public float y10(float theta, float phi) {
        return (float) Math.sqrt(3f / 4f / fPI) * (float) Math.cos(theta);
    }

    public double y10(double theta, double phi) {
        return Math.sqrt(3f / 4f / dPI) * Math.cos(theta);
    }

    public BigDecimal y10(BigDecimal theta, BigDecimal phi) {
        return BigDecimalMath.sqrt(THREE.divide(FOUR, mc).divide(bPI, mc), mc).multiply(BigDecimalMath.cos(theta), mc);
    }

    ///// Y20 /////
    public float y20(float theta, float phi) {
        float cosTheta = (float) Math.cos(theta);
        return (float) Math.sqrt(5f / 16f / fPI) * (3 * cosTheta * cosTheta - 1);
    }

    public double y20(double theta, double phi) {
        double cosTheta = Math.cos(theta);
        return Math.sqrt(5f / 16f / dPI) * (3 * cosTheta * cosTheta - 1);
    }

    public BigDecimal y20(BigDecimal theta, BigDecimal phi) {
        BigDecimal cosTheta = BigDecimalMath.cos(theta);
        return BigDecimalMath.sqrt(FIVE.divide(SIXTEEN, mc).divide(bPI, mc), mc).multiply(THREE.multiply(cosTheta.multiply(cosTheta, mc), mc).subtract(ONE, mc), mc);
    }
}

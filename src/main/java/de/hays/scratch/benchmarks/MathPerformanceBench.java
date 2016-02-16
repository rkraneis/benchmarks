package de.hays.scratch.benchmarks;

import java.math.BigDecimal;
import java.math.MathContext;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 *
 * @author rnk
 */
@State(Scope.Benchmark)
public class MathPerformanceBench {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        Options opt = new OptionsBuilder()
                .include(".*" + MathPerformanceBench.class.getSimpleName() + ".*")
                .warmupIterations(5)
                .measurementIterations(5)
                .forks(1)
                .build();
        new Runner(opt).run();
    }

    float f1 = 1.0f;
    float f2 = 2.0f;
    float f3 = 3.0f;
    float f4 = 0.25f;

    double d1 = 1.0;
    double d2 = 2.0;
    double d3 = 3.0;
    double d4 = 0.25;

    BigDecimal b1 = BigDecimal.ONE;
    BigDecimal b2 = BigDecimal.valueOf(2);
    BigDecimal b3 = BigDecimal.valueOf(3);
    BigDecimal b4 = BigDecimal.valueOf(25, 2);

    @Benchmark
    public void addMulFloat(Blackhole bh) {
        bh.consume(MathPerformance.addMul(f1, f2, f3));
    }

    @Benchmark
    public void addMulDouble(Blackhole bh) {
        bh.consume(MathPerformance.addMul(d1, d2, d3));
    }

    @Benchmark
    public void addMulB32(Blackhole bh) {
        bh.consume(MathPerformance.addMul(b1, b2, b3, MathContext.DECIMAL32));
    }

    @Benchmark
    public void addMulB64(Blackhole bh) {
        bh.consume(MathPerformance.addMul(b1, b2, b3, MathContext.DECIMAL64));
    }

    @Benchmark
    public void addMulB128(Blackhole bh) {
        bh.consume(MathPerformance.addMul(b1, b2, b3, MathContext.DECIMAL128));
    }

    @Benchmark
    public void addMulBUL(Blackhole bh) {
        bh.consume(MathPerformance.addMul(b1, b2, b3, MathContext.UNLIMITED));
    }

    @Benchmark
    public void addMulFracFloat(Blackhole bh) {
        bh.consume(MathPerformance.addMul(f1, f2, f4));
    }

    @Benchmark
    public void addMulFracDouble(Blackhole bh) {
        bh.consume(MathPerformance.addMul(d1, d2, d4));
    }

    @Benchmark
    public void addMulFracB32(Blackhole bh) {
        bh.consume(MathPerformance.addMul(b1, b2, b4, MathContext.DECIMAL32));
    }

    @Benchmark
    public void addMulFracB64(Blackhole bh) {
        bh.consume(MathPerformance.addMul(b1, b2, b4, MathContext.DECIMAL64));
    }

    @Benchmark
    public void addMulFracB128(Blackhole bh) {
        bh.consume(MathPerformance.addMul(b1, b2, b4, MathContext.DECIMAL128));
    }

    @Benchmark
    public void addMulFracBUL(Blackhole bh) {
        bh.consume(MathPerformance.addMul(b1, b2, b4, MathContext.UNLIMITED));
    }

    @Benchmark
    public void addDivFloat(Blackhole bh) {
        bh.consume(MathPerformance.addDiv(f1, f2, f3));
    }

    @Benchmark
    public void addDivFDouble(Blackhole bh) {
        bh.consume(MathPerformance.addDiv(d1, d2, d3));
    }

    @Benchmark
    public void addDivB32(Blackhole bh) {
        bh.consume(MathPerformance.addDiv(b1, b2, b3, MathContext.DECIMAL32));
    }

    @Benchmark
    public void addDivB64(Blackhole bh) {
        bh.consume(MathPerformance.addDiv(b1, b2, b3, MathContext.DECIMAL64));
    }

    @Benchmark
    public void addDivB128(Blackhole bh) {
        bh.consume(MathPerformance.addDiv(b1, b2, b3, MathContext.DECIMAL128));
    }

    //@Benchmark
    public void addDivBUL(Blackhole bh) {
        bh.consume(MathPerformance.addDiv(b1, b2, b3, MathContext.UNLIMITED));
    }

}

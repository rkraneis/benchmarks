package de.hays.scratch.benchmarks;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.VerboseMode;

@State(Scope.Benchmark)
@Warmup(time = 1, timeUnit = TimeUnit.SECONDS, iterations = 5)
@Measurement(time = 1, timeUnit = TimeUnit.SECONDS, iterations = 5)
@Fork(3)
public class FactorialBench {

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(".*" + FactorialBench.class.getSimpleName() + ".*")
                .build();
        new Runner(opt).run();
    }

    @Param({"1", "10", "100", "1000", "10000"})
    //@Param({"1000", "10000", "100000"})
    public long numberParam;
    
    private BigInteger number;
    
    @Setup
    public void setup() {
        number = BigInteger.valueOf(numberParam);
    }

    @Benchmark
    public BigInteger factorialTrampoline() {
        return Factorial.factorialTrampoline(number);
    }

    @Benchmark
    public BigInteger factorialTrampolineCyclops() {
        return Factorial.factorialTrampolineCyclops(number);
    }

    @Benchmark
    public BigInteger factorialTrampolineFj() {
        return Factorial.factorialTrampolineFj(number);
    }

    //@Benchmark
    public BigInteger factorialCorecursive() {
        return Factorial.factorialCorecursive(number);
    }

    @Benchmark
    public BigInteger factorialIterative() {
        return Factorial.factorialIterative(number);
    }

    //@Benchmark
    public BigInteger factorialRecursive() {
        return Factorial.factorialRecursive(number);
    }

    //@Benchmark
    public BigInteger factorialRecursive2() {
        return Factorial.factorialRecursive2(number);
    }

}

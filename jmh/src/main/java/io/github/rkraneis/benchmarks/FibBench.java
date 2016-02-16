package io.github.rkraneis.benchmarks;

import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
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
public class FibBench {

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(".*" + FibBench.class.getSimpleName() + ".*")
                .build();
        new Runner(opt).run();
    }

    @Param({"0", "1", "2", "3", "4", "5", "10", "20"})
    public int number;

    @Benchmark
    public long fibCorecursive() {
        return Fib.fibCorecursive(number);
    }

    @Benchmark
    public long fibIterative() {
        return Fib.fibIterative(number);
    }

    @Benchmark
    public long fibRecursive() {
        return Fib.fibRecursive(number);
    }

    @Benchmark
    public long fibRecursive2() {
        return Fib.fibRecursive2(number);
    }

}

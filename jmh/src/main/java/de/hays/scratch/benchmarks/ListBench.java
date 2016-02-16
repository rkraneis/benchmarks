package de.hays.scratch.benchmarks;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 10)
@Measurement(iterations = 10)
@Fork(3)
@State(Scope.Benchmark)
@Threads(1)
public class ListBench {

    public static void main(String[] args) throws RunnerException {
        Options opts = new OptionsBuilder()
                .include(".*" + ListBench.class.getSimpleName() + ".*")
                .param("iterations", "10", "100", "1000", "10000", "100000", "1000000")
                //.verbosity(VerboseMode.EXTRA)
                .warmupIterations(11)
                .measurementIterations(11)
                .forks(1)
                .build();
        new Runner(opts).run();
    }

    @Param("1000000")
    private int iterations;

    private ArrayList<String> arrayList;
    private LinkedList<String> linkedList;

    @Setup(Level.Iteration)
    public void setup() {
        arrayList = new ArrayList<>(iterations);
        for (int i = 0; i < iterations; i++) {
            arrayList.add(String.valueOf(i));
        }

        linkedList = new LinkedList<>();
        for (int i = 0; i < iterations; i++) {
            linkedList.add(String.valueOf(i));
        }
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void iterateArrayList(Blackhole bh) {
        for (String s : arrayList) {
            bh.consume(s);
        }
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void iterateLinkedList(Blackhole bh) {
        for (String s : linkedList) {
            bh.consume(s);
        }
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void appendArrayList(Blackhole bh) {
        arrayList = new ArrayList<>(iterations);
        for (int i = 0; i < iterations; i++) {
            arrayList.add(String.valueOf(i));
        }
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void appendLinkedList(Blackhole bh) {
        linkedList = new LinkedList<>();
        for (int i = 0; i < iterations; i++) {
            linkedList.add(String.valueOf(i));
        }
    }
}

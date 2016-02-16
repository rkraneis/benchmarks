package de.hays.scratch.benchmarks;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Consumer;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@State(Scope.Benchmark)
@Threads(2)
public class Java8IterationBenchmarks {

    public static void main(String[] args) throws RunnerException {
        Options opts = new OptionsBuilder()
                .include(".*" + Java8IterationBenchmarks.class.getSimpleName() + ".*")
                .warmupIterations(20)
                .measurementIterations(10)
                .forks(2)
                //.result(LocalDateTime.now(ZoneId.of("UTC")).format(DateTimeFormatter.ofPattern("yyyyMMdd'T'hhmmss")) + ".csv")
                .result(System.getProperty("java.version") + ".csv")
                .resultFormat(ResultFormatType.CSV)
                .build();
        new Runner(opts).run();
    }

    public @Param({"100", "10000", "1000000"})
    int elementCount;

    private ArrayList<LongAdder> list;
    private LongAdder[] array;

    @Setup
    public void setup() {
        list = new ArrayList<>(elementCount);
        for (int i = 0; i < elementCount; i++) {
            list.add(new LongAdder());
        }
        array = list.toArray(new LongAdder[list.size()]);
    }

    @Benchmark
    public void timeForEachOverList() {
        for (LongAdder t : list) {
            t.increment();
        }
    }

    @Benchmark
    public void timeForEachOverListAnonymousClass() {
        list.forEach(new Consumer<LongAdder>() {
            @Override
            public void accept(LongAdder t) {
                t.increment();
            }
        });
    }

    @Benchmark
    public void timeForEachOverListLambda() {
        list.forEach(t -> t.increment());
    }

    @Benchmark
    public void timeForEachOverStreamFromListLambda() {
        list.stream().forEach(t -> t.increment());
    }

    @Benchmark
    public void timeForEachOverParallelStreamFromListLambda() {
        list.parallelStream().forEach(t -> t.increment());
    }

    @Benchmark
    public void timeForEachOverArray() {
        for (LongAdder t : array) {
            t.increment();
        }
    }
}

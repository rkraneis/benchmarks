/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.rkraneis.benchmarks;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;
import org.openjdk.jmh.runner.options.VerboseMode;

/**
 *
 * @author rnk
 */
@State(Scope.Benchmark)
public class ForEachListObjectVsPrimitive {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RunnerException {
        Options opts = new OptionsBuilder()
                .include(".*" + ForEachListObjectVsPrimitive.class.getSimpleName() + ".*")
                .param("streamSize", "10", "100", "1000", "10000", "100000", "1000000")
                .verbosity(VerboseMode.EXTRA)
                .warmupTime(TimeValue.milliseconds(100))
                .warmupIterations(5)
                .measurementTime(TimeValue.milliseconds(100))
                .measurementIterations(3)
                .forks(1)
                .build();
        new Runner(opts).run();
    }
    @Param("1000")
    public long streamSize;

    private Random r = new Random();
    private long[] primitiveArray = r.longs(streamSize).toArray();
    private Long[] objectArray = LongStream.of(primitiveArray).boxed().toArray(i -> new Long[i]);
    private List<Long> objectList = LongStream.of(primitiveArray).boxed().collect(Collectors.toList());

    @Benchmark
    public long loopPrimitiveArrayPrimitive() {
        long sum = 0L;
        for (long l : primitiveArray) {
            sum += l;
        }
        return sum;
    }

    @Benchmark
    public long loopPrimitiveArrayObject() {
        Long sum = 0L;
        for (Long l : primitiveArray) {
            sum += l;
        }
        return sum;
    }

    @Benchmark
    public long loopObjectArrayPrimitive() {
        long sum = 0L;
        for (long l : objectArray) {
            sum += l;
        }
        return sum;
    }

    @Benchmark
    public long loopObjectArrayObject() {
        Long sum = 0L;
        for (Long l : objectArray) {
            sum += l;
        }
        return sum;
    }

    @Benchmark
    public long loopObjectListPrimitive() {
        long sum = 0L;
        for (long l : objectList) {
            sum += l;
        }
        return sum;
    }

    @Benchmark
    public long loopObjectListObject() {
        Long sum = 0L;
        for (Long l : objectList) {
            sum += l;
        }
        return sum;
    }

    @Benchmark
    public long streamObjectListPrimitive() {
        long sum = 0L;
        sum = objectList.stream().mapToLong(l -> l).sum();
        return sum;
    }

    @Benchmark
    public long streamObjectListObject() {
        Long sum = 0L;
        sum = objectList.stream().reduce(0L, Long::sum);
        return sum;
    }

}

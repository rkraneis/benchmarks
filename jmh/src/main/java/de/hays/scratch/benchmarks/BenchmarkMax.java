package de.hays.scratch.benchmarks;

import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import static java.util.function.Function.identity;
import java.util.stream.Collectors;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.profile.HotspotMemoryProfiler;
import org.openjdk.jmh.profile.WinPerfAsmProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.VerboseMode;

@State(Scope.Thread)
public class BenchmarkMax {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RunnerException {
        Options opts = new OptionsBuilder()
                .include(".*" + BenchmarkMax.class.getSimpleName() + ".*")
                .param("limit", "1", "10", "100", "1000", "10000", "100000")
                //.verbosity(VerboseMode.EXTRA)
                .warmupIterations(5)
                .measurementIterations(3)
                .addProfiler(HotspotMemoryProfiler.class)
                .output("output.txt")
                .forks(1)
                .build();
        new Runner(opts).run();
    }

    @Param("1000")
    private int limit;

    Random r = new Random();
    Map<Integer, Integer> map;

    @Setup
    public void setup() {
        map = r.ints(0, 10).limit(limit).boxed().collect(
                Collectors.toMap(k -> k, v -> 1, Integer::sum)
        );
    }

    @Benchmark
    public int collectionsMax() {
        return Collections.max(map.values());
    }

    @Benchmark
    public int streamMax() {
        return map.values().stream().max(Integer::compare).get();
    }

}

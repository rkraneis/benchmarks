package de.hays.scratch.benchmarks;

import java.util.List;
import java.util.Random;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.summingDouble;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.VerboseMode;

/*

Benchmark                                                                 (limit)   Mode  Cnt         Score         Error  Units
Benchmark_map_toDoubleSum_vs_collect_summingDouble.collect_summingDouble        1  thrpt   33  21236729,979 ±  647771,288  ops/s
Benchmark_map_toDoubleSum_vs_collect_summingDouble.collect_summingDouble       10  thrpt   33  10980892,319 ±   68849,971  ops/s
Benchmark_map_toDoubleSum_vs_collect_summingDouble.collect_summingDouble      100  thrpt   33   1816528,565 ±   25962,228  ops/s
Benchmark_map_toDoubleSum_vs_collect_summingDouble.collect_summingDouble     1000  thrpt   33    165636,411 ±     903,512  ops/s
Benchmark_map_toDoubleSum_vs_collect_summingDouble.collect_summingDouble    10000  thrpt   33     16615,463 ±     177,424  ops/s
Benchmark_map_toDoubleSum_vs_collect_summingDouble.collect_summingDouble   100000  thrpt   33      1409,792 ±     153,323  ops/s
Benchmark_map_toDoubleSum_vs_collect_summingDouble.mapToDouble_sum              1  thrpt   33  17876818,941 ± 1482713,030  ops/s
Benchmark_map_toDoubleSum_vs_collect_summingDouble.mapToDouble_sum             10  thrpt   33  10072354,233 ±  224550,378  ops/s
Benchmark_map_toDoubleSum_vs_collect_summingDouble.mapToDouble_sum            100  thrpt   33   1815045,019 ±    9234,001  ops/s
Benchmark_map_toDoubleSum_vs_collect_summingDouble.mapToDouble_sum           1000  thrpt   33    197968,118 ±    1148,747  ops/s
Benchmark_map_toDoubleSum_vs_collect_summingDouble.mapToDouble_sum          10000  thrpt   33     19942,543 ±     100,216  ops/s
Benchmark_map_toDoubleSum_vs_collect_summingDouble.mapToDouble_sum         100000  thrpt   33      1914,011 ±      31,008  ops/s

*/


@State(Scope.Thread)
public class Benchmark_map_toDoubleSum_vs_collect_summingDouble {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RunnerException {
        Options opts = new OptionsBuilder()
                .include(".*" + Benchmark_map_toDoubleSum_vs_collect_summingDouble.class.getSimpleName() + ".*")
                .param("limit", "1", "10", "100", "1000", "10000", "100000")
                .verbosity(VerboseMode.EXTRA)
                .warmupIterations(1)
                .measurementIterations(1)
                .forks(1)
                .build();
        new Runner(opts).run();
    }

    @Param("1000")
    private int limit;

    Random r = new Random();
    List<D> list;

    @Setup
    public void setup() {
        list = r.doubles(limit).mapToObj(D::of).collect(toList());
    }

    @Benchmark
    public double mapToDouble_sum() {
        return list.stream().mapToDouble(D::d).sum();
    }

    @Benchmark
    public double collect_summingDouble() {
        return list.stream().collect(summingDouble(D::d));
    }

}

class D {

    double d;

    D(double d) {
        this.d = d;
    }

    static D of(double d) {
        return new D(d);
    }

    double d() {
        return d;
    }
}

package de.hays.scratch.benchmarks;

import java.util.Random;
import java.util.stream.IntStream;
import org.openjdk.jmh.annotations.Benchmark;
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
public class LoopOrNot {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RunnerException {
        Options opts = new OptionsBuilder()
                .include(".*" + LoopOrNot.class.getSimpleName() + ".*")
                .verbosity(VerboseMode.EXTRA)
                .warmupTime(TimeValue.milliseconds(100))
                .warmupIterations(5)
                .measurementTime(TimeValue.milliseconds(100))
                .measurementIterations(3)
                .forks(1)
                .build();
        new Runner(opts).run();
    }

    Random r = new Random();
    double[] d = {123d, 456d, 789d};

    @Benchmark
    public double[] loop() {
        int l = d.length;
        double[] e = new double[l];
        for (int i = 0; i < l; i++) {
            e[i] = c(d[i], i);
        }
        return e;
    }

    @Benchmark
    public double[] stream() {
        return IntStream.range(0, d.length)
                .mapToDouble(i -> c(d[i], i))
                .toArray();
    }

    @Benchmark
    public double[] array() {
        return new double[]{c(d[0], 0), c(d[1], 1), c(d[2], 2)};
    }

    private double c(double f, double g) {
        return 1 + f * (g - 1);
    }
}

package io.github.rkraneis.benchmarks;

import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.cos;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.sin;
import java.util.Collection;
import java.util.Random;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.VerboseMode;

@State(Scope.Thread)
public class AlphaMaxPlusBetaMin {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RunnerException {
        Options opts = new OptionsBuilder()
                .include(".*" + AlphaMaxPlusBetaMin.class.getSimpleName() + ".*")
                .verbosity(VerboseMode.EXTRA)
                .warmupIterations(1)
                .measurementIterations(1)
                .forks(1)
                .build();
        Collection<RunResult> run = new Runner(opts).run();
        run.forEach(r -> {
            System.out.println(r.getAggregatedResult().getSecondaryResults().keySet());
        });
    }

    Random r = new Random();
    double x, y;

    @Setup
    public void setup() {
        x = r.nextDouble();
        y = r.nextDouble();
    }

    @Benchmark
    public double hypot() {
        return Math.hypot(x, y);
    }

    @Benchmark
    public double hypot_with_sqrt() {
        return hypot_with_sqrt(x, y);
    }

    public double hypot_with_sqrt(double x, double y) {
        x = abs(x);
        y = abs(y);
        if (x < y) {
            double tmp = y;
            y = x;
            x = tmp;
        }
        double yx = y / x;
        return x * Math.sqrt(1 + yx * yx);
    }
    
        @Benchmark
    public double hypot_with_sqrt_2() {
        return hypot_with_sqrt_2(x, y);
    }

    public double hypot_with_sqrt_2(double x, double y) {
        x = abs(x);
        y = abs(y);
        double tmp;
        if (x < y) {
            tmp = y;
            y = x;
            x = tmp;
        }
        tmp = y / x;
        return x * Math.sqrt(1 + tmp * tmp);
    }

    @Benchmark
    public double hypot_with_sqrt_wikipedia() {
        return hypot_with_sqrt_wikipedia(x, y);
    }

    public double hypot_with_sqrt_wikipedia(double x, double y) {
        x = abs(x);
        y = abs(y);
        double t = min(x, y);
        x = max(x, y);
        t = t / x;
        return x * Math.sqrt(1 + t * t);
    }

    @Benchmark
    public double hypot_with_sqrt_and_pow() {
        return hypot_with_sqrt_and_pow(x, y);
    }

    public double hypot_with_sqrt_and_pow(double x, double y) {
        x = abs(x);
        y = abs(y);
        if (x < y) {
            double tmp = y;
            y = x;
            x = tmp;
        }
        return x * Math.sqrt(1 + Math.pow(y / x, 2));
    }

    private final static double ALPHA = 2 * cos(PI / 8) / (1 + cos(PI / 8));
    private final static double BETA = 2 * sin(PI / 8) / (1 + cos(PI / 8));
    private final static double ALPHA2 = 15 / 16;
    private final static double BETA2 = 15 / 32;

    @Benchmark
    public double alphaMaxPlusBetaMinBest() {
        return alphaMaxPlusBetaMinBest(x, y);
    }
    
    public double alphaMaxPlusBetaMinBest(double x, double y) {
        x = abs(x);
        y = abs(y);
        return ALPHA * max(x, y) + BETA * min(x, y);
    }

    @Benchmark
    public double alphaMaxPlusBetaMinSimple() {
        return alphaMaxPlusBetaMinSimple(x, y);
    }
    
    public double alphaMaxPlusBetaMinSimple(double x, double y) {
        x = abs(x);
        y = abs(y);
        return ALPHA2 * max(x, y) + BETA2 * min(x, y);
    }

    @Benchmark
    public double sqrt() {
        return Math.sqrt(x * x + y * y);
    }

}

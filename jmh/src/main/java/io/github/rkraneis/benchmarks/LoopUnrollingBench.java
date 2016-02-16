package io.github.rkraneis.benchmarks;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 *
 * @author rnk
 */
@State(Scope.Benchmark)
//@Warmup(iterations = 10, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
//@Measurement(iterations = 10, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
//@Fork(5)
public class LoopUnrollingBench {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RunnerException {
        Options opts = new OptionsBuilder()
                .include(".*" + LoopUnrollingBench.class.getSimpleName() + ".*")
                .resultFormat(ResultFormatType.CSV)
                .result(LoopUnrollingBench.class.getSimpleName() + ".csv")
                .build();
        new Runner(opts).run();
    }

    @Param({"1", "10", "100", "1000"})
    int length;

    Random r = new Random();
    int[] is;

    @Setup
    public void setup() {
        is = r.ints(length, 0, 10).toArray();
    }

    @Benchmark
    public int sumFor() {
        return LoopUnrolling.sumFor(is);
    }

    @Benchmark
    public int sumForF() {
        return LoopUnrolling.sumForF(is);
    }

    @Benchmark
    public int sumFor2() {
        return LoopUnrolling.sumFor2(is);
    }

    @Benchmark
    public int sumFor4() {
        return LoopUnrolling.sumFor4(is);
    }

    @Benchmark
    public int sumFor4F() {
        return LoopUnrolling.sumFor4F(is);
    }

    @Benchmark
    public int sumFor4F2() {
        return LoopUnrolling.sumFor4F2(is);
    }

    @Benchmark
    public int sumFor8() {
        return LoopUnrolling.sumFor8(is);
    }

    @Benchmark
    public int sumFor16() {
        return LoopUnrolling.sumFor16(is);
    }

    @Benchmark
    public int sumFor16B1() {
        return LoopUnrolling.sumFor16B1(is);
    }

    @Benchmark
    public int sumFor16B2() {
        return LoopUnrolling.sumFor16B2(is);
    }

    @Benchmark
    public int sumWhile4() {
        return LoopUnrolling.sumWhile4(is);
    }

    @Benchmark
    public int sumWhile4F() {
        return LoopUnrolling.sumWhile4F(is);
    }

    @Benchmark
    public int sumWhile4F2() {
        return LoopUnrolling.sumWhile4F2(is);
    }

    @Benchmark
    public int sumForEach() {
        return LoopUnrolling.sumForEach(is);
    }

    @Benchmark
    public int sumForEachF() {
        return LoopUnrolling.sumForEachF(is);
    }

    @Benchmark
    public int sumStream() {
        return LoopUnrolling.sumStream(is);
    }

    @Benchmark
    public int sumStreamF() {
        return LoopUnrolling.sumStreamF(is);
    }

    @Benchmark
    public int sumParallelStream() {
        return LoopUnrolling.sumParallelStream(is);
    }

    @Benchmark
    public int sumParallelStreamF() {
        return LoopUnrolling.sumParallelStreamF(is);
    }
}

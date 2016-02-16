package de.hays.scratch.benchmarks;

import java.util.Random;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@State(Scope.Thread)
public class BenchmarkMatrixMultiplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RunnerException {
        Options opts = new OptionsBuilder()
                .include(".*" + BenchmarkMatrixMultiplication.class.getSimpleName() + ".*")
                .param("size", "10", "100", "1000", "16", "128", "1024")
                //.verbosity(VerboseMode.EXTRA)
                .warmupIterations(11)
                .measurementIterations(11)
                .forks(1)
                .build();
        new Runner(opts).run();
    }

    @Param("1024")
    private int size;

    Random r = new Random(0xcafebabe);
    double[][] a, b;
    double[] d, e;

    @Setup()
    public void setup() {
        a = new double[size][size];
        b = new double[size][size];
        d = new double[size * size];
        e = new double[size * size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                a[i][j] = d[i * size + j] = r.nextDouble();
                b[i][j] = e[i * size + j] = r.nextDouble();
            }
        }
    }

    // 2D 
    @Benchmark
    public void mmul2d_ijk(Blackhole bh) {
        bh.consume(MatrixMultiplication2D.ijk(a, b, size));
    }

    @Benchmark
    public void mmul2d_ikj(Blackhole bh) {
        bh.consume(MatrixMultiplication2D.ikj(a, b, size));
    }

    @Benchmark
    public void mmul2d_jik(Blackhole bh) {
        bh.consume(MatrixMultiplication2D.jik(a, b, size));
    }

    @Benchmark
    public void mmul2d_jki(Blackhole bh) {
        bh.consume(MatrixMultiplication2D.jki(a, b, size));
    }

    @Benchmark
    public void mmul2d_kij(Blackhole bh) {
        bh.consume(MatrixMultiplication2D.kij(a, b, size));
    }

    @Benchmark
    public void mmul2d_kji(Blackhole bh) {
        bh.consume(MatrixMultiplication2D.kji(a, b, size));
    }

    
    // 1D
    @Benchmark
    public void mmul1d_ijk(Blackhole bh) {
        bh.consume(MatrixMultiplication1D.ijk(d, e, size));
    }

    @Benchmark
    public void mmul1d_ikj(Blackhole bh) {
        bh.consume(MatrixMultiplication1D.ikj(d, e, size));
    }

    @Benchmark
    public void mmul1d_jik(Blackhole bh) {
        bh.consume(MatrixMultiplication1D.jik(d, e, size));
    }

    @Benchmark
    public void mmul1d_jki(Blackhole bh) {
        bh.consume(MatrixMultiplication1D.jki(d, e, size));
    }

    @Benchmark
    public void mmul1d_kij(Blackhole bh) {
        bh.consume(MatrixMultiplication1D.kij(d, e, size));
    }

    @Benchmark
    public void mmul1d_kji(Blackhole bh) {
        bh.consume(MatrixMultiplication1D.kji(d, e, size));
    }

}

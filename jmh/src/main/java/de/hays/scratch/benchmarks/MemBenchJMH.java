package de.hays.scratch.benchmarks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@State(Scope.Benchmark)
public class MemBenchJMH {

    private static class MutableInteger {

        public int value;

        MutableInteger(int value) {
            MutableInteger.this.value = value;
        }

    }

    private final static int CACHE_MIN = 1024 * 128;
    //private final static int CACHE_MIN = 1024;
    private final static int CACHE_MAX = 1024 * 128;
    //private final static int CACHE_MAX = 1024 * 1024;
    private final static int ARRAY[] = new int[CACHE_MAX];
    private final static MutableInteger ARRAY_MUTABLE_INTEGER[] = new MutableInteger[CACHE_MAX];
    private final static List<MutableInteger> ARRAY_LIST = new ArrayList<>(CACHE_MAX);
    MutableInteger mi;

    public static void main(String[] args) throws Exception {

        for (int cacheSize = CACHE_MIN; cacheSize <= CACHE_MAX; cacheSize *= 2) {

            Options opt = new OptionsBuilder()
                    .include(".*" + MemBenchJMH.class.getSimpleName() + ".*")
                    .warmupIterations(5)
                    .measurementIterations(5)
                    .forks(1)
                    .param("size", String.valueOf(cacheSize))
                    .param("stride", getStrides(cacheSize))
                    .build();
            new Runner(opt).run();
        }
    }

    private static String[] getStrides(int cacheSize) {
        List<String> strides = new ArrayList<>();
        for (int stride = 1; stride <= cacheSize / 2; stride *= 2) {
            strides.add(String.valueOf(stride));
        }
        return strides.toArray(new String[strides.size()]);
    }

    @Param("0")
    public int size;

    @Param("0")
    public int stride;

    long limit = 0;

    @Setup
    public void setUp() {
        Random r = new Random();
        for (int i = 0; i < ARRAY.length; i++) {
            int j = r.nextInt();
            ARRAY[i] = j;
            ARRAY_MUTABLE_INTEGER[i] = new MutableInteger(j);
            ARRAY_LIST.add(new MutableInteger(j));
        }
        limit = size - stride + 1;
    }

    @Benchmark
    public void memArray(Blackhole bh) {
        for (int i = stride; i != 0; i--) {
            for (int index = 0; index < limit; index += stride) {
                bh.consume(ARRAY[index]++);
            }
        }
    }

    @Benchmark
    public void memArrayMutableInteger(Blackhole bh) {
        for (int i = stride; i != 0; i--) {
            for (int index = 0; index < limit; index += stride) {
                bh.consume(ARRAY_MUTABLE_INTEGER[index].value++);
            }
        }
    }

    @Benchmark
    public void memArrayList(Blackhole bh) {
        for (int i = stride; i != 0; i--) {
            for (int index = 0; index < limit; index += stride) {
                bh.consume(ARRAY_LIST.get(index).value++);
            }
        }
    }
}

package io.github.rkraneis.benchmarks;

import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.VerboseMode;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(5)
@State(Scope.Thread)
public class TimerLatency {

	@Param("1")
	int backoff;

	@Benchmark
	public long nanotime() {
		Blackhole.consumeCPU(backoff);
		return System.nanoTime();
	}

	public static void main(String... args) throws RunnerException {
		PrintWriter pw = new PrintWriter(System.out, true);
		for (int b = 1; b <= 1048576; b *= 2) {
			for (int t = 1; t <= Runtime.getRuntime().availableProcessors(); t++) {
				runWith(pw, t, b);
			}
		}
	}

	private static void runWith(PrintWriter pw, int threads, int backoff) throws RunnerException {
		Options opts = new OptionsBuilder()
				.include(".*" + TimerLatency.class.getName() + ".*")
				.threads(threads)
				.verbosity(VerboseMode.SILENT)
				.param("backoff", String.valueOf(backoff))
				.build();

		RunResult r = new Runner(opts).runSingle();
		double score = r.getPrimaryResult().getScore();
		double scoreError = r.getPrimaryResult().getStatistics().getMeanErrorAt(0.99);
		pw.printf("%6d, %3d, %11.3f, %10.3f%n", backoff, threads, score, scoreError);
	}

}

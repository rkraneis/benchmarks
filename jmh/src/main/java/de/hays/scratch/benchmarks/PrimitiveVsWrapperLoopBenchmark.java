/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hays.scratch.benchmarks;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.Result;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.VerboseMode;

/**
 *
 * @author rnk
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class PrimitiveVsWrapperLoopBenchmark {

	/**
	 * @param args the command line arguments
	 * @throws org.openjdk.jmh.runner.RunnerException
	 */
	public static void main(String[] args) throws RunnerException {
		Options opts = new OptionsBuilder()
				.include(".*" + PrimitiveVsWrapperLoopBenchmark.class.getSimpleName() + ".benchmarkCustomIntegerLoop")
				.verbosity(VerboseMode.EXTRA)
				.warmupIterations(5)
				.measurementIterations(3)
				.forks(1)
				.build();
		Collection<RunResult> run = new Runner(opts).run();

		run.forEach(
				runResult -> {
					System.out.println(runResult.getPrimaryResult());
					Map<String, Result> secondaryResults = runResult.getSecondaryResults();
					secondaryResults.forEach(
							(string, result) -> {
								System.out.println(string);
								System.out.println(result);
							});
				});
	}

	@Param({"1"/*, "1000", "1000000", "1000000000"*/})
	public int maxValue;

	PrimitiveVsWrapperLoop primitiveVsWrapperLoop;

	@Setup
	public void setUp() {
		primitiveVsWrapperLoop = new PrimitiveVsWrapperLoop(maxValue);
	}

	@Benchmark
	public void benchmarkPrimitiveLoopLong(Blackhole bh) {
		bh.consume(primitiveVsWrapperLoop.primitiveLoopLong());
	}

	@Benchmark
	public void benchmarkWrapperLoopLong(Blackhole bh) {
		bh.consume(primitiveVsWrapperLoop.wrapperLoopLong());
	}

	@Benchmark
	public void benchmarkWrapperLoopLongLong(Blackhole bh) {
		bh.consume(primitiveVsWrapperLoop.wrapperLoopLongLong());
	}

	@Benchmark
	public void benchmarkPrimitiveLoopInt(Blackhole bh) {
		bh.consume(primitiveVsWrapperLoop.primitiveLoopLong());
	}

	@Benchmark
	public void benchmarkWrapperLoopInt(Blackhole bh) {
		bh.consume(primitiveVsWrapperLoop.wrapperLoopInt());
	}

	@Benchmark
	public void benchmarkWrapperLoopIntInt(Blackhole bh) {
		bh.consume(primitiveVsWrapperLoop.wrapperLoopIntInt());
	}

	@Benchmark
	public void benchmarkCustomIntegerLoop(Blackhole bh) {
		bh.consume(primitiveVsWrapperLoop.customIntegerLoop());
	}
}

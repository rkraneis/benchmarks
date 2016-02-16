package io.github.rkraneis.benchmarks;

import static io.github.rkraneis.benchmarks.FieldAccess.arrayComplexAddition;
import static io.github.rkraneis.benchmarks.FieldAccess.arrayComplexDivision;
import static io.github.rkraneis.benchmarks.FieldAccess.arrayComplexMultiplication;
import static io.github.rkraneis.benchmarks.FieldAccess.arrayInlinedComplexAddition;
import static io.github.rkraneis.benchmarks.FieldAccess.arrayInlinedComplexDivision;
import static io.github.rkraneis.benchmarks.FieldAccess.arrayInlinedComplexMultiplication;
import static io.github.rkraneis.benchmarks.FieldAccess.complexAddition;
import static io.github.rkraneis.benchmarks.FieldAccess.complexDivision;
import static io.github.rkraneis.benchmarks.FieldAccess.complexFlyweightAddition;
import static io.github.rkraneis.benchmarks.FieldAccess.complexFlyweightDivision;
import static io.github.rkraneis.benchmarks.FieldAccess.complexFlyweightMultiplication;
import static io.github.rkraneis.benchmarks.FieldAccess.complexInlinedAddition;
import static io.github.rkraneis.benchmarks.FieldAccess.complexInlinedDivision;
import static io.github.rkraneis.benchmarks.FieldAccess.complexInlinedFlyweightAddition;
import static io.github.rkraneis.benchmarks.FieldAccess.complexInlinedFlyweightDivision;
import static io.github.rkraneis.benchmarks.FieldAccess.complexInlinedFlyweightMultiplication;
import static io.github.rkraneis.benchmarks.FieldAccess.complexInlinedMultiplication;
import static io.github.rkraneis.benchmarks.FieldAccess.complexMultiplication;
import static io.github.rkraneis.benchmarks.FieldAccess.complexSimpleAddition;
import static io.github.rkraneis.benchmarks.FieldAccess.complexSimpleDivision;
import static io.github.rkraneis.benchmarks.FieldAccess.complexSimpleMultiplication;
import static io.github.rkraneis.benchmarks.FieldAccess.primitiveComplexAddition;
import static io.github.rkraneis.benchmarks.FieldAccess.primitiveComplexDivision;
import static io.github.rkraneis.benchmarks.FieldAccess.primitiveComplexDivisionInlined;
import static io.github.rkraneis.benchmarks.FieldAccess.primitiveComplexMultiplication;
import java.util.Random;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@State(Scope.Benchmark)
public class FieldAccessBench {

	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
				.include(".*" + FieldAccessBench.class.getSimpleName() + ".*")
				.warmupIterations(5)
				.measurementIterations(5)
				.forks(1)
				.build();
		new Runner(opt).run();
	}

	@Setup
	public void setup() {
		Random r = new Random();
		FieldAccess.setup(r.nextDouble(), r.nextDouble(), r.nextDouble(), r.nextDouble());
	}

	/*
	 * Addition
	 */
	@Benchmark
	public void timePrimitiveComplexAddition(Blackhole bh) {
		bh.consume(primitiveComplexAddition());
	}

	@Benchmark
	public void timeArrayComplexAddition(Blackhole bh) {
		bh.consume(arrayComplexAddition());
	}

	@Benchmark
	public void timeComplexFlyweightAddition(Blackhole bh) {
		bh.consume(complexFlyweightAddition());
	}

	@Benchmark
	public void timeArrayInlinedComplexAddition(Blackhole bh) {
		bh.consume(arrayInlinedComplexAddition());
	}

	@Benchmark
	public void timeComplexInlinedFlyweightAddition(Blackhole bh) {
		bh.consume(complexInlinedFlyweightAddition());
	}

	@Benchmark
	public void timeComplexSimpleAddition(Blackhole bh) {
		bh.consume(complexSimpleAddition());
	}

	@Benchmark
	public void timeComplexInlinedAddition(Blackhole bh) {
		bh.consume(complexInlinedAddition());
	}

	@Benchmark
	public void timeComplexAddition(Blackhole bh) {
		bh.consume(complexAddition());
	}

	/*
	 * Multiplication
	 */
	@Benchmark
	public void timePrimitiveComplexMultiplication(Blackhole bh) {
		bh.consume(primitiveComplexMultiplication());
	}

	@Benchmark
	public void timeArrayComplexMultiplication(Blackhole bh) {
		bh.consume(arrayComplexMultiplication());
	}

	@Benchmark
	public void timeComplexFlyweightMultiplication(Blackhole bh) {
		bh.consume(complexFlyweightMultiplication());
	}

	@Benchmark
	public void timeArrayInlinedComplexMultiplication(Blackhole bh) {
		bh.consume(arrayInlinedComplexMultiplication());
	}

	@Benchmark
	public void timeComplexInlinedFlyweightMultiplication(Blackhole bh) {
		bh.consume(complexInlinedFlyweightMultiplication());
	}

	@Benchmark
	public void timeComplexSimpleMultiplication(Blackhole bh) {
		bh.consume(complexSimpleMultiplication());
	}

	@Benchmark
	public void timeComplexInlinedMultiplication(Blackhole bh) {
		bh.consume(complexInlinedMultiplication());
	}

	@Benchmark
	public void timeComplexMultiplication(Blackhole bh) {
		bh.consume(complexMultiplication());
	}

	/*
	 * Division
	 */
	@Benchmark
	public void timePrimitiveComplexDivision(Blackhole bh) {
		bh.consume(primitiveComplexDivision());
	}

	@Benchmark
	public void timePrimitiveComplexDivisionInlined(Blackhole bh) {
		bh.consume(primitiveComplexDivisionInlined());
	}

	@Benchmark
	public void timeArrayComplexDivision(Blackhole bh) {
		bh.consume(arrayComplexDivision());
	}

	@Benchmark
	public void timeComplexFlyweightDivision(Blackhole bh) {
		bh.consume(complexFlyweightDivision());
	}

	@Benchmark
	public void timeArrayInlinedComplexDivision(Blackhole bh) {
		bh.consume(arrayInlinedComplexDivision());
	}

	@Benchmark
	public void timeComplexInlinedFlyweightDivision(Blackhole bh) {
		bh.consume(complexInlinedFlyweightDivision());
	}

	@Benchmark
	public void timeComplexSimpleDivision(Blackhole bh) {
		bh.consume(complexSimpleDivision());
	}

	@Benchmark
	public void timeComplexInlinedDivision(Blackhole bh) {
		bh.consume(complexInlinedDivision());
	}

	@Benchmark
	public void timeComplexDivision(Blackhole bh) {
		bh.consume(complexDivision());
	}
}

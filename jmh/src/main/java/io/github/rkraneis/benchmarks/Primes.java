/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.rkraneis.benchmarks;

import java.util.Random;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
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
public class Primes {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws RunnerException {
		Options opts = new OptionsBuilder()
				.include(".*" + Primes.class.getSimpleName() + ".*")
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

	@Setup
	public void setup() {
		while ((a = r.nextLong()) < Integer.MAX_VALUE);
		_5 = Integer.parseInt("5");
	}

	private long a;
	private long _5;

	@Benchmark
	public long div2() {
		return a / 2;
	}

	@Benchmark
	public long div3() {
		return a / 3;
	}

	@Benchmark
	public long div4() {
		return a / 4;
	}

	@Benchmark
	public long div5() {
		return a / 5;
	}

	@Benchmark
	public long div6() {
		return a / 6;
	}

	@Benchmark
	public long div7() {
		return a / 7;
	}

	@Benchmark
	public long div8() {
		return a / 8;
	}

	@Benchmark
	public long div9() {
		return a / 9;
	}

	@Benchmark
	public long div10() {
		return a / 10;
	}

	@Benchmark
	public long div11() {
		return a / 11;
	}

	@Benchmark
	public long div12() {
		return a / 12;
	}

	@Benchmark
	public long div13() {
		return a / 13;
	}

	@Benchmark
	public long div14() {
		return a / 14;
	}

	@Benchmark
	public long div15() {
		return a / 15;
	}

	@Benchmark
	public long div16() {
		return a / 16;
	}

	@Benchmark
	public long div17() {
		return a / 17;
	}

	@Benchmark
	public long div18() {
		return a / 18;
	}

	@Benchmark
	public long div19() {
		return a / 19;
	}

	@Benchmark
	public long div20() {
		return a / 20;
	}

	@Benchmark
	public long div21() {
		return a / 21;
	}

	@Benchmark
	public long div22() {
		return a / 22;
	}

	@Benchmark
	public long div23() {
		return a / 23;
	}

	@Benchmark
	public long div24() {
		return a / 24;
	}

	@Benchmark
	public long div25() {
		return a / 25;
	}

	@Benchmark
	public long div26() {
		return a / 26;
	}

	@Benchmark
	public long div27() {
		return a / 27;
	}

	@Benchmark
	public long div28() {
		return a / 28;
	}

	@Benchmark
	public long div29() {
		return a / 29;
	}

	@Benchmark
	public long div30() {
		return a / 30;
	}

	@Benchmark
	public long div31() {
		return a / 31;
	}

	@Benchmark
	public long div32() {
		return a / 32;
	}

	@Benchmark
	public long div33() {
		return a / 33;
	}

	@Benchmark
	public long div_5() {
		return a / _5;
	}

	//@Benchmark
	public int shift() {
		long b = a;
		while ((b >>= 1) > Integer.MAX_VALUE);
		return (int) b;
	}

	//@Benchmark
	public int shift2() {
		long b = a;
		while ((b = (b - (b >> 31)) >> 1) > Integer.MAX_VALUE);
		return (int) b;
	}

	//@Benchmark
	public int division() {
		long b = a;
		while ((b /= 2) > Integer.MAX_VALUE);
		return (int) b;
	}
}

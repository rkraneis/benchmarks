package de.hays.scratch.benchmarks;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@State(Scope.Benchmark)
@Threads(2)
public class RegexBenchmark {

    public static void main(String[] args) throws RunnerException {
        Options opts = new OptionsBuilder()
                .include(".*" + RegexBenchmark.class.getSimpleName() + ".*")
//                .warmupIterations(11)
//                .measurementIterations(11)
//                .forks(3)
                .build();
        new Runner(opts).run();
    }

    String simplePatternString = "(\\d+)(?:st|nd|rd|th)";
    String lookaheadPatternString = "\\d+(?=st|nd|rd|th)";
    String namedPatternString = "(?<number>\\d+)(?:st|nd|rd|th)";

    Pattern lookaheadPattern = Pattern.compile(lookaheadPatternString);
    Pattern simplePattern = Pattern.compile(simplePatternString);
    Pattern namedPattern = Pattern.compile(namedPatternString);

    String toMatch = "foo 21st bar 22nd baz";

    @Setup
    public void setup() {

    }

    @Benchmark
    public String lookahead() {
        Matcher m = lookaheadPattern.matcher(toMatch);
        m.find();
        return m.group();
    }

    @Benchmark
    public String simple() {
        Matcher m = simplePattern.matcher(toMatch);
        m.find();
        return m.group(1);
    }

    @Benchmark
    public String named() {
        Matcher m = namedPattern.matcher(toMatch);
        m.find();
        return m.group("number");
    }

    @Benchmark
    public String lookahead_compile(Blackhole b) {
        Pattern lookahead = Pattern.compile(lookaheadPatternString);
        Matcher m = lookahead.matcher(toMatch);
        m.find();
        return m.group();
    }

    @Benchmark
    public String simple_compile() {
        Pattern simple = Pattern.compile(simplePatternString);
        Matcher m = simple.matcher(toMatch);
        m.find();
        return m.group(1);
    }

    @Benchmark
    public String named_compile() {
        Pattern named = Pattern.compile(namedPatternString);
        Matcher m = named.matcher(toMatch);
        m.find();
        return m.group("number");
    }
}

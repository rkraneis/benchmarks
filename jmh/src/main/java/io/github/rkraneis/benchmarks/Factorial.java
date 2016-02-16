/*
 * Copyright (c) 2014 Hays AG
 */
package io.github.rkraneis.benchmarks;

import static io.github.rkraneis.benchmarks.TailCalls.call;
import static io.github.rkraneis.benchmarks.TailCalls.done;
import fj.P;
import fj.control.Trampoline;
import fj.data.List;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author <a href="mailto:Rene.Kraneis@hays.de">René Kraneis</a>
 */
public class Factorial {

    private Factorial() {
    }

    public static void test(BigInteger n) {
        if (n.compareTo(BigInteger.valueOf(499980)) > 0) {
            throw new StackOverflowError();
        }
    }

    public static <T, B> B binarySearch(Consumer<T> fut,
            B lower, B upper, Function<B, T> converter,
            BinaryOperator<B> bisector, BiPredicate<B, B> tester) {
        while (tester.test(lower, upper)) {
            B current = bisector.apply(lower, upper);
            try {
                fut.accept(converter.apply(current));
                lower = current;
            } catch (Throwable ignored) {
                upper = current;
            }
        }
        return lower;
    }

    public static <T> int binarySearch(Consumer<T> fut,
            int lower, int upper, Function<Integer, T> converter) {
        while (upper - lower != 1) {
            int current = (lower + upper) >>> 1;
            try {
                fut.accept(converter.apply(current));
                lower = current;
            } catch (Throwable ignored) {
                upper = current;
            }
        }
        return lower;
    }

    public static <T> long binarySearch(Consumer<T> fut,
            long lower, long upper, Function<Long, T> converter) {
        while (upper - lower != 1) {
            long current = (lower + upper) >>> 1;
            try {
                fut.accept(converter.apply(current));
                lower = current;
            } catch (Throwable ignored) {
                upper = current;
            }
        }
        return lower;
    }

    public static void main(String... args) {
        System.out.printf("test: %s%n",
                binarySearch(Factorial::test,
                        0L, 1000000L, BigInteger::valueOf,
                        (l, u) -> (l + u) >>> 1, (l, u) -> u - l != -1)
        );
        /*
        System.out.printf("factorialCorecursive: %s%n",
                binarySearch(Factorial::factorialCorecursive,
                        0, 1000000, BigInteger::valueOf)
        );
        System.out.printf("factorialRecursive: %s%n",
                binarySearch(Factorial::factorialRecursive,
                        0, 1000000, BigInteger::valueOf)
        );
        System.out.printf("factorialRecursive2: %s%n",
                binarySearch(Factorial::factorialRecursive2,
                        0, 1000000, BigInteger::valueOf)
        );
         */
        //System.out.println(factorialTrampoline(BigInteger.valueOf(100000)));
    }

    public static BigInteger factorialTrampoline(BigInteger n) {
        return factorialTrampoline(n, BigInteger.valueOf(1)).invoke();
    }

    private static TailCall<BigInteger> factorialTrampoline(BigInteger n, BigInteger a) {
        return n.compareTo(BigInteger.ONE) > 0
                ? call(() -> factorialTrampoline(n.subtract(BigInteger.ONE), n.multiply(a)))
                : done(a);
    }

    public static BigInteger factorialTrampolineCyclops(BigInteger n) {
        return factorialTrampolineCyclops(n, BigInteger.valueOf(1)).result();
    }

    private static com.aol.cyclops.trampoline.Trampoline<BigInteger> factorialTrampolineCyclops(BigInteger n, BigInteger a) {
        return n.compareTo(BigInteger.ONE) > 0
                ? com.aol.cyclops.trampoline.Trampoline.more(() -> factorialTrampolineCyclops(n.subtract(BigInteger.ONE), n.multiply(a)))
                : com.aol.cyclops.trampoline.Trampoline.done(a);
    }

    public static BigInteger factorialTrampolineFj(BigInteger n) {
        List<BigInteger> l = List.iterateWhile(i -> i.add(BigInteger.ONE), i -> i.compareTo(n) <= 0, BigInteger.ONE);
        return factorialTrampolineFj(BigInteger.ONE, l).run();
    }

    private static Trampoline<BigInteger> factorialTrampolineFj(BigInteger acc, List<BigInteger> stack) {
        return Trampoline.suspend(P.lazy(__
                -> stack.isEmpty()
                        ? Trampoline.pure(acc)
                        : factorialTrampolineFj(acc.multiply(stack.head()), stack.tail())
        ));
    }

    public static BigInteger factorialCorecursive(BigInteger n) {
        return factorialCorecursive(n, BigInteger.ONE);
    }

    private static BigInteger factorialCorecursive(BigInteger n, BigInteger a) {
        return n.compareTo(BigInteger.ONE) > 0
                ? factorialCorecursive(n.subtract(BigInteger.ONE), n.multiply(a))
                : a;
    }

    public static BigInteger factorialRecursive(BigInteger n) {
        return n.compareTo(BigInteger.ONE) > 0
                ? n.multiply(factorialRecursive(n.subtract(BigInteger.ONE)))
                : n;
    }

    public static BigInteger factorialRecursive2(BigInteger n) {
        BigInteger f;
        if (n.compareTo(BigInteger.ONE) > 0) {
            f = n.multiply(factorialRecursive2(n.subtract(BigInteger.ONE)));
        } else {
            f = n;
        }
        return f;
    }

    public static BigInteger factorialIterative(BigInteger n) {
        BigInteger a = n;
        while ((n = n.subtract(BigInteger.ONE)).compareTo(BigInteger.ONE) > 0) {
            a = a.multiply(n);
        }
        return a;
    }
}

interface TailCall<T> {

    TailCall<T> apply();

    default boolean isComplete() {
        return false;
    }

    default T result() {
        throw new UnsupportedOperationException("not implemented");
    }

    default T invoke() {
        return Stream.iterate(this, TailCall::apply)
                .filter(TailCall::isComplete)
                .findFirst()
                .get()
                .result();
    }
}

class TailCalls {

    public static <T> TailCall<T> call(final TailCall<T> nextCall) {
        return nextCall;
    }

    public static <T> TailCall<T> done(final T value) {
        return new TailCall<T>() {
            @Override
            public boolean isComplete() {
                return true;
            }

            @Override
            public T result() {
                return value;
            }

            @Override
            public TailCall<T> apply() {
                throw new UnsupportedOperationException("not implemented");
            }
        };
    }
}

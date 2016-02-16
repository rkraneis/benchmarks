/*
 * Copyright (c) 2014 Hays AG
 */
package de.hays.scratch.benchmarks;

/**
 * `a^2 + b^2 = c^2`
 *
 * @author <a href="mailto:Rene.Kraneis@hays.de">René Kraneis</a>
 */
public class Fib {

    private Fib() {
    }

    public static long fibCorecursive(int n) {
        return fibCorecursive(n, 0, 1, 0);
    }

    private static long fibCorecursive(int n, long a, long b, int i) {
        return i == n ? a : fibCorecursive(n, b, a + b, i + 1);
    }

    public static long fibCorecursive2(int n) {
        return fibCorecursive2(n, 0, 1);
    }

    private static long fibCorecursive2(int n, long a, long b) {
        return n > 0 ? fibCorecursive2(n - 1, b, a + b) : a;
    }

    public static long fibRecursive(int n) {
        return n > 1 ? fibRecursive(n - 2) + fibRecursive(n - 1) : n;
    }

    public static long fibRecursive2(int n) {
        long f;
        if (n > 1) {
            long f2 = fibRecursive(n - 2);
            long f1 = fibRecursive(n - 1);
            f = f1 + f2;
        } else {
            f = n;
        }
        return f;
    }

    public static long fibIterative(int n) {
        long a = 0, b = 1;
        while (n-- > 0) {
            long c = a;
            a = b;
            b = c + b;
        }
        return a;
    }
}

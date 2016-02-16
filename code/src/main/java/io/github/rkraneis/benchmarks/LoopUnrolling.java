package io.github.rkraneis.benchmarks;

import java.util.Arrays;

public class LoopUnrolling {

    private static final int M2 = 0xFFFFFFFF << 1;
    private static final int M4 = 0xFFFFFFFF << 2;
    private static final int M8 = 0xFFFFFFFF << 3;
    private static final int M16 = 0xFFFFFFFF << 4;

    public static int sumFor(int[] is) {
        int sum = 0;
        for (int i = 0; i < is.length; i++) {
            sum += is[i];
        }
        return sum;
    }

    public static int sumForF(final int[] is) {
        int sum = 0;
        for (int i = 0; i < is.length; i++) {
            sum += is[i];
        }
        return sum;
    }

    public static int sumFor2(int[] is) {
        int sum0 = 0, sum1 = 0, sum2 = 0;
        int n = is.length & M2;
        for (int i = 0; i < n; i += 2) {
            sum0 += is[i + 0];
            sum1 += is[i + 1];
        }
        for (int i = n; i < is.length; i++) {
            sum2 += is[i];
        }
        return sum0 + sum1 + sum2;
    }

    public static int sumFor4(int[] is) {
        int sum0 = 0, sum1 = 0, sum2 = 0, sum3 = 0, sum4 = 0;
        int n = is.length & M4;
        for (int i = 0; i < n; i += 4) {
            sum0 += is[i + 0];
            sum1 += is[i + 1];
            sum2 += is[i + 2];
            sum3 += is[i + 3];
        }
        for (int i = n; i < is.length; i++) {
            sum4 += is[i];
        }
        return sum0 + sum1 + sum2 + sum3 + sum4;
    }

    public static int sumFor4F(final int[] is) {
        int sum0 = 0, sum1 = 0, sum2 = 0, sum3 = 0, sum4 = 0;
        final int n = is.length & M4;
        for (int i = 0; i < n; i += 4) {
            sum0 += is[i + 0];
            sum1 += is[i + 1];
            sum2 += is[i + 2];
            sum3 += is[i + 3];
        }
        for (int i = n; i < is.length; i++) {
            sum4 += is[i];
        }
        return sum0 + sum1 + sum2 + sum3 + sum4;
    }

    public static int sumFor4F2(final int[] is) {
        int sum0 = 0, sum1 = 0, sum2 = 0, sum3 = 0, sum4 = 0;
        final int n = is.length & M4;
        for (int i = 0; i < n; i += 4) {
            sum0 += is[i + 0];
            sum1 += is[i + 1];
            sum2 += is[i + 2];
            sum3 += is[i + 3];
        }
        for (int i = n; i < is.length; i++) {
            sum4 += is[i];
        }
        return sum0 + sum1 + sum2 + sum3 + sum4;
    }

    public static int sumFor8(int[] is) {
        int sum0 = 0, sum1 = 0, sum2 = 0, sum3 = 0, sum4 = 0,
                sum5 = 0, sum6 = 0, sum7 = 0, sum8 = 0;
        int n = is.length & M8;
        for (int i = 0; i < n; i += 8) {
            sum0 += is[i + 0];
            sum1 += is[i + 1];
            sum2 += is[i + 2];
            sum3 += is[i + 3];
            sum4 += is[i + 4];
            sum5 += is[i + 5];
            sum6 += is[i + 6];
            sum7 += is[i + 7];
        }
        for (int i = n; i < is.length; i++) {
            sum8 += is[i];
        }
        return sum0 + sum1 + sum2 + sum3 + sum4
                + sum5 + sum6 + sum7 + sum8;
    }

    public static int sumFor16(int[] is) {
        int sum0 = 0, sum1 = 0, sum2 = 0, sum3 = 0, sum4 = 0,
                sum5 = 0, sum6 = 0, sum7 = 0, sum8 = 0,
                sum9 = 0, sum10 = 0, sum11 = 0, sum12 = 0,
                sum13 = 0, sum14 = 0, sum15 = 0, sum16 = 0;
        int n = is.length & M16;
        for (int i = 0; i < n; i += 16) {
            sum0 += is[i + 0];
            sum1 += is[i + 1];
            sum2 += is[i + 2];
            sum3 += is[i + 3];
            sum4 += is[i + 4];
            sum5 += is[i + 5];
            sum6 += is[i + 6];
            sum7 += is[i + 7];
            sum8 += is[i + 8];
            sum9 += is[i + 9];
            sum10 += is[i + 10];
            sum11 += is[i + 11];
            sum12 += is[i + 12];
            sum13 += is[i + 13];
            sum14 += is[i + 14];
            sum15 += is[i + 15];
        }
        for (int i = n; i < is.length; i++) {
            sum16 += is[i];
        }
        return sum0 + sum1 + sum2 + sum3 + sum4
                + sum5 + sum6 + sum7 + sum8
                + sum9 + sum10 + sum11 + sum12
                + sum13 + sum14 + sum15 + sum16;
    }

    public static int sumFor16B1(int[] is) {
        int sum0 = 0, sum1 = 0, sum2 = 0, sum3 = 0, sum4 = 0,
                sum5 = 0, sum6 = 0, sum7 = 0, sum8 = 0,
                sum9 = 0, sum10 = 0, sum11 = 0, sum12 = 0,
                sum13 = 0, sum14 = 0, sum15 = 0, sum16 = 0;
        int n;
        if (is.length >= 16) {
            n = is.length & M16;
            for (int i = 0; i < n; i += 16) {
                sum0 += is[i + 0];
                sum1 += is[i + 1];
                sum2 += is[i + 2];
                sum3 += is[i + 3];
                sum4 += is[i + 4];
                sum5 += is[i + 5];
                sum6 += is[i + 6];
                sum7 += is[i + 7];
                sum8 += is[i + 8];
                sum9 += is[i + 9];
                sum10 += is[i + 10];
                sum11 += is[i + 11];
                sum12 += is[i + 12];
                sum13 += is[i + 13];
                sum14 += is[i + 14];
                sum15 += is[i + 15];
            }
        } else {
            n = 0;
        }
        for (int i = n; i < is.length; i++) {
            sum16 += is[i];
        }
        return sum0 + sum1 + sum2 + sum3 + sum4
                + sum5 + sum6 + sum7 + sum8
                + sum9 + sum10 + sum11 + sum12
                + sum13 + sum14 + sum15 + sum16;
    }

    public static int sumFor16B2(int[] is) {
        if (is.length < 16) {
            return sumFor(is);
        }
        int sum0 = 0, sum1 = 0, sum2 = 0, sum3 = 0, sum4 = 0,
                sum5 = 0, sum6 = 0, sum7 = 0, sum8 = 0,
                sum9 = 0, sum10 = 0, sum11 = 0, sum12 = 0,
                sum13 = 0, sum14 = 0, sum15 = 0, sum16 = 0;
        int n = is.length & M16;
        for (int i = 0; i < n; i += 16) {
            sum0 += is[i + 0];
            sum1 += is[i + 1];
            sum2 += is[i + 2];
            sum3 += is[i + 3];
            sum4 += is[i + 4];
            sum5 += is[i + 5];
            sum6 += is[i + 6];
            sum7 += is[i + 7];
            sum8 += is[i + 8];
            sum9 += is[i + 9];
            sum10 += is[i + 10];
            sum11 += is[i + 11];
            sum12 += is[i + 12];
            sum13 += is[i + 13];
            sum14 += is[i + 14];
            sum15 += is[i + 15];
        }
        for (int i = n; i < is.length; i++) {
            sum16 += is[i];
        }
        return sum0 + sum1 + sum2 + sum3 + sum4
                + sum5 + sum6 + sum7 + sum8
                + sum9 + sum10 + sum11 + sum12
                + sum13 + sum14 + sum15 + sum16;
    }

    public static int sumWhile4(int[] is) {
        int sum0 = 0, sum1 = 0, sum2 = 0, sum3 = 0, sum4 = 0;
        int i = 0, n = (is.length / 4) * 4;
        while (i < n) {
            sum0 += is[i++];
            sum1 += is[i++];
            sum2 += is[i++];
            sum3 += is[i++];
        }
        while (i < is.length) {
            sum4 += is[i++];
        }
        return sum0 + sum1 + sum2 + sum3 + sum4;
    }

    public static int sumWhile4F(final int[] is) {
        int sum0 = 0, sum1 = 0, sum2 = 0, sum3 = 0, sum4 = 0;
        int i = 0;
        final int n = (is.length / 4) * 4;
        while (i < n) {
            sum0 += is[i++];
            sum1 += is[i++];
            sum2 += is[i++];
            sum3 += is[i++];
        }
        while (i < is.length) {
            sum4 += is[i++];
        }
        return sum0 + sum1 + sum2 + sum3 + sum4;
    }

    public static int sumWhile4F2(final int[] is) {
        int sum0 = 0, sum1 = 0, sum2 = 0, sum3 = 0, sum4 = 0;
        int i = 0;
        final int n = is.length & 0xfffffffc;
        while (i < n) {
            sum0 += is[i++];
            sum1 += is[i++];
            sum2 += is[i++];
            sum3 += is[i++];
        }
        while (i < is.length) {
            sum4 += is[i++];
        }
        return sum0 + sum1 + sum2 + sum3 + sum4;
    }

    public static int sumForEach(int[] is) {
        int sum = 0;
        for (int i : is) {
            sum += i;
        }
        return sum;
    }

    public static int sumForEachF(final int[] is) {
        int sum = 0;
        for (final int i : is) {
            sum += i;
        }
        return sum;
    }

    public static int sumStream(int[] is) {
        return Arrays.stream(is).sum();
    }

    public static int sumStreamF(final int[] is) {
        return Arrays.stream(is).sum();
    }

    public static int sumParallelStream(int[] is) {
        return Arrays.stream(is).parallel().sum();
    }

    public static int sumParallelStreamF(final int[] is) {
        return Arrays.stream(is).parallel().sum();
    }

}

package io.github.rkraneis.benchmarks;

/**
 *
 * @author RNK
 */
public class MatrixMultiplication1D {

    public static double[] ikj(double[] d, double[] e, int size) {
        double[] f = new double[size * size];
        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                for (int j = 0; j < size; j++) {
                    f[i * size + k] += d[i * size + j] * e[j * size + k];
                }
            }
        }
        return f;
    }

    public static double[] jki(double[] d, double[] e, int size) {
        double[] f = new double[size * size];
        for (int j = 0; j < size; j++) {
            for (int k = 0; k < size; k++) {
                for (int i = 0; i < size; i++) {
                    f[i * size + k] += d[i * size + j] * e[j * size + k];
                }
            }
        }
        return f;
    }

    public static double[] kij(double[] d, double[] e, int size) {
        double[] f = new double[size * size];
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    f[i * size + k] += d[i * size + j] * e[j * size + k];
                }
            }
        }
        return f;
    }

    public static double[] jik(double[] d, double[] e, int size) {
        double[] f = new double[size * size];
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                for (int k = 0; k < size; k++) {
                    f[i * size + k] += d[i * size + j] * e[j * size + k];
                }
            }
        }
        return f;
    }

    public static double[] kji(double[] d, double[] e, int size) {
        double[] f = new double[size * size];
        for (int k = 0; k < size; k++) {
            for (int j = 0; j < size; j++) {
                for (int i = 0; i < size; i++) {
                    f[i * size + k] += d[i * size + j] * e[j * size + k];
                }
            }
        }
        return f;
    }

    public static double[] ijk(double[] d, double[] e, int size) {
        double[] f = new double[size * size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    f[i * size + k] += d[i * size + j] * e[j * size + k];
                }
            }
        }
        return f;
    }

}

package io.github.rkraneis.benchmarks;

/**
 *
 * @author RNK
 */
public class MatrixMultiplication2D {

    public static double[][] jki(double[][] a, double[][] b, int size) {
        double[][] c = new double[size][size];
        for (int j = 0; j < size; j++) {
            for (int k = 0; k < size; k++) {
                for (int i = 0; i < size; i++) {
                    c[i][k] += a[i][j] * b[j][k];
                }
            }
        }
        return c;
    }

    public static double[][] ikj(double[][] a, double[][] b, int size) {
        double[][] c = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                for (int j = 0; j < size; j++) {
                    c[i][k] += a[i][j] * b[j][k];
                }
            }
        }
        return c;
    }

    public static double[][] jik(double[][] a, double[][] b, int size) {
        double[][] c = new double[size][size];
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                for (int k = 0; k < size; k++) {
                    c[i][k] += a[i][j] * b[j][k];
                }
            }
        }
        return c;
    }

    public static double[][] kij(double[][] a, double[][] b, int size) {
        double[][] c = new double[size][size];
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    c[i][k] += a[i][j] * b[j][k];
                }
            }
        }
        return c;
    }

    public static double[][] kji(double[][] a, double[][] b, int size) {
        double[][] c = new double[size][size];
        for (int k = 0; k < size; k++) {
            for (int j = 0; j < size; j++) {
                for (int i = 0; i < size; i++) {
                    c[i][k] += a[i][j] * b[j][k];
                }
            }
        }
        return c;
    }

    public static double[][] ijk(double[][] a, double[][] b, int size) {
        double[][] c = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    c[i][k] += a[i][j] * b[j][k];
                }
            }
        }
        return c;
    }

}

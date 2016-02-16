package de.hays.scratch.benchmarks;

import org.apache.commons.math3.complex.Complex;

public class FieldAccess {

	public static final class Complexes {

		private static final int RE = 0;
		private static final int IM = 1;

		static double[] add(double[] c1, double[] c2) {
			final double r = c1[RE] + c2[RE];
			final double i = c1[IM] + c2[IM];
			return new double[]{r, i};
		}

		static ComplexFlyweight add(ComplexFlyweight c1, ComplexFlyweight c2) {
			final double r = c1.re + c2.re;
			final double i = c1.im + c2.im;
			return new ComplexFlyweight(r, i);
		}

		static double[] multiply(double[] c1, double[] c2) {
			final double r = c1[RE] * c2[RE] - c1[IM] * c2[IM];
			final double i = c1[IM] * c2[RE] + c1[RE] * c2[IM];
			return new double[]{r, i};
		}

		static ComplexFlyweight multiply(ComplexFlyweight c1, ComplexFlyweight c2) {
			final double r = c1.re * c2.re - c1.im * c2.im;
			final double i = c1.im * c2.re + c1.re * c2.im;
			return new ComplexFlyweight(r, i);
		}

		static double[] divide(double[] c1, double[] c2) {
			final double r_nominator = c1[RE] * c2[RE] + c1[IM] * c2[IM];
			final double i_nominator = c1[IM] * c2[RE] - c1[RE] * c2[IM];
			final double denominator = c1[RE] * c1[RE] + c1[IM] * c1[IM];
			return new double[]{r_nominator / denominator, i_nominator / denominator
			};
		}

		static ComplexFlyweight divide(ComplexFlyweight c1, ComplexFlyweight c2) {
			final double r_nominator = c1.re * c2.re + c1.im * c2.im;
			final double i_nominator = c1.im * c2.re - c1.re * c2.im;
			final double denominator = c1.re * c1.re + c1.im * c1.im;
			return new ComplexFlyweight(r_nominator / denominator, i_nominator / denominator);
		}
	}

	public static final class ComplexesInlined {

		private static final int RE = 0;
		private static final int IM = 1;

		static double[] add(double[] c1, double[] c2) {
			return new double[]{
				c1[RE] + c2[RE],
				c1[IM] + c2[IM]
			};
		}

		static ComplexFlyweight add(ComplexFlyweight c1, ComplexFlyweight c2) {
			return new ComplexFlyweight((c1.re + c2.re), (c1.im + c2.im));
		}

		static double[] multiply(double[] c1, double[] c2) {
			return new double[]{
				c1[RE] * c2[RE] - c1[IM] * c2[IM],
				c1[IM] * c2[RE] + c1[RE] * c2[IM]};
		}

		static ComplexFlyweight multiply(ComplexFlyweight c1, ComplexFlyweight c2) {
			return new ComplexFlyweight((c1.re * c2.re - c1.im * c2.im), (c1.im * c2.re + c1.re * c2.im));
		}

		static double[] divide(double[] c1, double[] c2) {
			return new double[]{
				(c1[RE] * c2[RE] + c1[IM] * c2[IM]) / (c1[RE] * c1[RE] + c1[IM] * c1[IM]),
				(c1[IM] * c2[RE] - c1[RE] * c2[IM]) / (c1[RE] * c1[RE] + c1[IM] * c1[IM])
			};
		}

		static ComplexFlyweight divide(ComplexFlyweight c1, ComplexFlyweight c2) {
			return new ComplexFlyweight(
					(c1.re * c2.re + c1.im * c2.im) / (c1.re * c1.re + c1.im * c1.im),
					(c1.im * c2.re - c1.re * c2.im) / (c1.re * c1.re + c1.im * c1.im)
			);
		}
	}

	public static final class ComplexFlyweight {

		public final double re, im;

		ComplexFlyweight(double re, double im) {
			this.re = re;
			this.im = im;
		}
	}

	public static final class ComplexInlined {

		public final double re, im;

		ComplexInlined(double re, double im) {
			this.re = re;
			this.im = im;
		}

		ComplexInlined add(ComplexInlined c) {
			return new ComplexInlined((re + c.re), (im + c.im));
		}

		ComplexInlined multiply(ComplexInlined c) {
			return new ComplexInlined((re * c.re - im * c.im), (im * c.re + re * c.im));
		}

		ComplexInlined divide(ComplexInlined c) {
			return new ComplexInlined(
					(re * c.re + im * c.im) / (c.re * c.re + c.im * c.im),
					(im * c.re - re * c.im) / (c.re * c.re + c.im * c.im)
			);
		}
	}

	public static final class ComplexSimple {

		public final double re, im;

		ComplexSimple(double re, double im) {
			this.re = re;
			this.im = im;
		}

		ComplexSimple add(ComplexSimple c) {
			final double r = re + c.re;
			final double i = im + c.im;
			return new ComplexSimple(r, i);
		}

		ComplexSimple multiply(ComplexSimple c) {
			final double r = re * c.re - im * c.im;
			final double i = im * c.re + re * c.im;
			return new ComplexSimple(r, i);
		}

		ComplexSimple divide(ComplexSimple c) {
			final double r_nominator = re * c.re + im * c.im;
			final double i_nominator = im * c.re - re * c.im;
			final double denominator = c.re * c.re + c.im * c.im;
			return new ComplexSimple(
					r_nominator / denominator,
					i_nominator / denominator
			);
		}
	}

	private static double c1_re = -1.5, c1_im = -1.5;
	private static double c2_re = 2.5, c2_im = 2.5;

	private static double[] c1_a = {c1_re, c1_im};
	private static double[] c2_a = {c2_re, c2_im};

	private static ComplexFlyweight c1_f = new ComplexFlyweight(c1_re, c1_im);
	private static ComplexFlyweight c2_f = new ComplexFlyweight(c2_re, c2_im);

	private static ComplexSimple c1_s = new ComplexSimple(c1_re, c1_im);
	private static ComplexSimple c2_s = new ComplexSimple(c2_re, c2_im);

	private static ComplexInlined c1_i = new ComplexInlined(c1_re, c1_im);
	private static ComplexInlined c2_i = new ComplexInlined(c2_re, c2_im);

	private static Complex c1 = new Complex(c1_re, c1_im);
	private static Complex c2 = new Complex(c2_re, c2_im);

	public static void setup(double re1, double im1, double re2, double im2) {
		c1_re = re1;
		c1_im = im1;
		c2_re = re2;
		c2_im = im2;

		c1_a = new double[]{c1_re, c1_im};
		c2_a = new double[]{c2_re, c2_im};

		c1_f = new ComplexFlyweight(c1_re, c1_im);
		c2_f = new ComplexFlyweight(c2_re, c2_im);

		c1_s = new ComplexSimple(c1_re, c1_im);
		c2_s = new ComplexSimple(c2_re, c2_im);

		c1_i = new ComplexInlined(c1_re, c1_im);
		c2_i = new ComplexInlined(c2_re, c2_im);

		c1 = new Complex(c1_re, c1_im);
		c2 = new Complex(c2_re, c2_im);
	}

	/*
	 * Addition
	 */
	public static double primitiveComplexAddition() {
		final double c_re = c1_re + c2_re;
		final double c_im = c1_im + c2_im;
		return c_re + c_im;
	}

	public static double[] arrayComplexAddition() {
		return Complexes.add(c1_a, c2_a);
	}

	public static ComplexFlyweight complexFlyweightAddition() {
		return Complexes.add(c1_f, c2_f);
	}

	public static double[] arrayInlinedComplexAddition() {
		return ComplexesInlined.add(c1_a, c2_a);
	}

	public static ComplexFlyweight complexInlinedFlyweightAddition() {
		return ComplexesInlined.add(c1_f, c2_f);
	}

	public static ComplexSimple complexSimpleAddition() {
		return c1_s.add(c2_s);
	}

	public static ComplexInlined complexInlinedAddition() {
		return c1_i.add(c2_i);
	}

	public static Complex complexAddition() {
		return c1.add(c2);
	}

	/*
	 * Multiplication
	 */
	public static double primitiveComplexMultiplication() {
		final double c_re = c1_re * c2_re - c1_im * c2_im;
		final double c_im = c1_im * c2_re + c1_re * c2_im;
		return c_re + c_im;
	}

	public static double[] arrayComplexMultiplication() {
		return Complexes.multiply(c1_a, c2_a);
	}

	public static ComplexFlyweight complexFlyweightMultiplication() {
		return Complexes.multiply(c1_f, c2_f);
	}

	public static double[] arrayInlinedComplexMultiplication() {
		return ComplexesInlined.multiply(c1_a, c2_a);
	}

	public static ComplexFlyweight complexInlinedFlyweightMultiplication() {
		return ComplexesInlined.multiply(c1_f, c2_f);
	}

	public static ComplexSimple complexSimpleMultiplication() {
		return c1_s.multiply(c2_s);
	}

	public static ComplexInlined complexInlinedMultiplication() {
		return c1_i.multiply(c2_i);
	}

	public static Complex complexMultiplication() {
		return c1.multiply(c2);
	}

	/*
	 * Division
	 */
	public static double primitiveComplexDivision() {
		final double r_nominator = c1_re * c2_re + c1_im * c2_im;
		final double i_nominator = c1_im * c2_re - c1_re * c2_im;
		final double denominator = c1_re * c1_re + c1_im * c1_im;
		final double c_re = r_nominator / denominator;
		final double c_im = i_nominator / denominator;
		return c_re + c_im;
	}

	public static double primitiveComplexDivisionInlined() {
		final double c_re = (c1_re * c2_re + c1_im * c2_im) / (c1_re * c1_re + c1_im * c1_im);
		final double c_im = (c1_im * c2_re - c1_re * c2_im) / (c1_re * c1_re + c1_im * c1_im);
		return c_re + c_im;
	}

	public static double[] arrayComplexDivision() {
		return Complexes.divide(c1_a, c2_a);
	}

	public static ComplexFlyweight complexFlyweightDivision() {
		return Complexes.divide(c1_f, c2_f);
	}

	public static double[] arrayInlinedComplexDivision() {
		return ComplexesInlined.divide(c1_a, c2_a);
	}

	public static ComplexFlyweight complexInlinedFlyweightDivision() {
		return ComplexesInlined.divide(c1_f, c2_f);
	}

	public static ComplexSimple complexSimpleDivision() {
		return c1_s.divide(c2_s);
	}

	public static ComplexInlined complexInlinedDivision() {
		return c1_i.divide(c2_i);
	}

	public static Complex complexDivision() {
		return c1.divide(c2);
	}
}

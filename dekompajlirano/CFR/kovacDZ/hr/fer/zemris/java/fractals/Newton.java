/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  IFractalResultObserver
 */
package hr.fer.zemris.java.fractals;

import hr.fer.zemris.java.fractals.NewtonFractal;
import hr.fer.zemris.math.Complex;
import hr.fer.zemris.math.ComplexPolynomial;
import hr.fer.zemris.math.ComplexRootedPolynomial;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

public class Newton {
    public static void main(String[] arrstring) {
        throw new Error("Unresolved compilation problem: \n\tFractalViewer cannot be resolved\n");
    }

    private static Complex parseComplex(String input) {
        if (input.matches("\\s*(-i|\\+i|i)\\s*")) {
            input = input.replace("i", "1");
            return new Complex(0.0, Double.parseDouble(input));
        }
        if (input.matches("\\s*[+-]?i\\d+\\.?\\d*\\s*")) {
            return new Complex(0.0, Double.parseDouble(input.replace("i", "")));
        }
        if (input.matches("\\s*[+-]?\\d+(\\.)?\\d*(\\s*[+-]\\s*i\\d+\\.?\\d*)?\\s*")) {
            int indexOfI = input.indexOf("i");
            input = input.replace("i", "");
            if (indexOfI != -1) {
                int indexOfMinus;
                int indexOfPlus = input.lastIndexOf("+");
                int indexOfSplit = indexOfPlus > (indexOfMinus = input.lastIndexOf("-")) ? indexOfPlus : indexOfMinus;
                return new Complex(Double.parseDouble(input.substring(0, indexOfSplit)), Double.parseDouble(input.substring(indexOfSplit, input.length()).replaceAll("\\s", "")));
            }
            return new Complex(Double.parseDouble(input), 0.0);
        }
        if (input.matches("\\s*[+-]?\\d+\\.?\\d*\\s*[+-]\\s*i\\s*")) {
            int indexOfMinus;
            int indexOfPlus = (input = input.replace("i", "1")).lastIndexOf("+");
            int indexOfSplit = indexOfPlus > (indexOfMinus = input.lastIndexOf("-")) ? indexOfPlus : indexOfMinus;
            return new Complex(Double.parseDouble(input.substring(0, indexOfSplit)), Double.parseDouble(input.substring(indexOfSplit, input.length()).replaceAll("\\s", "")));
        }
        throw new IllegalArgumentException();
    }

    public static class NewtonFractalJob
    implements Callable<Void> {
        double reMin;
        double reMax;
        double imMin;
        double imMax;
        int width;
        int height;
        int yMin;
        int yMax;
        int m;
        short[] data;
        AtomicBoolean cancel;
        ComplexPolynomial polynomial;
        ComplexRootedPolynomial rootedPolynomial;
        double rootDistance;
        double convergenceTreshold;

        public NewtonFractalJob(double reMin, double reMax, double imMin, double imMax, int width, int height, int yMin, int yMax, int m, short[] data, AtomicBoolean cancel, ComplexPolynomial polynomial, ComplexRootedPolynomial rootedPolynomial, double rootDistance, double convergenceTreshold) {
            this.reMin = reMin;
            this.reMax = reMax;
            this.imMin = imMin;
            this.imMax = imMax;
            this.width = width;
            this.height = height;
            this.yMin = yMin;
            this.yMax = yMax;
            this.m = m;
            this.data = data;
            this.cancel = cancel;
            this.polynomial = polynomial;
            this.rootedPolynomial = rootedPolynomial;
            this.rootDistance = rootDistance;
            this.convergenceTreshold = convergenceTreshold;
        }

        @Override
        public Void call() throws Exception {
            NewtonFractal.calculate(this.reMin, this.reMax, this.imMin, this.imMax, this.width, this.height, this.yMin, this.yMax, this.m, this.data, this.cancel, this.polynomial, this.rootedPolynomial, this.rootDistance, this.convergenceTreshold);
            return null;
        }
    }

    private static class NewtonProducer {
        private ExecutorService pool;
        private ComplexRootedPolynomial rootedPolynomial;

        public NewtonProducer(ComplexRootedPolynomial complexRootedPolynomial) {
            throw new Error("Unresolved compilation problems: \n\tThe import hr.fer.zemris.java.fractals.viewer cannot be resolved\n\tThe import hr.fer.zemris.java.fractals.viewer cannot be resolved\n\tThe import hr.fer.zemris.java.fractals.viewer cannot be resolved\n\tFractalViewer cannot be resolved\n\tIFractalProducer cannot be resolved to a type\n\tIFractalResultObserver cannot be resolved to a type\n");
        }

        public void produce(double d, double d2, double d3, double d4, int n, int n2, long l, IFractalResultObserver iFractalResultObserver, AtomicBoolean atomicBoolean) {
            throw new Error("Unresolved compilation problem: \n\tIFractalResultObserver cannot be resolved to a type\n");
        }
    }

}

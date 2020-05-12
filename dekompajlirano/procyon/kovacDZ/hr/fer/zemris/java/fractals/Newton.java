// 
// Decompiled by Procyon v0.5.36
// 

package hr.fer.zemris.java.fractals;

import java.util.concurrent.ExecutorService;
import hr.fer.zemris.math.ComplexRootedPolynomial;
import hr.fer.zemris.math.ComplexPolynomial;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.Callable;
import hr.fer.zemris.math.Complex;

public class Newton
{
    public static void main(final String[] array) {
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
            final int indexOfI = input.indexOf("i");
            input = input.replace("i", "");
            if (indexOfI != -1) {
                final int indexOfPlus = input.lastIndexOf("+");
                final int indexOfMinus = input.lastIndexOf("-");
                final int indexOfSplit = (indexOfPlus > indexOfMinus) ? indexOfPlus : indexOfMinus;
                return new Complex(Double.parseDouble(input.substring(0, indexOfSplit)), Double.parseDouble(input.substring(indexOfSplit, input.length()).replaceAll("\\s", "")));
            }
            return new Complex(Double.parseDouble(input), 0.0);
        }
        else {
            if (input.matches("\\s*[+-]?\\d+\\.?\\d*\\s*[+-]\\s*i\\s*")) {
                input = input.replace("i", "1");
                final int indexOfPlus2 = input.lastIndexOf("+");
                final int indexOfMinus2 = input.lastIndexOf("-");
                final int indexOfSplit2 = (indexOfPlus2 > indexOfMinus2) ? indexOfPlus2 : indexOfMinus2;
                return new Complex(Double.parseDouble(input.substring(0, indexOfSplit2)), Double.parseDouble(input.substring(indexOfSplit2, input.length()).replaceAll("\\s", "")));
            }
            throw new IllegalArgumentException();
        }
    }
    
    public static class NewtonFractalJob implements Callable<Void>
    {
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
        
        public NewtonFractalJob(final double reMin, final double reMax, final double imMin, final double imMax, final int width, final int height, final int yMin, final int yMax, final int m, final short[] data, final AtomicBoolean cancel, final ComplexPolynomial polynomial, final ComplexRootedPolynomial rootedPolynomial, final double rootDistance, final double convergenceTreshold) {
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
    
    private static class NewtonProducer
    {
        private ExecutorService pool;
        private ComplexRootedPolynomial rootedPolynomial;
        
        public NewtonProducer(final ComplexRootedPolynomial complexRootedPolynomial) {
            throw new Error("Unresolved compilation problems: \n\tThe import hr.fer.zemris.java.fractals.viewer cannot be resolved\n\tThe import hr.fer.zemris.java.fractals.viewer cannot be resolved\n\tThe import hr.fer.zemris.java.fractals.viewer cannot be resolved\n\tFractalViewer cannot be resolved\n\tIFractalProducer cannot be resolved to a type\n\tIFractalResultObserver cannot be resolved to a type\n");
        }
        
        public void produce(final double n, final double n2, final double n3, final double n4, final int n5, final int n6, final long n7, final IFractalResultObserver fractalResultObserver, final AtomicBoolean atomicBoolean) {
            throw new Error("Unresolved compilation problem: \n\tIFractalResultObserver cannot be resolved to a type\n");
        }
    }
}

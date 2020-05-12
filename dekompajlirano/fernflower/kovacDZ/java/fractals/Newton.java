package hr.fer.zemris.java.fractals;

import hr.fer.zemris.math.Complex;
import hr.fer.zemris.math.ComplexPolynomial;
import hr.fer.zemris.math.ComplexRootedPolynomial;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

public class Newton {
   public static void main(String[] var0) {
      throw new Error("Unresolved compilation problem: \n\tFractalViewer cannot be resolved\n");
   }

   private static Complex parseComplex(String input) {
      if (input.matches("\\s*(-i|\\+i|i)\\s*")) {
         input = input.replace("i", "1");
         return new Complex(0.0D, Double.parseDouble(input));
      } else if (input.matches("\\s*[+-]?i\\d+\\.?\\d*\\s*")) {
         return new Complex(0.0D, Double.parseDouble(input.replace("i", "")));
      } else {
         int indexOfPlus;
         int indexOfMinus;
         int indexOfSplit;
         if (input.matches("\\s*[+-]?\\d+(\\.)?\\d*(\\s*[+-]\\s*i\\d+\\.?\\d*)?\\s*")) {
            indexOfPlus = input.indexOf("i");
            input = input.replace("i", "");
            if (indexOfPlus != -1) {
               indexOfMinus = input.lastIndexOf("+");
               indexOfSplit = input.lastIndexOf("-");
               int indexOfSplit = indexOfMinus > indexOfSplit ? indexOfMinus : indexOfSplit;
               return new Complex(Double.parseDouble(input.substring(0, indexOfSplit)), Double.parseDouble(input.substring(indexOfSplit, input.length()).replaceAll("\\s", "")));
            } else {
               return new Complex(Double.parseDouble(input), 0.0D);
            }
         } else if (input.matches("\\s*[+-]?\\d+\\.?\\d*\\s*[+-]\\s*i\\s*")) {
            input = input.replace("i", "1");
            indexOfPlus = input.lastIndexOf("+");
            indexOfMinus = input.lastIndexOf("-");
            indexOfSplit = indexOfPlus > indexOfMinus ? indexOfPlus : indexOfMinus;
            return new Complex(Double.parseDouble(input.substring(0, indexOfSplit)), Double.parseDouble(input.substring(indexOfSplit, input.length()).replaceAll("\\s", "")));
         } else {
            throw new IllegalArgumentException();
         }
      }
   }

   public static class NewtonFractalJob implements Callable {
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

      public Void call() throws Exception {
         NewtonFractal.calculate(this.reMin, this.reMax, this.imMin, this.imMax, this.width, this.height, this.yMin, this.yMax, this.m, this.data, this.cancel, this.polynomial, this.rootedPolynomial, this.rootDistance, this.convergenceTreshold);
         return null;
      }
   }

   private static class NewtonProducer {
      private ExecutorService pool;
      private ComplexRootedPolynomial rootedPolynomial;

      public NewtonProducer(ComplexRootedPolynomial var1) {
         throw new Error("Unresolved compilation problems: \n\tThe import hr.fer.zemris.java.fractals.viewer cannot be resolved\n\tThe import hr.fer.zemris.java.fractals.viewer cannot be resolved\n\tThe import hr.fer.zemris.java.fractals.viewer cannot be resolved\n\tFractalViewer cannot be resolved\n\tIFractalProducer cannot be resolved to a type\n\tIFractalResultObserver cannot be resolved to a type\n");
      }

      public void produce(double var1, double var3, double var5, double var7, int var9, int var10, long var11, IFractalResultObserver var13, AtomicBoolean var14) {
         throw new Error("Unresolved compilation problem: \n\tIFractalResultObserver cannot be resolved to a type\n");
      }
   }
}

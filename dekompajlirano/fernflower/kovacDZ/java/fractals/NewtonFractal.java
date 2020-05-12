package hr.fer.zemris.java.fractals;

import hr.fer.zemris.math.Complex;
import hr.fer.zemris.math.ComplexPolynomial;
import hr.fer.zemris.math.ComplexRootedPolynomial;
import java.util.concurrent.atomic.AtomicBoolean;

public class NewtonFractal {
   public static void calculate(double reMin, double reMax, double imMin, double imMax, int width, int height, int yMin, int yMax, int m, short[] data, AtomicBoolean cancel, ComplexPolynomial polynomial, ComplexRootedPolynomial rootedPolynomial, double rootDistance, double convergenceTreshold) {
      double realDistance = (reMax - reMin) / (double)width;
      double imaginaryDistance = (imMax - imMin) / (double)height;

      for(int y = yMin; y <= yMax; ++y) {
         for(int x = 0; x <= width; ++x) {
            Complex checkNumber = new Complex(reMin + (double)x * realDistance, imMax - (double)y * imaginaryDistance);
            checkNumber = iterate(checkNumber, m, rootedPolynomial, polynomial, convergenceTreshold);
            int index = -1;
            if (checkNumber != null) {
               index = rootedPolynomial.indexOfClosestRootFor(checkNumber, rootDistance);
            }

            data[y * width + x] = (short)(index + 1);
         }
      }

   }

   private static Complex iterate(Complex checkNumber, int numberOfIterations, ComplexRootedPolynomial rootedPol, ComplexPolynomial polynomial, double convergenceTreshold) {
      int iteration = 0;
      Complex zn = checkNumber;

      Complex oldZn;
      do {
         Complex numerator = rootedPol.apply(zn);
         Complex denominator = polynomial.derive().apply(zn);
         oldZn = zn;
         Complex fraction = numerator.divide(denominator);
         zn = zn.sub(fraction);
         ++iteration;
      } while(iteration < numberOfIterations && zn.sub(oldZn).module() > convergenceTreshold);

      return zn;
   }
}

// 
// Decompiled by Procyon v0.5.36
// 

package hr.fer.zemris.java.fractals;

import hr.fer.zemris.math.Complex;
import hr.fer.zemris.math.ComplexRootedPolynomial;
import hr.fer.zemris.math.ComplexPolynomial;
import java.util.concurrent.atomic.AtomicBoolean;

public class NewtonFractal
{
    public static void calculate(final double reMin, final double reMax, final double imMin, final double imMax, final int width, final int height, final int yMin, final int yMax, final int m, final short[] data, final AtomicBoolean cancel, final ComplexPolynomial polynomial, final ComplexRootedPolynomial rootedPolynomial, final double rootDistance, final double convergenceTreshold) {
        final double realDistance = (reMax - reMin) / width;
        final double imaginaryDistance = (imMax - imMin) / height;
        for (int y = yMin; y <= yMax; ++y) {
            for (int x = 0; x <= width; ++x) {
                Complex checkNumber = new Complex(reMin + x * realDistance, imMax - y * imaginaryDistance);
                checkNumber = iterate(checkNumber, m, rootedPolynomial, polynomial, convergenceTreshold);
                int index = -1;
                if (checkNumber != null) {
                    index = rootedPolynomial.indexOfClosestRootFor(checkNumber, rootDistance);
                }
                data[y * width + x] = (short)(index + 1);
            }
        }
    }
    
    private static Complex iterate(final Complex checkNumber, final int numberOfIterations, final ComplexRootedPolynomial rootedPol, final ComplexPolynomial polynomial, final double convergenceTreshold) {
        int iteration = 0;
        Complex zn = checkNumber;
        Complex oldZn;
        do {
            final Complex numerator = rootedPol.apply(zn);
            final Complex denominator = polynomial.derive().apply(zn);
            oldZn = zn;
            final Complex fraction = numerator.divide(denominator);
            zn = zn.sub(fraction);
        } while (++iteration < numberOfIterations && zn.sub(oldZn).module() > convergenceTreshold);
        return zn;
    }
}

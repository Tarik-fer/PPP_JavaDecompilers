package hr.fer.zemris.math;

import java.util.Arrays;

public class ComplexPolynomial {
   private Complex[] factors;

   public ComplexPolynomial(Complex... factors) {
      this.factors = factors;
   }

   public short order() {
      return (short)(this.factors.length - 1);
   }

   public ComplexPolynomial multiply(ComplexPolynomial p) {
      Complex[] newFactors = new Complex[this.order() + p.order() + 1];
      Arrays.fill(newFactors, Complex.ZERO);

      for(int i = 0; i < this.factors.length; ++i) {
         int j = 0;

         for(int limit = p.order() + 1; j < limit; ++j) {
            newFactors[i + j] = newFactors[i + j].add(this.factors[i].multiply(p.factors[j]));
         }
      }

      return new ComplexPolynomial(newFactors);
   }

   public ComplexPolynomial derive() {
      if (this.order() == 0) {
         return new ComplexPolynomial(new Complex[]{Complex.ZERO});
      } else {
         Complex[] newFactors = new Complex[this.order()];

         for(int i = 0; i < this.factors.length - 1; ++i) {
            newFactors[i] = this.factors[i + 1].multiply(new Complex((double)(i + 1), 0.0D));
         }

         return new ComplexPolynomial(newFactors);
      }
   }

   public Complex apply(Complex z) {
      Complex accumulator = Complex.ZERO;

      for(int i = 0; i < this.factors.length; ++i) {
         accumulator = accumulator.add(this.factors[i].multiply(z.power(i)));
      }

      return accumulator;
   }

   public String toString() {
      StringBuilder builder = new StringBuilder();

      for(int i = this.factors.length - 1; i > 0; --i) {
         builder.append(this.factors[i]).append("*z^").append(i).append("+");
      }

      return builder.append(this.factors[0]).toString();
   }
}

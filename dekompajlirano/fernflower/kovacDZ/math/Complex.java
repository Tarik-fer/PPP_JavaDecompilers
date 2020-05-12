package hr.fer.zemris.math;

import java.util.ArrayList;
import java.util.List;

public class Complex {
   private double real;
   private double imaginary;
   public static final Complex ZERO = new Complex(0.0D, 0.0D);
   public static final Complex ONE = new Complex(1.0D, 0.0D);
   public static final Complex ONE_NEG = new Complex(-1.0D, 0.0D);
   public static final Complex IM = new Complex(0.0D, 1.0D);
   public static final Complex IM_NEG = new Complex(0.0D, -1.0D);

   public Complex() {
      this(0.0D, 0.0D);
   }

   public Complex(double re, double im) {
      this.real = re;
      this.imaginary = im;
   }

   public double module() {
      return Math.sqrt(this.real * this.real + this.imaginary * this.imaginary);
   }

   public Complex multiply(Complex c) {
      return new Complex(this.real * c.real - this.imaginary * c.imaginary, this.imaginary * c.real + this.real * c.imaginary);
   }

   public Complex divide(Complex c) {
      double divisor = c.real * c.real + c.imaginary * c.imaginary;
      if (divisor == 0.0D) {
         throw new IllegalArgumentException("Complex divisor mustn't be (0(+/-)0i)");
      } else {
         return this.multiply(new Complex(c.real / divisor, -c.imaginary / divisor));
      }
   }

   public Complex add(Complex c) {
      return new Complex(this.real + c.real, this.imaginary + c.imaginary);
   }

   public Complex sub(Complex c) {
      return new Complex(this.real - c.real, this.imaginary - c.imaginary);
   }

   public Complex negate() {
      return new Complex(-this.real, -this.imaginary);
   }

   public Complex power(int n) {
      if (n < 0) {
         throw new IllegalArgumentException("Power of a complex number modeled by the Complex class must be a non-negative integer!");
      } else {
         Complex nthPower = ONE;

         for(int i = 0; i < n; ++i) {
            nthPower = nthPower.multiply(this);
         }

         return nthPower;
      }
   }

   private double angle() {
      if (this.real == 0.0D && this.imaginary == 0.0D) {
         return 0.0D;
      } else {
         return this.real == 0.0D ? Math.signum(this.imaginary) * 1.5707963267948966D : Math.atan(this.imaginary / this.real);
      }
   }

   public List root(int n) {
      if (n < 1) {
         throw new IllegalArgumentException("Root of a complex number modeled by the Complex class must be a positive integer!");
      } else {
         List roots = new ArrayList();
         double rootMod = Math.pow(this.module(), 1.0D / (double)n);
         double angle = this.angle();
         double twoPI = 6.283185307179586D;

         for(int k = 0; k < n; ++k) {
            double kthAngle = (angle + twoPI * (double)k) / (double)n;
            roots.add(new Complex(rootMod * Math.cos(kthAngle), rootMod * Math.sin(kthAngle)));
         }

         return roots;
      }
   }

   public String toString() {
      return "(" + Double.toString(this.real + 0.0D) + (this.imaginary >= 0.0D ? "+" : "-") + "i" + Math.abs(this.imaginary) + ")";
   }
}

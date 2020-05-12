package hr.fer.zemris.math;

public class ComplexRootedPolynomial {
   private Complex constant;
   private Complex[] roots;

   public ComplexRootedPolynomial(Complex constant, Complex... roots) {
      this.constant = constant;
      this.roots = roots;
   }

   public Complex apply(Complex z) {
      Complex accumulator = this.constant;

      for(int i = 0; i < this.roots.length; ++i) {
         accumulator = accumulator.multiply(z.sub(this.roots[i]));
      }

      return accumulator;
   }

   public ComplexPolynomial toComplexPolynom() {
      Complex[] factors = new Complex[this.roots.length + 1];

      for(int i = 0; i < this.roots.length + 1; ++i) {
         factors[i] = this.findCoefficient(i);
      }

      return new ComplexPolynomial(factors);
   }

   private Complex findCoefficient(int i) {
      Complex signum = (this.roots.length - i) % 2 == 0 ? Complex.ONE : Complex.ONE_NEG;
      if (i == this.roots.length) {
         return this.constant.multiply(signum);
      } else if (i == 0) {
         return this.constant.multiply(this.multiplyChosen(this.setup(this.roots.length))).multiply(signum);
      } else {
         int[] chosen = this.setup(this.roots.length - i);
         int indexOfIncrementable = this.roots.length - i - 1;

         Complex accumulator;
         for(accumulator = Complex.ZERO; indexOfIncrementable != -1; indexOfIncrementable = this.chooseElements(chosen, this.roots.length - i - 1)) {
            accumulator = accumulator.add(this.multiplyChosen(chosen));
         }

         return accumulator.multiply(this.constant).multiply(signum);
      }
   }

   private int chooseElements(int[] lastChosen, int indexOfIncrementable) {
      while(true) {
         if (lastChosen[indexOfIncrementable] + (lastChosen.length - indexOfIncrementable) == this.roots.length) {
            --indexOfIncrementable;
            if (indexOfIncrementable != -1) {
               continue;
            }

            return -1;
         }

         int var10002;
         if (indexOfIncrementable == lastChosen.length - 1) {
            var10002 = lastChosen[indexOfIncrementable]++;
            return indexOfIncrementable;
         }

         var10002 = lastChosen[indexOfIncrementable]++;

         for(int i = indexOfIncrementable + 1; i < lastChosen.length; ++i) {
            lastChosen[i] = lastChosen[i - 1] + 1;
         }

         return indexOfIncrementable;
      }
   }

   private int[] setup(int numberOfElements) {
      int[] setup = new int[numberOfElements];

      for(int i = 0; i < numberOfElements; setup[i] = i++) {
      }

      return setup;
   }

   private Complex multiplyChosen(int[] chosen) {
      Complex multiplier = this.roots[chosen[0]];

      for(int i = 1; i < chosen.length; ++i) {
         multiplier = multiplier.multiply(this.roots[chosen[i]]);
      }

      return multiplier;
   }

   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append(this.constant);

      for(int i = 0; i < this.roots.length; ++i) {
         builder.append("*(z-").append(this.roots[i]).append(")");
      }

      return builder.toString();
   }

   public int indexOfClosestRootFor(Complex z, double treshold) {
      double distance = treshold;
      int index = -1;

      for(int i = 0; i < this.roots.length; ++i) {
         double checkDistance = z.sub(this.roots[i]).module();
         if (checkDistance <= distance) {
            distance = checkDistance;
            index = i;
         }
      }

      return index;
   }
}

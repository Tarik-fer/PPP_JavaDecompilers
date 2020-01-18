package hr.fer.zemris.math;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa koja modelira kompleksni broj.
 *
 * @author Petar Kovač
 *
 */
public class Complex {
	/**
	 * Realni dio kompleksnog broja.
	 */
	private double real;
	/**
	 * Imaginarni dio kompleksnog broja.
	 */
	private double imaginary;
	
	/**
	 * 0 + i0
	 */
	public static final Complex ZERO = new Complex(0, 0);
	/**
	 * 1 + i0
	 */
	public static final Complex ONE = new Complex(1, 0);
	/**
	 * -1 + i0
	 */
	public static final Complex ONE_NEG = new Complex(-1, 0);
	/**
	 * 0 + i0
	 */
	public static final Complex IM = new Complex(0, 1);
	/**
	 * 0 - i0
	 */
	public static final Complex IM_NEG = new Complex(0, -1);

	/**
	 * Zadani konstruktor, stvara kompleksni broj jedna {@link #ZERO}.
	 */
	public Complex() {
		this(0,0);
	}
	
	public Complex(double re, double im) {
		this.real = re;
		this.imaginary = im;
	}
	
	/**
	 * Vraća modul ovog kompleksnog broja.
	 */
	public double module() {
		return Math.sqrt(real * real + imaginary * imaginary);
	}
	
	/**
	 * Množi ovaj kompleksni broj s danim argumentom i vraća rezultat.
	 * 
	 * @param c Argument 
	 * @return Rezultat množenja
	 */
	public Complex multiply(Complex c) {
		return new Complex(this.real * c.real - this.imaginary * c.imaginary, this.imaginary * c.real + this.real * c.imaginary);
	}
	
	/**
	 * Dijeli ovaj kompleksni broj s danim argumentom i vraća rezultat.
	 * 
	 * @param c Argument 
	 * @return Rezultat dijeljenja
	 * @throws IllegalArgumentException ako je kao argument dan kompleksni broj čiji je modul jednak 0
	 */
	public Complex divide(Complex c) {
		double divisor = c.real * c.real + c.imaginary * c.imaginary;
		if(divisor == 0) {
			throw new IllegalArgumentException("Complex divisor mustn't be (0(+/-)0i)");
		}
		return this.multiply(new Complex(c.real / divisor, -c.imaginary/divisor));
	}
	
	/**
	 * Zbraja ovaj kompleksni broj s danim argumentom i vraća rezultat.
	 * 
	 * @param c Argument 
	 * @return Rezultat zbrajanja
	 */
	public Complex add(Complex c) {
		return new Complex(this.real + c.real, this.imaginary + c.imaginary);
	}
	
	/**
	 * Oduzima argument od ovog kompleksnog broja i vraća rezultat.
	 * 
	 * @param c Argument 
	 * @return Rezultat oduzimanja
	 */
	public Complex sub(Complex c) {
		return new Complex(this.real - c.real, this.imaginary - c.imaginary);
	}
	
	/**
	 * Vraća kompleksni broj oblika <code>(-RePart, -imPart)</code>.
	 */
	public Complex negate() {
		return new Complex(-this.real, -this.imaginary);
	}
	
	/**
	 * Potencira ovaj kompleksni broj i vraća rezultat.
	 * 
	 * @param n Potencija
	 * @return Potencirani kompleksni broj
	 * @throws IllegalArgumentException ako je potencija negativan broj
	 */
	public Complex power(int n) {
		if(n < 0) {
			throw new IllegalArgumentException("Power of a complex number modeled by the Complex class must be a non-negative integer!");
		}
		
		Complex nthPower = Complex.ONE;
		for(int i = 0; i < n; i++) {
			nthPower = nthPower.multiply(this);
		}
		return nthPower;
	}
	
	/**
	 * Vrača argument ovog kompleksnog broja u radijanima.
	 */
	private double angle() {
		if(real == 0 && imaginary == 0) return 0;
		
		return real == 0 ? Math.signum(this.imaginary) * (Math.PI / 2) : Math.atan(this.imaginary/this.real);
	}
	
	/**
	 * Vraća n-te korijene ovog kompleksnog broja.
	 * 
	 * @param n 
	 * @return List<Complex> koja sadrži n korijena
	 * @throws IllegalArgumentException ako je kao argument dan broj < 1
	 */
	public List<Complex> root(int n) {
		if(n < 1) {
			throw new IllegalArgumentException("Root of a complex number modeled by the Complex class must be a positive integer!");
		}
		
		List<Complex> roots = new ArrayList<>();
		
		double rootMod = Math.pow(module(), (double) 1/n);
		double angle = angle();
		double twoPI = Math.PI * 2;
		
		for(int k = 0; k < n; k++) {
			double kthAngle = ((angle + twoPI * k) / n);
			roots.add(new Complex(rootMod * Math.cos(kthAngle), rootMod * Math.sin(kthAngle)));
		}
		
		return roots;
	}
	
	@Override
	public String toString() {
		return "(" + Double.toString(real + 0.0) + (imaginary >= 0 ? "+" : "-") + "i" + Math.abs(imaginary) + ")";
	}
}

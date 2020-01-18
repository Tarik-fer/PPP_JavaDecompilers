package hr.fer.zemris.math;

import java.util.Arrays;

/**
 * Klasa koja predstavlja kompleksni polinom oblika a0, a1*x, a2*x^2, ..., an*x^n.
 *
 * @author Petar Kovač
 */
public class ComplexPolynomial {
	/**
	 * Koeficijenti.
	 */
	private Complex[] factors;
	
	/**
	 * Zadani konstruktor koji prima varijabilni broj koeficijenata.
	 * Podrazumijeva se da je koeficijent na indeksu i jednak koeficijent ai.
	 * 
	 * @param factors Varijabilni broj koeficijenata.
	 */
	public ComplexPolynomial(Complex ...factors) {
		this.factors = factors;
	}
	
	/**
	 * Vraća red ovog polinoma.
	 */
	public short order() {
		return (short) (factors.length - 1);
	}
	
	/**
	 * Vraća rezultat množenja ovog polinoma nekim drugim polinomom.
	 * 
	 * @param p Drugi polinom
	 */
	public ComplexPolynomial multiply(ComplexPolynomial p) {
		Complex [] newFactors = new Complex[this.order() + p.order() + 1];
		Arrays.fill(newFactors, Complex.ZERO);
		
		for(int i = 0; i < factors.length; i++) {
			for(int j = 0, limit = p.order() + 1; j < limit; j++) {
				newFactors[i + j] = newFactors[i + j].add(this.factors[i].multiply(p.factors[j]));
			}
		}
		return new ComplexPolynomial(newFactors);
	}
	/**
	 * Derivira ovaj polinom.
	 * 
	 * @return Novi polinom ili Complex.ZERO, ako je originalni polinom bio konstanta.
	 */
	public ComplexPolynomial derive() {
		if(order() == 0) return new ComplexPolynomial(Complex.ZERO);
		
		Complex[] newFactors = new Complex[order()];
		for(int i = 0; i < factors.length - 1; i++) {
			newFactors[i] = factors[i + 1].multiply(new Complex(i + 1, 0));
		}
		return new ComplexPolynomial(newFactors);
	}
	
	/**
	 * Računa rješenje polinoma za dani argument.
	 * 
	 * @param z Argument funkcije
	 * @return Dobiveno rješenje
	 */
	public Complex apply(Complex z) {
		Complex accumulator = Complex.ZERO;
		
		for(int i = 0; i < factors.length; i++) {
			accumulator = accumulator.add(factors[i].multiply(z.power(i)));
		}
		return accumulator;
	}
	

	
	@Override 
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for(int i = factors.length - 1; i > 0; i--) {
			builder.append(factors[i]).append("*z^").append(i).append("+");
		}
		
		return builder.append(factors[0]).toString();
	}
}

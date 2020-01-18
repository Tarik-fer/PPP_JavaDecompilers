package hr.fer.zemris.math;

/**
 * Predstavlja kompleksni polinom definiran nultočkama.
 * Polinom može imati više jednakih nultočaka.
 * 
 *
 * @author Petar Kovač
 */
public class ComplexRootedPolynomial {
	/**
	 * Faktor uvećanja.
	 */
	private Complex constant;
	/**
	 * Nultočke polinoma.
	 */
	private Complex[] roots;
	
	/**
	 * Zadani konstruktor koji može primiti varijabilni broj nultočaka.
	 * 
	 * @param constant {@link #constant}
	 * @param roots Polje nultočaka
	 */
	public ComplexRootedPolynomial(Complex constant, Complex ...roots) {
		this.constant = constant;
		this.roots = roots;
	}
	
	/**
	 * Računa rješenje polinoma za dani argument.
	 * 
	 * @param z Argument funkcije
	 * @return Dobiveno rješenje
	 */
	public Complex apply(Complex z) {
		Complex accumulator = constant;
		for(int i = 0; i < roots.length; i++) {
			accumulator = accumulator.multiply((z.sub(roots[i])));
		}
		return accumulator;
	}
	
	/**
	 * Nalazi {@link ComplexPolynomial} koji predstavlja ovaj {@link ComplexRootedPolynomial}.
	 * 
	 * @return {@link ComplexPolynomial} ove funkcije
	 */
	public ComplexPolynomial toComplexPolynom() {
		Complex[] factors = new Complex[roots.length + 1];
		
		for(int i = 0; i < roots.length + 1; i++) {
			factors[i] = findCoefficient(i);
		}
		return new ComplexPolynomial(factors);
	}
	
	/**
	 * Funkcija koja pronalazi koeficijente za i-ti član polinoma.
	 * 
	 * @param i i-ti član
	 * @return Koeficijent uz dan član
	 */
	private Complex findCoefficient(int i) {
		Complex signum = (roots.length - i) % 2 == 0 ? Complex.ONE : Complex.ONE_NEG;
		if(i == roots.length) return constant.multiply(signum);
		if(i == 0) return constant.multiply(multiplyChosen(setup(roots.length))).multiply(signum);
		
		int []chosen = setup(roots.length - i);
		int indexOfIncrementable = roots.length - i - 1;
		Complex accumulator = Complex.ZERO;
		
		while(indexOfIncrementable != -1) {
			accumulator = accumulator.add(multiplyChosen(chosen));
			indexOfIncrementable = chooseElements(chosen, roots.length - i - 1);
		}
		return accumulator.multiply(constant).multiply(signum);
	}
	
	/**
	 * Metoda koja odabire nultočke koje će se množiti.
	 * 
	 * @param lastChosen Zadnje izabrane nultočke
	 * @param indexOfIncrementable Index člana koje se može uvećati
	 * @return Index člana koji se zadnji uvećao
	 */
	private int chooseElements(int[] lastChosen, int indexOfIncrementable) {

		while(lastChosen[indexOfIncrementable] +  (lastChosen.length - indexOfIncrementable) == roots.length) {
			indexOfIncrementable--;
			if(indexOfIncrementable == -1) return -1;
		}

		if(indexOfIncrementable != lastChosen.length - 1) {
			lastChosen[indexOfIncrementable]++;
			for(int i = indexOfIncrementable + 1; i < lastChosen.length; i++) {
				lastChosen[i] = lastChosen[i - 1] + 1;
			}
			return indexOfIncrementable;
		}
		
		lastChosen[indexOfIncrementable]++;
		
		return indexOfIncrementable;
	}
	
	/**
	 * Metoda koja postavlja polje indeksa na početnu vrijednost.
	 * 
	 * @param numberOfElements Broj nultočaka koje se trebaju izabrati
	 * @return Polje oblika {0, 1, ..., (numberOfElements-1)}
	 */
	private int[] setup(int numberOfElements) {
		int []setup = new int[numberOfElements];
		for(int i = 0; i < numberOfElements; i++) {
			setup[i] = i;
		}
		return setup;
	}
	
	/**
	 * Metoda koja množi odabrane nultočke.
	 * 
	 * @param chosen Odabrane nultočke
	 * @return Rezultat umnoška
	 */
	private Complex multiplyChosen(int []chosen) {
		Complex multiplier = roots[chosen[0]];
		for(int i = 1; i < chosen.length; i++) {
			multiplier = multiplier.multiply(roots[chosen[i]]);
		}
		return multiplier;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(constant);
		for(int i = 0; i < roots.length; i++) {
			builder.append("*(z-").append(roots[i]).append(")");
		}
		return builder.toString();
	}
	
	/**
	 * Metoda koja računa indeks nultočke najmanje udaljene od kompleksnog argumenta.
	 * Ako je udaljenost > treshold za svaku nultočku, metoda vraća -1.
	 * 
	 * @param z Kompleksni argument
	 * @param treshold Minimalna prihvatljiva udaljenost
	 * @return Indeks nultočke koja zadovoljava uvjet minimalne udaljenosti, -1 ako takva ne postoji.
	 */
	public int indexOfClosestRootFor(Complex z, double treshold) {
		double distance = treshold;
		int index = -1;
		
		for(int i = 0; i < roots.length; i++) {
			double checkDistance = z.sub(roots[i]).module();
			if(checkDistance <= distance) {
				distance = checkDistance;
				index = i;
			}
		}
		
		return index;
	}
}

package hr.fer.zemris.java.fractals;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;

import hr.fer.zemris.java.fractals.viewer.FractalViewer;
import hr.fer.zemris.java.fractals.viewer.IFractalProducer;
import hr.fer.zemris.java.fractals.viewer.IFractalResultObserver;
import hr.fer.zemris.math.Complex;
import hr.fer.zemris.math.ComplexPolynomial;
import hr.fer.zemris.math.ComplexRootedPolynomial;

/**
 * Razred koji pokazuje Newton-Raphsonove fraktale.
 *
 * @author Petar Kovač
 */
public class Newton {
	public static void main(String[] args) {
		List<Complex> factors = new ArrayList<>();
		System.out.println("Welcome to Newton-Raphson iteration-based fractal viewer.");
		System.out.println("Please enter at least two roots, one root per line. Enter 'done' when done.");
		Scanner sc= new Scanner(System.in);
		String input; 
		do  {
			System.out.printf("Root %d> ", factors.size() + 1);
			input = sc.nextLine();
			if(input.equals("done") && factors.size() < 2) {
				System.out.println("Please enter at least two roots.");
			}
			if(!input.toLowerCase().equals("done")) {
				try {
					factors.add(parseComplex(input));
				} catch(IllegalArgumentException e) {
					System.out.println(input + " is not a valid complex number.");
				}
			}
			
		} while(!input.equals("done") || factors.size() < 2);
		System.out.println("Image of fractal will appear shortly.");
		Complex[] roots = new Complex[factors.size()];
		sc.close();
		FractalViewer.show(new NewtonProducer(new ComplexRootedPolynomial(Complex.ONE, factors.toArray(roots))));
	}
	
	/**
	 * Metoda koja parsira kompleksni broj.
	 * 
	 * @param input Kompleksni broj u tekstualnom obliku
	 * @return Parsirani kompleksni broj
	 */
	private static Complex parseComplex(String input) {
		if(input.matches("\\s*(-i|\\+i|i)\\s*")) {
			input = input.replace("i", "1");
			return new Complex(0,  Double.parseDouble(input));
		}
		if(input.matches("\\s*[+-]?i\\d+\\.?\\d*\\s*")) {
			return new Complex(0, Double.parseDouble(input.replace("i", "")));
		}
		
		if(input.matches("\\s*[+-]?\\d+(\\.)?\\d*(\\s*[+-]\\s*i\\d+\\.?\\d*)?\\s*")) {

			int indexOfI = input.indexOf("i");
			input = input.replace("i", "");
			if(indexOfI != -1) {
				int indexOfPlus = input.lastIndexOf("+");
				int indexOfMinus = input.lastIndexOf("-");
				int indexOfSplit = indexOfPlus > indexOfMinus ? indexOfPlus : indexOfMinus;
				return new Complex(Double.parseDouble(input.substring(0, indexOfSplit)), 
									   Double.parseDouble(input.substring(indexOfSplit, input.length()).replaceAll("\\s", "")));
			}
			return new Complex(Double.parseDouble(input), 0);
		}
		if(input.matches("\\s*[+-]?\\d+\\.?\\d*\\s*[+-]\\s*i\\s*")) {
			input = input.replace("i", "1");
			int indexOfPlus = input.lastIndexOf("+");
			int indexOfMinus = input.lastIndexOf("-");
			int indexOfSplit = indexOfPlus > indexOfMinus ? indexOfPlus : indexOfMinus;
			return new Complex(Double.parseDouble(input.substring(0, indexOfSplit)), 
					   Double.parseDouble(input.substring(indexOfSplit, input.length()).replaceAll("\\s", "")));
			
		}
		throw new IllegalArgumentException();
	}
	
	/**
	 * Model posla koji omogućava jednoj dretvi crtanje jednog dijela Newton-Raphson fraktala.
	 *
	 * @author Petar Kovač
	 */
	public static class NewtonFractalJob implements Callable<Void> {
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
		
		public NewtonFractalJob(double reMin, double reMax, double imMin,
				double imMax, int width, int height, int yMin, int yMax, 
				int m, short[] data, AtomicBoolean cancel, ComplexPolynomial polynomial,
				ComplexRootedPolynomial rootedPolynomial,
				double rootDistance, double convergenceTreshold) {
			super();
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
			NewtonFractal.calculate(reMin, reMax, imMin, imMax, width, height,yMin, yMax, m,
					data, cancel, polynomial, rootedPolynomial, rootDistance, convergenceTreshold);
			
			return null;
		}
	}
	
	/**
	 * Razred u kojem se stvaraju nove dretve i u kojoj se daju parametri novog posla.
	 *
	 * @author Petar Kovač
	 */
	private static class NewtonProducer implements IFractalProducer {
		/**
		 * Bazen dretvi.
		 */
		private ExecutorService pool;
		/**
		 * {@link ComplexRootedPolynomial}
		 */
		private ComplexRootedPolynomial rootedPolynomial;
		
		/**
		 * Zadani konstruktor koji prima polinom i stvara bazen daemon dretvi.
		 * 
		 * @param polynomial Polinom
		 */
		public NewtonProducer(ComplexRootedPolynomial polynomial) {
			pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors(), new ThreadFactory() {
				
				@Override
				public Thread newThread(Runnable r) {
					Thread t = Executors.defaultThreadFactory().newThread(r);
	                t.setDaemon(true);
	                return t;
				}
			});
			this.rootedPolynomial = polynomial;
		}
		

		@Override
		public void produce(double reMin, double reMax, double imMin, double imMax,
				int width, int height, long requestNo, IFractalResultObserver observer, AtomicBoolean cancel) {
			System.out.println("Zapocinjem izracun...");
			int m = 500;
			double rootDistance = 0.002;
			ComplexPolynomial polynomial = rootedPolynomial.toComplexPolynom();
			double convergenceTreshold = 0.001;
			short[] data = new short[width * height];
			final int brojTraka = 8 * Runtime.getRuntime().availableProcessors();
			int brojYPoTraci = height / brojTraka;
			System.out.println("broj Y po traci: " + brojYPoTraci);
			
			List<Future<?>> rezultati = new ArrayList<>();
			
			for(int i = 0; i < brojTraka; i++) {
				int yMin = i*brojYPoTraci;
				int yMax = (i+1)*brojYPoTraci-1;
				if(i==brojTraka-1) {
					yMax = height-1;
				}
				NewtonFractalJob newtonJ = new NewtonFractalJob(reMin, reMax, imMin, imMax, width, height, yMin, yMax, m, 
																data, cancel, polynomial, rootedPolynomial,
																rootDistance, convergenceTreshold);
				rezultati.add(pool.submit(newtonJ));
			}
			for(Future<?> posao : rezultati) {
				try {
					posao.get();
				} catch (InterruptedException | ExecutionException e) {
				}
			}
			
			
			System.out.println("Racunanje gotovo. Idem obavijestiti promatraca tj. GUI!");
			observer.acceptResult(data, (short)(polynomial.order() + 1), requestNo);
			
		}
		
	}
}

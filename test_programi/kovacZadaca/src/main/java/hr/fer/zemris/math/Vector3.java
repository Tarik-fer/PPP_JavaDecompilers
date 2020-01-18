package hr.fer.zemris.math;

/**
 * Klasa koja modelira neizmjenjiv trokomponentni vektor.
 * Sve operacije vraćaju nove objekte ovog tipa.
 *
 * @author Petar Kovač
 *
 */
public class Vector3 {
	/**
	 * X koordinata.
	 */
	private double x;
	/**
	 * Y koordinata.
	 */
	private double y;
	/**
	 * Z koordinata.
	 */
	private double z;
	
	/**
	 * Konstruktor koji stvara 3D vektor s zadanim komponentama.
	 * 
	 * @param x 
	 * @param y
	 * @param z
	 */
	public Vector3(double x, double y, double z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Računa modul ovog vektora.
	 * 
	 * @return Modul vektora
	 */
	public double norm() {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
	}
	
	/**
	 * Vraća novi vektor koji je normaliziran.
	 * Normalizirani vektor je jedinični vektor čiji je smjer jednak smjeru originalnog vektora.
	 * 
	 * @return Normalizirani vektor
	 */
	public Vector3 normalized() {
		double norm = norm();
		return new Vector3(x/norm, y/norm, z/norm);
	}
	
	/**
	 * Vraća novi vektor koji predstavlja zbroj ova dva vektora.
	 * 
	 * @param other Drugi vektor
	 * @return Zbroj vektora
	 */
	public Vector3 add(Vector3 other) {
		double newX = this.x + other.x;
		double newY = this.y + other.y;
		double newZ = this.z + other.z;
		return new Vector3(newX, newY, newZ);
	}
	
	/**
	 * Vraća novi vektor koji predstavlja razliku ova dva vektora.
	 * 
	 * @param other Drugi vektor
	 * @return Razlika vektora
	 */
	public Vector3 sub(Vector3 other) {
		double newX = this.x - other.x;
		double newY = this.y - other.y;
		double newZ = this.z - other.z;
		return new Vector3(newX, newY, newZ);
	}
	
	/**
	 * Računa skalarni produkt dva vektora.
	 * 
	 * @param other Drugi vektor
	 * @return Skalarni produkt
	 */
	public double dot(Vector3 other) {
		return (this.x * other.x) + (this.y * other.y) + (this.z * other.z);
	}
	
	/**
	 * Računa vektorski produkt dva vektora i vraća novi vektor koji predstavlja rezultat.
	 * 
	 * @param other Drugi vektor
	 * @return Vektorski produkt
	 */
	public Vector3 cross(Vector3 other) {
		double s1 = (this.y * other.z) - (this.z * other.y);
		double s2 = (this.z * other.x) - (this.x * other.z);
		double s3  = (this.x * other.y) - (this.y * other.x);
		return new Vector3(s1, s2, s3);
	}
	
	/**
	 * Vraća novi vektor koji predstavlja rezultat uvećanja ovog vektora argumentom.
	 * 
	 * @param s Faktor uvećanja
	 * @return Uvećani vektor
	 */
	public Vector3 scale(double s) {
		return new Vector3(this.x * s, this.y * s, this.z * s);
	}
	
	/**
	 * Vraća kosinus kuta između dva vektora.
	 * 
	 * @param other Drugi vektor
	 * @return Kosinus kuta
	 */
	public double cosAngle(Vector3 other) {
		return this.dot(other) / (this.norm() * other.norm());
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}
	
	/**
	 * Vraća polje koordinata ovog vektora u obliku {x, y, z}
	 * 
	 * @return Koordinate vektora
	 */
	public double[] toArray() {
		double[] array = {x, y, z};
		return array;
	}
	
	@Override
	public String toString() {
		return String.format("(%.6f, %.6f, %.6f) ", x, y, z);
	}
	
}

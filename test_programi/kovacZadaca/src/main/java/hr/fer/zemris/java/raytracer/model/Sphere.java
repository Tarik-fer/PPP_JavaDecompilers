package hr.fer.zemris.java.raytracer.model;

/**
 * Razred koji modelira jednu kuglu kao geometrijski objekt s definiranom točkom središta.
 *
 * @author Petar Kovač
 */
public class Sphere extends GraphicalObject{
	/**
	 * Središte kugle.
	 */
	private Point3D center;
	/**
	 * Radijus kugle.
	 */
	private double radius;
	private double kdr;
	private double kdg;
	private double kdb;
	private double krr;
	private double krg;
	private double krb;
	private double krn;
	
	
	public Sphere(Point3D center, double radius, double kdr, double kdg, double kdb, double krr, double krg, double krb,
			double krn) {
		super();
		this.center = center;
		this.radius = radius;
		this.kdr = kdr;
		this.kdg = kdg;
		this.kdb = kdb;
		this.krr = krr;
		this.krg = krg;
		this.krb = krb;
		this.krn = krn;
	}

	@Override
	public RayIntersection findClosestRayIntersection(Ray ray) {
		Point3D position = ray.start;
		Point3D direction = ray.direction;
		double positionNorm = position.norm();
		double centerNorm = center.norm();
		double distance = center.sub(position).scalarProduct(direction);
		double discriminant = distance*distance - 
										(positionNorm*positionNorm + centerNorm*centerNorm - 
										radius* radius - 2 * position.scalarProduct(center));

		if(discriminant < 0) {
			return null;
		} 

		
		Point3D firstIntersection = position.add(direction.scalarMultiply(
											distance + Math.sqrt(discriminant)));
		Point3D secondIntersection = position.add(direction.scalarMultiply(
											distance - Math.sqrt(discriminant)));
		
		double distanceFromFirst = Math.abs(position.sub(firstIntersection).norm());
		double distanceFromSecond = Math.abs(position.sub(secondIntersection).norm());
		
		Point3D closestIntersection = distanceFromFirst < distanceFromSecond ? firstIntersection : secondIntersection;
		double distanceFromIntersection = distanceFromFirst < distanceFromSecond ? distanceFromFirst : distanceFromSecond;
		
		return new SphereIntersection(closestIntersection, distanceFromIntersection, true, this);
		
	}
	
	private static class SphereIntersection extends RayIntersection {
		private Sphere sphere;

		protected SphereIntersection(Point3D point, double distance, boolean outer, Sphere sphere) {
			super(point, distance, outer);
			this.sphere = sphere;
		}

		@Override
		public Point3D getNormal() {
			return sphere.center.sub(this.getPoint()).normalize();
		}

		@Override
		public double getKdr() {
			return sphere.kdr;
		}

		@Override
		public double getKdg() {
			return sphere.kdg;
		}

		@Override
		public double getKdb() {
			return sphere.kdb;
		}

		@Override
		public double getKrr() {
			return sphere.krr;
		}

		@Override
		public double getKrg() {
			return sphere.krg;
		}

		@Override
		public double getKrb() {
			return sphere.krb;
		}

		@Override
		public double getKrn() {
			return sphere.krn;
		}
		
	}

}

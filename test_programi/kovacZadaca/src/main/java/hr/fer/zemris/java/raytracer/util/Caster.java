package hr.fer.zemris.java.raytracer.util;

import java.util.List;

import hr.fer.zemris.java.raytracer.model.GraphicalObject;
import hr.fer.zemris.java.raytracer.model.LightSource;
import hr.fer.zemris.java.raytracer.model.Point3D;
import hr.fer.zemris.java.raytracer.model.Ray;
import hr.fer.zemris.java.raytracer.model.RayIntersection;
import hr.fer.zemris.java.raytracer.model.Scene;
/**
 * Klasa koja sadrži sve metode potrebne za rad Raycastera.
 *
 * @author Petar Kovač
 */
public class Caster {
	
	/**
	 * Metoda u kojoj se određuje pogađa li dana zraka neki objekt u sceni i
	 * ako da pridodaje pogođenoj točki boju.
	 * 
	 * @param scene Scena
	 * @param ray Zraka
	 * @param rgb Boja točke
	 */
	public static void tracer(Scene scene, Ray ray, short[] rgb) {
		rgb[0] = 0;
		rgb[1] = 0;
		rgb[2] = 0;
		RayIntersection closest = findClosestIntersection(scene, ray);
		determineColorFor(closest, scene, rgb, ray);
		return;
	}
	
	/**
	 * Metoda koja nalazi najbliže sjecište svih objekata na sceni s danom zrakom.
	 * 
	 * @param scene Scena
	 * @param ray Zraka
	 * @return Najbliže sjecište
	 */
	private static RayIntersection findClosestIntersection(Scene scene, Ray ray) {
		List<GraphicalObject> objects = scene.getObjects();
		RayIntersection intersection;
		RayIntersection closestIntersection = null;
		for(GraphicalObject object : objects) {
			if((intersection = object.findClosestRayIntersection(ray)) != null) {
				if(closestIntersection == null || closestIntersection.getDistance() > intersection.getDistance()) {
					closestIntersection = intersection;
				}
			}
		}
		return closestIntersection;
	}
	
	/**
	 * Metoda koja određuje boju za točku koja se siječe s zrakom.
	 * 
	 * @param intersection Sjecište zrake i najbližeg geometrijskog objekta na sceni
	 * @param scene Scena
	 * @param rgb Boja točke
	 * @param ray Zraka
	 */
	private static void determineColorFor(RayIntersection intersection, Scene scene, short[] rgb, Ray ray) {
		if(intersection == null) return;
		
		rgb[0] = 15;
		rgb[1] = 15;
		rgb[2] = 15;
		
		for(LightSource light : scene.getLights()) {
			Ray lightRay = Ray.fromPoints(light.getPoint(), intersection.getPoint());
			RayIntersection lightIntersection = findClosestIntersection(scene, lightRay);
			if(lightIntersection == null) continue;
			if((Math.abs(lightIntersection.getPoint().norm() - intersection.getPoint().norm())) < 1E-6) {
				Point3D l = lightRay.direction;
				Point3D n = lightIntersection.getNormal();
				double fac1 = l.scalarProduct(n);
				Point3D r = lightRay.direction.sub(n.scalarMultiply(
						(2 * lightRay.direction.scalarProduct(n))/(n.norm() * n.norm())));
				Point3D v = ray.direction;
				double fac2 = Math.pow(r.scalarProduct(v), lightIntersection.getKrn());
				
			
				
				
				rgb[0] += (light.getR()* fac1 * lightIntersection.getKdr() ) +
							(light.getR() * fac2 * lightIntersection.getKrr());
				rgb[1] += (light.getG()* fac1 * lightIntersection.getKdg() ) +
							(light.getG() * fac2 * lightIntersection.getKrg() );
				rgb[2] += (light.getB()* fac1 * lightIntersection.getKdb() ) +
						light.getB() * fac2 * lightIntersection.getKrb() ;
			}
		}
	}
	
}


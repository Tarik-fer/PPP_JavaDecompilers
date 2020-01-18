package hr.fer.zemris.java.raytracer;

import java.util.concurrent.atomic.AtomicBoolean;

import hr.fer.zemris.java.raytracer.model.IRayTracerProducer;
import hr.fer.zemris.java.raytracer.model.IRayTracerResultObserver;
import hr.fer.zemris.java.raytracer.model.Point3D;
import hr.fer.zemris.java.raytracer.model.Ray;
import hr.fer.zemris.java.raytracer.model.Scene;
import hr.fer.zemris.java.raytracer.util.Caster;
import hr.fer.zemris.java.raytracer.viewer.RayTracerViewer;
/**
 * Primjer rada RayCastera.
 *
 * @author Petar Kovač
 *
 */
public class RayCaster {
	public static void main(String[] args) {
		RayTracerViewer.show(getIRayTracerProducer(),
				new Point3D(10,0,0),
				new Point3D(0,0,0),
				new Point3D(0,0,10),
				20, 20);
	}
	private static IRayTracerProducer getIRayTracerProducer() {
		return new IRayTracerProducer() {
			@Override
			public void produce(Point3D eye, Point3D view, Point3D viewUp,
					double horizontal, double vertical, int width, int height,
					long requestNo, IRayTracerResultObserver observer, AtomicBoolean cancel) {
				System.out.println("Započinjem izračune...");
				short[] red = new short[width*height];
				short[] green = new short[width*height];
				short[] blue = new short[width*height];
				Point3D VUV = viewUp.normalize();
				Point3D zAxis =	 eye.sub(view).normalize();
				Point3D yAxis = VUV.modifySub(zAxis.scalarMultiply(zAxis.scalarProduct(VUV))).normalize();
				Point3D xAxis = yAxis.vectorProduct(zAxis).normalize();
				Point3D screenCorner = view.sub(xAxis.scalarMultiply(horizontal/2.0)).modifyAdd(
										yAxis.scalarMultiply(vertical/2.0));
				Scene scene = RayTracerViewer.createPredefinedScene();
				short[] rgb = new short[3];
				int offset = 0;
				for(int y = 0; y < height; y++) {
					for(int x = 0; x < width; x++) {
						Point3D screenPoint = screenCorner.add(xAxis.scalarMultiply(
											horizontal * ((double)x/(width - 1)))).modifySub(yAxis.scalarMultiply(
											vertical* ((double)y/(height-1))));
												
						Ray ray = Ray.fromPoints(eye, screenPoint);
						Caster.tracer(scene, ray, rgb);
						red[offset] = rgb[0] > 255 ? 255 : rgb[0];
						green[offset] = rgb[1] > 255 ? 255 : rgb[1];
						blue[offset] = rgb[2] > 255 ? 255 : rgb[2];
						offset++;
					}
				}
				System.out.println("Izračuni gotovi...");
				observer.acceptResult(red, green, blue, requestNo);
				System.out.println("Dojava gotova...");
			}
		};
		}

}

package hr.fer.zemris.java.raytracer;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicBoolean;

import hr.fer.zemris.java.raytracer.model.IRayTracerProducer;
import hr.fer.zemris.java.raytracer.model.IRayTracerResultObserver;
import hr.fer.zemris.java.raytracer.model.Point3D;
import hr.fer.zemris.java.raytracer.model.Ray;
import hr.fer.zemris.java.raytracer.model.Scene;
import hr.fer.zemris.java.raytracer.util.Caster;
import hr.fer.zemris.java.raytracer.viewer.RayTracerViewer;

/**
 * Prvi primjer rada RayCatera uz paralelizaciju.
 *
 * @author Petar Kovač
 *
 */
public class RayCasterParallel {
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
				
				ForkJoinPool pool = new ForkJoinPool();
				
				short[] red = new short[width*height];
				short[] green = new short[width*height];
				short[] blue = new short[width*height];
				Point3D VUV = viewUp.normalize();
				Point3D zAxis =	 eye.sub(view).normalize();
				Point3D yAxis = VUV.modifySub(zAxis.scalarMultiply(zAxis.scalarProduct(VUV))).normalize();
				Point3D xAxis = yAxis.vectorProduct(zAxis).normalize();
				Point3D screenCorner = view.sub(xAxis.scalarMultiply(horizontal/2.0)).modifyAdd(
										yAxis.scalarMultiply(vertical/2.0));
				Scene scene = RayTracerViewer.createPredefinedScene2();
				izracunajParalelno(pool, red, green, blue, xAxis, yAxis, zAxis, screenCorner, scene,
								width, height, eye, horizontal, vertical);
				
				pool.shutdown();
				System.out.println("Izračuni gotovi...");
				observer.acceptResult(red, green, blue, requestNo);
				System.out.println("Dojava gotova...");
			}
		};
	}
	
	private static void izracunajParalelno(ForkJoinPool pool, short[] red, short[] green, short[] blue,
			Point3D xAxis, Point3D yAxis, Point3D zAxis, Point3D screenCorner, Scene scene, int width, int height
			,Point3D eye, double horizontal, double vertical) {
		class Posao extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			private short[] red;
			private short[] green;
			private short[] blue;
			private Point3D xAxis;
			private Point3D yAxis;
			private Point3D zAxis;
			private Point3D screenCorner;
			private Scene scene;
			private int width;
			private int height;
			private Point3D eye;
			private int stripSize;
			private int yMin;
			private int yMax;
			private double horizontal;
			private double vertical;

			public Posao(short[] red, short[] green, short[] blue, Point3D xAxis, Point3D yAxis, Point3D zAxis,
					Point3D screenCorner, Scene scene, int width, int height, Point3D eye, int stripSize,
					int yMin, int yMax, double horizontal, double vertical) {
				super();
				this.red = red;
				this.green = green;
				this.blue = blue;
				this.xAxis = xAxis;
				this.yAxis = yAxis;
				this.zAxis = zAxis;
				this.screenCorner = screenCorner;
				this.scene = scene;
				this.width = width;
				this.height = height;
				this.eye = eye;
				this.stripSize = stripSize;
				this.yMin = yMin;
				this.yMax = yMax;
				this.horizontal = horizontal;
				this.vertical = vertical;
			}
			
			@Override
			protected void compute() {
				if((yMax - yMin) <= stripSize) {
					computeDirect();
					return;
				}
				int boundary = (yMin + ((yMax - yMin) / 2));
				Posao p1 = new Posao(red, green, blue, xAxis, yAxis, zAxis, screenCorner, scene,
								width, height, eye, stripSize, boundary, yMax, horizontal, vertical);
				Posao p2 = new Posao(red, green, blue, xAxis, yAxis, zAxis, screenCorner, scene, 
							width, height, eye, stripSize, yMin, boundary, horizontal, vertical);
				invokeAll(p1, p2);
			}
			private void computeDirect() {
				short[] rgb = new short[3];
				for(int y = yMin; y < yMax; y++) {
					for(int x = 0; x < width; x++) {
						Point3D screenPoint = screenCorner.add(xAxis.scalarMultiply(
											horizontal * ((double)x/(width - 1)))).modifySub(yAxis.scalarMultiply(
											vertical* ((double)y/(height-1))));
												
						Ray ray = Ray.fromPoints(eye, screenPoint);
						Caster.tracer(scene, ray, rgb);
						red[y*width + x] = rgb[0] > 255 ? 255 : rgb[0];
						green[y*width + x] = rgb[1] > 255 ? 255 : rgb[1];
						blue[y*width + x] = rgb[2] > 255 ? 255 : rgb[2];
					}
				}
			}
		}
		int stripSize = (height) / (8 * Runtime.getRuntime().availableProcessors());
		Posao p = new Posao(red, green, blue, xAxis, yAxis, zAxis, screenCorner, 
				scene, width, height, eye, stripSize, 0, height, horizontal, vertical);
		pool.invoke(p);
		return;
	}

}

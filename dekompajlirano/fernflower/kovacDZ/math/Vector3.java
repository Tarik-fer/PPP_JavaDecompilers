package hr.fer.zemris.math;

public class Vector3 {
   private double x;
   private double y;
   private double z;

   public Vector3(double x, double y, double z) {
      this.x = x;
      this.y = y;
      this.z = z;
   }

   public double norm() {
      return Math.sqrt(Math.pow(this.x, 2.0D) + Math.pow(this.y, 2.0D) + Math.pow(this.z, 2.0D));
   }

   public Vector3 normalized() {
      double norm = this.norm();
      return new Vector3(this.x / norm, this.y / norm, this.z / norm);
   }

   public Vector3 add(Vector3 other) {
      double newX = this.x + other.x;
      double newY = this.y + other.y;
      double newZ = this.z + other.z;
      return new Vector3(newX, newY, newZ);
   }

   public Vector3 sub(Vector3 other) {
      double newX = this.x - other.x;
      double newY = this.y - other.y;
      double newZ = this.z - other.z;
      return new Vector3(newX, newY, newZ);
   }

   public double dot(Vector3 other) {
      return this.x * other.x + this.y * other.y + this.z * other.z;
   }

   public Vector3 cross(Vector3 other) {
      double s1 = this.y * other.z - this.z * other.y;
      double s2 = this.z * other.x - this.x * other.z;
      double s3 = this.x * other.y - this.y * other.x;
      return new Vector3(s1, s2, s3);
   }

   public Vector3 scale(double s) {
      return new Vector3(this.x * s, this.y * s, this.z * s);
   }

   public double cosAngle(Vector3 other) {
      return this.dot(other) / (this.norm() * other.norm());
   }

   public double getX() {
      return this.x;
   }

   public double getY() {
      return this.y;
   }

   public double getZ() {
      return this.z;
   }

   public double[] toArray() {
      double[] array = new double[]{this.x, this.y, this.z};
      return array;
   }

   public String toString() {
      return String.format("(%.6f, %.6f, %.6f) ", this.x, this.y, this.z);
   }
}

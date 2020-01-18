/*     */ package org.apache.commons.io;
/*     */ 
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EndianUtils
/*     */ {
/*  57 */   public static short swapShort(short value) { return (short)(((value >> 0 & 0xFF) << 8) + ((value >> 8 & 0xFF) << 0)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   public static int swapInteger(int value) { return ((value >> 0 & 0xFF) << 24) + ((value >> 8 & 0xFF) << 16) + ((value >> 16 & 0xFF) << 8) + ((value >> 24 & 0xFF) << 0); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   public static long swapLong(long value) { return ((value >> 0L & 0xFFL) << 56L) + ((value >> 8L & 0xFFL) << 48L) + ((value >> 16L & 0xFFL) << 40L) + ((value >> 24L & 0xFFL) << 32L) + ((value >> 32L & 0xFFL) << 24L) + ((value >> 40L & 0xFFL) << 16L) + ((value >> 48L & 0xFFL) << 8L) + ((value >> 56L & 0xFFL) << 0L); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   public static float swapFloat(float value) { return Float.intBitsToFloat(swapInteger(Float.floatToIntBits(value))); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 106 */   public static double swapDouble(double value) { return Double.longBitsToDouble(swapLong(Double.doubleToLongBits(value))); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void writeSwappedShort(byte[] data, int offset, short value) {
/* 119 */     data[offset + 0] = (byte)(value >> 0 & 0xFF);
/* 120 */     data[offset + 1] = (byte)(value >> 8 & 0xFF);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 131 */   public static short readSwappedShort(byte[] data, int offset) { return (short)(((data[offset + 0] & 0xFF) << 0) + ((data[offset + 1] & 0xFF) << 8)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 144 */   public static int readSwappedUnsignedShort(byte[] data, int offset) { return ((data[offset + 0] & 0xFF) << 0) + ((data[offset + 1] & 0xFF) << 8); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void writeSwappedInteger(byte[] data, int offset, int value) {
/* 156 */     data[offset + 0] = (byte)(value >> 0 & 0xFF);
/* 157 */     data[offset + 1] = (byte)(value >> 8 & 0xFF);
/* 158 */     data[offset + 2] = (byte)(value >> 16 & 0xFF);
/* 159 */     data[offset + 3] = (byte)(value >> 24 & 0xFF);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 170 */   public static int readSwappedInteger(byte[] data, int offset) { return ((data[offset + 0] & 0xFF) << 0) + ((data[offset + 1] & 0xFF) << 8) + ((data[offset + 2] & 0xFF) << 16) + ((data[offset + 3] & 0xFF) << 24); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long readSwappedUnsignedInteger(byte[] data, int offset) {
/* 185 */     long low = (((data[offset + 0] & 0xFF) << 0) + ((data[offset + 1] & 0xFF) << 8) + ((data[offset + 2] & 0xFF) << 16));
/*     */ 
/*     */ 
/*     */     
/* 189 */     long high = (data[offset + 3] & 0xFF);
/*     */     
/* 191 */     return (high << 24L) + (0xFFFFFFFFL & low);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void writeSwappedLong(byte[] data, int offset, long value) {
/* 202 */     data[offset + 0] = (byte)(int)(value >> 0L & 0xFFL);
/* 203 */     data[offset + 1] = (byte)(int)(value >> 8L & 0xFFL);
/* 204 */     data[offset + 2] = (byte)(int)(value >> 16L & 0xFFL);
/* 205 */     data[offset + 3] = (byte)(int)(value >> 24L & 0xFFL);
/* 206 */     data[offset + 4] = (byte)(int)(value >> 32L & 0xFFL);
/* 207 */     data[offset + 5] = (byte)(int)(value >> 40L & 0xFFL);
/* 208 */     data[offset + 6] = (byte)(int)(value >> 48L & 0xFFL);
/* 209 */     data[offset + 7] = (byte)(int)(value >> 56L & 0xFFL);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long readSwappedLong(byte[] data, int offset) {
/* 220 */     long low = (((data[offset + 0] & 0xFF) << 0) + ((data[offset + 1] & 0xFF) << 8) + ((data[offset + 2] & 0xFF) << 16) + ((data[offset + 3] & 0xFF) << 24));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 225 */     long high = (((data[offset + 4] & 0xFF) << 0) + ((data[offset + 5] & 0xFF) << 8) + ((data[offset + 6] & 0xFF) << 16) + ((data[offset + 7] & 0xFF) << 24));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 230 */     return (high << 32L) + (0xFFFFFFFFL & low);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 241 */   public static void writeSwappedFloat(byte[] data, int offset, float value) { writeSwappedInteger(data, offset, Float.floatToIntBits(value)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 252 */   public static float readSwappedFloat(byte[] data, int offset) { return Float.intBitsToFloat(readSwappedInteger(data, offset)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 263 */   public static void writeSwappedDouble(byte[] data, int offset, double value) { writeSwappedLong(data, offset, Double.doubleToLongBits(value)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 274 */   public static double readSwappedDouble(byte[] data, int offset) { return Double.longBitsToDouble(readSwappedLong(data, offset)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void writeSwappedShort(OutputStream output, short value) throws IOException {
/* 287 */     output.write((byte)(value >> 0 & 0xFF));
/* 288 */     output.write((byte)(value >> 8 & 0xFF));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 301 */   public static short readSwappedShort(InputStream input) throws IOException { return (short)(((read(input) & 0xFF) << 0) + ((read(input) & 0xFF) << 8)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int readSwappedUnsignedShort(InputStream input) throws IOException {
/* 315 */     int value1 = read(input);
/* 316 */     int value2 = read(input);
/*     */     
/* 318 */     return ((value1 & 0xFF) << 0) + ((value2 & 0xFF) << 8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void writeSwappedInteger(OutputStream output, int value) throws IOException {
/* 332 */     output.write((byte)(value >> 0 & 0xFF));
/* 333 */     output.write((byte)(value >> 8 & 0xFF));
/* 334 */     output.write((byte)(value >> 16 & 0xFF));
/* 335 */     output.write((byte)(value >> 24 & 0xFF));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int readSwappedInteger(InputStream input) throws IOException {
/* 348 */     int value1 = read(input);
/* 349 */     int value2 = read(input);
/* 350 */     int value3 = read(input);
/* 351 */     int value4 = read(input);
/*     */     
/* 353 */     return ((value1 & 0xFF) << 0) + ((value2 & 0xFF) << 8) + ((value3 & 0xFF) << 16) + ((value4 & 0xFF) << 24);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long readSwappedUnsignedInteger(InputStream input) throws IOException {
/* 369 */     int value1 = read(input);
/* 370 */     int value2 = read(input);
/* 371 */     int value3 = read(input);
/* 372 */     int value4 = read(input);
/*     */     
/* 374 */     long low = (((value1 & 0xFF) << 0) + ((value2 & 0xFF) << 8) + ((value3 & 0xFF) << 16));
/*     */ 
/*     */ 
/*     */     
/* 378 */     long high = (value4 & 0xFF);
/*     */     
/* 380 */     return (high << 24L) + (0xFFFFFFFFL & low);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void writeSwappedLong(OutputStream output, long value) throws IOException {
/* 393 */     output.write((byte)(int)(value >> 0L & 0xFFL));
/* 394 */     output.write((byte)(int)(value >> 8L & 0xFFL));
/* 395 */     output.write((byte)(int)(value >> 16L & 0xFFL));
/* 396 */     output.write((byte)(int)(value >> 24L & 0xFFL));
/* 397 */     output.write((byte)(int)(value >> 32L & 0xFFL));
/* 398 */     output.write((byte)(int)(value >> 40L & 0xFFL));
/* 399 */     output.write((byte)(int)(value >> 48L & 0xFFL));
/* 400 */     output.write((byte)(int)(value >> 56L & 0xFFL));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long readSwappedLong(InputStream input) throws IOException {
/* 413 */     byte[] bytes = new byte[8];
/* 414 */     for (int i = 0; i < 8; i++) {
/* 415 */       bytes[i] = (byte)read(input);
/*     */     }
/* 417 */     return readSwappedLong(bytes, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 430 */   public static void writeSwappedFloat(OutputStream output, float value) throws IOException { writeSwappedInteger(output, Float.floatToIntBits(value)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 443 */   public static float readSwappedFloat(InputStream input) throws IOException { return Float.intBitsToFloat(readSwappedInteger(input)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 456 */   public static void writeSwappedDouble(OutputStream output, double value) throws IOException { writeSwappedLong(output, Double.doubleToLongBits(value)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 469 */   public static double readSwappedDouble(InputStream input) throws IOException { return Double.longBitsToDouble(readSwappedLong(input)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int read(InputStream input) throws IOException {
/* 481 */     int value = input.read();
/*     */     
/* 483 */     if (-1 == value) {
/* 484 */       throw new EOFException("Unexpected EOF reached");
/*     */     }
/*     */     
/* 487 */     return value;
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\commons\io\EndianUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.2
 */
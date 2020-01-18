/*     */ package org.apache.commons.io.input;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import org.apache.commons.io.EndianUtils;
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
/*     */ public class SwappedDataInputStream
/*     */   extends ProxyInputStream
/*     */   implements DataInput
/*     */ {
/*  47 */   public SwappedDataInputStream(InputStream input) { super(input); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   public boolean readBoolean() throws IOException, EOFException { return (0 == readByte()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   public byte readByte() throws IOException, EOFException { return (byte)this.in.read(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  68 */   public char readChar() throws IOException, EOFException { return (char)readShort(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  75 */   public double readDouble() throws IOException, EOFException { return EndianUtils.readSwappedDouble(this.in); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   public float readFloat() throws IOException, EOFException { return EndianUtils.readSwappedFloat(this.in); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  89 */   public void readFully(byte[] data) throws IOException, EOFException { readFully(data, 0, data.length); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readFully(byte[] data, int offset, int length) throws IOException, EOFException {
/*  96 */     int remaining = length;
/*     */     
/*  98 */     while (remaining > 0) {
/*     */       
/* 100 */       int location = offset + length - remaining;
/* 101 */       int count = read(data, location, remaining);
/*     */       
/* 103 */       if (-1 == count)
/*     */       {
/* 105 */         throw new EOFException();
/*     */       }
/*     */       
/* 108 */       remaining -= count;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 116 */   public int readInt() throws IOException, EOFException { return EndianUtils.readSwappedInteger(this.in); }
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
/* 127 */   public String readLine() throws IOException, EOFException { throw new UnsupportedOperationException("Operation not supported: readLine()"); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 135 */   public long readLong() throws IOException, EOFException { return EndianUtils.readSwappedLong(this.in); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 142 */   public short readShort() throws IOException, EOFException { return EndianUtils.readSwappedShort(this.in); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 149 */   public int readUnsignedByte() throws IOException, EOFException { return this.in.read(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 156 */   public int readUnsignedShort() throws IOException, EOFException { return EndianUtils.readSwappedUnsignedShort(this.in); }
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
/* 167 */   public String readUTF() throws IOException, EOFException { throw new UnsupportedOperationException("Operation not supported: readUTF()"); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 175 */   public int skipBytes(int count) throws IOException, EOFException { return (int)this.in.skip(count); }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\commons\io\input\SwappedDataInputStream.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.2
 */
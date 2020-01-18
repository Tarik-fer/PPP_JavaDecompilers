/*    */ package org.apache.commons.io.input;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.ObjectInputStream;
/*    */ import java.io.ObjectStreamClass;
/*    */ import java.io.StreamCorruptedException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ClassLoaderObjectInputStream
/*    */   extends ObjectInputStream
/*    */ {
/*    */   private ClassLoader classLoader;
/*    */   
/*    */   public ClassLoaderObjectInputStream(ClassLoader classLoader, InputStream inputStream) throws IOException, StreamCorruptedException {
/* 51 */     super(inputStream);
/* 52 */     this.classLoader = classLoader;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Class resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
/* 67 */     Class clazz = Class.forName(objectStreamClass.getName(), false, this.classLoader);
/*    */     
/* 69 */     if (clazz != null)
/*    */     {
/* 71 */       return clazz;
/*    */     }
/*    */     
/* 74 */     return super.resolveClass(objectStreamClass);
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\commons\io\input\ClassLoaderObjectInputStream.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.2
 */
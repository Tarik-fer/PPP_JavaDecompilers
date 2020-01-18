/*     */ package org.junit.internal.runners;
/*     */ 
/*     */ import java.lang.annotation.Annotation;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.junit.AfterClass;
/*     */ import org.junit.Before;
/*     */ import org.junit.BeforeClass;
/*     */ import org.junit.Test;
/*     */ import org.junit.internal.MethodSorter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public class TestClass
/*     */ {
/*     */   private final Class<?> klass;
/*     */   
/*  27 */   public TestClass(Class<?> klass) { this.klass = klass; }
/*     */ 
/*     */ 
/*     */   
/*  31 */   public List<Method> getTestMethods() { return getAnnotatedMethods((Class)Test.class); }
/*     */ 
/*     */ 
/*     */   
/*  35 */   List<Method> getBefores() { return getAnnotatedMethods((Class)BeforeClass.class); }
/*     */ 
/*     */ 
/*     */   
/*  39 */   List<Method> getAfters() { return getAnnotatedMethods((Class)AfterClass.class); }
/*     */ 
/*     */   
/*     */   public List<Method> getAnnotatedMethods(Class<? extends Annotation> annotationClass) {
/*  43 */     List<Method> results = new ArrayList<Method>();
/*  44 */     for (Class<?> eachClass : getSuperClasses(this.klass)) {
/*  45 */       Method[] methods = MethodSorter.getDeclaredMethods(eachClass);
/*  46 */       for (Method eachMethod : methods) {
/*  47 */         Object object = eachMethod.getAnnotation((Class)annotationClass);
/*  48 */         if (object != null && !isShadowed(eachMethod, results)) {
/*  49 */           results.add(eachMethod);
/*     */         }
/*     */       } 
/*     */     } 
/*  53 */     if (runsTopToBottom(annotationClass)) {
/*  54 */       Collections.reverse(results);
/*     */     }
/*  56 */     return results;
/*     */   }
/*     */ 
/*     */   
/*  60 */   private boolean runsTopToBottom(Class<? extends Annotation> annotation) { return (annotation.equals(Before.class) || annotation.equals(BeforeClass.class)); }
/*     */ 
/*     */   
/*     */   private boolean isShadowed(Method method, List<Method> results) {
/*  64 */     for (Method each : results) {
/*  65 */       if (isShadowed(method, each)) {
/*  66 */         return true;
/*     */       }
/*     */     } 
/*  69 */     return false;
/*     */   }
/*     */   
/*     */   private boolean isShadowed(Method current, Method previous) {
/*  73 */     if (!previous.getName().equals(current.getName())) {
/*  74 */       return false;
/*     */     }
/*  76 */     if ((previous.getParameterTypes()).length != (current.getParameterTypes()).length) {
/*  77 */       return false;
/*     */     }
/*  79 */     for (int i = 0; i < (previous.getParameterTypes()).length; i++) {
/*  80 */       if (!previous.getParameterTypes()[i].equals(current.getParameterTypes()[i])) {
/*  81 */         return false;
/*     */       }
/*     */     } 
/*  84 */     return true;
/*     */   }
/*     */   
/*     */   private List<Class<?>> getSuperClasses(Class<?> testClass) {
/*  88 */     ArrayList<Class<?>> results = new ArrayList<Class<?>>();
/*  89 */     Class<?> current = testClass;
/*  90 */     while (current != null) {
/*  91 */       results.add(current);
/*  92 */       current = current.getSuperclass();
/*     */     } 
/*  94 */     return results;
/*     */   }
/*     */ 
/*     */   
/*  98 */   public Constructor<?> getConstructor() throws SecurityException, NoSuchMethodException { return this.klass.getConstructor(new Class[0]); }
/*     */ 
/*     */ 
/*     */   
/* 102 */   public Class<?> getJavaClass() { return this.klass; }
/*     */ 
/*     */ 
/*     */   
/* 106 */   public String getName() { return this.klass.getName(); }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\internal\runners\TestClass.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
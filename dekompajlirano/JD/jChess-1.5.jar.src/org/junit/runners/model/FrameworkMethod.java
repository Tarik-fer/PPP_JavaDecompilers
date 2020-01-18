/*     */ package org.junit.runners.model;
/*     */ 
/*     */ import java.lang.annotation.Annotation;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.List;
/*     */ import org.junit.internal.runners.model.ReflectiveCallable;
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
/*     */ public class FrameworkMethod
/*     */   extends FrameworkMember<FrameworkMethod>
/*     */ {
/*     */   private final Method method;
/*     */   
/*     */   public FrameworkMethod(Method method) {
/*  26 */     if (method == null) {
/*  27 */       throw new NullPointerException("FrameworkMethod cannot be created without an underlying method.");
/*     */     }
/*     */     
/*  30 */     this.method = method;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  37 */   public Method getMethod() { return this.method; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  47 */   public Object invokeExplosively(final Object target, Object... params) throws Throwable { return (new ReflectiveCallable()
/*     */       {
/*     */         protected Object runReflectiveCall() throws Throwable {
/*  50 */           return FrameworkMethod.this.method.invoke(target, params);
/*     */         }
/*     */       }).run(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   public String getName() { return this.method.getName(); }
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
/*     */   public void validatePublicVoidNoArg(boolean isStatic, List<Throwable> errors) {
/*  74 */     validatePublicVoid(isStatic, errors);
/*  75 */     if ((this.method.getParameterTypes()).length != 0) {
/*  76 */       errors.add(new Exception("Method " + this.method.getName() + " should have no parameters"));
/*     */     }
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
/*     */   public void validatePublicVoid(boolean isStatic, List<Throwable> errors) {
/*  91 */     if (isStatic() != isStatic) {
/*  92 */       String state = isStatic ? "should" : "should not";
/*  93 */       errors.add(new Exception("Method " + this.method.getName() + "() " + state + " be static"));
/*     */     } 
/*  95 */     if (!isPublic()) {
/*  96 */       errors.add(new Exception("Method " + this.method.getName() + "() should be public"));
/*     */     }
/*  98 */     if (this.method.getReturnType() != void.class) {
/*  99 */       errors.add(new Exception("Method " + this.method.getName() + "() should be void"));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 105 */   protected int getModifiers() { return this.method.getModifiers(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 112 */   public Class<?> getReturnType() { return this.method.getReturnType(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 120 */   public Class<?> getType() { return getReturnType(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 128 */   public Class<?> getDeclaringClass() { return this.method.getDeclaringClass(); }
/*     */ 
/*     */ 
/*     */   
/* 132 */   public void validateNoTypeParametersOnArgs(List<Throwable> errors) { (new NoGenericTypeParametersValidator(this.method)).validate(errors); }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isShadowedBy(FrameworkMethod other) {
/* 137 */     if (!other.getName().equals(getName())) {
/* 138 */       return false;
/*     */     }
/* 140 */     if ((other.getParameterTypes()).length != (getParameterTypes()).length) {
/* 141 */       return false;
/*     */     }
/* 143 */     for (int i = 0; i < (other.getParameterTypes()).length; i++) {
/* 144 */       if (!other.getParameterTypes()[i].equals(getParameterTypes()[i])) {
/* 145 */         return false;
/*     */       }
/*     */     } 
/* 148 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 153 */     if (!FrameworkMethod.class.isInstance(obj)) {
/* 154 */       return false;
/*     */     }
/* 156 */     return ((FrameworkMethod)obj).method.equals(this.method);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 161 */   public int hashCode() { return this.method.hashCode(); }
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
/*     */   @Deprecated
/* 175 */   public boolean producesType(Type type) { return ((getParameterTypes()).length == 0 && type instanceof Class && ((Class)type).isAssignableFrom(this.method.getReturnType())); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 180 */   private Class<?>[] getParameterTypes() { return this.method.getParameterTypes(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 187 */   public Annotation[] getAnnotations() { return this.method.getAnnotations(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 195 */   public <T extends Annotation> T getAnnotation(Class<T> annotationType) { return this.method.getAnnotation(annotationType); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 200 */   public String toString() { return this.method.toString(); }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\runners\model\FrameworkMethod.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
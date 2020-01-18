/*    */ package org.junit.runners.model;
/*    */ 
/*    */ import java.lang.annotation.Annotation;
/*    */ import java.lang.reflect.Field;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FrameworkField
/*    */   extends FrameworkMember<FrameworkField>
/*    */ {
/*    */   private final Field field;
/*    */   
/*    */   FrameworkField(Field field) {
/* 18 */     if (field == null) {
/* 19 */       throw new NullPointerException("FrameworkField cannot be created without an underlying field.");
/*    */     }
/*    */     
/* 22 */     this.field = field;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 27 */   public String getName() { return getField().getName(); }
/*    */ 
/*    */ 
/*    */   
/* 31 */   public Annotation[] getAnnotations() { return this.field.getAnnotations(); }
/*    */ 
/*    */ 
/*    */   
/* 35 */   public <T extends Annotation> T getAnnotation(Class<T> annotationType) { return this.field.getAnnotation(annotationType); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 40 */   public boolean isShadowedBy(FrameworkField otherMember) { return otherMember.getName().equals(getName()); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 45 */   protected int getModifiers() { return this.field.getModifiers(); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 52 */   public Field getField() { return this.field; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 61 */   public Class<?> getType() { return this.field.getType(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 66 */   public Class<?> getDeclaringClass() { return this.field.getDeclaringClass(); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 73 */   public Object get(Object target) throws IllegalArgumentException, IllegalAccessException { return this.field.get(target); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 78 */   public String toString() { return this.field.toString(); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\runners\model\FrameworkField.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
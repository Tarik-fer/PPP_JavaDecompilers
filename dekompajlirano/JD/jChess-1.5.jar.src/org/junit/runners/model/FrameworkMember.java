/*    */ package org.junit.runners.model;
/*    */ 
/*    */ import java.lang.reflect.Modifier;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class FrameworkMember<T extends FrameworkMember<T>>
/*    */   implements Annotatable
/*    */ {
/*    */   abstract boolean isShadowedBy(T paramT);
/*    */   
/*    */   boolean isShadowedBy(List<T> members) {
/* 16 */     for (FrameworkMember frameworkMember : members) {
/* 17 */       if (isShadowedBy((T)frameworkMember)) {
/* 18 */         return true;
/*    */       }
/*    */     } 
/* 21 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected abstract int getModifiers();
/*    */ 
/*    */ 
/*    */   
/* 30 */   public boolean isStatic() { return Modifier.isStatic(getModifiers()); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 37 */   public boolean isPublic() { return Modifier.isPublic(getModifiers()); }
/*    */   
/*    */   public abstract String getName();
/*    */   
/*    */   public abstract Class<?> getType();
/*    */   
/*    */   public abstract Class<?> getDeclaringClass();
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\runners\model\FrameworkMember.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
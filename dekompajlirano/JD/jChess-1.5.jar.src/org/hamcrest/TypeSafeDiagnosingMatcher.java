/*    */ package org.hamcrest;
/*    */ 
/*    */ import org.hamcrest.internal.ReflectiveTypeFinder;
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
/*    */ public abstract class TypeSafeDiagnosingMatcher<T>
/*    */   extends BaseMatcher<T>
/*    */ {
/* 18 */   private static final ReflectiveTypeFinder TYPE_FINDER = new ReflectiveTypeFinder("matchesSafely", 2, 0);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private final Class<?> expectedType;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected abstract boolean matchesSafely(T paramT, Description paramDescription);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 33 */   protected TypeSafeDiagnosingMatcher(Class<?> expectedType) { this.expectedType = expectedType; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 42 */   protected TypeSafeDiagnosingMatcher(ReflectiveTypeFinder typeFinder) { this.expectedType = typeFinder.findExpectedType(getClass()); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 49 */   protected TypeSafeDiagnosingMatcher() { this(TYPE_FINDER); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 55 */   public final boolean matches(Object item) { return (item != null && this.expectedType.isInstance(item) && matchesSafely((T)item, new Description.NullDescription())); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public final void describeMismatch(Object item, Description mismatchDescription) {
/* 63 */     if (item == null || !this.expectedType.isInstance(item)) {
/* 64 */       super.describeMismatch(item, mismatchDescription);
/*    */     } else {
/* 66 */       matchesSafely((T)item, mismatchDescription);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\hamcrest\TypeSafeDiagnosingMatcher.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
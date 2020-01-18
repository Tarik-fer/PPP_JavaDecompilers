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
/*    */ public abstract class TypeSafeMatcher<T>
/*    */   extends BaseMatcher<T>
/*    */ {
/* 14 */   private static final ReflectiveTypeFinder TYPE_FINDER = new ReflectiveTypeFinder("matchesSafely", 1, 0);
/*    */ 
/*    */ 
/*    */   
/*    */   private final Class<?> expectedType;
/*    */ 
/*    */ 
/*    */   
/* 22 */   protected TypeSafeMatcher() { this(TYPE_FINDER); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 31 */   protected TypeSafeMatcher(Class<?> expectedType) { this.expectedType = expectedType; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 40 */   protected TypeSafeMatcher(ReflectiveTypeFinder typeFinder) { this.expectedType = typeFinder.findExpectedType(getClass()); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected abstract boolean matchesSafely(T paramT);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 54 */   protected void describeMismatchSafely(T item, Description mismatchDescription) { super.describeMismatch(item, mismatchDescription); }
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
/* 65 */   public final boolean matches(Object item) { return (item != null && this.expectedType.isInstance(item) && matchesSafely((T)item)); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public final void describeMismatch(Object item, Description description) {
/* 73 */     if (item == null) {
/* 74 */       super.describeMismatch(item, description);
/* 75 */     } else if (!this.expectedType.isInstance(item)) {
/* 76 */       description.appendText("was a ").appendText(item.getClass().getName()).appendText(" (").appendValue(item).appendText(")");
/*    */     
/*    */     }
/*    */     else {
/*    */ 
/*    */       
/* 82 */       describeMismatchSafely((T)item, description);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\hamcrest\TypeSafeMatcher.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
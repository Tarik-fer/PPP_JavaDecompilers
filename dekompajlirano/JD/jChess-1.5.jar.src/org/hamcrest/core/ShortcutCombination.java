/*    */ package org.hamcrest.core;
/*    */ 
/*    */ import org.hamcrest.BaseMatcher;
/*    */ import org.hamcrest.Description;
/*    */ import org.hamcrest.Matcher;
/*    */ 
/*    */ abstract class ShortcutCombination<T>
/*    */   extends BaseMatcher<T>
/*    */ {
/*    */   private final Iterable<Matcher<? super T>> matchers;
/*    */   
/* 12 */   public ShortcutCombination(Iterable<Matcher<? super T>> matchers) { this.matchers = matchers; }
/*    */ 
/*    */   
/*    */   public abstract boolean matches(Object paramObject);
/*    */ 
/*    */   
/*    */   public abstract void describeTo(Description paramDescription);
/*    */ 
/*    */   
/*    */   protected boolean matches(Object o, boolean shortcut) {
/* 22 */     for (Matcher<? super T> matcher : this.matchers) {
/* 23 */       if (matcher.matches(o) == shortcut) {
/* 24 */         return shortcut;
/*    */       }
/*    */     } 
/* 27 */     return !shortcut;
/*    */   }
/*    */ 
/*    */   
/* 31 */   public void describeTo(Description description, String operator) { description.appendList("(", " " + operator + " ", ")", this.matchers); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\hamcrest\core\ShortcutCombination.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
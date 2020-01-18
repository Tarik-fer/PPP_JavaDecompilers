/*    */ package org.hamcrest.core;
/*    */ 
/*    */ import org.hamcrest.Description;
/*    */ import org.hamcrest.TypeSafeMatcher;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class SubstringMatcher
/*    */   extends TypeSafeMatcher<String>
/*    */ {
/*    */   protected final String substring;
/*    */   
/* 14 */   protected SubstringMatcher(String substring) { this.substring = substring; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 19 */   public boolean matchesSafely(String item) { return evalSubstringOf(item); }
/*    */ 
/*    */ 
/*    */   
/* 23 */   public void describeMismatchSafely(String item, Description mismatchDescription) { mismatchDescription.appendText("was \"").appendText(item).appendText("\""); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 28 */   public void describeTo(Description description) { description.appendText("a string ").appendText(relationship()).appendText(" ").appendValue(this.substring); }
/*    */   
/*    */   protected abstract boolean evalSubstringOf(String paramString);
/*    */   
/*    */   protected abstract String relationship();
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\hamcrest\core\SubstringMatcher.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
/*    */ package org.hamcrest.core;
/*    */ 
/*    */ import org.hamcrest.Factory;
/*    */ import org.hamcrest.Matcher;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StringContains
/*    */   extends SubstringMatcher
/*    */ {
/* 13 */   public StringContains(String substring) { super(substring); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 18 */   protected boolean evalSubstringOf(String s) { return (s.indexOf(this.substring) >= 0); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 23 */   protected String relationship() { return "containing"; }
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
/*    */   @Factory
/* 39 */   public static Matcher<String> containsString(String substring) { return (Matcher<String>)new StringContains(substring); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\hamcrest\core\StringContains.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
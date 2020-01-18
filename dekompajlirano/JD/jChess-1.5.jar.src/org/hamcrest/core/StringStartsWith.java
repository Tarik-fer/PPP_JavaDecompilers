/*    */ package org.hamcrest.core;
/*    */ 
/*    */ import org.hamcrest.Factory;
/*    */ import org.hamcrest.Matcher;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StringStartsWith
/*    */   extends SubstringMatcher
/*    */ {
/* 13 */   public StringStartsWith(String substring) { super(substring); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 18 */   protected boolean evalSubstringOf(String s) { return s.startsWith(this.substring); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 23 */   protected String relationship() { return "starting with"; }
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
/* 38 */   public static Matcher<String> startsWith(String prefix) { return (Matcher<String>)new StringStartsWith(prefix); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\hamcrest\core\StringStartsWith.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
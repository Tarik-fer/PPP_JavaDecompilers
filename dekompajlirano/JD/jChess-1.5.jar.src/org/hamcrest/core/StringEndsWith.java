/*    */ package org.hamcrest.core;
/*    */ 
/*    */ import org.hamcrest.Factory;
/*    */ import org.hamcrest.Matcher;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StringEndsWith
/*    */   extends SubstringMatcher
/*    */ {
/* 13 */   public StringEndsWith(String substring) { super(substring); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 18 */   protected boolean evalSubstringOf(String s) { return s.endsWith(this.substring); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 23 */   protected String relationship() { return "ending with"; }
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
/* 38 */   public static Matcher<String> endsWith(String suffix) { return (Matcher<String>)new StringEndsWith(suffix); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\hamcrest\core\StringEndsWith.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
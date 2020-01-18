/*    */ package org.hamcrest;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StringDescription
/*    */   extends BaseDescription
/*    */ {
/*    */   private final Appendable out;
/*    */   
/* 12 */   public StringDescription() { this(new StringBuilder()); }
/*    */ 
/*    */ 
/*    */   
/* 16 */   public StringDescription(Appendable out) { this.out = out; }
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
/* 28 */   public static String toString(SelfDescribing selfDescribing) { return (new StringDescription()).appendDescriptionOf(selfDescribing).toString(); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 35 */   public static String asString(SelfDescribing selfDescribing) { return toString(selfDescribing); }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void append(String str) {
/*    */     try {
/* 41 */       this.out.append(str);
/* 42 */     } catch (IOException e) {
/* 43 */       throw new RuntimeException("Could not write description", e);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected void append(char c) {
/*    */     try {
/* 50 */       this.out.append(c);
/* 51 */     } catch (IOException e) {
/* 52 */       throw new RuntimeException("Could not write description", e);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 61 */   public String toString() { return this.out.toString(); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\hamcrest\StringDescription.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
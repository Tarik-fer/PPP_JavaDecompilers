/*    */ package org.junit.internal;
/*    */ 
/*    */ import org.junit.Assert;
/*    */ 
/*    */ public class InexactComparisonCriteria
/*    */   extends ComparisonCriteria {
/*    */   public Object fDelta;
/*    */   
/*  9 */   public InexactComparisonCriteria(double delta) { this.fDelta = Double.valueOf(delta); }
/*    */ 
/*    */ 
/*    */   
/* 13 */   public InexactComparisonCriteria(float delta) { this.fDelta = Float.valueOf(delta); }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void assertElementsEqual(Object expected, Object actual) {
/* 18 */     if (expected instanceof Double) {
/* 19 */       Assert.assertEquals(((Double)expected).doubleValue(), ((Double)actual).doubleValue(), ((Double)this.fDelta).doubleValue());
/*    */     } else {
/* 21 */       Assert.assertEquals(((Float)expected).floatValue(), ((Float)actual).floatValue(), ((Float)this.fDelta).floatValue());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\internal\InexactComparisonCriteria.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
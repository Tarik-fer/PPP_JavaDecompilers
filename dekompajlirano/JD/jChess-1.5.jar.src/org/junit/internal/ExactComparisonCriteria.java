/*   */ package org.junit.internal;
/*   */ 
/*   */ import org.junit.Assert;
/*   */ 
/*   */ public class ExactComparisonCriteria
/*   */   extends ComparisonCriteria
/*   */ {
/* 8 */   protected void assertElementsEqual(Object expected, Object actual) { Assert.assertEquals(expected, actual); }
/*   */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\internal\ExactComparisonCriteria.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
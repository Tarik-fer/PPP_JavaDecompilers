/*    */ package junit.framework;
/*    */ 
/*    */ import org.junit.runner.Describable;
/*    */ import org.junit.runner.Description;
/*    */ 
/*    */ public class JUnit4TestCaseFacade
/*    */   implements Test, Describable {
/*    */   private final Description fDescription;
/*    */   
/* 10 */   JUnit4TestCaseFacade(Description description) { this.fDescription = description; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 15 */   public String toString() { return getDescription().toString(); }
/*    */ 
/*    */ 
/*    */   
/* 19 */   public int countTestCases() { return 1; }
/*    */ 
/*    */ 
/*    */   
/* 23 */   public void run(TestResult result) { throw new RuntimeException("This test stub created only for informational purposes."); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 28 */   public Description getDescription() { return this.fDescription; }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\junit\framework\JUnit4TestCaseFacade.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
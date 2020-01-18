/*    */ package junit.framework;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.junit.Ignore;
/*    */ import org.junit.runner.Describable;
/*    */ import org.junit.runner.Description;
/*    */ import org.junit.runner.Request;
/*    */ import org.junit.runner.Runner;
/*    */ import org.junit.runner.manipulation.Filter;
/*    */ import org.junit.runner.manipulation.Filterable;
/*    */ import org.junit.runner.manipulation.NoTestsRemainException;
/*    */ import org.junit.runner.manipulation.Sortable;
/*    */ import org.junit.runner.manipulation.Sorter;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JUnit4TestAdapter
/*    */   implements Test, Filterable, Sortable, Describable
/*    */ {
/*    */   private final Class<?> fNewTestClass;
/*    */   private final Runner fRunner;
/*    */   private final JUnit4TestAdapterCache fCache;
/*    */   
/* 24 */   public JUnit4TestAdapter(Class<?> newTestClass) { this(newTestClass, JUnit4TestAdapterCache.getDefault()); }
/*    */ 
/*    */   
/*    */   public JUnit4TestAdapter(Class<?> newTestClass, JUnit4TestAdapterCache cache) {
/* 28 */     this.fCache = cache;
/* 29 */     this.fNewTestClass = newTestClass;
/* 30 */     this.fRunner = Request.classWithoutSuiteMethod(newTestClass).getRunner();
/*    */   }
/*    */ 
/*    */   
/* 34 */   public int countTestCases() { return this.fRunner.testCount(); }
/*    */ 
/*    */ 
/*    */   
/* 38 */   public void run(TestResult result) { this.fRunner.run(this.fCache.getNotifier(result, this)); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 43 */   public List<Test> getTests() { return this.fCache.asTestList(getDescription()); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 48 */   public Class<?> getTestClass() { return this.fNewTestClass; }
/*    */ 
/*    */   
/*    */   public Description getDescription() {
/* 52 */     Description description = this.fRunner.getDescription();
/* 53 */     return removeIgnored(description);
/*    */   }
/*    */   
/*    */   private Description removeIgnored(Description description) {
/* 57 */     if (isIgnored(description)) {
/* 58 */       return Description.EMPTY;
/*    */     }
/* 60 */     Description result = description.childlessCopy();
/* 61 */     for (Description each : description.getChildren()) {
/* 62 */       Description child = removeIgnored(each);
/* 63 */       if (!child.isEmpty()) {
/* 64 */         result.addChild(child);
/*    */       }
/*    */     } 
/* 67 */     return result;
/*    */   }
/*    */ 
/*    */   
/* 71 */   private boolean isIgnored(Description description) { return (description.getAnnotation(Ignore.class) != null); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 76 */   public String toString() { return this.fNewTestClass.getName(); }
/*    */ 
/*    */ 
/*    */   
/* 80 */   public void filter(Filter filter) throws NoTestsRemainException { filter.apply(this.fRunner); }
/*    */ 
/*    */ 
/*    */   
/* 84 */   public void sort(Sorter sorter) { sorter.apply(this.fRunner); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\junit\framework\JUnit4TestAdapter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
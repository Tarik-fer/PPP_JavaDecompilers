/*     */ package org.junit.internal.runners;
/*     */ 
/*     */ import java.lang.annotation.Annotation;
/*     */ import java.lang.reflect.Method;
/*     */ import junit.extensions.TestDecorator;
/*     */ import junit.framework.AssertionFailedError;
/*     */ import junit.framework.Test;
/*     */ import junit.framework.TestCase;
/*     */ import junit.framework.TestListener;
/*     */ import junit.framework.TestResult;
/*     */ import junit.framework.TestSuite;
/*     */ import org.junit.runner.Describable;
/*     */ import org.junit.runner.Description;
/*     */ import org.junit.runner.Runner;
/*     */ import org.junit.runner.manipulation.Filter;
/*     */ import org.junit.runner.manipulation.Filterable;
/*     */ import org.junit.runner.manipulation.NoTestsRemainException;
/*     */ import org.junit.runner.manipulation.Sortable;
/*     */ import org.junit.runner.manipulation.Sorter;
/*     */ import org.junit.runner.notification.Failure;
/*     */ import org.junit.runner.notification.RunNotifier;
/*     */ 
/*     */ public class JUnit38ClassRunner extends Runner implements Filterable, Sortable {
/*     */   private volatile Test test;
/*     */   
/*     */   private static final class OldTestClassAdaptingListener implements TestListener {
/*     */     private final RunNotifier notifier;
/*     */     
/*  29 */     private OldTestClassAdaptingListener(RunNotifier notifier) { this.notifier = notifier; }
/*     */ 
/*     */ 
/*     */     
/*  33 */     public void endTest(Test test) { this.notifier.fireTestFinished(asDescription(test)); }
/*     */ 
/*     */ 
/*     */     
/*  37 */     public void startTest(Test test) { this.notifier.fireTestStarted(asDescription(test)); }
/*     */ 
/*     */ 
/*     */     
/*     */     public void addError(Test test, Throwable e) {
/*  42 */       Failure failure = new Failure(asDescription(test), e);
/*  43 */       this.notifier.fireTestFailure(failure);
/*     */     }
/*     */     
/*     */     private Description asDescription(Test test) {
/*  47 */       if (test instanceof Describable) {
/*  48 */         Describable facade = (Describable)test;
/*  49 */         return facade.getDescription();
/*     */       } 
/*  51 */       return Description.createTestDescription(getEffectiveClass(test), getName(test));
/*     */     }
/*     */ 
/*     */     
/*  55 */     private Class<? extends Test> getEffectiveClass(Test test) { return (Class)test.getClass(); }
/*     */ 
/*     */     
/*     */     private String getName(Test test) {
/*  59 */       if (test instanceof TestCase) {
/*  60 */         return ((TestCase)test).getName();
/*     */       }
/*  62 */       return test.toString();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  67 */     public void addFailure(Test test, AssertionFailedError t) { addError(test, (Throwable)t); }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  74 */   public JUnit38ClassRunner(Class<?> klass) { this((Test)new TestSuite(klass.asSubclass(TestCase.class))); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  79 */   public JUnit38ClassRunner(Test test) { setTest(test); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void run(RunNotifier notifier) {
/*  84 */     TestResult result = new TestResult();
/*  85 */     result.addListener(createAdaptingListener(notifier));
/*  86 */     getTest().run(result);
/*     */   }
/*     */ 
/*     */   
/*  90 */   public TestListener createAdaptingListener(RunNotifier notifier) { return new OldTestClassAdaptingListener(notifier); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  95 */   public Description getDescription() { return makeDescription(getTest()); }
/*     */ 
/*     */   
/*     */   private static Description makeDescription(Test test) {
/*  99 */     if (test instanceof TestCase) {
/* 100 */       TestCase tc = (TestCase)test;
/* 101 */       return Description.createTestDescription(tc.getClass(), tc.getName(), getAnnotations(tc));
/*     */     } 
/* 103 */     if (test instanceof TestSuite) {
/* 104 */       TestSuite ts = (TestSuite)test;
/* 105 */       String name = (ts.getName() == null) ? createSuiteDescription(ts) : ts.getName();
/* 106 */       Description description = Description.createSuiteDescription(name, new Annotation[0]);
/* 107 */       int n = ts.testCount();
/* 108 */       for (int i = 0; i < n; i++) {
/* 109 */         Description made = makeDescription(ts.testAt(i));
/* 110 */         description.addChild(made);
/*     */       } 
/* 112 */       return description;
/* 113 */     }  if (test instanceof Describable) {
/* 114 */       Describable adapter = (Describable)test;
/* 115 */       return adapter.getDescription();
/* 116 */     }  if (test instanceof TestDecorator) {
/* 117 */       TestDecorator decorator = (TestDecorator)test;
/* 118 */       return makeDescription(decorator.getTest());
/*     */     } 
/*     */     
/* 121 */     return Description.createSuiteDescription(test.getClass());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Annotation[] getAnnotations(TestCase test) {
/*     */     
/* 131 */     try { Method m = test.getClass().getMethod(test.getName(), new Class[0]);
/* 132 */       return m.getDeclaredAnnotations(); }
/* 133 */     catch (SecurityException e) {  }
/* 134 */     catch (NoSuchMethodException e) {}
/*     */     
/* 136 */     return new Annotation[0];
/*     */   }
/*     */   
/*     */   private static String createSuiteDescription(TestSuite ts) {
/* 140 */     int count = ts.countTestCases();
/* 141 */     String example = (count == 0) ? "" : String.format(" [example: %s]", new Object[] { ts.testAt(0) });
/* 142 */     return String.format("TestSuite with %s tests%s", new Object[] { Integer.valueOf(count), example });
/*     */   }
/*     */   
/*     */   public void filter(Filter filter) throws NoTestsRemainException {
/* 146 */     if (getTest() instanceof Filterable) {
/* 147 */       Filterable adapter = (Filterable)getTest();
/* 148 */       adapter.filter(filter);
/* 149 */     } else if (getTest() instanceof TestSuite) {
/* 150 */       TestSuite suite = (TestSuite)getTest();
/* 151 */       TestSuite filtered = new TestSuite(suite.getName());
/* 152 */       int n = suite.testCount();
/* 153 */       for (int i = 0; i < n; i++) {
/* 154 */         Test test = suite.testAt(i);
/* 155 */         if (filter.shouldRun(makeDescription(test))) {
/* 156 */           filtered.addTest(test);
/*     */         }
/*     */       } 
/* 159 */       setTest((Test)filtered);
/* 160 */       if (filtered.testCount() == 0) {
/* 161 */         throw new NoTestsRemainException();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void sort(Sorter sorter) {
/* 167 */     if (getTest() instanceof Sortable) {
/* 168 */       Sortable adapter = (Sortable)getTest();
/* 169 */       adapter.sort(sorter);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 174 */   private void setTest(Test test) { this.test = test; }
/*     */ 
/*     */ 
/*     */   
/* 178 */   private Test getTest() { return this.test; }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\internal\runners\JUnit38ClassRunner.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
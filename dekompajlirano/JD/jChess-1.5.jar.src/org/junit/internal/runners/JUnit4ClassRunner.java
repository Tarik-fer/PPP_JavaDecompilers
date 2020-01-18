/*     */ package org.junit.internal.runners;
/*     */ 
/*     */ import java.lang.annotation.Annotation;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public class JUnit4ClassRunner
/*     */   extends Runner
/*     */   implements Filterable, Sortable
/*     */ {
/*     */   private final List<Method> testMethods;
/*     */   private TestClass testClass;
/*     */   
/*     */   public JUnit4ClassRunner(Class<?> klass) throws InitializationError {
/*  33 */     this.testClass = new TestClass(klass);
/*  34 */     this.testMethods = getTestMethods();
/*  35 */     validate();
/*     */   }
/*     */ 
/*     */   
/*  39 */   protected List<Method> getTestMethods() { return this.testClass.getTestMethods(); }
/*     */ 
/*     */   
/*     */   protected void validate() throws InitializationError {
/*  43 */     MethodValidator methodValidator = new MethodValidator(this.testClass);
/*  44 */     methodValidator.validateMethodsForDefaultRunner();
/*  45 */     methodValidator.assertValid();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  50 */   public void run(final RunNotifier notifier) { (new ClassRoadie(notifier, this.testClass, getDescription(), new Runnable()
/*     */         {
/*  52 */           public void run() { JUnit4ClassRunner.this.runMethods(notifier); }
/*     */         })).runProtected(); }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void runMethods(RunNotifier notifier) {
/*  58 */     for (Method method : this.testMethods) {
/*  59 */       invokeTestMethod(method, notifier);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Description getDescription() {
/*  65 */     Description spec = Description.createSuiteDescription(getName(), classAnnotations());
/*  66 */     List<Method> testMethods = this.testMethods;
/*  67 */     for (Method method : testMethods) {
/*  68 */       spec.addChild(methodDescription(method));
/*     */     }
/*  70 */     return spec;
/*     */   }
/*     */ 
/*     */   
/*  74 */   protected Annotation[] classAnnotations() { return this.testClass.getJavaClass().getAnnotations(); }
/*     */ 
/*     */ 
/*     */   
/*  78 */   protected String getName() { return getTestClass().getName(); }
/*     */ 
/*     */ 
/*     */   
/*  82 */   protected Object createTest() throws Exception { return getTestClass().getConstructor().newInstance(new Object[0]); }
/*     */   
/*     */   protected void invokeTestMethod(Method method, RunNotifier notifier) {
/*     */     Object test;
/*  86 */     Description description = methodDescription(method);
/*     */     
/*     */     try {
/*  89 */       test = createTest();
/*  90 */     } catch (InvocationTargetException e) {
/*  91 */       testAborted(notifier, description, e.getCause());
/*     */       return;
/*  93 */     } catch (Exception e) {
/*  94 */       testAborted(notifier, description, e);
/*     */       return;
/*     */     } 
/*  97 */     TestMethod testMethod = wrapMethod(method);
/*  98 */     (new MethodRoadie(test, testMethod, notifier, description)).run();
/*     */   }
/*     */ 
/*     */   
/*     */   private void testAborted(RunNotifier notifier, Description description, Throwable e) {
/* 103 */     notifier.fireTestStarted(description);
/* 104 */     notifier.fireTestFailure(new Failure(description, e));
/* 105 */     notifier.fireTestFinished(description);
/*     */   }
/*     */ 
/*     */   
/* 109 */   protected TestMethod wrapMethod(Method method) { return new TestMethod(method, this.testClass); }
/*     */ 
/*     */ 
/*     */   
/* 113 */   protected String testName(Method method) { return method.getName(); }
/*     */ 
/*     */ 
/*     */   
/* 117 */   protected Description methodDescription(Method method) { return Description.createTestDescription(getTestClass().getJavaClass(), testName(method), testAnnotations(method)); }
/*     */ 
/*     */ 
/*     */   
/* 121 */   protected Annotation[] testAnnotations(Method method) { return method.getAnnotations(); }
/*     */ 
/*     */   
/*     */   public void filter(Filter filter) throws NoTestsRemainException {
/* 125 */     for (Iterator<Method> iter = this.testMethods.iterator(); iter.hasNext(); ) {
/* 126 */       Method method = iter.next();
/* 127 */       if (!filter.shouldRun(methodDescription(method))) {
/* 128 */         iter.remove();
/*     */       }
/*     */     } 
/* 131 */     if (this.testMethods.isEmpty()) {
/* 132 */       throw new NoTestsRemainException();
/*     */     }
/*     */   }
/*     */   
/*     */   public void sort(final Sorter sorter) {
/* 137 */     Collections.sort(this.testMethods, new Comparator<Method>()
/*     */         {
/* 139 */           public int compare(Method o1, Method o2) { return sorter.compare(JUnit4ClassRunner.this.methodDescription(o1), JUnit4ClassRunner.this.methodDescription(o2)); }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 145 */   protected TestClass getTestClass() { return this.testClass; }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\internal\runners\JUnit4ClassRunner.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
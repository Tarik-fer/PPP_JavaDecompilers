/*     */ package org.junit.experimental.max;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import junit.framework.Test;
/*     */ import junit.framework.TestSuite;
/*     */ import org.junit.internal.runners.ErrorReportingRunner;
/*     */ import org.junit.internal.runners.JUnit38ClassRunner;
/*     */ import org.junit.runner.Description;
/*     */ import org.junit.runner.JUnitCore;
/*     */ import org.junit.runner.Request;
/*     */ import org.junit.runner.Result;
/*     */ import org.junit.runner.Runner;
/*     */ import org.junit.runners.Suite;
/*     */ import org.junit.runners.model.InitializationError;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MaxCore
/*     */ {
/*     */   private static final String MALFORMED_JUNIT_3_TEST_CLASS_PREFIX = "malformed JUnit 3 test class: ";
/*     */   private final MaxHistory history;
/*     */   
/*     */   @Deprecated
/*  42 */   public static MaxCore forFolder(String folderName) { return storedLocally(new File(folderName)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   public static MaxCore storedLocally(File storedResults) { return new MaxCore(storedResults); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   private MaxCore(File storedResults) { this.history = MaxHistory.forFolder(storedResults); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   public Result run(Class<?> testClass) { return run(Request.aClass(testClass)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  74 */   public Result run(Request request) { return run(request, new JUnitCore()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Result run(Request request, JUnitCore core) {
/*  88 */     core.addListener(this.history.listener());
/*  89 */     return core.run(sortRequest(request).getRunner());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Request sortRequest(Request request) {
/*  96 */     if (request instanceof org.junit.internal.requests.SortingRequest)
/*     */     {
/*  98 */       return request;
/*     */     }
/* 100 */     List<Description> leaves = findLeaves(request);
/* 101 */     Collections.sort(leaves, this.history.testComparator());
/* 102 */     return constructLeafRequest(leaves);
/*     */   }
/*     */   
/*     */   private Request constructLeafRequest(List<Description> leaves) {
/* 106 */     final List<Runner> runners = new ArrayList<Runner>();
/* 107 */     for (Description each : leaves) {
/* 108 */       runners.add(buildRunner(each));
/*     */     }
/* 110 */     return new Request()
/*     */       {
/*     */         public Runner getRunner() {
/*     */           try {
/* 114 */             return (Runner)new Suite((Class)null, runners) {  }
/*     */               ;
/* 116 */           } catch (InitializationError e) {
/* 117 */             return (Runner)new ErrorReportingRunner(null, (Throwable)e);
/*     */           } 
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   private Runner buildRunner(Description each) {
/* 124 */     if (each.toString().equals("TestSuite with 0 tests")) {
/* 125 */       return Suite.emptySuite();
/*     */     }
/* 127 */     if (each.toString().startsWith("malformed JUnit 3 test class: "))
/*     */     {
/*     */ 
/*     */ 
/*     */       
/* 132 */       return (Runner)new JUnit38ClassRunner((Test)new TestSuite(getMalformedTestClass(each)));
/*     */     }
/* 134 */     Class<?> type = each.getTestClass();
/* 135 */     if (type == null) {
/* 136 */       throw new RuntimeException("Can't build a runner from description [" + each + "]");
/*     */     }
/* 138 */     String methodName = each.getMethodName();
/* 139 */     if (methodName == null) {
/* 140 */       return Request.aClass(type).getRunner();
/*     */     }
/* 142 */     return Request.method(type, methodName).getRunner();
/*     */   }
/*     */   
/*     */   private Class<?> getMalformedTestClass(Description each) {
/*     */     try {
/* 147 */       return Class.forName(each.toString().replace("malformed JUnit 3 test class: ", ""));
/* 148 */     } catch (ClassNotFoundException e) {
/* 149 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 159 */   public List<Description> sortedLeavesForTest(Request request) { return findLeaves(sortRequest(request)); }
/*     */ 
/*     */   
/*     */   private List<Description> findLeaves(Request request) {
/* 163 */     List<Description> results = new ArrayList<Description>();
/* 164 */     findLeaves(null, request.getRunner().getDescription(), results);
/* 165 */     return results;
/*     */   }
/*     */   
/*     */   private void findLeaves(Description parent, Description description, List<Description> results) {
/* 169 */     if (description.getChildren().isEmpty()) {
/* 170 */       if (description.toString().equals("warning(junit.framework.TestSuite$1)")) {
/* 171 */         results.add(Description.createSuiteDescription("malformed JUnit 3 test class: " + parent, new java.lang.annotation.Annotation[0]));
/*     */       } else {
/* 173 */         results.add(description);
/*     */       } 
/*     */     } else {
/* 176 */       for (Description each : description.getChildren())
/* 177 */         findLeaves(description, each, results); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\experimental\max\MaxCore.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
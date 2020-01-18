/*     */ package org.junit.runner;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import org.junit.internal.builders.AllDefaultPossibilitiesBuilder;
/*     */ import org.junit.internal.requests.ClassRequest;
/*     */ import org.junit.internal.requests.FilterRequest;
/*     */ import org.junit.internal.requests.SortingRequest;
/*     */ import org.junit.internal.runners.ErrorReportingRunner;
/*     */ import org.junit.runner.manipulation.Filter;
/*     */ import org.junit.runners.model.InitializationError;
/*     */ import org.junit.runners.model.RunnerBuilder;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Request
/*     */ {
/*     */   public static Request method(Class<?> clazz, String methodName) {
/*  38 */     Description method = Description.createTestDescription(clazz, methodName);
/*  39 */     return aClass(clazz).filterWith(method);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  50 */   public static Request aClass(Class<?> clazz) { return (Request)new ClassRequest(clazz); }
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
/*  61 */   public static Request classWithoutSuiteMethod(Class<?> clazz) { return (Request)new ClassRequest(clazz, false); }
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
/*     */   public static Request classes(Computer computer, Class<?>... classes) {
/*     */     try {
/*  74 */       AllDefaultPossibilitiesBuilder builder = new AllDefaultPossibilitiesBuilder(true);
/*  75 */       Runner suite = computer.getSuite((RunnerBuilder)builder, classes);
/*  76 */       return runner(suite);
/*  77 */     } catch (InitializationError e) {
/*  78 */       throw new RuntimeException("Bug in saff's brain: Suite constructor, called as above, should always complete");
/*     */     } 
/*     */   }
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
/*  91 */   public static Request classes(Class<?>... classes) { return classes(JUnitCore.defaultComputer(), classes); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   public static Request errorReport(Class<?> klass, Throwable cause) { return runner((Runner)new ErrorReportingRunner(klass, cause)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Request runner(final Runner runner) {
/* 108 */     return new Request()
/*     */       {
/*     */         public Runner getRunner() {
/* 111 */           return runner;
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract Runner getRunner();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 131 */   public Request filterWith(Filter filter) { return (Request)new FilterRequest(this, filter); }
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
/* 142 */   public Request filterWith(Description desiredDescription) { return filterWith(Filter.matchMethodDescription(desiredDescription)); }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 168 */   public Request sortWith(Comparator<Description> comparator) { return (Request)new SortingRequest(this, comparator); }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\runner\Request.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
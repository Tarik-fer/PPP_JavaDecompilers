/*     */ package org.junit.runners;
/*     */ 
/*     */ import java.lang.annotation.ElementType;
/*     */ import java.lang.annotation.Inherited;
/*     */ import java.lang.annotation.Retention;
/*     */ import java.lang.annotation.RetentionPolicy;
/*     */ import java.lang.annotation.Target;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.junit.internal.builders.AllDefaultPossibilitiesBuilder;
/*     */ import org.junit.runner.Description;
/*     */ import org.junit.runner.Runner;
/*     */ import org.junit.runner.notification.RunNotifier;
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
/*     */ public class Suite
/*     */   extends ParentRunner<Runner>
/*     */ {
/*     */   private final List<Runner> runners;
/*     */   
/*     */   public static Runner emptySuite() {
/*     */     try {
/*  33 */       return new Suite((Class)null, new Class[0]);
/*  34 */     } catch (InitializationError e) {
/*  35 */       throw new RuntimeException("This shouldn't be possible");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Class<?>[] getAnnotatedClasses(Class<?> klass) throws InitializationError {
/*  54 */     SuiteClasses annotation = klass.getAnnotation(SuiteClasses.class);
/*  55 */     if (annotation == null) {
/*  56 */       throw new InitializationError(String.format("class '%s' must have a SuiteClasses annotation", new Object[] { klass.getName() }));
/*     */     }
/*  58 */     return annotation.value();
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
/*  70 */   public Suite(Class<?> klass, RunnerBuilder builder) throws InitializationError { this(builder, klass, getAnnotatedClasses(klass)); }
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
/*  81 */   public Suite(RunnerBuilder builder, Class<?>[] classes) throws InitializationError { this(null, builder.runners(null, classes)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  91 */   protected Suite(Class<?> klass, Class<?>[] suiteClasses) throws InitializationError { this((RunnerBuilder)new AllDefaultPossibilitiesBuilder(true), klass, suiteClasses); }
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
/* 102 */   protected Suite(RunnerBuilder builder, Class<?> klass, Class<?>[] suiteClasses) throws InitializationError { this(klass, builder.runners(klass, suiteClasses)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Suite(Class<?> klass, List<Runner> runners) throws InitializationError {
/* 112 */     super(klass);
/* 113 */     this.runners = Collections.unmodifiableList(runners);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 118 */   protected List<Runner> getChildren() { return this.runners; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 123 */   protected Description describeChild(Runner child) { return child.getDescription(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 128 */   protected void runChild(Runner runner, RunNotifier notifier) { runner.run(notifier); }
/*     */   
/*     */   @Retention(RetentionPolicy.RUNTIME)
/*     */   @Target({ElementType.TYPE})
/*     */   @Inherited
/*     */   public static @interface SuiteClasses {
/*     */     Class<?>[] value();
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\runners\Suite.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
/*     */ package org.junit.rules;
/*     */ 
/*     */ import org.hamcrest.CoreMatchers;
/*     */ import org.hamcrest.Matcher;
/*     */ import org.hamcrest.SelfDescribing;
/*     */ import org.hamcrest.StringDescription;
/*     */ import org.junit.Assert;
/*     */ import org.junit.internal.matchers.ThrowableCauseMatcher;
/*     */ import org.junit.internal.matchers.ThrowableMessageMatcher;
/*     */ import org.junit.runner.Description;
/*     */ import org.junit.runners.model.Statement;
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
/*     */ public class ExpectedException
/*     */   implements TestRule
/*     */ {
/* 112 */   public static ExpectedException none() { return new ExpectedException(); }
/*     */ 
/*     */   
/* 115 */   private final ExpectedExceptionMatcherBuilder matcherBuilder = new ExpectedExceptionMatcherBuilder();
/*     */   
/* 117 */   private String missingExceptionMessage = "Expected test to throw %s";
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
/*     */   @Deprecated
/* 129 */   public ExpectedException handleAssertionErrors() { return this; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/* 139 */   public ExpectedException handleAssumptionViolatedExceptions() { return this; }
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
/*     */   public ExpectedException reportMissingExceptionWithMessage(String message) {
/* 153 */     this.missingExceptionMessage = message;
/* 154 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 159 */   public Statement apply(Statement base, Description description) { return new ExpectedExceptionStatement(base); }
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
/* 173 */   public void expect(Matcher<?> matcher) { this.matcherBuilder.add(matcher); }
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
/* 186 */   public void expect(Class<? extends Throwable> type) { expect(CoreMatchers.instanceOf(type)); }
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
/* 199 */   public void expectMessage(String substring) { expectMessage(CoreMatchers.containsString(substring)); }
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
/* 212 */   public void expectMessage(Matcher<String> matcher) { expect(ThrowableMessageMatcher.hasMessage(matcher)); }
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
/* 226 */   public void expectCause(Matcher<? extends Throwable> expectedCause) { expect(ThrowableCauseMatcher.hasCause(expectedCause)); }
/*     */   
/*     */   private class ExpectedExceptionStatement
/*     */     extends Statement
/*     */   {
/*     */     private final Statement next;
/*     */     
/* 233 */     public ExpectedExceptionStatement(Statement base) { this.next = base; }
/*     */ 
/*     */ 
/*     */     
/*     */     public void evaluate() throws Throwable {
/*     */       try {
/* 239 */         this.next.evaluate();
/* 240 */       } catch (Throwable e) {
/* 241 */         ExpectedException.this.handleException(e);
/*     */         return;
/*     */       } 
/* 244 */       if (ExpectedException.this.isAnyExceptionExpected()) {
/* 245 */         ExpectedException.this.failDueToMissingException();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void handleException(Throwable e) throws Throwable {
/* 251 */     if (isAnyExceptionExpected()) {
/* 252 */       Assert.assertThat(e, this.matcherBuilder.build());
/*     */     } else {
/* 254 */       throw e;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 259 */   private boolean isAnyExceptionExpected() { return this.matcherBuilder.expectsThrowable(); }
/*     */ 
/*     */ 
/*     */   
/* 263 */   private void failDueToMissingException() throws AssertionError { Assert.fail(missingExceptionMessage()); }
/*     */ 
/*     */   
/*     */   private String missingExceptionMessage() {
/* 267 */     String expectation = StringDescription.toString((SelfDescribing)this.matcherBuilder.build());
/* 268 */     return String.format(this.missingExceptionMessage, new Object[] { expectation });
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\rules\ExpectedException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
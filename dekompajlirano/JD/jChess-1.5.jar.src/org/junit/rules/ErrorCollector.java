/*    */ package org.junit.rules;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.concurrent.Callable;
/*    */ import org.hamcrest.Matcher;
/*    */ import org.junit.Assert;
/*    */ import org.junit.runners.model.MultipleFailureException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ErrorCollector
/*    */   extends Verifier
/*    */ {
/* 35 */   private List<Throwable> errors = new ArrayList<Throwable>();
/*    */ 
/*    */ 
/*    */   
/* 39 */   protected void verify() throws Throwable { MultipleFailureException.assertEmpty(this.errors); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 46 */   public void addError(Throwable error) { this.errors.add(error); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 54 */   public <T> void checkThat(T value, Matcher<T> matcher) { checkThat("", value, matcher); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public <T> void checkThat(final String reason, final T value, final Matcher<T> matcher) {
/* 63 */     checkSucceeds(new Callable() {
/*    */           public Object call() throws Exception {
/* 65 */             Assert.assertThat(reason, value, matcher);
/* 66 */             return value;
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public <T> T checkSucceeds(Callable<T> callable) {
/*    */     try {
/* 78 */       return callable.call();
/* 79 */     } catch (Throwable e) {
/* 80 */       addError(e);
/* 81 */       return null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\rules\ErrorCollector.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
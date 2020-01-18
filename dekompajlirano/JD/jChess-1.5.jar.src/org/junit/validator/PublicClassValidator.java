/*    */ package org.junit.validator;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import org.junit.runners.model.TestClass;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PublicClassValidator
/*    */   implements TestClassValidator
/*    */ {
/* 16 */   private static final List<Exception> NO_VALIDATION_ERRORS = Collections.emptyList();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Exception> validateTestClass(TestClass testClass) {
/* 26 */     if (testClass.isPublic()) {
/* 27 */       return NO_VALIDATION_ERRORS;
/*    */     }
/* 29 */     return Collections.singletonList(new Exception("The class " + testClass.getName() + " is not public."));
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\validator\PublicClassValidator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
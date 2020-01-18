/*    */ package org.junit.validator;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import org.junit.runners.model.FrameworkField;
/*    */ import org.junit.runners.model.FrameworkMethod;
/*    */ import org.junit.runners.model.TestClass;
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
/*    */ public abstract class AnnotationValidator
/*    */ {
/* 22 */   private static final List<Exception> NO_VALIDATION_ERRORS = Collections.emptyList();
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
/* 33 */   public List<Exception> validateAnnotatedClass(TestClass testClass) { return NO_VALIDATION_ERRORS; }
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
/* 45 */   public List<Exception> validateAnnotatedField(FrameworkField field) { return NO_VALIDATION_ERRORS; }
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
/* 58 */   public List<Exception> validateAnnotatedMethod(FrameworkMethod method) { return NO_VALIDATION_ERRORS; }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\validator\AnnotationValidator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
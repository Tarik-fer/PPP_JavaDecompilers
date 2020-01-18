/*     */ package org.junit.validator;
/*     */ 
/*     */ import java.lang.annotation.Annotation;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.junit.runners.model.Annotatable;
/*     */ import org.junit.runners.model.FrameworkField;
/*     */ import org.junit.runners.model.FrameworkMethod;
/*     */ import org.junit.runners.model.TestClass;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class AnnotationsValidator
/*     */   implements TestClassValidator
/*     */ {
/*  22 */   private static final List<AnnotatableValidator<?>> VALIDATORS = Arrays.asList((AnnotatableValidator<?>[])new AnnotatableValidator[] { new ClassValidator(), new MethodValidator(), new FieldValidator() });
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
/*     */   public List<Exception> validateTestClass(TestClass testClass) {
/*  34 */     List<Exception> validationErrors = new ArrayList<Exception>();
/*  35 */     for (AnnotatableValidator<?> validator : VALIDATORS) {
/*  36 */       List<Exception> additionalErrors = validator.validateTestClass(testClass);
/*     */       
/*  38 */       validationErrors.addAll(additionalErrors);
/*     */     } 
/*  40 */     return validationErrors;
/*     */   }
/*     */   
/*     */   private static abstract class AnnotatableValidator<T extends Annotatable> {
/*  44 */     private static final AnnotationValidatorFactory ANNOTATION_VALIDATOR_FACTORY = new AnnotationValidatorFactory();
/*     */ 
/*     */ 
/*     */     
/*     */     private AnnotatableValidator() {}
/*     */ 
/*     */     
/*     */     public List<Exception> validateTestClass(TestClass testClass) {
/*  52 */       List<Exception> validationErrors = new ArrayList<Exception>();
/*  53 */       for (Annotatable annotatable1 : getAnnotatablesForTestClass(testClass)) {
/*  54 */         List<Exception> additionalErrors = validateAnnotatable((T)annotatable1);
/*  55 */         validationErrors.addAll(additionalErrors);
/*     */       } 
/*  57 */       return validationErrors;
/*     */     }
/*     */     abstract Iterable<T> getAnnotatablesForTestClass(TestClass param1TestClass);
/*     */     private List<Exception> validateAnnotatable(T annotatable) {
/*  61 */       List<Exception> validationErrors = new ArrayList<Exception>();
/*  62 */       for (Annotation annotation : annotatable.getAnnotations()) {
/*  63 */         Class<? extends Annotation> annotationType = annotation.annotationType();
/*     */         
/*  65 */         ValidateWith validateWith = annotationType.getAnnotation(ValidateWith.class);
/*     */         
/*  67 */         if (validateWith != null) {
/*  68 */           AnnotationValidator annotationValidator = ANNOTATION_VALIDATOR_FACTORY.createAnnotationValidator(validateWith);
/*     */           
/*  70 */           List<Exception> errors = validateAnnotatable(annotationValidator, annotatable);
/*     */           
/*  72 */           validationErrors.addAll(errors);
/*     */         } 
/*     */       } 
/*  75 */       return validationErrors;
/*     */     }
/*     */     
/*     */     abstract List<Exception> validateAnnotatable(AnnotationValidator param1AnnotationValidator, T param1T);
/*     */   }
/*     */   
/*     */   private static class ClassValidator extends AnnotatableValidator<TestClass> {
/*  82 */     Iterable<TestClass> getAnnotatablesForTestClass(TestClass testClass) { return Collections.singletonList(testClass); }
/*     */ 
/*     */     
/*     */     private ClassValidator() {}
/*     */ 
/*     */     
/*  88 */     List<Exception> validateAnnotatable(AnnotationValidator validator, TestClass testClass) { return validator.validateAnnotatedClass(testClass); }
/*     */   }
/*     */ 
/*     */   
/*     */   private static class MethodValidator
/*     */     extends AnnotatableValidator<FrameworkMethod>
/*     */   {
/*     */     private MethodValidator() {}
/*     */     
/*  97 */     Iterable<FrameworkMethod> getAnnotatablesForTestClass(TestClass testClass) { return testClass.getAnnotatedMethods(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 103 */     List<Exception> validateAnnotatable(AnnotationValidator validator, FrameworkMethod method) { return validator.validateAnnotatedMethod(method); }
/*     */   }
/*     */   
/*     */   private static class FieldValidator
/*     */     extends AnnotatableValidator<FrameworkField>
/*     */   {
/*     */     private FieldValidator() {}
/*     */     
/* 111 */     Iterable<FrameworkField> getAnnotatablesForTestClass(TestClass testClass) { return testClass.getAnnotatedFields(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 117 */     List<Exception> validateAnnotatable(AnnotationValidator validator, FrameworkField field) { return validator.validateAnnotatedField(field); }
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\validator\AnnotationsValidator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
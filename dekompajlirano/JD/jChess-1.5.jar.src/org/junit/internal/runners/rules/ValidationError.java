/*   */ package org.junit.internal.runners.rules;
/*   */ 
/*   */ import java.lang.annotation.Annotation;
/*   */ import org.junit.runners.model.FrameworkMember;
/*   */ 
/*   */ class ValidationError
/*   */   extends Exception
/*   */ {
/* 9 */   public ValidationError(FrameworkMember<?> member, Class<? extends Annotation> annotation, String suffix) { super(String.format("The @%s '%s' %s", new Object[] { annotation.getSimpleName(), member.getName(), suffix })); }
/*   */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\internal\runners\rules\ValidationError.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
/*    */ package org.junit.internal.runners.model;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.junit.runners.model.MultipleFailureException;
/*    */ 
/*    */ @Deprecated
/*    */ public class MultipleFailureException extends MultipleFailureException {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/* 10 */   public MultipleFailureException(List<Throwable> errors) { super(errors); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\internal\runners\model\MultipleFailureException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
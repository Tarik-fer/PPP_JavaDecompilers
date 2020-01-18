/*    */ package org.apache.log4j.xml;
/*    */ 
/*    */ import org.apache.log4j.helpers.LogLog;
/*    */ import org.xml.sax.ErrorHandler;
/*    */ import org.xml.sax.SAXParseException;
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
/*    */ public class SAXErrorHandler
/*    */   implements ErrorHandler
/*    */ {
/* 28 */   public void error(SAXParseException ex) { emitMessage("Continuable parsing error ", ex); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 33 */   public void fatalError(SAXParseException ex) { emitMessage("Fatal parsing error ", ex); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 38 */   public void warning(SAXParseException ex) { emitMessage("Parsing warning ", ex); }
/*    */ 
/*    */   
/*    */   private static void emitMessage(String msg, SAXParseException ex) {
/* 42 */     LogLog.warn(msg + ex.getLineNumber() + " and column " + ex.getColumnNumber());
/*    */     
/* 44 */     LogLog.warn(ex.getMessage(), ex.getException());
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\log4j\xml\SAXErrorHandler.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.2
 */
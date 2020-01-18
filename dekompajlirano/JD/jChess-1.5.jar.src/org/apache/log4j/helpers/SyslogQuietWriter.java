/*    */ package org.apache.log4j.helpers;
/*    */ 
/*    */ import java.io.Writer;
/*    */ import org.apache.log4j.spi.ErrorHandler;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SyslogQuietWriter
/*    */   extends QuietWriter
/*    */ {
/*    */   int syslogFacility;
/*    */   int level;
/*    */   
/*    */   public SyslogQuietWriter(Writer writer, int syslogFacility, ErrorHandler eh) {
/* 38 */     super(writer, eh);
/* 39 */     this.syslogFacility = syslogFacility;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 44 */   public void setLevel(int level) { this.level = level; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 49 */   public void setSyslogFacility(int syslogFacility) { this.syslogFacility = syslogFacility; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 54 */   public void write(String string) { super.write("<" + (this.syslogFacility | this.level) + ">" + string); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\log4j\helpers\SyslogQuietWriter.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.2
 */
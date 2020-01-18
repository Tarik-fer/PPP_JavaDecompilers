/*     */ package org.apache.log4j;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import org.apache.log4j.helpers.LogLog;
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
/*     */ public class ConsoleAppender
/*     */   extends WriterAppender
/*     */ {
/*     */   public static final String SYSTEM_OUT = "System.out";
/*     */   public static final String SYSTEM_ERR = "System.err";
/*  37 */   protected String target = "System.out";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean follow = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConsoleAppender() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   public ConsoleAppender(Layout layout) { this(layout, "System.out"); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConsoleAppender(Layout layout, String target) {
/*  66 */     setLayout(layout);
/*  67 */     setTarget(target);
/*  68 */     activateOptions();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTarget(String value) {
/*  78 */     String v = value.trim();
/*     */     
/*  80 */     if ("System.out".equalsIgnoreCase(v)) {
/*  81 */       this.target = "System.out";
/*  82 */     } else if ("System.err".equalsIgnoreCase(v)) {
/*  83 */       this.target = "System.err";
/*     */     } else {
/*  85 */       targetWarn(value);
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
/*  97 */   public String getTarget() { return this.target; }
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
/* 108 */   public final void setFollow(boolean newValue) { this.follow = newValue; }
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
/* 119 */   public final boolean getFollow() { return this.follow; }
/*     */ 
/*     */   
/*     */   void targetWarn(String val) {
/* 123 */     LogLog.warn("[" + val + "] should be System.out or System.err.");
/* 124 */     LogLog.warn("Using previously set target, System.out by default.");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void activateOptions() {
/* 131 */     if (this.follow) {
/* 132 */       if (this.target.equals("System.err")) {
/* 133 */         setWriter(createWriter(new SystemErrStream()));
/*     */       } else {
/* 135 */         setWriter(createWriter(new SystemOutStream()));
/*     */       }
/*     */     
/* 138 */     } else if (this.target.equals("System.err")) {
/* 139 */       setWriter(createWriter(System.err));
/*     */     } else {
/* 141 */       setWriter(createWriter(System.out));
/*     */     } 
/*     */ 
/*     */     
/* 145 */     super.activateOptions();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final void closeWriter() {
/* 154 */     if (this.follow) {
/* 155 */       super.closeWriter();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class SystemErrStream
/*     */     extends OutputStream
/*     */   {
/*     */     public void close() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 173 */     public void flush() { System.err.flush(); }
/*     */ 
/*     */ 
/*     */     
/* 177 */     public void write(byte[] b) throws IOException { System.err.write(b); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 182 */     public void write(byte[] b, int off, int len) throws IOException { System.err.write(b, off, len); }
/*     */ 
/*     */ 
/*     */     
/* 186 */     public void write(int b) throws IOException { System.err.write(b); }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class SystemOutStream
/*     */     extends OutputStream
/*     */   {
/*     */     public void close() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 203 */     public void flush() { System.out.flush(); }
/*     */ 
/*     */ 
/*     */     
/* 207 */     public void write(byte[] b) throws IOException { System.out.write(b); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 212 */     public void write(byte[] b, int off, int len) throws IOException { System.out.write(b, off, len); }
/*     */ 
/*     */ 
/*     */     
/* 216 */     public void write(int b) throws IOException { System.out.write(b); }
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\log4j\ConsoleAppender.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.2
 */
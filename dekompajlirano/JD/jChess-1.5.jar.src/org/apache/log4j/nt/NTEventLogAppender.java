/*     */ package org.apache.log4j.nt;
/*     */ 
/*     */ import org.apache.log4j.AppenderSkeleton;
/*     */ import org.apache.log4j.Layout;
/*     */ import org.apache.log4j.TTCCLayout;
/*     */ import org.apache.log4j.helpers.LogLog;
/*     */ import org.apache.log4j.spi.LoggingEvent;
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
/*     */ public class NTEventLogAppender
/*     */   extends AppenderSkeleton
/*     */ {
/*  42 */   private int _handle = 0;
/*     */   
/*  44 */   private String source = null;
/*  45 */   private String server = null;
/*     */ 
/*     */ 
/*     */   
/*  49 */   public NTEventLogAppender() { this(null, null, null); }
/*     */ 
/*     */ 
/*     */   
/*  53 */   public NTEventLogAppender(String source) { this(null, source, null); }
/*     */ 
/*     */ 
/*     */   
/*  57 */   public NTEventLogAppender(String server, String source) { this(server, source, null); }
/*     */ 
/*     */ 
/*     */   
/*  61 */   public NTEventLogAppender(Layout layout) { this(null, null, layout); }
/*     */ 
/*     */ 
/*     */   
/*  65 */   public NTEventLogAppender(String source, Layout layout) { this(null, source, layout); }
/*     */ 
/*     */   
/*     */   public NTEventLogAppender(String server, String source, Layout layout) {
/*  69 */     if (source == null) {
/*  70 */       source = "Log4j";
/*     */     }
/*  72 */     if (layout == null) {
/*  73 */       this.layout = (Layout)new TTCCLayout();
/*     */     } else {
/*  75 */       this.layout = layout;
/*     */     } 
/*     */     
/*     */     try {
/*  79 */       this._handle = registerEventSource(server, source);
/*  80 */     } catch (Exception e) {
/*  81 */       e.printStackTrace();
/*  82 */       this._handle = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void activateOptions() {
/*  93 */     if (this.source != null) {
/*     */       try {
/*  95 */         this._handle = registerEventSource(this.server, this.source);
/*  96 */       } catch (Exception e) {
/*  97 */         LogLog.error("Could not register event source.", e);
/*  98 */         this._handle = 0;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void append(LoggingEvent event) {
/* 106 */     StringBuffer sbuf = new StringBuffer();
/*     */     
/* 108 */     sbuf.append(this.layout.format(event));
/* 109 */     if (this.layout.ignoresThrowable()) {
/* 110 */       String[] s = event.getThrowableStrRep();
/* 111 */       if (s != null) {
/* 112 */         int len = s.length;
/* 113 */         for (int i = 0; i < len; i++) {
/* 114 */           sbuf.append(s[i]);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 119 */     int nt_category = event.getLevel().toInt();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 125 */     reportEvent(this._handle, sbuf.toString(), nt_category);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void finalize() {
/* 131 */     deregisterEventSource(this._handle);
/* 132 */     this._handle = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 141 */   public void setSource(String source) { this.source = source.trim(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 146 */   public String getSource() { return this.source; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 154 */   public boolean requiresLayout() { return true; }
/*     */ 
/*     */ 
/*     */   
/*     */   private native int registerEventSource(String paramString1, String paramString2);
/*     */ 
/*     */   
/*     */   static  {
/*     */     String[] arrayOfString;
/*     */     try {
/* 164 */       arrayOfString = new String[] { System.getProperty("os.arch") };
/* 165 */     } catch (SecurityException e) {
/* 166 */       arrayOfString = new String[] { "amd64", "ia64", "x86" };
/*     */     } 
/* 168 */     boolean loaded = false;
/* 169 */     for (int i = 0; i < arrayOfString.length; i++) {
/*     */       try {
/* 171 */         System.loadLibrary("NTEventLogAppender." + arrayOfString[i]);
/* 172 */         loaded = true;
/*     */         break;
/* 174 */       } catch (UnsatisfiedLinkError e) {
/* 175 */         loaded = false;
/*     */       } 
/*     */     } 
/* 178 */     if (!loaded)
/* 179 */       System.loadLibrary("NTEventLogAppender"); 
/*     */   }
/*     */   
/*     */   private native void reportEvent(int paramInt1, String paramString, int paramInt2);
/*     */   
/*     */   private native void deregisterEventSource(int paramInt);
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\log4j\nt\NTEventLogAppender.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.2
 */
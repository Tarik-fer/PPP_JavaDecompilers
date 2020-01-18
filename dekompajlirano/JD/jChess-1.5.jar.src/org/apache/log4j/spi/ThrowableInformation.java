/*    */ package org.apache.log4j.spi;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.log4j.Category;
/*    */ import org.apache.log4j.DefaultThrowableRenderer;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ThrowableInformation
/*    */   implements Serializable
/*    */ {
/*    */   static final long serialVersionUID = -4748765566864322735L;
/*    */   private transient Throwable throwable;
/*    */   private transient Category category;
/*    */   private String[] rep;
/*    */   
/* 46 */   public ThrowableInformation(Throwable throwable) { this.throwable = throwable; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ThrowableInformation(Throwable throwable, Category category) {
/* 56 */     this.throwable = throwable;
/* 57 */     this.category = category;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ThrowableInformation(String[] r) {
/* 66 */     if (r != null) {
/* 67 */       this.rep = (String[])r.clone();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 74 */   public Throwable getThrowable() { return this.throwable; }
/*    */ 
/*    */   
/*    */   public synchronized String[] getThrowableStrRep() {
/* 78 */     if (this.rep == null) {
/* 79 */       ThrowableRenderer renderer = null;
/* 80 */       if (this.category != null) {
/* 81 */         LoggerRepository repo = this.category.getLoggerRepository();
/* 82 */         if (repo instanceof ThrowableRendererSupport) {
/* 83 */           renderer = ((ThrowableRendererSupport)repo).getThrowableRenderer();
/*    */         }
/*    */       } 
/* 86 */       if (renderer == null) {
/* 87 */         this.rep = DefaultThrowableRenderer.render(this.throwable);
/*    */       } else {
/* 89 */         this.rep = renderer.doRender(this.throwable);
/*    */       } 
/*    */     } 
/* 92 */     return (String[])this.rep.clone();
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\log4j\spi\ThrowableInformation.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.2
 */
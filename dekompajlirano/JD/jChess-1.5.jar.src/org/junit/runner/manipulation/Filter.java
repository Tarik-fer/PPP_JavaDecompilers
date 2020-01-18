/*     */ package org.junit.runner.manipulation;
/*     */ 
/*     */ import org.junit.runner.Description;
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
/*     */ public abstract class Filter
/*     */ {
/*  21 */   public static final Filter ALL = new Filter()
/*     */     {
/*     */       public boolean shouldRun(Description description) {
/*  24 */         return true;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*  29 */       public String describe() { return "all tests"; }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       public void apply(Object child) throws NoTestsRemainException {}
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  39 */       public Filter intersect(Filter second) { return second; }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Filter matchMethodDescription(final Description desiredDescription) {
/*  48 */     return new Filter()
/*     */       {
/*     */         public boolean shouldRun(Description description) {
/*  51 */           if (description.isTest()) {
/*  52 */             return desiredDescription.equals(description);
/*     */           }
/*     */ 
/*     */           
/*  56 */           for (Description each : description.getChildren()) {
/*  57 */             if (shouldRun(each)) {
/*  58 */               return true;
/*     */             }
/*     */           } 
/*  61 */           return false;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*  66 */         public String describe() { return String.format("Method %s", new Object[] { this.val$desiredDescription.getDisplayName() }); }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract boolean shouldRun(Description paramDescription);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract String describe();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void apply(Object child) throws NoTestsRemainException {
/*  93 */     if (!(child instanceof Filterable)) {
/*     */       return;
/*     */     }
/*  96 */     Filterable filterable = (Filterable)child;
/*  97 */     filterable.filter(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Filter intersect(final Filter second) {
/* 105 */     if (second == this || second == ALL) {
/* 106 */       return this;
/*     */     }
/* 108 */     final Filter first = this;
/* 109 */     return new Filter()
/*     */       {
/*     */         public boolean shouldRun(Description description) {
/* 112 */           return (first.shouldRun(description) && second.shouldRun(description));
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 118 */         public String describe() { return first.describe() + " and " + second.describe(); }
/*     */       };
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\runner\manipulation\Filter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
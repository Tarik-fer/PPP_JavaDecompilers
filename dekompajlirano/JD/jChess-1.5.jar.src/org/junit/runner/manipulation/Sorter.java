/*    */ package org.junit.runner.manipulation;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import org.junit.runner.Description;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Sorter
/*    */   implements Comparator<Description>
/*    */ {
/* 17 */   public static final Sorter NULL = new Sorter(new Comparator<Description>()
/*    */       {
/* 19 */         public int compare(Description o1, Description o2) { return 0; }
/*    */       });
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private final Comparator<Description> comparator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 32 */   public Sorter(Comparator<Description> comparator) { this.comparator = comparator; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void apply(Object object) {
/* 39 */     if (object instanceof Sortable) {
/* 40 */       Sortable sortable = (Sortable)object;
/* 41 */       sortable.sort(this);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/* 46 */   public int compare(Description o1, Description o2) { return this.comparator.compare(o1, o2); }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\runner\manipulation\Sorter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
/*    */ package org.apache.log4j.lf5.viewer.categoryexplorer;
/*    */ 
/*    */ import java.util.Enumeration;
/*    */ import javax.swing.tree.TreeNode;
/*    */ import org.apache.log4j.lf5.LogRecord;
/*    */ import org.apache.log4j.lf5.LogRecordFilter;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CategoryExplorerLogRecordFilter
/*    */   implements LogRecordFilter
/*    */ {
/*    */   protected CategoryExplorerModel _model;
/*    */   
/* 52 */   public CategoryExplorerLogRecordFilter(CategoryExplorerModel model) { this._model = model; }
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
/*    */   public boolean passes(LogRecord record) {
/* 66 */     CategoryPath path = new CategoryPath(record.getCategory());
/* 67 */     return this._model.isCategoryPathActive(path);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 74 */   public void reset() { resetAllNodes(); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void resetAllNodes() {
/* 82 */     Enumeration nodes = this._model.getRootCategoryNode().depthFirstEnumeration();
/*    */     
/* 84 */     while (nodes.hasMoreElements()) {
/* 85 */       CategoryNode current = (CategoryNode)nodes.nextElement();
/* 86 */       current.resetNumberOfContainedRecords();
/* 87 */       this._model.nodeChanged(current);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\log4j\lf5\viewer\categoryexplorer\CategoryExplorerLogRecordFilter.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.2
 */
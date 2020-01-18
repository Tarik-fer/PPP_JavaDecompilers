/*     */ package org.apache.log4j.lf5.viewer.categoryexplorer;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import javax.swing.tree.DefaultMutableTreeNode;
/*     */ import javax.swing.tree.TreeNode;
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
/*     */ public class CategoryNode
/*     */   extends DefaultMutableTreeNode
/*     */ {
/*     */   private static final long serialVersionUID = 5958994817693177319L;
/*     */   protected boolean _selected = true;
/*  42 */   protected int _numberOfContainedRecords = 0;
/*  43 */   protected int _numberOfRecordsFromChildren = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean _hasFatalChildren = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean _hasFatalRecords = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  59 */   public CategoryNode(String title) { setUserObject(title); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   public String getTitle() { return (String)getUserObject(); }
/*     */ 
/*     */   
/*     */   public void setSelected(boolean s) {
/*  70 */     if (s != this._selected) {
/*  71 */       this._selected = s;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*  76 */   public boolean isSelected() { return this._selected; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAllDescendantsSelected() {
/*  83 */     Enumeration children = children();
/*  84 */     while (children.hasMoreElements()) {
/*  85 */       CategoryNode node = (CategoryNode)children.nextElement();
/*  86 */       node.setSelected(true);
/*  87 */       node.setAllDescendantsSelected();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAllDescendantsDeSelected() {
/*  95 */     Enumeration children = children();
/*  96 */     while (children.hasMoreElements()) {
/*  97 */       CategoryNode node = (CategoryNode)children.nextElement();
/*  98 */       node.setSelected(false);
/*  99 */       node.setAllDescendantsDeSelected();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 104 */   public String toString() { return getTitle(); }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 108 */     if (obj instanceof CategoryNode) {
/* 109 */       CategoryNode node = (CategoryNode)obj;
/* 110 */       String tit1 = getTitle().toLowerCase();
/* 111 */       String tit2 = node.getTitle().toLowerCase();
/*     */       
/* 113 */       if (tit1.equals(tit2)) {
/* 114 */         return true;
/*     */       }
/*     */     } 
/* 117 */     return false;
/*     */   }
/*     */ 
/*     */   
/* 121 */   public int hashCode() { return getTitle().hashCode(); }
/*     */ 
/*     */   
/*     */   public void addRecord() {
/* 125 */     this._numberOfContainedRecords++;
/* 126 */     addRecordToParent();
/*     */   }
/*     */ 
/*     */   
/* 130 */   public int getNumberOfContainedRecords() { return this._numberOfContainedRecords; }
/*     */ 
/*     */   
/*     */   public void resetNumberOfContainedRecords() {
/* 134 */     this._numberOfContainedRecords = 0;
/* 135 */     this._numberOfRecordsFromChildren = 0;
/* 136 */     this._hasFatalRecords = false;
/* 137 */     this._hasFatalChildren = false;
/*     */   }
/*     */ 
/*     */   
/* 141 */   public boolean hasFatalRecords() { return this._hasFatalRecords; }
/*     */ 
/*     */ 
/*     */   
/* 145 */   public boolean hasFatalChildren() { return this._hasFatalChildren; }
/*     */ 
/*     */ 
/*     */   
/* 149 */   public void setHasFatalRecords(boolean flag) { this._hasFatalRecords = flag; }
/*     */ 
/*     */ 
/*     */   
/* 153 */   public void setHasFatalChildren(boolean flag) { this._hasFatalChildren = flag; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 161 */   protected int getTotalNumberOfRecords() { return getNumberOfRecordsFromChildren() + getNumberOfContainedRecords(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addRecordFromChild() {
/* 168 */     this._numberOfRecordsFromChildren++;
/* 169 */     addRecordToParent();
/*     */   }
/*     */ 
/*     */   
/* 173 */   protected int getNumberOfRecordsFromChildren() { return this._numberOfRecordsFromChildren; }
/*     */ 
/*     */   
/*     */   protected void addRecordToParent() {
/* 177 */     TreeNode parent = getParent();
/* 178 */     if (parent == null) {
/*     */       return;
/*     */     }
/* 181 */     ((CategoryNode)parent).addRecordFromChild();
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\log4j\lf5\viewer\categoryexplorer\CategoryNode.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.2
 */
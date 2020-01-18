/*    */ package pl.art.lach.mateusz.javaopenchess.core.data_transfer;
/*    */ 
/*    */ import pl.art.lach.mateusz.javaopenchess.core.data_transfer.implementations.FenNotation;
/*    */ import pl.art.lach.mateusz.javaopenchess.core.data_transfer.implementations.PGNNotation;
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
/*    */ public class DataTransferFactory
/*    */ {
/*    */   public static DataExporter getExporterInstance(TransferFormat format) {
/* 29 */     switch (format) {
/*    */       
/*    */       case FEN:
/* 32 */         return (DataExporter)new FenNotation();
/*    */       case PGN:
/* 34 */         return (DataExporter)new PGNNotation();
/*    */     } 
/* 36 */     return (DataExporter)new FenNotation();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static DataImporter getImporterInstance(TransferFormat format) {
/* 42 */     switch (format) {
/*    */       
/*    */       case FEN:
/* 45 */         return (DataImporter)new FenNotation();
/*    */       case PGN:
/* 47 */         return (DataImporter)new PGNNotation();
/*    */     } 
/* 49 */     return (DataImporter)new FenNotation();
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\core\data_transfer\DataTransferFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */
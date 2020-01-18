/*    */ package pl.art.lach.mateusz.javaopenchess.utils;
/*    */ 
/*    */ import java.math.BigInteger;
/*    */ import java.security.MessageDigest;
/*    */ import java.security.NoSuchAlgorithmException;
/*    */ import org.apache.log4j.Logger;
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
/*    */ public class MD5
/*    */ {
/* 31 */   private static final Logger LOG = Logger.getLogger(MD5.class);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String encrypt(String str) {
/*    */     try {
/* 39 */       MessageDigest message = MessageDigest.getInstance("MD5");
/* 40 */       message.update(str.getBytes(), 0, str.length());
/* 41 */       return (new BigInteger(1, message.digest())).toString(16);
/*    */     }
/* 43 */     catch (NoSuchAlgorithmException ex) {
/*    */       
/* 45 */       LOG.error("NoSuchAlgorithmException: " + ex);
/* 46 */       return null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenches\\utils\MD5.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */
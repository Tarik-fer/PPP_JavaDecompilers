/*    */ package pl.art.lach.mateusz.javaopenchess;
/*    */ 
/*    */ import java.awt.Window;
/*    */ import java.util.Properties;
/*    */ import org.apache.log4j.PropertyConfigurator;
/*    */ import org.jdesktop.application.Application;
/*    */ import org.jdesktop.application.SingleFrameApplication;
/*    */ import org.jdesktop.application.View;
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
/*    */ public class JChessApp
/*    */   extends SingleFrameApplication
/*    */ {
/*    */   protected static JChessView javaChessView;
/*    */   public static final String LOG_FILE = "log4j.properties";
/* 35 */   public static final String MAIN_PACKAGE_NAME = JChessApp.class.getPackage().getName();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 42 */   public static JChessView getJavaChessView() { return javaChessView; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void startup() {
/* 51 */     javaChessView = new JChessView(this);
/* 52 */     show((View)getJavaChessView());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void configureWindow(Window root) {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 69 */   public static JChessApp getApplication() { return (JChessApp)Application.getInstance(JChessApp.class); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void main(String[] args) {
/* 78 */     launch(JChessApp.class, args);
/* 79 */     Properties logProp = new Properties();
/*    */     
/*    */     try {
/* 82 */       logProp.load(JChessApp.class.getClassLoader().getResourceAsStream("log4j.properties"));
/* 83 */       PropertyConfigurator.configure(logProp);
/*    */     }
/* 85 */     catch (NullPointerException|java.io.IOException e) {
/*    */       
/* 87 */       System.err.println("Logging not enabled : " + e.getMessage());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\JChessApp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */
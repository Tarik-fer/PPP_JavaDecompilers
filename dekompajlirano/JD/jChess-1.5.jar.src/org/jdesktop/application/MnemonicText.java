/*     */ package org.jdesktop.application;
/*     */ 
/*     */ import java.text.StringCharacterIterator;
/*     */ import javax.swing.AbstractButton;
/*     */ import javax.swing.Action;
/*     */ import javax.swing.JLabel;
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
/*     */ class MnemonicText
/*     */ {
/*     */   private static final String DISPLAYED_MNEMONIC_INDEX_KEY = "SwingDisplayedMnemonicIndexKey";
/*     */   
/*     */   public static void configure(Object paramObject, String paramString) {
/*  38 */     String str = paramString;
/*  39 */     int i = -1;
/*  40 */     int j = 0;
/*     */     
/*  42 */     int k = mnemonicMarkerIndex(paramString, '&');
/*  43 */     if (k == -1) {
/*  44 */       k = mnemonicMarkerIndex(paramString, '_');
/*     */     }
/*  46 */     if (k != -1) {
/*  47 */       str = str.substring(0, k) + str.substring(k + 1);
/*  48 */       i = k;
/*  49 */       StringCharacterIterator stringCharacterIterator = new StringCharacterIterator(paramString, k);
/*  50 */       j = mnemonicKey(stringCharacterIterator.next());
/*     */     } 
/*  52 */     if (paramObject instanceof Action) {
/*  53 */       configureAction((Action)paramObject, str, j, i);
/*     */     }
/*  55 */     else if (paramObject instanceof AbstractButton) {
/*  56 */       configureButton((AbstractButton)paramObject, str, j, i);
/*     */     }
/*  58 */     else if (paramObject instanceof JLabel) {
/*  59 */       configureLabel((JLabel)paramObject, str, j, i);
/*     */     } else {
/*     */       
/*  62 */       throw new IllegalArgumentException("unrecognized target type " + paramObject);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static int mnemonicMarkerIndex(String paramString, char paramChar) {
/*  67 */     if (paramString == null || paramString.length() < 2) return -1; 
/*  68 */     StringCharacterIterator stringCharacterIterator = new StringCharacterIterator(paramString);
/*  69 */     int i = 0;
/*  70 */     while (i != -1) {
/*  71 */       i = paramString.indexOf(paramChar, i);
/*  72 */       if (i != -1) {
/*  73 */         stringCharacterIterator.setIndex(i);
/*  74 */         char c1 = stringCharacterIterator.previous();
/*  75 */         stringCharacterIterator.setIndex(i);
/*  76 */         char c2 = stringCharacterIterator.next();
/*  77 */         boolean bool = (c1 == '\'' && c2 == '\'') ? true : false;
/*  78 */         boolean bool1 = Character.isWhitespace(c2);
/*  79 */         if (!bool && !bool1 && c2 != Character.MAX_VALUE) {
/*  80 */           return i;
/*     */         }
/*     */       } 
/*  83 */       if (i != -1) i++; 
/*     */     } 
/*  85 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int mnemonicKey(char paramChar) {
/*  94 */     char c = paramChar;
/*  95 */     if (c >= 'a' && c <= 'z') {
/*  96 */       c -= ' ';
/*     */     }
/*  98 */     return c;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void configureAction(Action paramAction, String paramString, int paramInt1, int paramInt2) {
/* 108 */     paramAction.putValue("Name", paramString);
/* 109 */     if (paramInt1 != 0) paramAction.putValue("MnemonicKey", Integer.valueOf(paramInt1)); 
/* 110 */     if (paramInt2 != -1) paramAction.putValue("SwingDisplayedMnemonicIndexKey", Integer.valueOf(paramInt2)); 
/*     */   }
/*     */   
/*     */   private static void configureButton(AbstractButton paramAbstractButton, String paramString, int paramInt1, int paramInt2) {
/* 114 */     paramAbstractButton.setText(paramString);
/* 115 */     if (paramInt1 != 0) paramAbstractButton.setMnemonic(paramInt1); 
/* 116 */     if (paramInt2 != -1) paramAbstractButton.setDisplayedMnemonicIndex(paramInt2); 
/*     */   }
/*     */   
/*     */   private static void configureLabel(JLabel paramJLabel, String paramString, int paramInt1, int paramInt2) {
/* 120 */     paramJLabel.setText(paramString);
/* 121 */     if (paramInt1 != 0) paramJLabel.setDisplayedMnemonic(paramInt1); 
/* 122 */     if (paramInt2 != -1) paramJLabel.setDisplayedMnemonicIndex(paramInt2); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\jdesktop\application\MnemonicText.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
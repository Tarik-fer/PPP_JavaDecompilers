/*    */ package org.junit.experimental.theories.internal;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.junit.experimental.theories.ParameterSignature;
/*    */ import org.junit.experimental.theories.ParameterSupplier;
/*    */ import org.junit.experimental.theories.PotentialAssignment;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnumSupplier
/*    */   extends ParameterSupplier
/*    */ {
/*    */   private Class<?> enumType;
/*    */   
/* 15 */   public EnumSupplier(Class<?> enumType) { this.enumType = enumType; }
/*    */   
/*    */   public List<PotentialAssignment> getValueSources(ParameterSignature sig) { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: getfield enumType : Ljava/lang/Class;
/*    */     //   4: invokevirtual getEnumConstants : ()[Ljava/lang/Object;
/*    */     //   7: astore_2
/*    */     //   8: new java/util/ArrayList
/*    */     //   11: dup
/*    */     //   12: invokespecial <init> : ()V
/*    */     //   15: astore_3
/*    */     //   16: aload_2
/*    */     //   17: astore #4
/*    */     //   19: aload #4
/*    */     //   21: arraylength
/*    */     //   22: istore #5
/*    */     //   24: iconst_0
/*    */     //   25: istore #6
/*    */     //   27: iload #6
/*    */     //   29: iload #5
/*    */     //   31: if_icmpge -> 64
/*    */     //   34: aload #4
/*    */     //   36: iload #6
/*    */     //   38: aaload
/*    */     //   39: astore #7
/*    */     //   41: aload_3
/*    */     //   42: aload #7
/*    */     //   44: invokevirtual toString : ()Ljava/lang/String;
/*    */     //   47: aload #7
/*    */     //   49: invokestatic forValue : (Ljava/lang/String;Ljava/lang/Object;)Lorg/junit/experimental/theories/PotentialAssignment;
/*    */     //   52: invokeinterface add : (Ljava/lang/Object;)Z
/*    */     //   57: pop
/*    */     //   58: iinc #6, 1
/*    */     //   61: goto -> 27
/*    */     //   64: aload_3
/*    */     //   65: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #20	-> 0
/*    */     //   #22	-> 8
/*    */     //   #23	-> 16
/*    */     //   #24	-> 41
/*    */     //   #23	-> 58
/*    */     //   #27	-> 64
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   41	17	7	value	Ljava/lang/Object;
/*    */     //   19	45	4	arr$	[Ljava/lang/Object;
/*    */     //   24	40	5	len$	I
/*    */     //   27	37	6	i$	I
/*    */     //   0	66	0	this	Lorg/junit/experimental/theories/internal/EnumSupplier;
/*    */     //   0	66	1	sig	Lorg/junit/experimental/theories/ParameterSignature;
/*    */     //   8	58	2	enumValues	[Ljava/lang/Object;
/*    */     //   16	50	3	assignments	Ljava/util/List;
/*    */     // Local variable type table:
/*    */     //   start	length	slot	name	signature
/*    */     //   16	50	3	assignments	Ljava/util/List<Lorg/junit/experimental/theories/PotentialAssignment;>; }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\experimental\theories\internal\EnumSupplier.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
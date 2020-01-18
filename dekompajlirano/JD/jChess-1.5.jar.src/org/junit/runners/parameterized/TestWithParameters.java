/*    */ package org.junit.runners.parameterized;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import org.junit.runners.model.TestClass;
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
/*    */ public class TestWithParameters
/*    */ {
/*    */   private final String name;
/*    */   private final TestClass testClass;
/*    */   private final List<Object> parameters;
/*    */   
/*    */   public TestWithParameters(String name, TestClass testClass, List<Object> parameters) {
/* 26 */     notNull(name, "The name is missing.");
/* 27 */     notNull(testClass, "The test class is missing.");
/* 28 */     notNull(parameters, "The parameters are missing.");
/* 29 */     this.name = name;
/* 30 */     this.testClass = testClass;
/* 31 */     this.parameters = Collections.unmodifiableList(new ArrayList(parameters));
/*    */   }
/*    */ 
/*    */   
/* 35 */   public String getName() { return this.name; }
/*    */ 
/*    */ 
/*    */   
/* 39 */   public TestClass getTestClass() { return this.testClass; }
/*    */ 
/*    */ 
/*    */   
/* 43 */   public List<Object> getParameters() { return this.parameters; }
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 48 */     int prime = 14747;
/* 49 */     int result = prime + this.name.hashCode();
/* 50 */     result = prime * result + this.testClass.hashCode();
/* 51 */     return prime * result + this.parameters.hashCode();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 56 */     if (this == obj) {
/* 57 */       return true;
/*    */     }
/* 59 */     if (obj == null) {
/* 60 */       return false;
/*    */     }
/* 62 */     if (getClass() != obj.getClass()) {
/* 63 */       return false;
/*    */     }
/* 65 */     TestWithParameters other = (TestWithParameters)obj;
/* 66 */     return (this.name.equals(other.name) && this.parameters.equals(other.parameters) && this.testClass.equals(other.testClass));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 73 */   public String toString() { return this.testClass.getName() + " '" + this.name + "' with parameters " + this.parameters; }
/*    */ 
/*    */ 
/*    */   
/*    */   private static void notNull(Object value, String message) {
/* 78 */     if (value == null)
/* 79 */       throw new NullPointerException(message); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\runners\parameterized\TestWithParameters.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
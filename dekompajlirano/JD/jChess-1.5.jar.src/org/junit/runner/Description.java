/*     */ package org.junit.runner;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.lang.annotation.Annotation;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.concurrent.ConcurrentLinkedQueue;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
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
/*     */ public class Description
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  33 */   private static final Pattern METHOD_AND_CLASS_NAME_PATTERN = Pattern.compile("([\\s\\S]*)\\((.*)\\)");
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
/*  45 */   public static Description createSuiteDescription(String name, Annotation... annotations) { return new Description(null, name, annotations); }
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
/*  58 */   public static Description createSuiteDescription(String name, Serializable uniqueId, Annotation... annotations) { return new Description(null, name, uniqueId, annotations); }
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
/*  73 */   public static Description createTestDescription(String className, String name, Annotation... annotations) { return new Description(null, formatDisplayName(name, className), annotations); }
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
/*  86 */   public static Description createTestDescription(Class<?> clazz, String name, Annotation... annotations) { return new Description(clazz, formatDisplayName(name, clazz.getName()), annotations); }
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
/*  99 */   public static Description createTestDescription(Class<?> clazz, String name) { return new Description(clazz, formatDisplayName(name, clazz.getName()), new Annotation[0]); }
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
/* 110 */   public static Description createTestDescription(String className, String name, Serializable uniqueId) { return new Description(null, formatDisplayName(name, className), uniqueId, new Annotation[0]); }
/*     */ 
/*     */ 
/*     */   
/* 114 */   private static String formatDisplayName(String name, String className) { return String.format("%s(%s)", new Object[] { name, className }); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 124 */   public static Description createSuiteDescription(Class<?> testClass) { return new Description(testClass, testClass.getName(), testClass.getAnnotations()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 130 */   public static final Description EMPTY = new Description(null, "No Tests", new Annotation[0]);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 137 */   public static final Description TEST_MECHANISM = new Description(null, "Test mechanism", new Annotation[0]);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 144 */   private final Collection<Description> fChildren = new ConcurrentLinkedQueue<Description>();
/*     */   
/*     */   private final String fDisplayName;
/*     */   private final Serializable fUniqueId;
/*     */   private final Annotation[] fAnnotations;
/*     */   private volatile Class<?> fTestClass;
/*     */   
/* 151 */   private Description(Class<?> clazz, String displayName, Annotation... annotations) { this(clazz, displayName, displayName, annotations); }
/*     */ 
/*     */   
/*     */   private Description(Class<?> testClass, String displayName, Serializable uniqueId, Annotation... annotations) {
/* 155 */     if (displayName == null || displayName.length() == 0) {
/* 156 */       throw new IllegalArgumentException("The display name must not be empty.");
/*     */     }
/*     */     
/* 159 */     if (uniqueId == null) {
/* 160 */       throw new IllegalArgumentException("The unique id must not be null.");
/*     */     }
/*     */     
/* 163 */     this.fTestClass = testClass;
/* 164 */     this.fDisplayName = displayName;
/* 165 */     this.fUniqueId = uniqueId;
/* 166 */     this.fAnnotations = annotations;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 173 */   public String getDisplayName() { return this.fDisplayName; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 182 */   public void addChild(Description description) { this.fChildren.add(description); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 190 */   public ArrayList<Description> getChildren() { return new ArrayList<Description>(this.fChildren); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 197 */   public boolean isSuite() { return !isTest(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 204 */   public boolean isTest() { return this.fChildren.isEmpty(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int testCount() {
/* 211 */     if (isTest()) {
/* 212 */       return 1;
/*     */     }
/* 214 */     int result = 0;
/* 215 */     for (Description child : this.fChildren) {
/* 216 */       result += child.testCount();
/*     */     }
/* 218 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 223 */   public int hashCode() { return this.fUniqueId.hashCode(); }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 228 */     if (!(obj instanceof Description)) {
/* 229 */       return false;
/*     */     }
/* 231 */     Description d = (Description)obj;
/* 232 */     return this.fUniqueId.equals(d.fUniqueId);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 237 */   public String toString() { return getDisplayName(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 244 */   public boolean isEmpty() { return equals(EMPTY); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 252 */   public Description childlessCopy() { return new Description(this.fTestClass, this.fDisplayName, this.fAnnotations); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Annotation> T getAnnotation(Class<T> annotationType) {
/* 260 */     for (Annotation each : this.fAnnotations) {
/* 261 */       if (each.annotationType().equals(annotationType)) {
/* 262 */         return annotationType.cast(each);
/*     */       }
/*     */     } 
/* 265 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 272 */   public Collection<Annotation> getAnnotations() { return Arrays.asList(this.fAnnotations); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Class<?> getTestClass() {
/* 280 */     if (this.fTestClass != null) {
/* 281 */       return this.fTestClass;
/*     */     }
/* 283 */     String name = getClassName();
/* 284 */     if (name == null) {
/* 285 */       return null;
/*     */     }
/*     */     try {
/* 288 */       this.fTestClass = Class.forName(name, false, getClass().getClassLoader());
/* 289 */       return this.fTestClass;
/* 290 */     } catch (ClassNotFoundException e) {
/* 291 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 300 */   public String getClassName() { return (this.fTestClass != null) ? this.fTestClass.getName() : methodAndClassNamePatternGroupOrDefault(2, toString()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 308 */   public String getMethodName() { return methodAndClassNamePatternGroupOrDefault(1, null); }
/*     */ 
/*     */ 
/*     */   
/*     */   private String methodAndClassNamePatternGroupOrDefault(int group, String defaultString) {
/* 313 */     Matcher matcher = METHOD_AND_CLASS_NAME_PATTERN.matcher(toString());
/* 314 */     return matcher.matches() ? matcher.group(group) : defaultString;
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\runner\Description.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
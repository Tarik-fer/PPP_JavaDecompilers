/*     */ package org.jdesktop.application;
/*     */ 
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.net.URL;
/*     */ import java.text.MessageFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
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
/*     */ public abstract class ResourceConverter
/*     */ {
/*     */   protected final Class type;
/*     */   
/*     */   public abstract Object parseString(String paramString, ResourceMap paramResourceMap) throws ResourceConverterException;
/*     */   
/*  67 */   public String toString(Object paramObject) { return (paramObject == null) ? "null" : paramObject.toString(); }
/*     */ 
/*     */   
/*     */   protected ResourceConverter(Class paramClass) {
/*  71 */     if (paramClass == null) {
/*  72 */       throw new IllegalArgumentException("null type");
/*     */     }
/*  74 */     this.type = paramClass;
/*     */   }
/*  76 */   private ResourceConverter() { this.type = null; }
/*     */ 
/*     */   
/*  79 */   public boolean supportsType(Class paramClass) { return this.type.equals(paramClass); }
/*     */   
/*     */   public static class ResourceConverterException extends Exception {
/*     */     private final String badString;
/*     */     
/*     */     private String maybeShorten(String param1String) {
/*  85 */       int i = param1String.length();
/*  86 */       return (i < 128) ? param1String : (param1String.substring(0, 128) + "...[" + (i - 128) + " more characters]");
/*     */     }
/*     */     
/*     */     public ResourceConverterException(String param1String1, String param1String2, Throwable param1Throwable) {
/*  90 */       super(param1String1, param1Throwable);
/*  91 */       this.badString = maybeShorten(param1String2);
/*     */     }
/*     */     
/*     */     public ResourceConverterException(String param1String1, String param1String2) {
/*  95 */       super(param1String1);
/*  96 */       this.badString = maybeShorten(param1String2);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 100 */       StringBuffer stringBuffer = new StringBuffer(super.toString());
/* 101 */       stringBuffer.append(" string: \"");
/* 102 */       stringBuffer.append(this.badString);
/* 103 */       stringBuffer.append("\"");
/* 104 */       return stringBuffer.toString();
/*     */     }
/*     */   }
/*     */   
/*     */   public static void register(ResourceConverter paramResourceConverter) {
/* 109 */     if (paramResourceConverter == null) {
/* 110 */       throw new IllegalArgumentException("null resourceConverter");
/*     */     }
/* 112 */     resourceConverters.add(paramResourceConverter);
/*     */   }
/*     */   
/*     */   public static ResourceConverter forType(Class paramClass) {
/* 116 */     if (paramClass == null) {
/* 117 */       throw new IllegalArgumentException("null type");
/*     */     }
/* 119 */     for (ResourceConverter resourceConverter : resourceConverters) {
/* 120 */       if (resourceConverter.supportsType(paramClass)) {
/* 121 */         return resourceConverter;
/*     */       }
/*     */     } 
/* 124 */     return null;
/*     */   }
/*     */   
/* 127 */   private static ResourceConverter[] resourceConvertersArray = new ResourceConverter[] { new BooleanResourceConverter(new String[] { "true", "on", "yes" }), new IntegerResourceConverter(), new MessageFormatResourceConverter(), new FloatResourceConverter(), new DoubleResourceConverter(), new LongResourceConverter(), new ShortResourceConverter(), new ByteResourceConverter(), new URLResourceConverter(), new URIResourceConverter() };
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
/* 139 */   private static List<ResourceConverter> resourceConverters = new ArrayList<ResourceConverter>(Arrays.asList(resourceConvertersArray));
/*     */   
/*     */   private static class BooleanResourceConverter
/*     */     extends ResourceConverter {
/*     */     private final String[] trueStrings;
/*     */     
/*     */     BooleanResourceConverter(String... param1VarArgs) {
/* 146 */       super(Boolean.class);
/* 147 */       this.trueStrings = param1VarArgs;
/*     */     }
/*     */     
/*     */     public Object parseString(String param1String, ResourceMap param1ResourceMap) {
/* 151 */       param1String = param1String.trim();
/* 152 */       for (String str : this.trueStrings) {
/* 153 */         if (param1String.equalsIgnoreCase(str)) {
/* 154 */           return Boolean.TRUE;
/*     */         }
/*     */       } 
/* 157 */       return Boolean.FALSE;
/*     */     }
/*     */ 
/*     */     
/* 161 */     public boolean supportsType(Class param1Class) { return (param1Class.equals(Boolean.class) || param1Class.equals(boolean.class)); }
/*     */   }
/*     */   
/*     */   private static abstract class NumberResourceConverter extends ResourceConverter {
/*     */     private final Class primitiveType;
/*     */     
/*     */     NumberResourceConverter(Class param1Class1, Class param1Class2) {
/* 168 */       super(param1Class1);
/* 169 */       this.primitiveType = param1Class2;
/*     */     }
/*     */     
/*     */     protected abstract Number parseString(String param1String) throws NumberFormatException;
/*     */     
/*     */     public Object parseString(String param1String, ResourceMap param1ResourceMap) throws ResourceConverter.ResourceConverterException {
/*     */       try {
/* 176 */         return parseString(param1String);
/*     */       }
/* 178 */       catch (NumberFormatException numberFormatException) {
/* 179 */         throw new ResourceConverter.ResourceConverterException("invalid " + this.type.getSimpleName(), param1String, numberFormatException);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 184 */     public boolean supportsType(Class param1Class) { return (param1Class.equals(this.type) || param1Class.equals(this.primitiveType)); }
/*     */   }
/*     */   
/*     */   private static class FloatResourceConverter
/*     */     extends NumberResourceConverter
/*     */   {
/* 190 */     FloatResourceConverter() { super(Float.class, float.class); }
/*     */ 
/*     */ 
/*     */     
/* 194 */     protected Number parseString(String param1String) throws NumberFormatException { return Float.valueOf(Float.parseFloat(param1String)); }
/*     */   }
/*     */   
/*     */   private static class DoubleResourceConverter
/*     */     extends NumberResourceConverter
/*     */   {
/* 200 */     DoubleResourceConverter() { super(Double.class, double.class); }
/*     */ 
/*     */ 
/*     */     
/* 204 */     protected Number parseString(String param1String) throws NumberFormatException { return Double.valueOf(Double.parseDouble(param1String)); }
/*     */   }
/*     */   
/*     */   private static abstract class INumberResourceConverter extends ResourceConverter {
/*     */     private final Class primitiveType;
/*     */     
/*     */     INumberResourceConverter(Class param1Class1, Class param1Class2) {
/* 211 */       super(param1Class1);
/* 212 */       this.primitiveType = param1Class2;
/*     */     }
/*     */     
/*     */     protected abstract Number parseString(String param1String, int param1Int) throws NumberFormatException;
/*     */     
/*     */     public Object parseString(String param1String, ResourceMap param1ResourceMap) throws ResourceConverter.ResourceConverterException {
/*     */       try {
/* 219 */         String[] arrayOfString = param1String.split("&");
/* 220 */         boolean bool = (arrayOfString.length == 2) ? Integer.parseInt(arrayOfString[1]) : true;
/* 221 */         return parseString(arrayOfString[0], bool);
/*     */       }
/* 223 */       catch (NumberFormatException numberFormatException) {
/* 224 */         throw new ResourceConverter.ResourceConverterException("invalid " + this.type.getSimpleName(), param1String, numberFormatException);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 229 */     public boolean supportsType(Class param1Class) { return (param1Class.equals(this.type) || param1Class.equals(this.primitiveType)); }
/*     */   }
/*     */   
/*     */   private static class ByteResourceConverter
/*     */     extends INumberResourceConverter
/*     */   {
/* 235 */     ByteResourceConverter() { super(Byte.class, byte.class); }
/*     */ 
/*     */ 
/*     */     
/* 239 */     protected Number parseString(String param1String, int param1Int) throws NumberFormatException { return Byte.valueOf((param1Int == -1) ? Byte.decode(param1String).byteValue() : Byte.parseByte(param1String, param1Int)); }
/*     */   }
/*     */   
/*     */   private static class IntegerResourceConverter
/*     */     extends INumberResourceConverter
/*     */   {
/* 245 */     IntegerResourceConverter() { super(Integer.class, int.class); }
/*     */ 
/*     */ 
/*     */     
/* 249 */     protected Number parseString(String param1String, int param1Int) throws NumberFormatException { return Integer.valueOf((param1Int == -1) ? Integer.decode(param1String).intValue() : Integer.parseInt(param1String, param1Int)); }
/*     */   }
/*     */   
/*     */   private static class LongResourceConverter
/*     */     extends INumberResourceConverter
/*     */   {
/* 255 */     LongResourceConverter() { super(Long.class, long.class); }
/*     */ 
/*     */ 
/*     */     
/* 259 */     protected Number parseString(String param1String, int param1Int) throws NumberFormatException { return Long.valueOf((param1Int == -1) ? Long.decode(param1String).longValue() : Long.parseLong(param1String, param1Int)); }
/*     */   }
/*     */   
/*     */   private static class ShortResourceConverter
/*     */     extends INumberResourceConverter
/*     */   {
/* 265 */     ShortResourceConverter() { super(Short.class, short.class); }
/*     */ 
/*     */ 
/*     */     
/* 269 */     protected Number parseString(String param1String, int param1Int) throws NumberFormatException { return Short.valueOf((param1Int == -1) ? Short.decode(param1String).shortValue() : Short.parseShort(param1String, param1Int)); }
/*     */   }
/*     */   
/*     */   private static class MessageFormatResourceConverter
/*     */     extends ResourceConverter
/*     */   {
/* 275 */     MessageFormatResourceConverter() { super(MessageFormat.class); }
/*     */ 
/*     */ 
/*     */     
/* 279 */     public Object parseString(String param1String, ResourceMap param1ResourceMap) { return new MessageFormat(param1String); }
/*     */   }
/*     */   
/*     */   private static class URLResourceConverter
/*     */     extends ResourceConverter
/*     */   {
/* 285 */     URLResourceConverter() { super(URL.class); }
/*     */ 
/*     */     
/*     */     public Object parseString(String param1String, ResourceMap param1ResourceMap) throws ResourceConverter.ResourceConverterException {
/*     */       try {
/* 290 */         return new URL(param1String);
/*     */       }
/* 292 */       catch (MalformedURLException malformedURLException) {
/* 293 */         throw new ResourceConverter.ResourceConverterException("invalid URL", param1String, malformedURLException);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private static class URIResourceConverter
/*     */     extends ResourceConverter {
/* 300 */     URIResourceConverter() { super(URI.class); }
/*     */ 
/*     */     
/*     */     public Object parseString(String param1String, ResourceMap param1ResourceMap) throws ResourceConverter.ResourceConverterException {
/*     */       try {
/* 305 */         return new URI(param1String);
/*     */       }
/* 307 */       catch (URISyntaxException uRISyntaxException) {
/* 308 */         throw new ResourceConverter.ResourceConverterException("invalid URI", param1String, uRISyntaxException);
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\jdesktop\application\ResourceConverter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
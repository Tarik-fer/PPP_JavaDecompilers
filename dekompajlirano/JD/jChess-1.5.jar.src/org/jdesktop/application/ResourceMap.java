/*      */ package org.jdesktop.application;
/*      */ 
/*      */ import java.awt.Color;
/*      */ import java.awt.Component;
/*      */ import java.awt.Container;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Font;
/*      */ import java.awt.Image;
/*      */ import java.awt.Insets;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.Toolkit;
/*      */ import java.beans.BeanInfo;
/*      */ import java.beans.IntrospectionException;
/*      */ import java.beans.Introspector;
/*      */ import java.beans.PropertyDescriptor;
/*      */ import java.lang.reflect.Array;
/*      */ import java.lang.reflect.Field;
/*      */ import java.lang.reflect.Method;
/*      */ import java.net.URL;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collections;
/*      */ import java.util.Enumeration;
/*      */ import java.util.HashSet;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.MissingResourceException;
/*      */ import java.util.ResourceBundle;
/*      */ import java.util.Set;
/*      */ import java.util.concurrent.ConcurrentHashMap;
/*      */ import java.util.logging.Logger;
/*      */ import java.util.regex.Matcher;
/*      */ import java.util.regex.Pattern;
/*      */ import javax.swing.Icon;
/*      */ import javax.swing.ImageIcon;
/*      */ import javax.swing.JMenu;
/*      */ import javax.swing.KeyStroke;
/*      */ import javax.swing.border.EmptyBorder;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ResourceMap
/*      */ {
/*   94 */   private static Logger logger = Logger.getLogger(ResourceMap.class.getName());
/*   95 */   private static final Object nullResource = new String("null resource");
/*      */   private final ClassLoader classLoader;
/*      */   private final ResourceMap parent;
/*      */   private final List<String> bundleNames;
/*      */   private final String resourcesDir;
/*  100 */   private Map<String, Object> bundlesMapP = null;
/*  101 */   private Locale locale = Locale.getDefault();
/*  102 */   private Set<String> bundlesMapKeysP = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean bundlesLoaded = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResourceMap(ResourceMap paramResourceMap, ClassLoader paramClassLoader, List<String> paramList) {
/*  151 */     if (paramClassLoader == null) {
/*  152 */       throw new IllegalArgumentException("null ClassLoader");
/*      */     }
/*  154 */     if (paramList == null || paramList.size() == 0) {
/*  155 */       throw new IllegalArgumentException("no bundle specified");
/*      */     }
/*  157 */     for (String str1 : paramList) {
/*  158 */       if (str1 == null || str1.length() == 0) {
/*  159 */         throw new IllegalArgumentException("invalid bundleName: \"" + str1 + "\"");
/*      */       }
/*      */     } 
/*  162 */     String str = bundlePackageName(paramList.get(0));
/*  163 */     for (String str1 : paramList) {
/*  164 */       if (!str.equals(bundlePackageName(str1))) {
/*  165 */         throw new IllegalArgumentException("bundles not colocated: \"" + str1 + "\" != \"" + str + "\"");
/*      */       }
/*      */     } 
/*  168 */     this.parent = paramResourceMap;
/*  169 */     this.classLoader = paramClassLoader;
/*  170 */     this.bundleNames = Collections.unmodifiableList(new ArrayList<String>(paramList));
/*  171 */     this.resourcesDir = str.replace(".", "/") + "/";
/*      */   }
/*      */   
/*      */   private String bundlePackageName(String paramString) {
/*  175 */     int i = paramString.lastIndexOf(".");
/*  176 */     return (i == -1) ? "" : paramString.substring(0, i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  185 */   public ResourceMap(ResourceMap paramResourceMap, ClassLoader paramClassLoader, String... paramVarArgs) { this(paramResourceMap, paramClassLoader, Arrays.asList(paramVarArgs)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  196 */   public ResourceMap getParent() { return this.parent; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  206 */   public List<String> getBundleNames() { return this.bundleNames; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  216 */   public ClassLoader getClassLoader() { return this.classLoader; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  232 */   public String getResourcesDir() { return this.resourcesDir; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private synchronized Map<String, Object> getBundlesMap() {
/*  241 */     Locale locale1 = Locale.getDefault();
/*  242 */     if (this.locale != locale1) {
/*  243 */       this.bundlesLoaded = false;
/*  244 */       this.locale = locale1;
/*      */     } 
/*  246 */     if (!this.bundlesLoaded) {
/*  247 */       ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<Object, Object>();
/*  248 */       for (int i = this.bundleNames.size() - 1; i >= 0; i--) {
/*      */         try {
/*  250 */           String str = this.bundleNames.get(i);
/*  251 */           ResourceBundle resourceBundle = ResourceBundle.getBundle(str, this.locale, this.classLoader);
/*  252 */           Enumeration<String> enumeration = resourceBundle.getKeys();
/*  253 */           while (enumeration.hasMoreElements()) {
/*  254 */             String str1 = enumeration.nextElement();
/*  255 */             concurrentHashMap.put(str1, resourceBundle.getObject(str1));
/*      */           }
/*      */         
/*  258 */         } catch (MissingResourceException missingResourceException) {}
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  264 */       this.bundlesMapP = (Map)concurrentHashMap;
/*  265 */       this.bundlesLoaded = true;
/*      */     } 
/*  267 */     return this.bundlesMapP;
/*      */   }
/*      */   
/*      */   private void checkNullKey(String paramString) {
/*  271 */     if (paramString == null) {
/*  272 */       throw new IllegalArgumentException("null key");
/*      */     }
/*      */   }
/*      */   
/*      */   private synchronized Set<String> getBundlesMapKeys() {
/*  277 */     if (this.bundlesMapKeysP == null) {
/*  278 */       HashSet<String> hashSet = new HashSet<String>(getResourceKeySet());
/*  279 */       ResourceMap resourceMap = getParent();
/*  280 */       if (resourceMap != null) {
/*  281 */         hashSet.addAll(resourceMap.keySet());
/*      */       }
/*  283 */       this.bundlesMapKeysP = Collections.unmodifiableSet(hashSet);
/*      */     } 
/*  285 */     return this.bundlesMapKeysP;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  296 */   public Set<String> keySet() { return getBundlesMapKeys(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean containsKey(String paramString) {
/*  308 */     checkNullKey(paramString);
/*  309 */     if (containsResourceKey(paramString)) {
/*  310 */       return true;
/*      */     }
/*      */     
/*  313 */     ResourceMap resourceMap = getParent();
/*  314 */     return (resourceMap != null) ? resourceMap.containsKey(paramString) : false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class LookupException
/*      */     extends RuntimeException
/*      */   {
/*      */     private final Class type;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private final String key;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public LookupException(String param1String1, String param1String2, Class param1Class) {
/*  339 */       super(String.format("%s: resource %s, type %s", new Object[] { param1String1, param1String2, param1Class }));
/*  340 */       this.key = param1String2;
/*  341 */       this.type = param1Class;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  349 */     public Class getType() { return this.type; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  357 */     public String getKey() { return this.key; }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Set<String> getResourceKeySet() {
/*  378 */     Map<String, Object> map = getBundlesMap();
/*  379 */     if (map == null) {
/*  380 */       return Collections.emptySet();
/*      */     }
/*      */     
/*  383 */     return map.keySet();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean containsResourceKey(String paramString) {
/*  407 */     checkNullKey(paramString);
/*  408 */     Map<String, Object> map = getBundlesMap();
/*  409 */     return (map != null && map.containsKey(paramString));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Object getResource(String paramString) {
/*  435 */     checkNullKey(paramString);
/*  436 */     Map<String, Object> map = getBundlesMap();
/*  437 */     V v = (map != null) ? (V)map.get(paramString) : null;
/*  438 */     return (v == nullResource) ? null : v;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void putResource(String paramString, Object paramObject) {
/*  462 */     checkNullKey(paramString);
/*  463 */     Map<String, Object> map = getBundlesMap();
/*  464 */     if (map != null) {
/*  465 */       map.put(paramString, (paramObject == null) ? nullResource : paramObject);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getObject(String paramString, Class<boolean> paramClass) {
/*      */     Object object1;
/*  521 */     checkNullKey(paramString);
/*  522 */     if (paramClass == null) {
/*  523 */       throw new IllegalArgumentException("null type");
/*      */     }
/*  525 */     if (paramClass.isPrimitive())
/*  526 */       if (paramClass == boolean.class) { object1 = Boolean.class; }
/*  527 */       else { Class<Character> clazz; if (object1 == char.class) { clazz = Character.class; }
/*  528 */         else { Class<Byte> clazz1; if (clazz == byte.class) { clazz1 = Byte.class; }
/*  529 */           else { Class<Short> clazz2; if (clazz1 == short.class) { clazz2 = Short.class; }
/*  530 */             else { Class<Integer> clazz3; if (clazz2 == int.class) { clazz3 = Integer.class; }
/*  531 */               else { Class<Long> clazz4; if (clazz3 == long.class) { clazz4 = Long.class; }
/*  532 */                 else { Class<Float> clazz5; if (clazz4 == float.class) { clazz5 = Float.class; }
/*  533 */                   else if (clazz5 == double.class) { object1 = Double.class; }  }  }  }  }  }
/*      */          }
/*  535 */         Object object2 = null;
/*  536 */     ResourceMap resourceMap = this;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  541 */     while (resourceMap != null) {
/*  542 */       if (resourceMap.containsResourceKey(paramString)) {
/*  543 */         object2 = resourceMap.getResource(paramString);
/*      */         break;
/*      */       } 
/*  546 */       resourceMap = resourceMap.getParent();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  552 */     if (object2 instanceof String && ((String)object2).contains("${")) {
/*  553 */       object2 = evaluateStringExpression((String)object2);
/*  554 */       resourceMap.putResource(paramString, object2);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  565 */     if (object2 != null) {
/*  566 */       Class<?> clazz = object2.getClass();
/*  567 */       if (!object1.isAssignableFrom(clazz)) {
/*  568 */         if (object2 instanceof String) {
/*  569 */           ResourceConverter resourceConverter = ResourceConverter.forType((Class)object1);
/*  570 */           if (resourceConverter != null) {
/*  571 */             String str = (String)object2;
/*      */             try {
/*  573 */               object2 = resourceConverter.parseString(str, resourceMap);
/*  574 */               resourceMap.putResource(paramString, object2);
/*      */             }
/*  576 */             catch (ResourceConverterException resourceConverterException) {
/*  577 */               String str1 = "string conversion failed";
/*  578 */               LookupException lookupException = new LookupException(str1, paramString, (Class)object1);
/*  579 */               lookupException.initCause(resourceConverterException);
/*  580 */               throw lookupException;
/*      */             } 
/*      */           } else {
/*      */             
/*  584 */             String str = "no StringConverter for required type";
/*  585 */             throw new LookupException(str, paramString, (Class)object1);
/*      */           } 
/*      */         } else {
/*      */           
/*  589 */           String str = "named resource has wrong type";
/*  590 */           throw new LookupException(str, paramString, (Class)object1);
/*      */         } 
/*      */       }
/*      */     } 
/*  594 */     return object2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String evaluateStringExpression(String paramString) {
/*  607 */     if (paramString.trim().equals("${null}")) {
/*  608 */       return null;
/*      */     }
/*  610 */     StringBuffer stringBuffer = new StringBuffer();
/*  611 */     int i = 0, j = 0;
/*  612 */     while ((j = paramString.indexOf("${", i)) != -1) {
/*  613 */       if (j == 0 || (j > 0 && paramString.charAt(j - 1) != '\\')) {
/*  614 */         int k = paramString.indexOf("}", j);
/*  615 */         if (k != -1 && k > j + 2) {
/*  616 */           String str1 = paramString.substring(j + 2, k);
/*  617 */           String str2 = getString(str1, new Object[0]);
/*  618 */           stringBuffer.append(paramString.substring(i, j));
/*  619 */           if (str2 != null) {
/*  620 */             stringBuffer.append(str2);
/*      */           } else {
/*      */             
/*  623 */             String str3 = String.format("no value for \"%s\" in \"%s\"", new Object[] { str1, paramString });
/*  624 */             throw new LookupException(str3, str1, String.class);
/*      */           } 
/*  626 */           i = k + 1;
/*      */           continue;
/*      */         } 
/*  629 */         String str = String.format("no closing brace in \"%s\"", new Object[] { paramString });
/*  630 */         throw new LookupException(str, "<not found>", String.class);
/*      */       } 
/*      */ 
/*      */       
/*  634 */       stringBuffer.append(paramString.substring(i, j - 1));
/*  635 */       stringBuffer.append("${");
/*  636 */       i = j + 2;
/*      */     } 
/*      */     
/*  639 */     stringBuffer.append(paramString.substring(i));
/*  640 */     return stringBuffer.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getString(String paramString, Object... paramVarArgs) {
/*  665 */     if (paramVarArgs.length == 0) {
/*  666 */       return (String)getObject(paramString, String.class);
/*      */     }
/*      */     
/*  669 */     String str = (String)getObject(paramString, String.class);
/*  670 */     return (str == null) ? null : String.format(str, paramVarArgs);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  685 */   public final Boolean getBoolean(String paramString) { return (Boolean)getObject(paramString, Boolean.class); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  699 */   public final Integer getInteger(String paramString) { return (Integer)getObject(paramString, Integer.class); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  713 */   public final Long getLong(String paramString) { return (Long)getObject(paramString, Long.class); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  727 */   public final Short getShort(String paramString) { return (Short)getObject(paramString, Short.class); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  741 */   public final Byte getByte(String paramString) { return (Byte)getObject(paramString, Byte.class); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  755 */   public final Float getFloat(String paramString) { return (Float)getObject(paramString, Float.class); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  769 */   public final Double getDouble(String paramString) { return (Double)getObject(paramString, Double.class); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  787 */   public final Icon getIcon(String paramString) { return (Icon)getObject(paramString, Icon.class); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  826 */   public final ImageIcon getImageIcon(String paramString) { return (ImageIcon)getObject(paramString, ImageIcon.class); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  852 */   public final Font getFont(String paramString) { return (Font)getObject(paramString, Font.class); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  883 */   public final Color getColor(String paramString) { return (Color)getObject(paramString, Color.class); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  902 */   public final KeyStroke getKeyStroke(String paramString) { return (KeyStroke)getObject(paramString, KeyStroke.class); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Integer getKeyCode(String paramString) {
/*  917 */     KeyStroke keyStroke = getKeyStroke(paramString);
/*  918 */     return (keyStroke != null) ? new Integer(keyStroke.getKeyCode()) : null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class PropertyInjectionException
/*      */     extends RuntimeException
/*      */   {
/*      */     private final String key;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private final Component component;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private final String propertyName;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public PropertyInjectionException(String param1String1, String param1String2, Component param1Component, String param1String3) {
/*  944 */       super(String.format("%s: resource %s, property %s, component %s", new Object[] { param1String1, param1String2, param1String3, param1Component }));
/*  945 */       this.key = param1String2;
/*  946 */       this.component = param1Component;
/*  947 */       this.propertyName = param1String3;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  955 */     public String getKey() { return this.key; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  963 */     public Component getComponent() { return this.component; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  971 */     public String getPropertyName() { return this.propertyName; }
/*      */   }
/*      */ 
/*      */   
/*      */   private void injectComponentProperty(Component paramComponent, PropertyDescriptor paramPropertyDescriptor, String paramString) {
/*  976 */     Method method = paramPropertyDescriptor.getWriteMethod();
/*  977 */     Class<?> clazz = paramPropertyDescriptor.getPropertyType();
/*  978 */     if (method != null && clazz != null && containsKey(paramString)) {
/*  979 */       Object object = getObject(paramString, clazz);
/*  980 */       String str = paramPropertyDescriptor.getName();
/*      */ 
/*      */       
/*      */       try {
/*  984 */         if ("text".equals(str) && paramComponent instanceof javax.swing.AbstractButton) {
/*  985 */           MnemonicText.configure(paramComponent, (String)object);
/*      */         }
/*  987 */         else if ("text".equals(str) && paramComponent instanceof javax.swing.JLabel) {
/*  988 */           MnemonicText.configure(paramComponent, (String)object);
/*      */         } else {
/*      */           
/*  991 */           method.invoke(paramComponent, new Object[] { object });
/*      */         }
/*      */       
/*  994 */       } catch (Exception exception) {
/*  995 */         String str1 = paramPropertyDescriptor.getName();
/*  996 */         String str2 = "property setter failed";
/*  997 */         PropertyInjectionException propertyInjectionException = new PropertyInjectionException(str2, paramString, paramComponent, str1);
/*  998 */         propertyInjectionException.initCause(exception);
/*  999 */         throw propertyInjectionException;
/*      */       } 
/*      */     } else {
/* 1002 */       if (clazz != null) {
/* 1003 */         String str1 = paramPropertyDescriptor.getName();
/* 1004 */         String str2 = "no value specified for resource";
/* 1005 */         throw new PropertyInjectionException(str2, paramString, paramComponent, str1);
/*      */       } 
/* 1007 */       if (method == null) {
/* 1008 */         String str1 = paramPropertyDescriptor.getName();
/* 1009 */         String str2 = "can't set read-only property";
/* 1010 */         throw new PropertyInjectionException(str2, paramString, paramComponent, str1);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   private void injectComponentProperties(Component paramComponent) {
/* 1015 */     String str = paramComponent.getName();
/* 1016 */     if (str != null) {
/*      */ 
/*      */ 
/*      */       
/* 1020 */       boolean bool = false;
/* 1021 */       for (String str1 : keySet()) {
/* 1022 */         int i = str1.lastIndexOf(".");
/* 1023 */         if (i != -1 && str.equals(str1.substring(0, i))) {
/* 1024 */           bool = true;
/*      */           break;
/*      */         } 
/*      */       } 
/* 1028 */       if (!bool) {
/*      */         return;
/*      */       }
/* 1031 */       BeanInfo beanInfo = null;
/*      */       try {
/* 1033 */         beanInfo = Introspector.getBeanInfo(paramComponent.getClass());
/*      */       }
/* 1035 */       catch (IntrospectionException introspectionException) {
/* 1036 */         String str1 = "introspection failed";
/* 1037 */         PropertyInjectionException propertyInjectionException = new PropertyInjectionException(str1, null, paramComponent, null);
/* 1038 */         propertyInjectionException.initCause(introspectionException);
/* 1039 */         throw propertyInjectionException;
/*      */       } 
/* 1041 */       PropertyDescriptor[] arrayOfPropertyDescriptor = beanInfo.getPropertyDescriptors();
/* 1042 */       if (arrayOfPropertyDescriptor != null && arrayOfPropertyDescriptor.length > 0) {
/* 1043 */         for (String str1 : keySet()) {
/* 1044 */           int i = str1.lastIndexOf(".");
/* 1045 */           String str2 = (i == -1) ? null : str1.substring(0, i);
/* 1046 */           if (str.equals(str2)) {
/* 1047 */             if (i + 1 == str1.length()) {
/*      */ 
/*      */ 
/*      */               
/* 1051 */               String str4 = "component resource lacks property name suffix";
/* 1052 */               logger.warning(str4);
/*      */               break;
/*      */             } 
/* 1055 */             String str3 = str1.substring(i + 1);
/* 1056 */             boolean bool1 = false;
/* 1057 */             for (PropertyDescriptor propertyDescriptor : arrayOfPropertyDescriptor) {
/* 1058 */               if (propertyDescriptor.getName().equals(str3)) {
/* 1059 */                 injectComponentProperty(paramComponent, propertyDescriptor, str1);
/* 1060 */                 bool1 = true;
/*      */                 break;
/*      */               } 
/*      */             } 
/* 1064 */             if (!bool1) {
/* 1065 */               String str4 = String.format("[resource %s] component named %s doesn't have a property named %s", new Object[] { str1, str, str3 });
/*      */ 
/*      */               
/* 1068 */               logger.warning(str4);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void injectComponent(Component paramComponent) {
/* 1122 */     if (paramComponent == null) {
/* 1123 */       throw new IllegalArgumentException("null target");
/*      */     }
/* 1125 */     injectComponentProperties(paramComponent);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void injectComponents(Component paramComponent) {
/* 1139 */     injectComponent(paramComponent);
/* 1140 */     if (paramComponent instanceof JMenu) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1147 */       JMenu jMenu = (JMenu)paramComponent;
/* 1148 */       for (Component component : jMenu.getMenuComponents()) {
/* 1149 */         injectComponents(component);
/*      */       }
/*      */     }
/* 1152 */     else if (paramComponent instanceof Container) {
/* 1153 */       Container container = (Container)paramComponent;
/* 1154 */       for (Component component : container.getComponents()) {
/* 1155 */         injectComponents(component);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class InjectFieldException
/*      */     extends RuntimeException
/*      */   {
/*      */     private final Field field;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private final Object target;
/*      */ 
/*      */ 
/*      */     
/*      */     private final String key;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public InjectFieldException(String param1String1, Field param1Field, Object param1Object, String param1String2) {
/* 1182 */       super(String.format("%s: resource %s, field %s, target %s", new Object[] { param1String1, param1String2, param1Field, param1Object }));
/* 1183 */       this.field = param1Field;
/* 1184 */       this.target = param1Object;
/* 1185 */       this.key = param1String2;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1193 */     public Field getField() { return this.field; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1201 */     public Object getTarget() { return this.target; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1209 */     public String getKey() { return this.key; }
/*      */   }
/*      */ 
/*      */   
/*      */   private void injectField(Field paramField, Object paramObject, String paramString) {
/* 1214 */     Class<?> clazz = paramField.getType();
/* 1215 */     if (clazz.isArray()) {
/* 1216 */       clazz = clazz.getComponentType();
/* 1217 */       Pattern pattern = Pattern.compile(paramString + "\\[([\\d]+)\\]");
/* 1218 */       ArrayList arrayList = new ArrayList();
/* 1219 */       for (String str : keySet()) {
/* 1220 */         Matcher matcher = pattern.matcher(str);
/* 1221 */         if (matcher.matches()) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1227 */           Object object = getObject(str, clazz);
/* 1228 */           if (!paramField.isAccessible()) {
/* 1229 */             paramField.setAccessible(true);
/*      */           }
/*      */           try {
/* 1232 */             int i = Integer.parseInt(matcher.group(1));
/* 1233 */             Array.set(paramField.get(paramObject), i, object);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           }
/* 1239 */           catch (Exception exception) {
/* 1240 */             String str1 = "unable to set array element";
/* 1241 */             InjectFieldException injectFieldException = new InjectFieldException(str1, paramField, paramObject, paramString);
/* 1242 */             injectFieldException.initCause(exception);
/* 1243 */             throw injectFieldException;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       
/* 1249 */       Object object = getObject(paramString, clazz);
/* 1250 */       if (object != null) {
/* 1251 */         if (!paramField.isAccessible()) {
/* 1252 */           paramField.setAccessible(true);
/*      */         }
/*      */         try {
/* 1255 */           paramField.set(paramObject, object);
/*      */ 
/*      */ 
/*      */         
/*      */         }
/* 1260 */         catch (Exception exception) {
/* 1261 */           String str = "unable to set field's value";
/* 1262 */           InjectFieldException injectFieldException = new InjectFieldException(str, paramField, paramObject, paramString);
/* 1263 */           injectFieldException.initCause(exception);
/* 1264 */           throw injectFieldException;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void injectFields(Object paramObject) {
/* 1308 */     if (paramObject == null) {
/* 1309 */       throw new IllegalArgumentException("null target");
/*      */     }
/* 1311 */     Class<?> clazz = paramObject.getClass();
/* 1312 */     if (clazz.isArray()) {
/* 1313 */       throw new IllegalArgumentException("array target");
/*      */     }
/* 1315 */     String str = clazz.getSimpleName() + ".";
/* 1316 */     for (Field field : clazz.getDeclaredFields()) {
/* 1317 */       Resource resource = field.getAnnotation(Resource.class);
/* 1318 */       if (resource != null) {
/* 1319 */         String str1 = resource.key();
/* 1320 */         String str2 = (str1.length() > 0) ? str1 : (str + field.getName());
/* 1321 */         injectField(field, paramObject, str2);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static  {
/* 1330 */     ResourceConverter[] arrayOfResourceConverter = { new ColorStringConverter(), new IconStringConverter(), new ImageStringConverter(), new FontStringConverter(), new KeyStrokeStringConverter(), new DimensionStringConverter(), new PointStringConverter(), new RectangleStringConverter(), new InsetsStringConverter(), new EmptyBorderStringConverter() };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1342 */     for (ResourceConverter resourceConverter : arrayOfResourceConverter) {
/* 1343 */       ResourceConverter.register(resourceConverter);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String resourcePath(String paramString, ResourceMap paramResourceMap) {
/* 1351 */     String str = paramString;
/* 1352 */     if (paramString == null) {
/* 1353 */       str = null;
/*      */     }
/* 1355 */     else if (paramString.startsWith("/")) {
/* 1356 */       str = (paramString.length() > 1) ? paramString.substring(1) : null;
/*      */     } else {
/*      */       
/* 1359 */       str = paramResourceMap.getResourcesDir() + paramString;
/*      */     } 
/* 1361 */     return str;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static ImageIcon loadImageIcon(String paramString, ResourceMap paramResourceMap) throws ResourceConverter.ResourceConverterException {
/* 1367 */     String str1 = resourcePath(paramString, paramResourceMap);
/* 1368 */     if (str1 == null) {
/* 1369 */       String str = String.format("invalid image/icon path \"%s\"", new Object[] { paramString });
/* 1370 */       throw new ResourceConverter.ResourceConverterException(str, paramString);
/*      */     } 
/* 1372 */     URL uRL = paramResourceMap.getClassLoader().getResource(str1);
/* 1373 */     if (uRL != null) {
/* 1374 */       return new ImageIcon(uRL);
/*      */     }
/*      */     
/* 1377 */     String str2 = String.format("couldn't find Icon resource \"%s\"", new Object[] { paramString });
/* 1378 */     throw new ResourceConverter.ResourceConverterException(str2, paramString);
/*      */   }
/*      */   
/*      */   private static class FontStringConverter
/*      */     extends ResourceConverter
/*      */   {
/* 1384 */     FontStringConverter() { super(Font.class); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1391 */     public Object parseString(String param1String, ResourceMap param1ResourceMap) throws ResourceConverter.ResourceConverterException { return Font.decode(param1String); }
/*      */   }
/*      */   
/*      */   private static class ColorStringConverter
/*      */     extends ResourceConverter
/*      */   {
/* 1397 */     ColorStringConverter() { super(Color.class); }
/*      */ 
/*      */     
/* 1400 */     private void error(String param1String1, String param1String2, Exception param1Exception) throws ResourceConverter.ResourceConverterException { throw new ResourceConverter.ResourceConverterException(param1String1, param1String2, param1Exception); }
/*      */ 
/*      */ 
/*      */     
/* 1404 */     private void error(String param1String1, String param1String2) throws ResourceConverter.ResourceConverterException { error(param1String1, param1String2, null); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Object parseString(String param1String, ResourceMap param1ResourceMap) throws ResourceConverter.ResourceConverterException {
/* 1414 */       Color color = null;
/* 1415 */       if (param1String.startsWith("#"))
/* 1416 */       { int j; int i; switch (param1String.length())
/*      */         
/*      */         { case 7:
/* 1419 */             color = Color.decode(param1String);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1455 */             return color;case 9: i = Integer.decode(param1String.substring(0, 3)).intValue(); j = Integer.decode("#" + param1String.substring(3)).intValue(); color = new Color(i << 24 | j, true); return color; }  throw new ResourceConverter.ResourceConverterException("invalid #RRGGBB or #AARRGGBB color string", param1String); }  String[] arrayOfString = param1String.split(","); if (arrayOfString.length < 3 || arrayOfString.length > 4) throw new ResourceConverter.ResourceConverterException("invalid R, G, B[, A] color string", param1String);  try { if (arrayOfString.length == 4) { int i = Integer.parseInt(arrayOfString[0].trim()); int j = Integer.parseInt(arrayOfString[1].trim()); int k = Integer.parseInt(arrayOfString[2].trim()); int m = Integer.parseInt(arrayOfString[3].trim()); color = new Color(i, j, k, m); } else { int i = Integer.parseInt(arrayOfString[0].trim()); int j = Integer.parseInt(arrayOfString[1].trim()); int k = Integer.parseInt(arrayOfString[2].trim()); color = new Color(i, j, k); }  } catch (NumberFormatException numberFormatException) { throw new ResourceConverter.ResourceConverterException("invalid R, G, B[, A] color string", param1String, numberFormatException); }  return color;
/*      */     }
/*      */   }
/*      */   
/*      */   private static class IconStringConverter
/*      */     extends ResourceConverter {
/* 1461 */     IconStringConverter() { super(Icon.class); }
/*      */ 
/*      */ 
/*      */     
/* 1465 */     public Object parseString(String param1String, ResourceMap param1ResourceMap) throws ResourceConverter.ResourceConverterException { return ResourceMap.loadImageIcon(param1String, param1ResourceMap); }
/*      */ 
/*      */ 
/*      */     
/* 1469 */     public boolean supportsType(Class param1Class) { return (param1Class.equals(Icon.class) || param1Class.equals(ImageIcon.class)); }
/*      */   }
/*      */   
/*      */   private static class ImageStringConverter
/*      */     extends ResourceConverter
/*      */   {
/* 1475 */     ImageStringConverter() { super(Image.class); }
/*      */ 
/*      */ 
/*      */     
/* 1479 */     public Object parseString(String param1String, ResourceMap param1ResourceMap) throws ResourceConverter.ResourceConverterException { return ResourceMap.loadImageIcon(param1String, param1ResourceMap).getImage(); }
/*      */   }
/*      */   
/*      */   private static class KeyStrokeStringConverter
/*      */     extends ResourceConverter
/*      */   {
/* 1485 */     KeyStrokeStringConverter() { super(KeyStroke.class); }
/*      */ 
/*      */     
/*      */     public Object parseString(String param1String, ResourceMap param1ResourceMap) {
/* 1489 */       if (param1String.contains("shortcut")) {
/* 1490 */         int i = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
/* 1491 */         param1String = param1String.replaceAll("shortcut", (i == 4) ? "meta" : "control");
/*      */       } 
/* 1493 */       return KeyStroke.getKeyStroke(param1String);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static List<Double> parseDoubles(String paramString1, int paramInt, String paramString2) throws ResourceConverter.ResourceConverterException {
/* 1503 */     String[] arrayOfString = paramString1.split(",", paramInt + 1);
/* 1504 */     if (arrayOfString.length != paramInt) {
/* 1505 */       throw new ResourceConverter.ResourceConverterException(paramString2, paramString1);
/*      */     }
/*      */     
/* 1508 */     ArrayList<Double> arrayList = new ArrayList(paramInt);
/* 1509 */     for (String str : arrayOfString) {
/*      */       try {
/* 1511 */         arrayList.add(Double.valueOf(str));
/*      */       }
/* 1513 */       catch (NumberFormatException numberFormatException) {
/* 1514 */         throw new ResourceConverter.ResourceConverterException(paramString2, paramString1, numberFormatException);
/*      */       } 
/*      */     } 
/* 1517 */     return arrayList;
/*      */   }
/*      */   
/*      */   private static class DimensionStringConverter
/*      */     extends ResourceConverter
/*      */   {
/* 1523 */     DimensionStringConverter() { super(Dimension.class); }
/*      */     
/*      */     public Object parseString(String param1String, ResourceMap param1ResourceMap) throws ResourceConverter.ResourceConverterException {
/* 1526 */       List<Double> list = ResourceMap.parseDoubles(param1String, 2, "invalid x,y Dimension string");
/* 1527 */       Dimension dimension = new Dimension();
/* 1528 */       dimension.setSize(((Double)list.get(0)).doubleValue(), ((Double)list.get(1)).doubleValue());
/* 1529 */       return dimension;
/*      */     }
/*      */   }
/*      */   
/*      */   private static class PointStringConverter
/*      */     extends ResourceConverter {
/* 1535 */     PointStringConverter() { super(Point.class); }
/*      */     
/*      */     public Object parseString(String param1String, ResourceMap param1ResourceMap) throws ResourceConverter.ResourceConverterException {
/* 1538 */       List<Double> list = ResourceMap.parseDoubles(param1String, 2, "invalid x,y Point string");
/* 1539 */       Point point = new Point();
/* 1540 */       point.setLocation(((Double)list.get(0)).doubleValue(), ((Double)list.get(1)).doubleValue());
/* 1541 */       return point;
/*      */     }
/*      */   }
/*      */   
/*      */   private static class RectangleStringConverter
/*      */     extends ResourceConverter {
/* 1547 */     RectangleStringConverter() { super(Rectangle.class); }
/*      */     
/*      */     public Object parseString(String param1String, ResourceMap param1ResourceMap) throws ResourceConverter.ResourceConverterException {
/* 1550 */       List<Double> list = ResourceMap.parseDoubles(param1String, 4, "invalid x,y,width,height Rectangle string");
/* 1551 */       Rectangle rectangle = new Rectangle();
/* 1552 */       rectangle.setFrame(((Double)list.get(0)).doubleValue(), ((Double)list.get(1)).doubleValue(), ((Double)list.get(2)).doubleValue(), ((Double)list.get(3)).doubleValue());
/* 1553 */       return rectangle;
/*      */     }
/*      */   }
/*      */   
/*      */   private static class InsetsStringConverter
/*      */     extends ResourceConverter {
/* 1559 */     InsetsStringConverter() { super(Insets.class); }
/*      */     
/*      */     public Object parseString(String param1String, ResourceMap param1ResourceMap) throws ResourceConverter.ResourceConverterException {
/* 1562 */       List<Double> list = ResourceMap.parseDoubles(param1String, 4, "invalid top,left,bottom,right Insets string");
/* 1563 */       return new Insets(((Double)list.get(0)).intValue(), ((Double)list.get(1)).intValue(), ((Double)list.get(2)).intValue(), ((Double)list.get(3)).intValue());
/*      */     }
/*      */   }
/*      */   
/*      */   private static class EmptyBorderStringConverter
/*      */     extends ResourceConverter {
/* 1569 */     EmptyBorderStringConverter() { super(EmptyBorder.class); }
/*      */     
/*      */     public Object parseString(String param1String, ResourceMap param1ResourceMap) throws ResourceConverter.ResourceConverterException {
/* 1572 */       List<Double> list = ResourceMap.parseDoubles(param1String, 4, "invalid top,left,bottom,right EmptyBorder string");
/* 1573 */       return new EmptyBorder(((Double)list.get(0)).intValue(), ((Double)list.get(1)).intValue(), ((Double)list.get(2)).intValue(), ((Double)list.get(3)).intValue());
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\jdesktop\application\ResourceMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
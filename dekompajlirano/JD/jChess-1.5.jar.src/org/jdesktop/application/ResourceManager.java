/*     */ package org.jdesktop.application;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.logging.Logger;
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
/*     */ public class ResourceManager
/*     */   extends AbstractBean
/*     */ {
/*  56 */   private static final Logger logger = Logger.getLogger(ResourceManager.class.getName());
/*     */   private final Map<String, ResourceMap> resourceMaps;
/*     */   private final ApplicationContext context;
/*  59 */   private List<String> applicationBundleNames = null;
/*  60 */   private ResourceMap appResourceMap = null;
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
/*     */   protected ResourceManager(ApplicationContext paramApplicationContext) {
/*  80 */     if (paramApplicationContext == null) {
/*  81 */       throw new IllegalArgumentException("null context");
/*     */     }
/*  83 */     this.context = paramApplicationContext;
/*  84 */     this.resourceMaps = new ConcurrentHashMap<String, ResourceMap>();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  89 */   protected final ApplicationContext getContext() { return this.context; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<String> allBundleNames(Class paramClass1, Class paramClass2) {
/* 100 */     ArrayList<String> arrayList = new ArrayList();
/* 101 */     Class clazz1 = paramClass2.getSuperclass();
/* 102 */     for (Class clazz2 = paramClass1; clazz2 != clazz1; clazz2 = clazz2.getSuperclass()) {
/* 103 */       arrayList.addAll(getClassBundleNames(clazz2));
/*     */     }
/* 105 */     return Collections.unmodifiableList(arrayList);
/*     */   }
/*     */   
/*     */   private String bundlePackageName(String paramString) {
/* 109 */     int i = paramString.lastIndexOf(".");
/* 110 */     return (i == -1) ? "" : paramString.substring(0, i);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ResourceMap createResourceMapChain(ClassLoader paramClassLoader, ResourceMap paramResourceMap, ListIterator<String> paramListIterator) {
/* 120 */     if (!paramListIterator.hasNext()) {
/* 121 */       return paramResourceMap;
/*     */     }
/*     */     
/* 124 */     String str1 = paramListIterator.next();
/* 125 */     String str2 = bundlePackageName(str1);
/* 126 */     ArrayList<String> arrayList = new ArrayList();
/* 127 */     arrayList.add(str1);
/* 128 */     while (paramListIterator.hasNext()) {
/* 129 */       String str = paramListIterator.next();
/* 130 */       if (str2.equals(bundlePackageName(str))) {
/* 131 */         arrayList.add(str);
/*     */         continue;
/*     */       } 
/* 134 */       paramListIterator.previous();
/*     */     } 
/*     */ 
/*     */     
/* 138 */     ResourceMap resourceMap = createResourceMapChain(paramClassLoader, paramResourceMap, paramListIterator);
/* 139 */     return createResourceMap(paramClassLoader, resourceMap, arrayList);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ResourceMap getApplicationResourceMap() {
/* 150 */     if (this.appResourceMap == null) {
/* 151 */       List<String> list = getApplicationBundleNames();
/* 152 */       Class<Application> clazz = getContext().getApplicationClass();
/* 153 */       if (clazz == null) {
/* 154 */         logger.warning("getApplicationResourceMap(): no Application class");
/* 155 */         clazz = Application.class;
/*     */       } 
/* 157 */       ClassLoader classLoader = clazz.getClassLoader();
/* 158 */       this.appResourceMap = createResourceMapChain(classLoader, null, list.listIterator());
/*     */     } 
/* 160 */     return this.appResourceMap;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ResourceMap getClassResourceMap(Class paramClass1, Class paramClass2) {
/* 167 */     String str = paramClass1.getName() + paramClass2.getName();
/* 168 */     ResourceMap resourceMap = this.resourceMaps.get(str);
/* 169 */     if (resourceMap == null) {
/* 170 */       List<String> list = allBundleNames(paramClass1, paramClass2);
/* 171 */       ClassLoader classLoader = paramClass1.getClassLoader();
/* 172 */       ResourceMap resourceMap1 = getResourceMap();
/* 173 */       resourceMap = createResourceMapChain(classLoader, resourceMap1, list.listIterator());
/* 174 */       this.resourceMaps.put(str, resourceMap);
/*     */     } 
/* 176 */     return resourceMap;
/*     */   }
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
/*     */   public ResourceMap getResourceMap(Class<?> paramClass1, Class paramClass2) {
/* 245 */     if (paramClass1 == null) {
/* 246 */       throw new IllegalArgumentException("null startClass");
/*     */     }
/* 248 */     if (paramClass2 == null) {
/* 249 */       throw new IllegalArgumentException("null stopClass");
/*     */     }
/* 251 */     if (!paramClass2.isAssignableFrom(paramClass1)) {
/* 252 */       throw new IllegalArgumentException("startClass is not a subclass, or the same as, stopClass");
/*     */     }
/* 254 */     return getClassResourceMap(paramClass1, paramClass2);
/*     */   }
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
/*     */   public final ResourceMap getResourceMap(Class paramClass) {
/* 269 */     if (paramClass == null) {
/* 270 */       throw new IllegalArgumentException("null class");
/*     */     }
/* 272 */     return getResourceMap(paramClass, paramClass);
/*     */   }
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
/* 289 */   public ResourceMap getResourceMap() { return getApplicationResourceMap(); }
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
/*     */   public List<String> getApplicationBundleNames() {
/* 326 */     if (this.applicationBundleNames == null) {
/* 327 */       Class clazz = getContext().getApplicationClass();
/* 328 */       if (clazz == null) {
/* 329 */         return allBundleNames(Application.class, Application.class);
/*     */       }
/*     */       
/* 332 */       this.applicationBundleNames = allBundleNames(clazz, Application.class);
/*     */     } 
/*     */     
/* 335 */     return this.applicationBundleNames;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setApplicationBundleNames(List<String> paramList) {
/* 346 */     if (paramList != null) {
/* 347 */       for (String str : paramList) {
/* 348 */         if (str == null || paramList.size() == 0) {
/* 349 */           throw new IllegalArgumentException("invalid bundle name \"" + str + "\"");
/*     */         }
/*     */       } 
/*     */     }
/* 353 */     List<String> list = this.applicationBundleNames;
/* 354 */     if (paramList != null) {
/* 355 */       this.applicationBundleNames = Collections.unmodifiableList(new ArrayList<String>(paramList));
/*     */     } else {
/*     */       
/* 358 */       this.applicationBundleNames = null;
/*     */     } 
/* 360 */     this.resourceMaps.clear();
/* 361 */     firePropertyChange("applicationBundleNames", list, this.applicationBundleNames);
/*     */   }
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
/*     */   private String classBundleBaseName(Class paramClass) {
/* 376 */     String str = paramClass.getName();
/* 377 */     StringBuffer stringBuffer = new StringBuffer();
/* 378 */     int i = str.lastIndexOf('.');
/* 379 */     if (i > 0) {
/* 380 */       stringBuffer.append(str.substring(0, i));
/* 381 */       stringBuffer.append(".resources.");
/* 382 */       stringBuffer.append(paramClass.getSimpleName());
/*     */     } else {
/*     */       
/* 385 */       stringBuffer.append("resources.");
/* 386 */       stringBuffer.append(paramClass.getSimpleName());
/*     */     } 
/* 388 */     return stringBuffer.toString();
/*     */   }
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
/*     */   protected List<String> getClassBundleNames(Class paramClass) {
/* 422 */     String str = classBundleBaseName(paramClass);
/* 423 */     return Collections.singletonList(str);
/*     */   }
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
/* 436 */   protected ResourceMap createResourceMap(ClassLoader paramClassLoader, ResourceMap paramResourceMap, List<String> paramList) { return new ResourceMap(paramResourceMap, paramClassLoader, paramList); }
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
/* 449 */   public String getPlatform() { return getResourceMap().getString("platform", new Object[0]); }
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
/*     */   public void setPlatform(String paramString) {
/* 475 */     if (paramString == null) {
/* 476 */       throw new IllegalArgumentException("null platform");
/*     */     }
/* 478 */     getResourceMap().putResource("platform", paramString);
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\jdesktop\application\ResourceManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
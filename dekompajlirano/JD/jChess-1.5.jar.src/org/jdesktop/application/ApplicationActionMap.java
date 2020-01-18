/*     */ package org.jdesktop.application;
/*     */ 
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import javax.swing.Action;
/*     */ import javax.swing.ActionMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ApplicationActionMap
/*     */   extends ActionMap
/*     */ {
/*     */   private final ApplicationContext context;
/*     */   private final ResourceMap resourceMap;
/*     */   private final Class actionsClass;
/*     */   private final Object actionsObject;
/*     */   private final List<ApplicationAction> proxyActions;
/*     */   
/*     */   public ApplicationActionMap(ApplicationContext paramApplicationContext, Class paramClass, Object paramObject, ResourceMap paramResourceMap) {
/*  64 */     if (paramApplicationContext == null) {
/*  65 */       throw new IllegalArgumentException("null context");
/*     */     }
/*  67 */     if (paramClass == null) {
/*  68 */       throw new IllegalArgumentException("null actionsClass");
/*     */     }
/*  70 */     if (paramObject == null) {
/*  71 */       throw new IllegalArgumentException("null actionsObject");
/*     */     }
/*  73 */     if (!paramClass.isInstance(paramObject)) {
/*  74 */       throw new IllegalArgumentException("actionsObject not an instanceof actionsClass");
/*     */     }
/*  76 */     this.context = paramApplicationContext;
/*  77 */     this.actionsClass = paramClass;
/*  78 */     this.actionsObject = paramObject;
/*  79 */     this.resourceMap = paramResourceMap;
/*  80 */     this.proxyActions = new ArrayList<ApplicationAction>();
/*  81 */     addAnnotationActions(paramResourceMap);
/*  82 */     maybeAddActionsPCL();
/*     */   }
/*     */ 
/*     */   
/*  86 */   public final ApplicationContext getContext() { return this.context; }
/*     */ 
/*     */ 
/*     */   
/*  90 */   public final Class getActionsClass() { return this.actionsClass; }
/*     */ 
/*     */ 
/*     */   
/*  94 */   public final Object getActionsObject() { return this.actionsObject; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<ApplicationAction> getProxyActions() {
/* 110 */     ArrayList<ApplicationAction> arrayList = new ArrayList<ApplicationAction>(this.proxyActions);
/* 111 */     ActionMap actionMap = getParent();
/* 112 */     while (actionMap != null) {
/* 113 */       if (actionMap instanceof ApplicationActionMap) {
/* 114 */         arrayList.addAll(((ApplicationActionMap)actionMap).proxyActions);
/*     */       }
/* 116 */       actionMap = actionMap.getParent();
/*     */     } 
/* 118 */     return Collections.unmodifiableList(arrayList);
/*     */   }
/*     */ 
/*     */   
/* 122 */   private String aString(String paramString1, String paramString2) { return (paramString1.length() == 0) ? paramString2 : paramString1; }
/*     */ 
/*     */   
/*     */   private void putAction(String paramString, ApplicationAction paramApplicationAction) {
/* 126 */     if (get(paramString) != null);
/*     */ 
/*     */     
/* 129 */     put(paramString, paramApplicationAction);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addAnnotationActions(ResourceMap paramResourceMap) {
/* 137 */     Class clazz = getActionsClass();
/*     */     
/* 139 */     for (Method method : clazz.getDeclaredMethods()) {
/* 140 */       Action action = method.getAnnotation(Action.class);
/* 141 */       if (action != null) {
/* 142 */         String str1 = method.getName();
/* 143 */         String str2 = aString(action.enabledProperty(), null);
/* 144 */         String str3 = aString(action.selectedProperty(), null);
/* 145 */         String str4 = aString(action.name(), str1);
/* 146 */         Task.BlockingScope blockingScope = action.block();
/* 147 */         ApplicationAction applicationAction = new ApplicationAction(this, paramResourceMap, str4, method, str2, str3, blockingScope);
/*     */         
/* 149 */         putAction(str4, applicationAction);
/*     */       } 
/*     */     } 
/*     */     
/* 153 */     ProxyActions proxyActions1 = (ProxyActions)clazz.getAnnotation(ProxyActions.class);
/* 154 */     if (proxyActions1 != null) {
/* 155 */       for (String str : proxyActions1.value()) {
/* 156 */         ApplicationAction applicationAction = new ApplicationAction(this, paramResourceMap, str);
/* 157 */         applicationAction.setEnabled(false);
/* 158 */         putAction(str, applicationAction);
/* 159 */         this.proxyActions.add(applicationAction);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void maybeAddActionsPCL() {
/* 171 */     boolean bool = false;
/* 172 */     Object[] arrayOfObject = keys();
/* 173 */     if (arrayOfObject != null) {
/* 174 */       for (Object object : arrayOfObject) {
/* 175 */         Action action = get(object);
/* 176 */         if (action instanceof ApplicationAction) {
/* 177 */           ApplicationAction applicationAction = (ApplicationAction)action;
/* 178 */           if (applicationAction.getEnabledProperty() != null || applicationAction.getSelectedProperty() != null) {
/*     */             
/* 180 */             bool = true;
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/* 185 */       if (bool) {
/*     */         try {
/* 187 */           Class clazz = getActionsClass();
/* 188 */           Method method = clazz.getMethod("addPropertyChangeListener", new Class[] { PropertyChangeListener.class });
/* 189 */           method.invoke(getActionsObject(), new Object[] { new ActionsPCL() });
/*     */         }
/* 191 */         catch (Exception exception) {
/* 192 */           String str = "addPropertyChangeListener undefined " + this.actionsClass;
/* 193 */           throw new Error(str, exception);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private class ActionsPCL
/*     */     implements PropertyChangeListener
/*     */   {
/*     */     private ActionsPCL() {}
/*     */     
/*     */     public void propertyChange(PropertyChangeEvent param1PropertyChangeEvent) {
/* 205 */       String str = param1PropertyChangeEvent.getPropertyName();
/* 206 */       Object[] arrayOfObject = ApplicationActionMap.this.keys();
/* 207 */       if (arrayOfObject != null)
/* 208 */         for (Object object : arrayOfObject) {
/* 209 */           Action action = ApplicationActionMap.this.get(object);
/* 210 */           if (action instanceof ApplicationAction) {
/* 211 */             ApplicationAction applicationAction = (ApplicationAction)action;
/* 212 */             if (str.equals(applicationAction.getEnabledProperty())) {
/* 213 */               applicationAction.forwardPropertyChangeEvent(param1PropertyChangeEvent, "enabled");
/*     */             }
/* 215 */             else if (str.equals(applicationAction.getSelectedProperty())) {
/* 216 */               applicationAction.forwardPropertyChangeEvent(param1PropertyChangeEvent, "selected");
/*     */             } 
/*     */           } 
/*     */         }  
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\jdesktop\application\ApplicationActionMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
/*     */ package org.jdesktop.application;
/*     */ 
/*     */ import java.awt.KeyboardFocusManager;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.WeakHashMap;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.Action;
/*     */ import javax.swing.ActionMap;
/*     */ import javax.swing.JComponent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ActionManager
/*     */   extends AbstractBean
/*     */ {
/*  34 */   private static final Logger logger = Logger.getLogger(ActionManager.class.getName());
/*     */   private final ApplicationContext context;
/*     */   private final WeakHashMap<Object, WeakReference<ApplicationActionMap>> actionMaps;
/*  37 */   private ApplicationActionMap globalActionMap = null;
/*     */   
/*     */   protected ActionManager(ApplicationContext paramApplicationContext) {
/*  40 */     if (paramApplicationContext == null) {
/*  41 */       throw new IllegalArgumentException("null context");
/*     */     }
/*  43 */     this.context = paramApplicationContext;
/*  44 */     this.actionMaps = new WeakHashMap<Object, WeakReference<ApplicationActionMap>>();
/*     */   }
/*     */ 
/*     */   
/*  48 */   protected final ApplicationContext getContext() { return this.context; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ApplicationActionMap createActionMapChain(Class<?> paramClass1, Class paramClass2, Object paramObject, ResourceMap paramResourceMap) {
/*  54 */     ArrayList<Class<?>> arrayList = new ArrayList();
/*  55 */     for (Class<?> clazz = paramClass1;; clazz = clazz.getSuperclass()) {
/*  56 */       arrayList.add(clazz);
/*  57 */       if (clazz.equals(paramClass2))
/*     */         break; 
/*  59 */     }  Collections.reverse(arrayList);
/*     */     
/*  61 */     ApplicationContext applicationContext = getContext();
/*  62 */     ActionMap actionMap = null;
/*  63 */     for (Class<?> clazz1 : arrayList) {
/*  64 */       ApplicationActionMap applicationActionMap = new ApplicationActionMap(applicationContext, clazz1, paramObject, paramResourceMap);
/*  65 */       applicationActionMap.setParent(actionMap);
/*  66 */       actionMap = applicationActionMap;
/*     */     } 
/*  68 */     return (ApplicationActionMap)actionMap;
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
/*     */   public ApplicationActionMap getActionMap() {
/*  96 */     if (this.globalActionMap == null) {
/*  97 */       ApplicationContext applicationContext = getContext();
/*  98 */       Application application = applicationContext.getApplication();
/*  99 */       Class clazz = applicationContext.getApplicationClass();
/* 100 */       ResourceMap resourceMap = applicationContext.getResourceMap();
/* 101 */       this.globalActionMap = createActionMapChain(clazz, Application.class, application, resourceMap);
/* 102 */       initProxyActionSupport();
/*     */     } 
/* 104 */     return this.globalActionMap;
/*     */   }
/*     */   
/*     */   private void initProxyActionSupport() {
/* 108 */     KeyboardFocusManager keyboardFocusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
/* 109 */     keyboardFocusManager.addPropertyChangeListener(new KeyboardFocusPCL());
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
/*     */   public ApplicationActionMap getActionMap(Class paramClass, Object paramObject) {
/* 153 */     if (paramClass == null) {
/* 154 */       throw new IllegalArgumentException("null actionsClass");
/*     */     }
/* 156 */     if (paramObject == null) {
/* 157 */       throw new IllegalArgumentException("null actionsObject");
/*     */     }
/* 159 */     if (!paramClass.isAssignableFrom(paramObject.getClass())) {
/* 160 */       throw new IllegalArgumentException("actionsObject not instanceof actionsClass");
/*     */     }
/* 162 */     synchronized (this.actionMaps) {
/* 163 */       WeakReference<ApplicationActionMap> weakReference = this.actionMaps.get(paramObject);
/* 164 */       ApplicationActionMap applicationActionMap = (weakReference != null) ? weakReference.get() : null;
/* 165 */       if (applicationActionMap == null || applicationActionMap.getActionsClass() != paramClass) {
/* 166 */         ApplicationContext applicationContext = getContext();
/* 167 */         Class<?> clazz = paramObject.getClass();
/* 168 */         ResourceMap resourceMap = applicationContext.getResourceMap(clazz, paramClass);
/* 169 */         applicationActionMap = createActionMapChain(clazz, paramClass, paramObject, resourceMap);
/* 170 */         ActionMap actionMap = applicationActionMap;
/* 171 */         while (actionMap.getParent() != null) {
/* 172 */           actionMap = actionMap.getParent();
/*     */         }
/* 174 */         actionMap.setParent(getActionMap());
/* 175 */         this.actionMaps.put(paramObject, new WeakReference<ApplicationActionMap>(applicationActionMap));
/*     */       } 
/* 177 */       return applicationActionMap;
/*     */     } 
/*     */   }
/*     */   
/*     */   private final class KeyboardFocusPCL
/*     */     implements PropertyChangeListener
/*     */   {
/* 184 */     private final TextActions textActions = new TextActions(ActionManager.this.getContext());
/*     */     
/*     */     public void propertyChange(PropertyChangeEvent param1PropertyChangeEvent) {
/* 187 */       if (param1PropertyChangeEvent.getPropertyName() == "permanentFocusOwner") {
/* 188 */         JComponent jComponent1 = ActionManager.this.getContext().getFocusOwner();
/* 189 */         Object object = param1PropertyChangeEvent.getNewValue();
/* 190 */         JComponent jComponent2 = (object instanceof JComponent) ? (JComponent)object : null;
/* 191 */         this.textActions.updateFocusOwner(jComponent1, jComponent2);
/* 192 */         ActionManager.this.getContext().setFocusOwner(jComponent2);
/* 193 */         ActionManager.this.updateAllProxyActions(jComponent1, jComponent2);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateAllProxyActions(JComponent paramJComponent1, JComponent paramJComponent2) {
/* 204 */     if (paramJComponent2 != null) {
/* 205 */       ActionMap actionMap = paramJComponent2.getActionMap();
/* 206 */       if (actionMap != null) {
/* 207 */         updateProxyActions(getActionMap(), actionMap, paramJComponent2);
/* 208 */         for (WeakReference<ApplicationActionMap> weakReference : this.actionMaps.values()) {
/* 209 */           ApplicationActionMap applicationActionMap = weakReference.get();
/* 210 */           if (applicationActionMap == null) {
/*     */             continue;
/*     */           }
/* 213 */           updateProxyActions(applicationActionMap, actionMap, paramJComponent2);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateProxyActions(ApplicationActionMap paramApplicationActionMap, ActionMap paramActionMap, JComponent paramJComponent) {
/* 225 */     for (ApplicationAction applicationAction : paramApplicationActionMap.getProxyActions()) {
/* 226 */       String str = applicationAction.getName();
/* 227 */       Action action = paramActionMap.get(str);
/* 228 */       if (action != null) {
/* 229 */         applicationAction.setProxy(action);
/* 230 */         applicationAction.setProxySource(paramJComponent);
/*     */         continue;
/*     */       } 
/* 233 */       applicationAction.setProxy(null);
/* 234 */       applicationAction.setProxySource(null);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\jdesktop\application\ActionManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
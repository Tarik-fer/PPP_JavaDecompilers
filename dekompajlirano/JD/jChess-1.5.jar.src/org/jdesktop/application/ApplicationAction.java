/*     */ package org.jdesktop.application;
/*     */ 
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.lang.annotation.Annotation;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.AbstractAction;
/*     */ import javax.swing.Action;
/*     */ import javax.swing.ActionMap;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.KeyStroke;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ApplicationAction
/*     */   extends AbstractAction
/*     */ {
/* 113 */   private static final Logger logger = Logger.getLogger(ApplicationAction.class.getName());
/*     */   private final ApplicationActionMap appAM;
/*     */   private final ResourceMap resourceMap;
/*     */   private final String actionName;
/*     */   private final Method actionMethod;
/*     */   private final String enabledProperty;
/*     */   private final Method isEnabledMethod;
/*     */   private final Method setEnabledMethod;
/*     */   private final String selectedProperty;
/*     */   private final Method isSelectedMethod;
/*     */   private final Method setSelectedMethod;
/*     */   private final Task.BlockingScope block;
/* 125 */   private Action proxy = null;
/* 126 */   private Object proxySource = null;
/* 127 */   private PropertyChangeListener proxyPCL = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String SELECTED_KEY = "SwingSelectedKey";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String DISPLAYED_MNEMONIC_INDEX_KEY = "SwingDisplayedMnemonicIndexKey";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String LARGE_ICON_KEY = "SwingLargeIconKey";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ApplicationAction(ApplicationActionMap paramApplicationActionMap, ResourceMap paramResourceMap, String paramString1, Method paramMethod, String paramString2, String paramString3, Task.BlockingScope paramBlockingScope) {
/* 206 */     if (paramApplicationActionMap == null) {
/* 207 */       throw new IllegalArgumentException("null appAM");
/*     */     }
/* 209 */     if (paramString1 == null) {
/* 210 */       throw new IllegalArgumentException("null baseName");
/*     */     }
/* 212 */     this.appAM = paramApplicationActionMap;
/* 213 */     this.resourceMap = paramResourceMap;
/* 214 */     this.actionName = paramString1;
/* 215 */     this.actionMethod = paramMethod;
/* 216 */     this.enabledProperty = paramString2;
/* 217 */     this.selectedProperty = paramString3;
/* 218 */     this.block = paramBlockingScope;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 223 */     if (paramString2 != null) {
/* 224 */       this.setEnabledMethod = propertySetMethod(paramString2, boolean.class);
/* 225 */       this.isEnabledMethod = propertyGetMethod(paramString2);
/* 226 */       if (this.isEnabledMethod == null) {
/* 227 */         throw newNoSuchPropertyException(paramString2);
/*     */       }
/*     */     } else {
/*     */       
/* 231 */       this.isEnabledMethod = null;
/* 232 */       this.setEnabledMethod = null;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 238 */     if (paramString3 != null) {
/* 239 */       this.setSelectedMethod = propertySetMethod(paramString3, boolean.class);
/* 240 */       this.isSelectedMethod = propertyGetMethod(paramString3);
/* 241 */       if (this.isSelectedMethod == null) {
/* 242 */         throw newNoSuchPropertyException(paramString3);
/*     */       }
/* 244 */       super.putValue("SwingSelectedKey", Boolean.FALSE);
/*     */     } else {
/*     */       
/* 247 */       this.isSelectedMethod = null;
/* 248 */       this.setSelectedMethod = null;
/*     */     } 
/*     */     
/* 251 */     if (paramResourceMap != null) {
/* 252 */       initActionProperties(paramResourceMap, paramString1);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 260 */   ApplicationAction(ApplicationActionMap paramApplicationActionMap, ResourceMap paramResourceMap, String paramString) { this(paramApplicationActionMap, paramResourceMap, paramString, null, null, null, Task.BlockingScope.NONE); }
/*     */ 
/*     */   
/*     */   private IllegalArgumentException newNoSuchPropertyException(String paramString) {
/* 264 */     String str1 = this.appAM.getActionsClass().getName();
/* 265 */     String str2 = String.format("no property named %s in %s", new Object[] { paramString, str1 });
/* 266 */     return new IllegalArgumentException(str2);
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
/* 278 */   String getEnabledProperty() { return this.enabledProperty; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 289 */   String getSelectedProperty() { return this.selectedProperty; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 302 */   public Action getProxy() { return this.proxy; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setProxy(Action paramAction) {
/* 330 */     Action action = this.proxy;
/* 331 */     this.proxy = paramAction;
/* 332 */     if (action != null) {
/* 333 */       action.removePropertyChangeListener(this.proxyPCL);
/* 334 */       this.proxyPCL = null;
/*     */     } 
/* 336 */     if (this.proxy != null) {
/* 337 */       updateProxyProperties();
/* 338 */       this.proxyPCL = new ProxyPCL();
/* 339 */       paramAction.addPropertyChangeListener(this.proxyPCL);
/*     */     }
/* 341 */     else if (action != null) {
/* 342 */       setEnabled(false);
/* 343 */       setSelected(false);
/*     */     } 
/* 345 */     firePropertyChange("proxy", action, this.proxy);
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
/* 358 */   public Object getProxySource() { return this.proxySource; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setProxySource(Object paramObject) {
/* 371 */     Object object = this.proxySource;
/* 372 */     this.proxySource = paramObject;
/* 373 */     firePropertyChange("proxySource", object, this.proxySource);
/*     */   }
/*     */   
/*     */   private void maybePutDescriptionValue(String paramString, Action paramAction) {
/* 377 */     Object object = paramAction.getValue(paramString);
/* 378 */     if (object instanceof String) {
/* 379 */       putValue(paramString, object);
/*     */     }
/*     */   }
/*     */   
/*     */   private void updateProxyProperties() {
/* 384 */     Action action = getProxy();
/* 385 */     if (action != null) {
/* 386 */       setEnabled(action.isEnabled());
/* 387 */       Object object = action.getValue("SwingSelectedKey");
/* 388 */       setSelected((object instanceof Boolean && ((Boolean)object).booleanValue()));
/* 389 */       maybePutDescriptionValue("ShortDescription", action);
/* 390 */       maybePutDescriptionValue("LongDescription", action);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private class ProxyPCL
/*     */     implements PropertyChangeListener
/*     */   {
/*     */     private ProxyPCL() {}
/*     */     
/*     */     public void propertyChange(PropertyChangeEvent param1PropertyChangeEvent) {
/* 401 */       String str = param1PropertyChangeEvent.getPropertyName();
/* 402 */       if (str == null || "enabled".equals(str) || "selected".equals(str) || "ShortDescription".equals(str) || "LongDescription".equals(str))
/*     */       {
/*     */ 
/*     */ 
/*     */         
/* 407 */         ApplicationAction.this.updateProxyProperties();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initActionProperties(ResourceMap paramResourceMap, String paramString) {
/* 424 */     boolean bool = false;
/* 425 */     Object object = null;
/*     */ 
/*     */     
/* 428 */     String str = paramResourceMap.getString(paramString + ".Action.text", new Object[0]);
/* 429 */     if (str != null) {
/* 430 */       MnemonicText.configure(this, str);
/* 431 */       bool = true;
/*     */     } 
/*     */     
/* 434 */     Integer integer1 = paramResourceMap.getKeyCode(paramString + ".Action.mnemonic");
/* 435 */     if (integer1 != null) {
/* 436 */       putValue("MnemonicKey", integer1);
/*     */     }
/*     */     
/* 439 */     Integer integer2 = paramResourceMap.getInteger(paramString + ".Action.displayedMnemonicIndex");
/* 440 */     if (integer2 != null) {
/* 441 */       putValue("SwingDisplayedMnemonicIndexKey", integer2);
/*     */     }
/*     */     
/* 444 */     KeyStroke keyStroke = paramResourceMap.getKeyStroke(paramString + ".Action.accelerator");
/* 445 */     if (keyStroke != null) {
/* 446 */       putValue("AcceleratorKey", keyStroke);
/*     */     }
/*     */     
/* 449 */     Icon icon1 = paramResourceMap.getIcon(paramString + ".Action.icon");
/* 450 */     if (icon1 != null) {
/* 451 */       putValue("SmallIcon", icon1);
/* 452 */       putValue("SwingLargeIconKey", icon1);
/* 453 */       bool = true;
/*     */     } 
/*     */     
/* 456 */     Icon icon2 = paramResourceMap.getIcon(paramString + ".Action.smallIcon");
/* 457 */     if (icon2 != null) {
/* 458 */       putValue("SmallIcon", icon2);
/* 459 */       bool = true;
/*     */     } 
/*     */     
/* 462 */     Icon icon3 = paramResourceMap.getIcon(paramString + ".Action.largeIcon");
/* 463 */     if (icon3 != null) {
/* 464 */       putValue("SwingLargeIconKey", icon3);
/* 465 */       bool = true;
/*     */     } 
/*     */     
/* 468 */     putValue("ShortDescription", paramResourceMap.getString(paramString + ".Action.shortDescription", new Object[0]));
/*     */ 
/*     */     
/* 471 */     putValue("LongDescription", paramResourceMap.getString(paramString + ".Action.longDescription", new Object[0]));
/*     */ 
/*     */     
/* 474 */     putValue("ActionCommandKey", paramResourceMap.getString(paramString + ".Action.command", new Object[0]));
/*     */ 
/*     */ 
/*     */     
/* 478 */     if (!bool) {
/* 479 */       putValue("Name", this.actionName);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/* 484 */   private String propertyMethodName(String paramString1, String paramString2) { return paramString1 + paramString2.substring(0, 1).toUpperCase() + paramString2.substring(1); }
/*     */ 
/*     */   
/*     */   private Method propertyGetMethod(String paramString) {
/* 488 */     String[] arrayOfString = { propertyMethodName("is", paramString), propertyMethodName("get", paramString) };
/*     */ 
/*     */ 
/*     */     
/* 492 */     Class clazz = this.appAM.getActionsClass();
/* 493 */     for (String str : arrayOfString) {
/*     */       try {
/* 495 */         return clazz.getMethod(str, new Class[0]);
/*     */       }
/* 497 */       catch (NoSuchMethodException noSuchMethodException) {}
/*     */     } 
/* 499 */     return null;
/*     */   }
/*     */   
/*     */   private Method propertySetMethod(String paramString, Class paramClass) {
/* 503 */     Class clazz = this.appAM.getActionsClass();
/*     */     try {
/* 505 */       return clazz.getMethod(propertyMethodName("set", paramString), new Class[] { paramClass });
/*     */     }
/* 507 */     catch (NoSuchMethodException noSuchMethodException) {
/* 508 */       return null;
/*     */     } 
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
/* 537 */   public String getName() { return this.actionName; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 546 */   public ResourceMap getResourceMap() { return this.resourceMap; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object getActionArgument(Class<ActionEvent> paramClass, String paramString, ActionEvent paramActionEvent) {
/* 602 */     Object object = null;
/* 603 */     if (paramClass == ActionEvent.class) {
/* 604 */       object = paramActionEvent;
/*     */     }
/* 606 */     else if (paramClass == Action.class) {
/* 607 */       ApplicationAction applicationAction = this;
/*     */     }
/* 609 */     else if (paramClass == ActionMap.class) {
/* 610 */       ApplicationActionMap applicationActionMap = this.appAM;
/*     */     }
/* 612 */     else if (paramClass == ResourceMap.class) {
/* 613 */       ResourceMap resourceMap1 = this.resourceMap;
/*     */     }
/* 615 */     else if (paramClass == ApplicationContext.class) {
/* 616 */       ApplicationContext applicationContext = this.appAM.getContext();
/*     */     }
/* 618 */     else if (paramClass == Application.class) {
/* 619 */       object = this.appAM.getContext().getApplication();
/*     */     } else {
/*     */       
/* 622 */       IllegalArgumentException illegalArgumentException = new IllegalArgumentException("unrecognized @Action method parameter");
/* 623 */       actionFailed(paramActionEvent, illegalArgumentException);
/*     */     } 
/* 625 */     return object;
/*     */   }
/*     */ 
/*     */   
/*     */   private Task.InputBlocker createInputBlocker(Task paramTask, ActionEvent paramActionEvent) {
/* 630 */     Object object = paramActionEvent.getSource();
/* 631 */     if (this.block == Task.BlockingScope.ACTION) {
/* 632 */       object = this;
/*     */     }
/* 634 */     return new DefaultInputBlocker(paramTask, this.block, object, this);
/*     */   }
/*     */   
/*     */   private void noProxyActionPerformed(ActionEvent paramActionEvent) {
/* 638 */     Object object = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 643 */     Annotation[][] arrayOfAnnotation = this.actionMethod.getParameterAnnotations();
/* 644 */     Class[] arrayOfClass = this.actionMethod.getParameterTypes();
/* 645 */     Object[] arrayOfObject = new Object[arrayOfClass.length];
/* 646 */     for (byte b = 0; b < arrayOfClass.length; b++) {
/* 647 */       String str = null;
/* 648 */       for (Annotation annotation : arrayOfAnnotation[b]) {
/* 649 */         if (annotation instanceof Action.Parameter) {
/* 650 */           str = ((Action.Parameter)annotation).value();
/*     */           break;
/*     */         } 
/*     */       } 
/* 654 */       arrayOfObject[b] = getActionArgument(arrayOfClass[b], str, paramActionEvent);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 661 */       Object object1 = this.appAM.getActionsObject();
/* 662 */       object = this.actionMethod.invoke(object1, arrayOfObject);
/*     */     }
/* 664 */     catch (Exception exception) {
/* 665 */       actionFailed(paramActionEvent, exception);
/*     */     } 
/*     */     
/* 668 */     if (object instanceof Task) {
/* 669 */       Task task = (Task)object;
/* 670 */       if (task.getInputBlocker() == null) {
/* 671 */         task.setInputBlocker(createInputBlocker(task, paramActionEvent));
/*     */       }
/* 673 */       ApplicationContext applicationContext = this.appAM.getContext();
/* 674 */       applicationContext.getTaskService().execute(task);
/*     */     } 
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
/*     */   public void actionPerformed(ActionEvent paramActionEvent) {
/* 692 */     Action action = getProxy();
/* 693 */     if (action != null) {
/* 694 */       paramActionEvent.setSource(getProxySource());
/* 695 */       action.actionPerformed(paramActionEvent);
/*     */     }
/* 697 */     else if (this.actionMethod != null) {
/* 698 */       noProxyActionPerformed(paramActionEvent);
/*     */     } 
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
/*     */   public boolean isEnabled() {
/* 715 */     if (getProxy() != null || this.isEnabledMethod == null) {
/* 716 */       return super.isEnabled();
/*     */     }
/*     */     
/*     */     try {
/* 720 */       Object object = this.isEnabledMethod.invoke(this.appAM.getActionsObject(), new Object[0]);
/* 721 */       return ((Boolean)object).booleanValue();
/*     */     }
/* 723 */     catch (Exception exception) {
/* 724 */       throw newInvokeError(this.isEnabledMethod, exception, new Object[0]);
/*     */     } 
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
/*     */   public void setEnabled(boolean paramBoolean) {
/* 742 */     if (getProxy() != null || this.setEnabledMethod == null) {
/* 743 */       super.setEnabled(paramBoolean);
/*     */     } else {
/*     */       
/*     */       try {
/* 747 */         this.setEnabledMethod.invoke(this.appAM.getActionsObject(), new Object[] { Boolean.valueOf(paramBoolean) });
/*     */       }
/* 749 */       catch (Exception exception) {
/* 750 */         throw newInvokeError(this.setEnabledMethod, exception, new Object[] { Boolean.valueOf(paramBoolean) });
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSelected() {
/* 767 */     if (getProxy() != null || this.isSelectedMethod == null) {
/* 768 */       Object object = getValue("SwingSelectedKey");
/* 769 */       return (object instanceof Boolean) ? ((Boolean)object).booleanValue() : false;
/*     */     } 
/*     */     
/*     */     try {
/* 773 */       Object object = this.isSelectedMethod.invoke(this.appAM.getActionsObject(), new Object[0]);
/* 774 */       return ((Boolean)object).booleanValue();
/*     */     }
/* 776 */     catch (Exception exception) {
/* 777 */       throw newInvokeError(this.isSelectedMethod, exception, new Object[0]);
/*     */     } 
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
/*     */   public void setSelected(boolean paramBoolean) {
/* 795 */     if (getProxy() != null || this.setSelectedMethod == null) {
/* 796 */       super.putValue("SwingSelectedKey", Boolean.valueOf(paramBoolean));
/*     */     } else {
/*     */       
/*     */       try {
/* 800 */         super.putValue("SwingSelectedKey", Boolean.valueOf(paramBoolean));
/* 801 */         if (paramBoolean != isSelected()) {
/* 802 */           this.setSelectedMethod.invoke(this.appAM.getActionsObject(), new Object[] { Boolean.valueOf(paramBoolean) });
/*     */         }
/*     */       }
/* 805 */       catch (Exception exception) {
/* 806 */         throw newInvokeError(this.setSelectedMethod, exception, new Object[] { Boolean.valueOf(paramBoolean) });
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
/*     */   
/*     */   public void putValue(String paramString, Object paramObject) {
/* 819 */     if ("SwingSelectedKey".equals(paramString) && paramObject instanceof Boolean) {
/* 820 */       setSelected(((Boolean)paramObject).booleanValue());
/*     */     } else {
/*     */       
/* 823 */       super.putValue(paramString, paramObject);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Error newInvokeError(Method paramMethod, Exception paramException, Object... paramVarArgs) {
/* 831 */     String str1 = (paramVarArgs.length == 0) ? "" : paramVarArgs[0].toString();
/* 832 */     for (byte b = 1; b < paramVarArgs.length; b++) {
/* 833 */       str1 = str1 + ", " + paramVarArgs[b];
/*     */     }
/* 835 */     String str2 = this.appAM.getActionsObject().getClass().getName();
/* 836 */     String str3 = String.format("%s.%s(%s) failed", new Object[] { str2, paramMethod, str1 });
/* 837 */     return new Error(str3, paramException);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void forwardPropertyChangeEvent(PropertyChangeEvent paramPropertyChangeEvent, String paramString) {
/* 847 */     if ("selected".equals(paramString) && paramPropertyChangeEvent.getNewValue() instanceof Boolean) {
/* 848 */       putValue("SwingSelectedKey", paramPropertyChangeEvent.getNewValue());
/*     */     }
/* 850 */     firePropertyChange(paramString, paramPropertyChangeEvent.getOldValue(), paramPropertyChangeEvent.getNewValue());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 859 */   private void actionFailed(ActionEvent paramActionEvent, Exception paramException) { throw new Error(paramException); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 874 */     StringBuilder stringBuilder = new StringBuilder();
/* 875 */     stringBuilder.append(getClass().getName());
/* 876 */     stringBuilder.append(" ");
/* 877 */     boolean bool = isEnabled();
/* 878 */     if (!bool) stringBuilder.append("("); 
/* 879 */     stringBuilder.append(getName());
/* 880 */     Object object1 = getValue("SwingSelectedKey");
/* 881 */     if (object1 instanceof Boolean && (
/* 882 */       (Boolean)object1).booleanValue()) {
/* 883 */       stringBuilder.append("+");
/*     */     }
/*     */     
/* 886 */     if (!bool) stringBuilder.append(")"); 
/* 887 */     Object object2 = getValue("Name");
/* 888 */     if (object2 instanceof String) {
/* 889 */       stringBuilder.append(" \"");
/* 890 */       stringBuilder.append((String)object2);
/* 891 */       stringBuilder.append("\"");
/*     */     } 
/* 893 */     this.proxy = getProxy();
/* 894 */     if (this.proxy != null) {
/* 895 */       stringBuilder.append(" Proxy for: ");
/* 896 */       stringBuilder.append(this.proxy.toString());
/*     */     } 
/* 898 */     return stringBuilder.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\jdesktop\application\ApplicationAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
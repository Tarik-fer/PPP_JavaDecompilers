// 
// Decompiled by Procyon v0.5.36
// 

package org.jdesktop.application;

import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import java.awt.Toolkit;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;
import java.awt.datatransfer.Clipboard;
import javax.swing.JComponent;
import java.util.List;
import java.util.logging.Logger;

public class ApplicationContext extends AbstractBean
{
    private static final Logger logger;
    private final List<TaskService> taskServices;
    private final List<TaskService> taskServicesReadOnly;
    private ResourceManager resourceManager;
    private ActionManager actionManager;
    private LocalStorage localStorage;
    private SessionStorage sessionStorage;
    private Application application;
    private Class applicationClass;
    private JComponent focusOwner;
    private Clipboard clipboard;
    private Throwable uncaughtException;
    private TaskMonitor taskMonitor;
    
    protected ApplicationContext() {
        this.application = null;
        this.applicationClass = null;
        this.focusOwner = null;
        this.clipboard = null;
        this.uncaughtException = null;
        this.taskMonitor = null;
        this.resourceManager = new ResourceManager(this);
        this.actionManager = new ActionManager(this);
        this.localStorage = new LocalStorage(this);
        this.sessionStorage = new SessionStorage(this);
        (this.taskServices = new CopyOnWriteArrayList<TaskService>()).add(new TaskService("default"));
        this.taskServicesReadOnly = Collections.unmodifiableList((List<? extends TaskService>)this.taskServices);
    }
    
    public final synchronized Class getApplicationClass() {
        return this.applicationClass;
    }
    
    public final synchronized void setApplicationClass(final Class applicationClass) {
        if (this.application != null) {
            throw new IllegalStateException("application has been launched");
        }
        this.applicationClass = applicationClass;
    }
    
    public final synchronized Application getApplication() {
        return this.application;
    }
    
    synchronized void setApplication(final Application application) {
        if (this.application != null) {
            throw new IllegalStateException("application has already been launched");
        }
        this.application = application;
    }
    
    public final ResourceManager getResourceManager() {
        return this.resourceManager;
    }
    
    protected void setResourceManager(final ResourceManager resourceManager) {
        if (resourceManager == null) {
            throw new IllegalArgumentException("null resourceManager");
        }
        this.firePropertyChange("resourceManager", this.resourceManager, this.resourceManager = resourceManager);
    }
    
    public final ResourceMap getResourceMap(final Class clazz) {
        return this.getResourceManager().getResourceMap(clazz, clazz);
    }
    
    public final ResourceMap getResourceMap(final Class clazz, final Class clazz2) {
        return this.getResourceManager().getResourceMap(clazz, clazz2);
    }
    
    public final ResourceMap getResourceMap() {
        return this.getResourceManager().getResourceMap();
    }
    
    public final ActionManager getActionManager() {
        return this.actionManager;
    }
    
    protected void setActionManager(final ActionManager actionManager) {
        if (actionManager == null) {
            throw new IllegalArgumentException("null actionManager");
        }
        this.firePropertyChange("actionManager", this.actionManager, this.actionManager = actionManager);
    }
    
    public final ApplicationActionMap getActionMap() {
        return this.getActionManager().getActionMap();
    }
    
    public final ApplicationActionMap getActionMap(final Class clazz, final Object o) {
        return this.getActionManager().getActionMap(clazz, o);
    }
    
    public final ApplicationActionMap getActionMap(final Object o) {
        if (o == null) {
            throw new IllegalArgumentException("null actionsObject");
        }
        return this.getActionManager().getActionMap(o.getClass(), o);
    }
    
    public final LocalStorage getLocalStorage() {
        return this.localStorage;
    }
    
    protected void setLocalStorage(final LocalStorage localStorage) {
        if (localStorage == null) {
            throw new IllegalArgumentException("null localStorage");
        }
        this.firePropertyChange("localStorage", this.localStorage, this.localStorage = localStorage);
    }
    
    public final SessionStorage getSessionStorage() {
        return this.sessionStorage;
    }
    
    protected void setSessionStorage(final SessionStorage sessionStorage) {
        if (sessionStorage == null) {
            throw new IllegalArgumentException("null sessionStorage");
        }
        this.firePropertyChange("sessionStorage", this.sessionStorage, this.sessionStorage = sessionStorage);
    }
    
    public Clipboard getClipboard() {
        if (this.clipboard == null) {
            try {
                this.clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            }
            catch (SecurityException ex) {
                this.clipboard = new Clipboard("sandbox");
            }
        }
        return this.clipboard;
    }
    
    public JComponent getFocusOwner() {
        return this.focusOwner;
    }
    
    void setFocusOwner(final JComponent focusOwner) {
        this.firePropertyChange("focusOwner", this.focusOwner, this.focusOwner = focusOwner);
    }
    
    private List<TaskService> copyTaskServices() {
        return new ArrayList<TaskService>(this.taskServices);
    }
    
    public void addTaskService(final TaskService taskService) {
        if (taskService == null) {
            throw new IllegalArgumentException("null taskService");
        }
        Object copyTaskServices = null;
        Object copyTaskServices2 = null;
        boolean b = false;
        synchronized (this.taskServices) {
            if (!this.taskServices.contains(taskService)) {
                copyTaskServices = this.copyTaskServices();
                this.taskServices.add(taskService);
                copyTaskServices2 = this.copyTaskServices();
                b = true;
            }
        }
        if (b) {
            this.firePropertyChange("taskServices", copyTaskServices, copyTaskServices2);
        }
    }
    
    public void removeTaskService(final TaskService taskService) {
        if (taskService == null) {
            throw new IllegalArgumentException("null taskService");
        }
        Object copyTaskServices = null;
        Object copyTaskServices2 = null;
        boolean b = false;
        synchronized (this.taskServices) {
            if (this.taskServices.contains(taskService)) {
                copyTaskServices = this.copyTaskServices();
                this.taskServices.remove(taskService);
                copyTaskServices2 = this.copyTaskServices();
                b = true;
            }
        }
        if (b) {
            this.firePropertyChange("taskServices", copyTaskServices, copyTaskServices2);
        }
    }
    
    public TaskService getTaskService(final String s) {
        if (s == null) {
            throw new IllegalArgumentException("null name");
        }
        for (final TaskService taskService : this.taskServices) {
            if (s.equals(taskService.getName())) {
                return taskService;
            }
        }
        return null;
    }
    
    public final TaskService getTaskService() {
        return this.getTaskService("default");
    }
    
    public List<TaskService> getTaskServices() {
        return this.taskServicesReadOnly;
    }
    
    public final TaskMonitor getTaskMonitor() {
        if (this.taskMonitor == null) {
            this.taskMonitor = new TaskMonitor(this);
        }
        return this.taskMonitor;
    }
    
    static {
        logger = Logger.getLogger(ApplicationContext.class.getName());
    }
}

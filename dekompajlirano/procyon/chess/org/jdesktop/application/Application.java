// 
// Decompiled by Procyon v0.5.36
// 

package org.jdesktop.application;

import java.util.EventListener;
import java.awt.Rectangle;
import java.awt.ActiveEvent;
import java.awt.event.PaintEvent;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.EventObject;
import java.awt.EventQueue;
import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.Toolkit;
import javax.swing.JPanel;
import java.lang.reflect.Constructor;
import javax.swing.UIManager;
import java.beans.Beans;
import javax.swing.SwingUtilities;
import java.util.logging.Level;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;
import java.util.logging.Logger;

@ProxyActions({ "cut", "copy", "paste", "delete" })
public abstract class Application extends AbstractBean
{
    private static final Logger logger;
    private static Application application;
    private final List<ExitListener> exitListeners;
    private final ApplicationContext context;
    
    protected Application() {
        this.exitListeners = new CopyOnWriteArrayList<ExitListener>();
        this.context = new ApplicationContext();
    }
    
    public static synchronized <T extends Application> void launch(final Class<T> clazz, final String[] array) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    Application.application = Application.create((Class<Application>)clazz);
                    Application.application.initialize(array);
                    Application.application.startup();
                    Application.application.waitForReady();
                }
                catch (Exception ex) {
                    final String format = String.format("Application %s failed to launch", clazz);
                    Application.logger.log(Level.SEVERE, format, ex);
                    throw new Error(format, ex);
                }
            }
        });
    }
    
    static <T extends Application> T create(final Class<T> applicationClass) throws Exception {
        if (!Beans.isDesignTime()) {
            try {
                System.setProperty("java.net.useSystemProxies", "true");
            }
            catch (SecurityException ex2) {}
        }
        final Constructor<T> declaredConstructor = applicationClass.getDeclaredConstructor((Class<?>[])new Class[0]);
        if (!declaredConstructor.isAccessible()) {
            try {
                declaredConstructor.setAccessible(true);
            }
            catch (SecurityException ex3) {}
        }
        final Application application = declaredConstructor.newInstance(new Object[0]);
        final ApplicationContext context = application.getContext();
        context.setApplicationClass(applicationClass);
        context.setApplication(application);
        final ResourceMap resourceMap = context.getResourceMap();
        resourceMap.putResource("platform", platform());
        if (!Beans.isDesignTime()) {
            final String s = "Application.lookAndFeel";
            final String string = resourceMap.getString(s, new Object[0]);
            final String lookAndFeel = (string == null) ? "system" : string;
            try {
                if (lookAndFeel.equalsIgnoreCase("system")) {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
                else if (!lookAndFeel.equalsIgnoreCase("default")) {
                    UIManager.setLookAndFeel(lookAndFeel);
                }
            }
            catch (Exception ex) {
                Application.logger.log(Level.WARNING, "Couldn't set LookandFeel " + s + " = \"" + string + "\"", ex);
            }
        }
        return (T)application;
    }
    
    private static String platform() {
        String s = "default";
        try {
            final String property = System.getProperty("os.name");
            if (property != null && property.toLowerCase().startsWith("mac os x")) {
                s = "osx";
            }
        }
        catch (SecurityException ex) {}
        return s;
    }
    
    void waitForReady() {
        new DoWaitForEmptyEventQ().execute();
    }
    
    protected void initialize(final String[] array) {
    }
    
    protected abstract void startup();
    
    protected void ready() {
    }
    
    protected void shutdown() {
    }
    
    private void waitForEmptyEventQ() {
        boolean eventQEmpty = false;
        final JPanel panel = new JPanel();
        final EventQueue systemEventQueue = Toolkit.getDefaultToolkit().getSystemEventQueue();
        while (!eventQEmpty) {
            final NotifyingEvent notifyingEvent = new NotifyingEvent(panel);
            systemEventQueue.postEvent(notifyingEvent);
            synchronized (notifyingEvent) {
                while (!notifyingEvent.isDispatched()) {
                    try {
                        notifyingEvent.wait();
                    }
                    catch (InterruptedException ex) {}
                }
                eventQEmpty = notifyingEvent.isEventQEmpty();
            }
        }
    }
    
    public final void exit() {
        this.exit(null);
    }
    
    public void exit(final EventObject eventObject) {
        final Iterator<ExitListener> iterator = this.exitListeners.iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().canExit(eventObject)) {
                return;
            }
        }
        try {
            for (final ExitListener exitListener : this.exitListeners) {
                try {
                    exitListener.willExit(eventObject);
                }
                catch (Exception ex) {
                    Application.logger.log(Level.WARNING, "ExitListener.willExit() failed", ex);
                }
            }
            this.shutdown();
        }
        catch (Exception ex2) {
            Application.logger.log(Level.WARNING, "unexpected error in Application.shutdown()", ex2);
        }
        finally {
            this.end();
        }
    }
    
    protected void end() {
        Runtime.getRuntime().exit(0);
    }
    
    public void addExitListener(final ExitListener exitListener) {
        this.exitListeners.add(exitListener);
    }
    
    public void removeExitListener(final ExitListener exitListener) {
        this.exitListeners.remove(exitListener);
    }
    
    public ExitListener[] getExitListeners() {
        return this.exitListeners.toArray(new ExitListener[this.exitListeners.size()]);
    }
    
    @Action
    public void quit(final ActionEvent actionEvent) {
        this.exit(actionEvent);
    }
    
    public final ApplicationContext getContext() {
        return this.context;
    }
    
    public static synchronized <T extends Application> T getInstance(final Class<T> clazz) {
        if (Application.application == null) {
            try {
                Application.application = create((Class<Application>)clazz);
            }
            catch (Exception ex) {
                throw new Error(String.format("Couldn't construct %s", clazz), ex);
            }
        }
        return clazz.cast(Application.application);
    }
    
    public static synchronized Application getInstance() {
        if (Application.application == null) {
            Application.application = new NoApplication();
        }
        return Application.application;
    }
    
    public void show(final View view) {
        final Window window = (Window)view.getRootPane().getParent();
        if (window != null) {
            window.pack();
            window.setVisible(true);
        }
    }
    
    public void hide(final View view) {
        view.getRootPane().getParent().setVisible(false);
    }
    
    static {
        logger = Logger.getLogger(Application.class.getName());
        Application.application = null;
    }
    
    private static class NotifyingEvent extends PaintEvent implements ActiveEvent
    {
        private boolean dispatched;
        private boolean qEmpty;
        
        NotifyingEvent(final Component component) {
            super(component, 801, null);
            this.dispatched = false;
            this.qEmpty = false;
        }
        
        synchronized boolean isDispatched() {
            return this.dispatched;
        }
        
        synchronized boolean isEventQEmpty() {
            return this.qEmpty;
        }
        
        public void dispatch() {
            final EventQueue systemEventQueue = Toolkit.getDefaultToolkit().getSystemEventQueue();
            synchronized (this) {
                this.qEmpty = (systemEventQueue.peekEvent() == null);
                this.dispatched = true;
                this.notifyAll();
            }
        }
    }
    
    private class DoWaitForEmptyEventQ extends Task<Void, Void>
    {
        DoWaitForEmptyEventQ() {
            super(Application.this);
        }
        
        @Override
        protected Void doInBackground() {
            Application.this.waitForEmptyEventQ();
            return null;
        }
        
        @Override
        protected void finished() {
            Application.this.ready();
        }
    }
    
    private static class NoApplication extends Application
    {
        protected NoApplication() {
            final ApplicationContext context = this.getContext();
            context.setApplicationClass(this.getClass());
            context.setApplication(this);
            context.getResourceMap().putResource("platform", platform());
        }
        
        @Override
        protected void startup() {
        }
    }
    
    public interface ExitListener extends EventListener
    {
        boolean canExit(final EventObject p0);
        
        void willExit(final EventObject p0);
    }
}

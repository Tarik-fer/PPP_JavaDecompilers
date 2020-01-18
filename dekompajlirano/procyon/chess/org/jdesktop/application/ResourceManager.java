// 
// Decompiled by Procyon v0.5.36
// 

package org.jdesktop.application;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class ResourceManager extends AbstractBean
{
    private static final Logger logger;
    private final Map<String, ResourceMap> resourceMaps;
    private final ApplicationContext context;
    private List<String> applicationBundleNames;
    private ResourceMap appResourceMap;
    
    protected ResourceManager(final ApplicationContext context) {
        this.applicationBundleNames = null;
        this.appResourceMap = null;
        if (context == null) {
            throw new IllegalArgumentException("null context");
        }
        this.context = context;
        this.resourceMaps = new ConcurrentHashMap<String, ResourceMap>();
    }
    
    protected final ApplicationContext getContext() {
        return this.context;
    }
    
    private List<String> allBundleNames(final Class clazz, final Class clazz2) {
        final ArrayList<String> list = new ArrayList<String>();
        for (Class superclass = clazz2.getSuperclass(), superclass2 = clazz; superclass2 != superclass; superclass2 = superclass2.getSuperclass()) {
            list.addAll((Collection<?>)this.getClassBundleNames(superclass2));
        }
        return (List<String>)Collections.unmodifiableList((List<?>)list);
    }
    
    private String bundlePackageName(final String s) {
        final int lastIndex = s.lastIndexOf(".");
        return (lastIndex == -1) ? "" : s.substring(0, lastIndex);
    }
    
    private ResourceMap createResourceMapChain(final ClassLoader classLoader, final ResourceMap resourceMap, final ListIterator<String> listIterator) {
        if (!listIterator.hasNext()) {
            return resourceMap;
        }
        final String s = listIterator.next();
        final String bundlePackageName = this.bundlePackageName(s);
        final ArrayList<String> list = new ArrayList<String>();
        list.add(s);
        while (listIterator.hasNext()) {
            final String s2 = listIterator.next();
            if (!bundlePackageName.equals(this.bundlePackageName(s2))) {
                listIterator.previous();
                break;
            }
            list.add(s2);
        }
        return this.createResourceMap(classLoader, this.createResourceMapChain(classLoader, resourceMap, listIterator), list);
    }
    
    private ResourceMap getApplicationResourceMap() {
        if (this.appResourceMap == null) {
            final List<String> applicationBundleNames = this.getApplicationBundleNames();
            Class<Application> applicationClass = (Class<Application>)this.getContext().getApplicationClass();
            if (applicationClass == null) {
                ResourceManager.logger.warning("getApplicationResourceMap(): no Application class");
                applicationClass = Application.class;
            }
            this.appResourceMap = this.createResourceMapChain(applicationClass.getClassLoader(), null, applicationBundleNames.listIterator());
        }
        return this.appResourceMap;
    }
    
    private ResourceMap getClassResourceMap(final Class clazz, final Class clazz2) {
        final String string = clazz.getName() + clazz2.getName();
        ResourceMap resourceMapChain = this.resourceMaps.get(string);
        if (resourceMapChain == null) {
            resourceMapChain = this.createResourceMapChain(clazz.getClassLoader(), this.getResourceMap(), this.allBundleNames(clazz, clazz2).listIterator());
            this.resourceMaps.put(string, resourceMapChain);
        }
        return resourceMapChain;
    }
    
    public ResourceMap getResourceMap(final Class clazz, final Class clazz2) {
        if (clazz == null) {
            throw new IllegalArgumentException("null startClass");
        }
        if (clazz2 == null) {
            throw new IllegalArgumentException("null stopClass");
        }
        if (!clazz2.isAssignableFrom(clazz)) {
            throw new IllegalArgumentException("startClass is not a subclass, or the same as, stopClass");
        }
        return this.getClassResourceMap(clazz, clazz2);
    }
    
    public final ResourceMap getResourceMap(final Class clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("null class");
        }
        return this.getResourceMap(clazz, clazz);
    }
    
    public ResourceMap getResourceMap() {
        return this.getApplicationResourceMap();
    }
    
    public List<String> getApplicationBundleNames() {
        if (this.applicationBundleNames == null) {
            final Class applicationClass = this.getContext().getApplicationClass();
            if (applicationClass == null) {
                return this.allBundleNames(Application.class, Application.class);
            }
            this.applicationBundleNames = this.allBundleNames(applicationClass, Application.class);
        }
        return this.applicationBundleNames;
    }
    
    public void setApplicationBundleNames(final List<String> list) {
        if (list != null) {
            for (final String s : list) {
                if (s == null || list.size() == 0) {
                    throw new IllegalArgumentException("invalid bundle name \"" + s + "\"");
                }
            }
        }
        final List<String> applicationBundleNames = this.applicationBundleNames;
        if (list != null) {
            this.applicationBundleNames = Collections.unmodifiableList((List<? extends String>)new ArrayList<String>(list));
        }
        else {
            this.applicationBundleNames = null;
        }
        this.resourceMaps.clear();
        this.firePropertyChange("applicationBundleNames", applicationBundleNames, this.applicationBundleNames);
    }
    
    private String classBundleBaseName(final Class clazz) {
        final String name = clazz.getName();
        final StringBuffer sb = new StringBuffer();
        final int lastIndex = name.lastIndexOf(46);
        if (lastIndex > 0) {
            sb.append(name.substring(0, lastIndex));
            sb.append(".resources.");
            sb.append(clazz.getSimpleName());
        }
        else {
            sb.append("resources.");
            sb.append(clazz.getSimpleName());
        }
        return sb.toString();
    }
    
    protected List<String> getClassBundleNames(final Class clazz) {
        return Collections.singletonList(this.classBundleBaseName(clazz));
    }
    
    protected ResourceMap createResourceMap(final ClassLoader classLoader, final ResourceMap resourceMap, final List<String> list) {
        return new ResourceMap(resourceMap, classLoader, list);
    }
    
    public String getPlatform() {
        return this.getResourceMap().getString("platform", new Object[0]);
    }
    
    public void setPlatform(final String s) {
        if (s == null) {
            throw new IllegalArgumentException("null platform");
        }
        this.getResourceMap().putResource("platform", s);
    }
    
    static {
        logger = Logger.getLogger(ResourceManager.class.getName());
    }
}

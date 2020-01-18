// 
// Decompiled by Procyon v0.5.36
// 

package org.jdesktop.application;

import javax.swing.border.EmptyBorder;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Image;
import java.net.URL;
import java.util.regex.Matcher;
import java.lang.reflect.Array;
import java.util.regex.Pattern;
import java.lang.reflect.Field;
import java.awt.Container;
import javax.swing.JMenu;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.lang.reflect.Method;
import javax.swing.JLabel;
import javax.swing.AbstractButton;
import java.beans.PropertyDescriptor;
import java.awt.Component;
import javax.swing.KeyStroke;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import java.util.HashSet;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Set;
import java.util.Locale;
import java.util.Map;
import java.util.List;
import java.util.logging.Logger;

public class ResourceMap
{
    private static Logger logger;
    private static final Object nullResource;
    private final ClassLoader classLoader;
    private final ResourceMap parent;
    private final List<String> bundleNames;
    private final String resourcesDir;
    private Map<String, Object> bundlesMapP;
    private Locale locale;
    private Set<String> bundlesMapKeysP;
    private boolean bundlesLoaded;
    
    public ResourceMap(final ResourceMap parent, final ClassLoader classLoader, final List<String> list) {
        this.bundlesMapP = null;
        this.locale = Locale.getDefault();
        this.bundlesMapKeysP = null;
        this.bundlesLoaded = false;
        if (classLoader == null) {
            throw new IllegalArgumentException("null ClassLoader");
        }
        if (list == null || list.size() == 0) {
            throw new IllegalArgumentException("no bundle specified");
        }
        for (final String s : list) {
            if (s == null || s.length() == 0) {
                throw new IllegalArgumentException("invalid bundleName: \"" + s + "\"");
            }
        }
        final String bundlePackageName = this.bundlePackageName(list.get(0));
        for (final String s2 : list) {
            if (!bundlePackageName.equals(this.bundlePackageName(s2))) {
                throw new IllegalArgumentException("bundles not colocated: \"" + s2 + "\" != \"" + bundlePackageName + "\"");
            }
        }
        this.parent = parent;
        this.classLoader = classLoader;
        this.bundleNames = Collections.unmodifiableList((List<? extends String>)new ArrayList<String>(list));
        this.resourcesDir = bundlePackageName.replace(".", "/") + "/";
    }
    
    private String bundlePackageName(final String s) {
        final int lastIndex = s.lastIndexOf(".");
        return (lastIndex == -1) ? "" : s.substring(0, lastIndex);
    }
    
    public ResourceMap(final ResourceMap resourceMap, final ClassLoader classLoader, final String... array) {
        this(resourceMap, classLoader, Arrays.asList(array));
    }
    
    public ResourceMap getParent() {
        return this.parent;
    }
    
    public List<String> getBundleNames() {
        return this.bundleNames;
    }
    
    public ClassLoader getClassLoader() {
        return this.classLoader;
    }
    
    public String getResourcesDir() {
        return this.resourcesDir;
    }
    
    private synchronized Map<String, Object> getBundlesMap() {
        final Locale default1 = Locale.getDefault();
        if (this.locale != default1) {
            this.bundlesLoaded = false;
            this.locale = default1;
        }
        if (!this.bundlesLoaded) {
            final ConcurrentHashMap<String, Object> bundlesMapP = new ConcurrentHashMap<String, Object>();
            for (int i = this.bundleNames.size() - 1; i >= 0; --i) {
                try {
                    final ResourceBundle bundle = ResourceBundle.getBundle(this.bundleNames.get(i), this.locale, this.classLoader);
                    final Enumeration<String> keys = bundle.getKeys();
                    while (keys.hasMoreElements()) {
                        final String s = keys.nextElement();
                        bundlesMapP.put(s, bundle.getObject(s));
                    }
                }
                catch (MissingResourceException ex) {}
            }
            this.bundlesMapP = bundlesMapP;
            this.bundlesLoaded = true;
        }
        return this.bundlesMapP;
    }
    
    private void checkNullKey(final String s) {
        if (s == null) {
            throw new IllegalArgumentException("null key");
        }
    }
    
    private synchronized Set<String> getBundlesMapKeys() {
        if (this.bundlesMapKeysP == null) {
            final HashSet<String> set = new HashSet<String>(this.getResourceKeySet());
            final ResourceMap parent = this.getParent();
            if (parent != null) {
                set.addAll((Collection<?>)parent.keySet());
            }
            this.bundlesMapKeysP = (Set<String>)Collections.unmodifiableSet((Set<?>)set);
        }
        return this.bundlesMapKeysP;
    }
    
    public Set<String> keySet() {
        return this.getBundlesMapKeys();
    }
    
    public boolean containsKey(final String s) {
        this.checkNullKey(s);
        if (this.containsResourceKey(s)) {
            return true;
        }
        final ResourceMap parent = this.getParent();
        return parent != null && parent.containsKey(s);
    }
    
    protected Set<String> getResourceKeySet() {
        final Map<String, Object> bundlesMap = this.getBundlesMap();
        if (bundlesMap == null) {
            return Collections.emptySet();
        }
        return bundlesMap.keySet();
    }
    
    protected boolean containsResourceKey(final String s) {
        this.checkNullKey(s);
        final Map<String, Object> bundlesMap = this.getBundlesMap();
        return bundlesMap != null && bundlesMap.containsKey(s);
    }
    
    protected Object getResource(final String s) {
        this.checkNullKey(s);
        final Map<String, Object> bundlesMap = this.getBundlesMap();
        final Object o = (bundlesMap != null) ? bundlesMap.get(s) : null;
        return (o == ResourceMap.nullResource) ? null : o;
    }
    
    protected void putResource(final String s, final Object o) {
        this.checkNullKey(s);
        final Map<String, Object> bundlesMap = this.getBundlesMap();
        if (bundlesMap != null) {
            bundlesMap.put(s, (o == null) ? ResourceMap.nullResource : o);
        }
    }
    
    public Object getObject(final String s, Class s2) {
        this.checkNullKey(s);
        if (s2 == null) {
            throw new IllegalArgumentException("null type");
        }
        if (((Class)s2).isPrimitive()) {
            if (s2 == Boolean.TYPE) {
                s2 = Boolean.class;
            }
            else if (s2 == Character.TYPE) {
                s2 = Character.class;
            }
            else if (s2 == Byte.TYPE) {
                s2 = Byte.class;
            }
            else if (s2 == Short.TYPE) {
                s2 = Short.class;
            }
            else if (s2 == Integer.TYPE) {
                s2 = Integer.class;
            }
            else if (s2 == Long.TYPE) {
                s2 = Long.class;
            }
            else if (s2 == Float.TYPE) {
                s2 = Float.class;
            }
            else if (s2 == Double.TYPE) {
                s2 = Double.class;
            }
        }
        Object o = null;
        ResourceMap parent;
        for (parent = this; parent != null; parent = parent.getParent()) {
            if (parent.containsResourceKey(s)) {
                o = parent.getResource(s);
                break;
            }
        }
        if (o instanceof String && ((String)o).contains("${")) {
            o = this.evaluateStringExpression((String)o);
            parent.putResource(s, o);
        }
        if (o != null && !((Class)s2).isAssignableFrom(((String)o).getClass())) {
            if (!(o instanceof String)) {
                throw new LookupException("named resource has wrong type", s, (Class)s2);
            }
            final ResourceConverter forType = ResourceConverter.forType((Class)s2);
            if (forType == null) {
                throw new LookupException("no StringConverter for required type", s, (Class)s2);
            }
            final String s3 = (String)o;
            try {
                o = forType.parseString(s3, parent);
                parent.putResource(s, o);
            }
            catch (ResourceConverter.ResourceConverterException ex2) {
                final LookupException ex = new LookupException("string conversion failed", s, (Class)s2);
                ex.initCause(ex2);
                throw ex;
            }
        }
        return o;
    }
    
    private String evaluateStringExpression(final String s) {
        if (s.trim().equals("${null}")) {
            return null;
        }
        final StringBuffer sb = new StringBuffer();
        int n = 0;
        int index;
        while ((index = s.indexOf("${", n)) != -1) {
            if (index == 0 || (index > 0 && s.charAt(index - 1) != '\\')) {
                final int index2 = s.indexOf("}", index);
                if (index2 == -1 || index2 <= index + 2) {
                    throw new LookupException(String.format("no closing brace in \"%s\"", s), "<not found>", String.class);
                }
                final String substring = s.substring(index + 2, index2);
                final String string = this.getString(substring, new Object[0]);
                sb.append(s.substring(n, index));
                if (string == null) {
                    throw new LookupException(String.format("no value for \"%s\" in \"%s\"", substring, s), substring, String.class);
                }
                sb.append(string);
                n = index2 + 1;
            }
            else {
                sb.append(s.substring(n, index - 1));
                sb.append("${");
                n = index + 2;
            }
        }
        sb.append(s.substring(n));
        return sb.toString();
    }
    
    public String getString(final String s, final Object... array) {
        if (array.length == 0) {
            return (String)this.getObject(s, String.class);
        }
        final String s2 = (String)this.getObject(s, String.class);
        return (s2 == null) ? null : String.format(s2, array);
    }
    
    public final Boolean getBoolean(final String s) {
        return (Boolean)this.getObject(s, Boolean.class);
    }
    
    public final Integer getInteger(final String s) {
        return (Integer)this.getObject(s, Integer.class);
    }
    
    public final Long getLong(final String s) {
        return (Long)this.getObject(s, Long.class);
    }
    
    public final Short getShort(final String s) {
        return (Short)this.getObject(s, Short.class);
    }
    
    public final Byte getByte(final String s) {
        return (Byte)this.getObject(s, Byte.class);
    }
    
    public final Float getFloat(final String s) {
        return (Float)this.getObject(s, Float.class);
    }
    
    public final Double getDouble(final String s) {
        return (Double)this.getObject(s, Double.class);
    }
    
    public final Icon getIcon(final String s) {
        return (Icon)this.getObject(s, Icon.class);
    }
    
    public final ImageIcon getImageIcon(final String s) {
        return (ImageIcon)this.getObject(s, ImageIcon.class);
    }
    
    public final Font getFont(final String s) {
        return (Font)this.getObject(s, Font.class);
    }
    
    public final Color getColor(final String s) {
        return (Color)this.getObject(s, Color.class);
    }
    
    public final KeyStroke getKeyStroke(final String s) {
        return (KeyStroke)this.getObject(s, KeyStroke.class);
    }
    
    public Integer getKeyCode(final String s) {
        final KeyStroke keyStroke = this.getKeyStroke(s);
        return (keyStroke != null) ? new Integer(keyStroke.getKeyCode()) : null;
    }
    
    private void injectComponentProperty(final Component component, final PropertyDescriptor propertyDescriptor, final String s) {
        final Method writeMethod = propertyDescriptor.getWriteMethod();
        final Class<?> propertyType = propertyDescriptor.getPropertyType();
        if (writeMethod != null && propertyType != null && this.containsKey(s)) {
            final Object object = this.getObject(s, propertyType);
            final String name = propertyDescriptor.getName();
            try {
                if ("text".equals(name) && component instanceof AbstractButton) {
                    MnemonicText.configure(component, (String)object);
                }
                else if ("text".equals(name) && component instanceof JLabel) {
                    MnemonicText.configure(component, (String)object);
                }
                else {
                    writeMethod.invoke(component, object);
                }
            }
            catch (Exception ex2) {
                final PropertyInjectionException ex = new PropertyInjectionException("property setter failed", s, component, propertyDescriptor.getName());
                ex.initCause(ex2);
                throw ex;
            }
        }
        else {
            if (propertyType != null) {
                throw new PropertyInjectionException("no value specified for resource", s, component, propertyDescriptor.getName());
            }
            if (writeMethod == null) {
                throw new PropertyInjectionException("can't set read-only property", s, component, propertyDescriptor.getName());
            }
        }
    }
    
    private void injectComponentProperties(final Component component) {
        final String name = component.getName();
        if (name != null) {
            boolean b = false;
            for (final String s : this.keySet()) {
                final int lastIndex = s.lastIndexOf(".");
                if (lastIndex != -1 && name.equals(s.substring(0, lastIndex))) {
                    b = true;
                    break;
                }
            }
            if (!b) {
                return;
            }
            BeanInfo beanInfo;
            try {
                beanInfo = Introspector.getBeanInfo(component.getClass());
            }
            catch (IntrospectionException ex2) {
                final PropertyInjectionException ex = new PropertyInjectionException("introspection failed", null, component, null);
                ex.initCause(ex2);
                throw ex;
            }
            final PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            if (propertyDescriptors != null && propertyDescriptors.length > 0) {
                for (final String s2 : this.keySet()) {
                    final int lastIndex2 = s2.lastIndexOf(".");
                    if (name.equals((lastIndex2 == -1) ? null : s2.substring(0, lastIndex2))) {
                        if (lastIndex2 + 1 == s2.length()) {
                            ResourceMap.logger.warning("component resource lacks property name suffix");
                            break;
                        }
                        final String substring = s2.substring(lastIndex2 + 1);
                        boolean b2 = false;
                        for (final PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                            if (propertyDescriptor.getName().equals(substring)) {
                                this.injectComponentProperty(component, propertyDescriptor, s2);
                                b2 = true;
                                break;
                            }
                        }
                        if (b2) {
                            continue;
                        }
                        ResourceMap.logger.warning(String.format("[resource %s] component named %s doesn't have a property named %s", s2, name, substring));
                    }
                }
            }
        }
    }
    
    public void injectComponent(final Component component) {
        if (component == null) {
            throw new IllegalArgumentException("null target");
        }
        this.injectComponentProperties(component);
    }
    
    public void injectComponents(final Component component) {
        this.injectComponent(component);
        if (component instanceof JMenu) {
            final Component[] menuComponents = ((JMenu)component).getMenuComponents();
            for (int length = menuComponents.length, i = 0; i < length; ++i) {
                this.injectComponents(menuComponents[i]);
            }
        }
        else if (component instanceof Container) {
            final Component[] components = ((Container)component).getComponents();
            for (int length2 = components.length, j = 0; j < length2; ++j) {
                this.injectComponents(components[j]);
            }
        }
    }
    
    private void injectField(final Field field, final Object o, final String s) {
        final Class<?> type = field.getType();
        if (type.isArray()) {
            final Class componentType = type.getComponentType();
            final Pattern compile = Pattern.compile(s + "\\[([\\d]+)\\]");
            final ArrayList list = new ArrayList();
            for (final String s2 : this.keySet()) {
                final Matcher matcher = compile.matcher(s2);
                if (matcher.matches()) {
                    final Object object = this.getObject(s2, componentType);
                    if (!field.isAccessible()) {
                        field.setAccessible(true);
                    }
                    try {
                        Array.set(field.get(o), Integer.parseInt(matcher.group(1)), object);
                    }
                    catch (Exception ex2) {
                        final InjectFieldException ex = new InjectFieldException("unable to set array element", field, o, s);
                        ex.initCause(ex2);
                        throw ex;
                    }
                }
            }
        }
        else {
            final Object object2 = this.getObject(s, type);
            if (object2 != null) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                try {
                    field.set(o, object2);
                }
                catch (Exception ex4) {
                    final InjectFieldException ex3 = new InjectFieldException("unable to set field's value", field, o, s);
                    ex3.initCause(ex4);
                    throw ex3;
                }
            }
        }
    }
    
    public void injectFields(final Object o) {
        if (o == null) {
            throw new IllegalArgumentException("null target");
        }
        final Class<?> class1 = o.getClass();
        if (class1.isArray()) {
            throw new IllegalArgumentException("array target");
        }
        final String string = class1.getSimpleName() + ".";
        for (final Field field : class1.getDeclaredFields()) {
            final Resource resource = field.getAnnotation(Resource.class);
            if (resource != null) {
                final String key = resource.key();
                this.injectField(field, o, (key.length() > 0) ? key : (string + field.getName()));
            }
        }
    }
    
    private static String resourcePath(final String s, final ResourceMap resourceMap) {
        String string;
        if (s == null) {
            string = null;
        }
        else if (s.startsWith("/")) {
            string = ((s.length() > 1) ? s.substring(1) : null);
        }
        else {
            string = resourceMap.getResourcesDir() + s;
        }
        return string;
    }
    
    private static ImageIcon loadImageIcon(final String s, final ResourceMap resourceMap) throws ResourceConverter.ResourceConverterException {
        final String resourcePath = resourcePath(s, resourceMap);
        if (resourcePath == null) {
            throw new ResourceConverter.ResourceConverterException(String.format("invalid image/icon path \"%s\"", s), s);
        }
        final URL resource = resourceMap.getClassLoader().getResource(resourcePath);
        if (resource != null) {
            return new ImageIcon(resource);
        }
        throw new ResourceConverter.ResourceConverterException(String.format("couldn't find Icon resource \"%s\"", s), s);
    }
    
    private static List<Double> parseDoubles(final String s, final int n, final String s2) throws ResourceConverter.ResourceConverterException {
        final String[] split = s.split(",", n + 1);
        if (split.length != n) {
            throw new ResourceConverter.ResourceConverterException(s2, s);
        }
        final ArrayList<Double> list = new ArrayList<Double>(n);
        for (final String s3 : split) {
            try {
                list.add(Double.valueOf(s3));
            }
            catch (NumberFormatException ex) {
                throw new ResourceConverter.ResourceConverterException(s2, s, ex);
            }
        }
        return list;
    }
    
    static {
        ResourceMap.logger = Logger.getLogger(ResourceMap.class.getName());
        nullResource = new String("null resource");
        final ResourceConverter[] array = { new ColorStringConverter(), new IconStringConverter(), new ImageStringConverter(), new FontStringConverter(), new KeyStrokeStringConverter(), new DimensionStringConverter(), new PointStringConverter(), new RectangleStringConverter(), new InsetsStringConverter(), new EmptyBorderStringConverter() };
        for (int length = array.length, i = 0; i < length; ++i) {
            ResourceConverter.register(array[i]);
        }
    }
    
    public static class LookupException extends RuntimeException
    {
        private final Class type;
        private final String key;
        
        public LookupException(final String s, final String key, final Class type) {
            super(String.format("%s: resource %s, type %s", s, key, type));
            this.key = key;
            this.type = type;
        }
        
        public Class getType() {
            return this.type;
        }
        
        public String getKey() {
            return this.key;
        }
    }
    
    public static class PropertyInjectionException extends RuntimeException
    {
        private final String key;
        private final Component component;
        private final String propertyName;
        
        public PropertyInjectionException(final String s, final String key, final Component component, final String propertyName) {
            super(String.format("%s: resource %s, property %s, component %s", s, key, propertyName, component));
            this.key = key;
            this.component = component;
            this.propertyName = propertyName;
        }
        
        public String getKey() {
            return this.key;
        }
        
        public Component getComponent() {
            return this.component;
        }
        
        public String getPropertyName() {
            return this.propertyName;
        }
    }
    
    public static class InjectFieldException extends RuntimeException
    {
        private final Field field;
        private final Object target;
        private final String key;
        
        public InjectFieldException(final String s, final Field field, final Object target, final String key) {
            super(String.format("%s: resource %s, field %s, target %s", s, key, field, target));
            this.field = field;
            this.target = target;
            this.key = key;
        }
        
        public Field getField() {
            return this.field;
        }
        
        public Object getTarget() {
            return this.target;
        }
        
        public String getKey() {
            return this.key;
        }
    }
    
    private static class FontStringConverter extends ResourceConverter
    {
        FontStringConverter() {
            super(Font.class);
        }
        
        @Override
        public Object parseString(final String s, final ResourceMap resourceMap) throws ResourceConverterException {
            return Font.decode(s);
        }
    }
    
    private static class ColorStringConverter extends ResourceConverter
    {
        ColorStringConverter() {
            super(Color.class);
        }
        
        private void error(final String s, final String s2, final Exception ex) throws ResourceConverterException {
            throw new ResourceConverterException(s, s2, ex);
        }
        
        private void error(final String s, final String s2) throws ResourceConverterException {
            this.error(s, s2, null);
        }
        
        @Override
        public Object parseString(final String s, final ResourceMap resourceMap) throws ResourceConverterException {
            Color decode = null;
            if (s.startsWith("#")) {
                switch (s.length()) {
                    case 7: {
                        decode = Color.decode(s);
                        break;
                    }
                    case 9: {
                        decode = new Color(Integer.decode(s.substring(0, 3)) << 24 | Integer.decode("#" + s.substring(3)), true);
                        break;
                    }
                    default: {
                        throw new ResourceConverterException("invalid #RRGGBB or #AARRGGBB color string", s);
                    }
                }
            }
            else {
                final String[] split = s.split(",");
                if (split.length < 3 || split.length > 4) {
                    throw new ResourceConverterException("invalid R, G, B[, A] color string", s);
                }
                try {
                    if (split.length == 4) {
                        decode = new Color(Integer.parseInt(split[0].trim()), Integer.parseInt(split[1].trim()), Integer.parseInt(split[2].trim()), Integer.parseInt(split[3].trim()));
                    }
                    else {
                        decode = new Color(Integer.parseInt(split[0].trim()), Integer.parseInt(split[1].trim()), Integer.parseInt(split[2].trim()));
                    }
                }
                catch (NumberFormatException ex) {
                    throw new ResourceConverterException("invalid R, G, B[, A] color string", s, ex);
                }
            }
            return decode;
        }
    }
    
    private static class IconStringConverter extends ResourceConverter
    {
        IconStringConverter() {
            super(Icon.class);
        }
        
        @Override
        public Object parseString(final String s, final ResourceMap resourceMap) throws ResourceConverterException {
            return loadImageIcon(s, resourceMap);
        }
        
        @Override
        public boolean supportsType(final Class clazz) {
            return clazz.equals(Icon.class) || clazz.equals(ImageIcon.class);
        }
    }
    
    private static class ImageStringConverter extends ResourceConverter
    {
        ImageStringConverter() {
            super(Image.class);
        }
        
        @Override
        public Object parseString(final String s, final ResourceMap resourceMap) throws ResourceConverterException {
            return loadImageIcon(s, resourceMap).getImage();
        }
    }
    
    private static class KeyStrokeStringConverter extends ResourceConverter
    {
        KeyStrokeStringConverter() {
            super(KeyStroke.class);
        }
        
        @Override
        public Object parseString(String replaceAll, final ResourceMap resourceMap) {
            if (replaceAll.contains("shortcut")) {
                replaceAll = replaceAll.replaceAll("shortcut", (Toolkit.getDefaultToolkit().getMenuShortcutKeyMask() == 4) ? "meta" : "control");
            }
            return KeyStroke.getKeyStroke(replaceAll);
        }
    }
    
    private static class DimensionStringConverter extends ResourceConverter
    {
        DimensionStringConverter() {
            super(Dimension.class);
        }
        
        @Override
        public Object parseString(final String s, final ResourceMap resourceMap) throws ResourceConverterException {
            final List access$100 = parseDoubles(s, 2, "invalid x,y Dimension string");
            final Dimension dimension = new Dimension();
            dimension.setSize(access$100.get(0), access$100.get(1));
            return dimension;
        }
    }
    
    private static class PointStringConverter extends ResourceConverter
    {
        PointStringConverter() {
            super(Point.class);
        }
        
        @Override
        public Object parseString(final String s, final ResourceMap resourceMap) throws ResourceConverterException {
            final List access$100 = parseDoubles(s, 2, "invalid x,y Point string");
            final Point point = new Point();
            point.setLocation(access$100.get(0), access$100.get(1));
            return point;
        }
    }
    
    private static class RectangleStringConverter extends ResourceConverter
    {
        RectangleStringConverter() {
            super(Rectangle.class);
        }
        
        @Override
        public Object parseString(final String s, final ResourceMap resourceMap) throws ResourceConverterException {
            final List access$100 = parseDoubles(s, 4, "invalid x,y,width,height Rectangle string");
            final Rectangle rectangle = new Rectangle();
            rectangle.setFrame(access$100.get(0), access$100.get(1), access$100.get(2), access$100.get(3));
            return rectangle;
        }
    }
    
    private static class InsetsStringConverter extends ResourceConverter
    {
        InsetsStringConverter() {
            super(Insets.class);
        }
        
        @Override
        public Object parseString(final String s, final ResourceMap resourceMap) throws ResourceConverterException {
            final List access$100 = parseDoubles(s, 4, "invalid top,left,bottom,right Insets string");
            return new Insets(access$100.get(0).intValue(), access$100.get(1).intValue(), access$100.get(2).intValue(), access$100.get(3).intValue());
        }
    }
    
    private static class EmptyBorderStringConverter extends ResourceConverter
    {
        EmptyBorderStringConverter() {
            super(EmptyBorder.class);
        }
        
        @Override
        public Object parseString(final String s, final ResourceMap resourceMap) throws ResourceConverterException {
            final List access$100 = parseDoubles(s, 4, "invalid top,left,bottom,right EmptyBorder string");
            return new EmptyBorder(access$100.get(0).intValue(), access$100.get(1).intValue(), access$100.get(2).intValue(), access$100.get(3).intValue());
        }
    }
}

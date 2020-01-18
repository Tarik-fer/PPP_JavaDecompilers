// 
// Decompiled by Procyon v0.5.36
// 

package org.jdesktop.application;

import java.net.URISyntaxException;
import java.net.URI;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public abstract class ResourceConverter
{
    protected final Class type;
    private static ResourceConverter[] resourceConvertersArray;
    private static List<ResourceConverter> resourceConverters;
    
    public abstract Object parseString(final String p0, final ResourceMap p1) throws ResourceConverterException;
    
    public String toString(final Object o) {
        return (o == null) ? "null" : o.toString();
    }
    
    protected ResourceConverter(final Class type) {
        if (type == null) {
            throw new IllegalArgumentException("null type");
        }
        this.type = type;
    }
    
    private ResourceConverter() {
        this.type = null;
    }
    
    public boolean supportsType(final Class clazz) {
        return this.type.equals(clazz);
    }
    
    public static void register(final ResourceConverter resourceConverter) {
        if (resourceConverter == null) {
            throw new IllegalArgumentException("null resourceConverter");
        }
        ResourceConverter.resourceConverters.add(resourceConverter);
    }
    
    public static ResourceConverter forType(final Class clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("null type");
        }
        for (final ResourceConverter resourceConverter : ResourceConverter.resourceConverters) {
            if (resourceConverter.supportsType(clazz)) {
                return resourceConverter;
            }
        }
        return null;
    }
    
    static {
        ResourceConverter.resourceConvertersArray = new ResourceConverter[] { new BooleanResourceConverter(new String[] { "true", "on", "yes" }), new IntegerResourceConverter(), new MessageFormatResourceConverter(), new FloatResourceConverter(), new DoubleResourceConverter(), new LongResourceConverter(), new ShortResourceConverter(), new ByteResourceConverter(), new URLResourceConverter(), new URIResourceConverter() };
        ResourceConverter.resourceConverters = new ArrayList<ResourceConverter>(Arrays.asList(ResourceConverter.resourceConvertersArray));
    }
    
    public static class ResourceConverterException extends Exception
    {
        private final String badString;
        
        private String maybeShorten(final String s) {
            final int length = s.length();
            return (length < 128) ? s : (s.substring(0, 128) + "...[" + (length - 128) + " more characters]");
        }
        
        public ResourceConverterException(final String s, final String s2, final Throwable t) {
            super(s, t);
            this.badString = this.maybeShorten(s2);
        }
        
        public ResourceConverterException(final String s, final String s2) {
            super(s);
            this.badString = this.maybeShorten(s2);
        }
        
        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer(super.toString());
            sb.append(" string: \"");
            sb.append(this.badString);
            sb.append("\"");
            return sb.toString();
        }
    }
    
    private static class BooleanResourceConverter extends ResourceConverter
    {
        private final String[] trueStrings;
        
        BooleanResourceConverter(final String... trueStrings) {
            super(Boolean.class);
            this.trueStrings = trueStrings;
        }
        
        @Override
        public Object parseString(String trim, final ResourceMap resourceMap) {
            trim = trim.trim();
            final String[] trueStrings = this.trueStrings;
            for (int length = trueStrings.length, i = 0; i < length; ++i) {
                if (trim.equalsIgnoreCase(trueStrings[i])) {
                    return Boolean.TRUE;
                }
            }
            return Boolean.FALSE;
        }
        
        @Override
        public boolean supportsType(final Class clazz) {
            return clazz.equals(Boolean.class) || clazz.equals(Boolean.TYPE);
        }
    }
    
    private abstract static class NumberResourceConverter extends ResourceConverter
    {
        private final Class primitiveType;
        
        NumberResourceConverter(final Class clazz, final Class primitiveType) {
            super(clazz);
            this.primitiveType = primitiveType;
        }
        
        protected abstract Number parseString(final String p0) throws NumberFormatException;
        
        @Override
        public Object parseString(final String s, final ResourceMap resourceMap) throws ResourceConverterException {
            try {
                return this.parseString(s);
            }
            catch (NumberFormatException ex) {
                throw new ResourceConverterException("invalid " + this.type.getSimpleName(), s, ex);
            }
        }
        
        @Override
        public boolean supportsType(final Class clazz) {
            return clazz.equals(this.type) || clazz.equals(this.primitiveType);
        }
    }
    
    private static class FloatResourceConverter extends NumberResourceConverter
    {
        FloatResourceConverter() {
            super(Float.class, Float.TYPE);
        }
        
        @Override
        protected Number parseString(final String s) throws NumberFormatException {
            return Float.parseFloat(s);
        }
    }
    
    private static class DoubleResourceConverter extends NumberResourceConverter
    {
        DoubleResourceConverter() {
            super(Double.class, Double.TYPE);
        }
        
        @Override
        protected Number parseString(final String s) throws NumberFormatException {
            return Double.parseDouble(s);
        }
    }
    
    private abstract static class INumberResourceConverter extends ResourceConverter
    {
        private final Class primitiveType;
        
        INumberResourceConverter(final Class clazz, final Class primitiveType) {
            super(clazz);
            this.primitiveType = primitiveType;
        }
        
        protected abstract Number parseString(final String p0, final int p1) throws NumberFormatException;
        
        @Override
        public Object parseString(final String s, final ResourceMap resourceMap) throws ResourceConverterException {
            try {
                final String[] split = s.split("&");
                return this.parseString(split[0], (split.length == 2) ? Integer.parseInt(split[1]) : -1);
            }
            catch (NumberFormatException ex) {
                throw new ResourceConverterException("invalid " + this.type.getSimpleName(), s, ex);
            }
        }
        
        @Override
        public boolean supportsType(final Class clazz) {
            return clazz.equals(this.type) || clazz.equals(this.primitiveType);
        }
    }
    
    private static class ByteResourceConverter extends INumberResourceConverter
    {
        ByteResourceConverter() {
            super(Byte.class, Byte.TYPE);
        }
        
        @Override
        protected Number parseString(final String s, final int n) throws NumberFormatException {
            return (n == -1) ? Byte.decode(s) : Byte.parseByte(s, n);
        }
    }
    
    private static class IntegerResourceConverter extends INumberResourceConverter
    {
        IntegerResourceConverter() {
            super(Integer.class, Integer.TYPE);
        }
        
        @Override
        protected Number parseString(final String s, final int n) throws NumberFormatException {
            return (n == -1) ? Integer.decode(s) : Integer.parseInt(s, n);
        }
    }
    
    private static class LongResourceConverter extends INumberResourceConverter
    {
        LongResourceConverter() {
            super(Long.class, Long.TYPE);
        }
        
        @Override
        protected Number parseString(final String s, final int n) throws NumberFormatException {
            return (n == -1) ? Long.decode(s) : Long.parseLong(s, n);
        }
    }
    
    private static class ShortResourceConverter extends INumberResourceConverter
    {
        ShortResourceConverter() {
            super(Short.class, Short.TYPE);
        }
        
        @Override
        protected Number parseString(final String s, final int n) throws NumberFormatException {
            return (n == -1) ? Short.decode(s) : Short.parseShort(s, n);
        }
    }
    
    private static class MessageFormatResourceConverter extends ResourceConverter
    {
        MessageFormatResourceConverter() {
            super(MessageFormat.class);
        }
        
        @Override
        public Object parseString(final String s, final ResourceMap resourceMap) {
            return new MessageFormat(s);
        }
    }
    
    private static class URLResourceConverter extends ResourceConverter
    {
        URLResourceConverter() {
            super(URL.class);
        }
        
        @Override
        public Object parseString(final String s, final ResourceMap resourceMap) throws ResourceConverterException {
            try {
                return new URL(s);
            }
            catch (MalformedURLException ex) {
                throw new ResourceConverterException("invalid URL", s, ex);
            }
        }
    }
    
    private static class URIResourceConverter extends ResourceConverter
    {
        URIResourceConverter() {
            super(URI.class);
        }
        
        @Override
        public Object parseString(final String s, final ResourceMap resourceMap) throws ResourceConverterException {
            try {
                return new URI(s);
            }
            catch (URISyntaxException ex) {
                throw new ResourceConverterException("invalid URI", s, ex);
            }
        }
    }
}

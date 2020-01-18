// 
// Decompiled by Procyon v0.5.36
// 

package org.jdesktop.application;

import javax.jnlp.FileContents;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.jnlp.UnavailableServiceException;
import javax.jnlp.ServiceManager;
import javax.jnlp.PersistenceService;
import javax.jnlp.BasicService;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.beans.Expression;
import java.beans.Encoder;
import java.beans.DefaultPersistenceDelegate;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.logging.Level;
import java.io.Closeable;
import java.beans.XMLDecoder;
import java.beans.ExceptionListener;
import java.beans.PersistenceDelegate;
import java.awt.Rectangle;
import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.util.logging.Logger;

public class LocalStorage extends AbstractBean
{
    private static Logger logger;
    private final ApplicationContext context;
    private long storageLimit;
    private LocalIO localIO;
    private final File unspecifiedFile;
    private File directory;
    private static boolean persistenceDelegatesInitialized;
    
    protected LocalStorage(final ApplicationContext context) {
        this.storageLimit = -1L;
        this.localIO = null;
        this.unspecifiedFile = new File("unspecified");
        this.directory = this.unspecifiedFile;
        if (context == null) {
            throw new IllegalArgumentException("null context");
        }
        this.context = context;
    }
    
    protected final ApplicationContext getContext() {
        return this.context;
    }
    
    private void checkFileName(final String s) {
        if (s == null) {
            throw new IllegalArgumentException("null fileName");
        }
    }
    
    public InputStream openInputFile(final String s) throws IOException {
        this.checkFileName(s);
        return this.getLocalIO().openInputFile(s);
    }
    
    public OutputStream openOutputFile(final String s) throws IOException {
        this.checkFileName(s);
        return this.getLocalIO().openOutputFile(s);
    }
    
    public boolean deleteFile(final String s) throws IOException {
        this.checkFileName(s);
        return this.getLocalIO().deleteFile(s);
    }
    
    public void save(final Object o, final String s) throws IOException {
        final AbortExceptionListener exceptionListener = new AbortExceptionListener();
        XMLEncoder xmlEncoder = null;
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            xmlEncoder = new XMLEncoder(byteArrayOutputStream);
            if (!LocalStorage.persistenceDelegatesInitialized) {
                xmlEncoder.setPersistenceDelegate(Rectangle.class, new RectanglePD());
                LocalStorage.persistenceDelegatesInitialized = true;
            }
            xmlEncoder.setExceptionListener(exceptionListener);
            xmlEncoder.writeObject(o);
        }
        finally {
            if (xmlEncoder != null) {
                xmlEncoder.close();
            }
        }
        if (exceptionListener.exception != null) {
            throw new LSException("save failed \"" + s + "\"", exceptionListener.exception);
        }
        OutputStream openOutputFile = null;
        try {
            openOutputFile = this.openOutputFile(s);
            openOutputFile.write(byteArrayOutputStream.toByteArray());
        }
        finally {
            if (openOutputFile != null) {
                openOutputFile.close();
            }
        }
    }
    
    public Object load(final String s) throws IOException {
        InputStream openInputFile;
        try {
            openInputFile = this.openInputFile(s);
        }
        catch (IOException ex) {
            return null;
        }
        final AbortExceptionListener exceptionListener = new AbortExceptionListener();
        XMLDecoder xmlDecoder = null;
        try {
            xmlDecoder = new XMLDecoder(openInputFile);
            xmlDecoder.setExceptionListener(exceptionListener);
            final Object object = xmlDecoder.readObject();
            if (exceptionListener.exception != null) {
                throw new LSException("load failed \"" + s + "\"", exceptionListener.exception);
            }
            return object;
        }
        finally {
            if (xmlDecoder != null) {
                xmlDecoder.close();
            }
        }
    }
    
    private void closeStream(final Closeable closeable, final String s) throws IOException {
        if (closeable != null) {
            try {
                closeable.close();
            }
            catch (IOException ex) {
                throw new LSException("close failed \"" + s + "\"", ex);
            }
        }
    }
    
    public long getStorageLimit() {
        return this.storageLimit;
    }
    
    public void setStorageLimit(final long storageLimit) {
        if (storageLimit < -1L) {
            throw new IllegalArgumentException("invalid storageLimit");
        }
        final long storageLimit2 = this.storageLimit;
        this.storageLimit = storageLimit;
        this.firePropertyChange("storageLimit", storageLimit2, this.storageLimit);
    }
    
    private String getId(final String s, final String s2) {
        String string = this.getContext().getResourceMap().getString(s, new Object[0]);
        if (string == null) {
            LocalStorage.logger.log(Level.WARNING, "unspecified resource " + s + " using " + s2);
            string = s2;
        }
        else if (string.trim().length() == 0) {
            LocalStorage.logger.log(Level.WARNING, "empty resource " + s + " using " + s2);
            string = s2;
        }
        return string;
    }
    
    private String getApplicationId() {
        return this.getId("Application.id", this.getContext().getApplicationClass().getSimpleName());
    }
    
    private String getVendorId() {
        return this.getId("Application.vendorId", "UnknownApplicationVendor");
    }
    
    private OSId getOSId() {
        final PrivilegedAction<String> privilegedAction = new PrivilegedAction<String>() {
            public String run() {
                return System.getProperty("os.name");
            }
        };
        OSId osId = OSId.UNIX;
        final String s = AccessController.doPrivileged((PrivilegedAction<String>)privilegedAction);
        if (s != null) {
            if (s.toLowerCase().startsWith("mac os x")) {
                osId = OSId.OSX;
            }
            else if (s.contains("Windows")) {
                osId = OSId.WINDOWS;
            }
        }
        return osId;
    }
    
    public File getDirectory() {
        if (this.directory == this.unspecifiedFile) {
            this.directory = null;
            String property = null;
            try {
                property = System.getProperty("user.home");
            }
            catch (SecurityException ex) {}
            if (property != null) {
                final String applicationId = this.getApplicationId();
                final OSId osId = this.getOSId();
                if (osId == OSId.WINDOWS) {
                    File file = null;
                    try {
                        final String getenv = System.getenv("APPDATA");
                        if (getenv != null && getenv.length() > 0) {
                            file = new File(getenv);
                        }
                    }
                    catch (SecurityException ex2) {}
                    final String vendorId = this.getVendorId();
                    if (file != null && file.isDirectory()) {
                        this.directory = new File(file, vendorId + "\\" + applicationId + "\\");
                    }
                    else {
                        this.directory = new File(property, "Application Data\\" + vendorId + "\\" + applicationId + "\\");
                    }
                }
                else if (osId == OSId.OSX) {
                    this.directory = new File(property, "Library/Application Support/" + applicationId + "/");
                }
                else {
                    this.directory = new File(property, "." + applicationId + "/");
                }
            }
        }
        return this.directory;
    }
    
    public void setDirectory(final File directory) {
        this.firePropertyChange("directory", this.directory, this.directory = directory);
    }
    
    private synchronized LocalIO getLocalIO() {
        if (this.localIO == null) {
            this.localIO = this.getPersistenceServiceIO();
            if (this.localIO == null) {
                this.localIO = new LocalFileIO();
            }
        }
        return this.localIO;
    }
    
    private LocalIO getPersistenceServiceIO() {
        try {
            final String[] array = (String[])Class.forName("javax.jnlp.ServiceManager").getMethod("getServiceNames", (Class<?>[])new Class[0]).invoke(null, new Object[0]);
            boolean b = false;
            boolean b2 = false;
            for (final String s : array) {
                if (s.equals("javax.jnlp.BasicService")) {
                    b2 = true;
                }
                else if (s.equals("javax.jnlp.PersistenceService")) {
                    b = true;
                }
            }
            if (b2 && b) {
                return new PersistenceServiceIO();
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    static {
        LocalStorage.logger = Logger.getLogger(LocalStorage.class.getName());
        LocalStorage.persistenceDelegatesInitialized = false;
    }
    
    private static class AbortExceptionListener implements ExceptionListener
    {
        public Exception exception;
        
        private AbortExceptionListener() {
            this.exception = null;
        }
        
        public void exceptionThrown(final Exception exception) {
            if (this.exception == null) {
                this.exception = exception;
            }
        }
    }
    
    private enum OSId
    {
        WINDOWS, 
        OSX, 
        UNIX;
    }
    
    private static class LSException extends IOException
    {
        public LSException(final String s, final Throwable t) {
            super(s);
            this.initCause(t);
        }
        
        public LSException(final String s) {
            super(s);
        }
    }
    
    private static class RectanglePD extends DefaultPersistenceDelegate
    {
        public RectanglePD() {
            super(new String[] { "x", "y", "width", "height" });
        }
        
        @Override
        protected Expression instantiate(final Object o, final Encoder encoder) {
            final Rectangle rectangle = (Rectangle)o;
            return new Expression(o, o.getClass(), "new", new Object[] { rectangle.x, rectangle.y, rectangle.width, rectangle.height });
        }
    }
    
    private abstract class LocalIO
    {
        public abstract InputStream openInputFile(final String p0) throws IOException;
        
        public abstract OutputStream openOutputFile(final String p0) throws IOException;
        
        public abstract boolean deleteFile(final String p0) throws IOException;
    }
    
    private class LocalFileIO extends LocalIO
    {
        @Override
        public InputStream openInputFile(final String s) throws IOException {
            final File file = new File(LocalStorage.this.getDirectory(), s);
            try {
                return new BufferedInputStream(new FileInputStream(file));
            }
            catch (IOException ex) {
                throw new LSException("couldn't open input file \"" + s + "\"", ex);
            }
        }
        
        @Override
        public OutputStream openOutputFile(final String s) throws IOException {
            final File directory = LocalStorage.this.getDirectory();
            if (!directory.isDirectory() && !directory.mkdirs()) {
                throw new LSException("couldn't create directory " + directory);
            }
            final File file = new File(directory, s);
            try {
                return new BufferedOutputStream(new FileOutputStream(file));
            }
            catch (IOException ex) {
                throw new LSException("couldn't open output file \"" + s + "\"", ex);
            }
        }
        
        @Override
        public boolean deleteFile(final String s) throws IOException {
            return new File(LocalStorage.this.getDirectory(), s).delete();
        }
    }
    
    private class PersistenceServiceIO extends LocalIO
    {
        private BasicService bs;
        private PersistenceService ps;
        
        private String initFailedMessage(final String s) {
            return this.getClass().getName() + " initialization failed: " + s;
        }
        
        PersistenceServiceIO() {
            try {
                this.bs = (BasicService)ServiceManager.lookup("javax.jnlp.BasicService");
                this.ps = (PersistenceService)ServiceManager.lookup("javax.jnlp.PersistenceService");
            }
            catch (UnavailableServiceException ex) {
                LocalStorage.logger.log(Level.SEVERE, this.initFailedMessage("ServiceManager.lookup"), (Throwable)ex);
                this.bs = null;
                this.ps = null;
            }
        }
        
        private void checkBasics(final String s) throws IOException {
            if (this.bs == null || this.ps == null) {
                throw new IOException(this.initFailedMessage(s));
            }
        }
        
        private URL fileNameToURL(final String s) throws IOException {
            try {
                return new URL(this.bs.getCodeBase(), s);
            }
            catch (MalformedURLException ex) {
                throw new LSException("invalid filename \"" + s + "\"", ex);
            }
        }
        
        @Override
        public InputStream openInputFile(final String s) throws IOException {
            this.checkBasics("openInputFile");
            final URL fileNameToURL = this.fileNameToURL(s);
            try {
                return new BufferedInputStream(this.ps.get(fileNameToURL).getInputStream());
            }
            catch (Exception ex) {
                throw new LSException("openInputFile \"" + s + "\" failed", ex);
            }
        }
        
        @Override
        public OutputStream openOutputFile(final String s) throws IOException {
            this.checkBasics("openOutputFile");
            final URL fileNameToURL = this.fileNameToURL(s);
            try {
                FileContents fileContents = null;
                try {
                    fileContents = this.ps.get(fileNameToURL);
                }
                catch (FileNotFoundException ex2) {
                    final long n = 131072L;
                    if (this.ps.create(fileNameToURL, n) >= n) {
                        fileContents = this.ps.get(fileNameToURL);
                    }
                }
                if (fileContents != null && fileContents.canWrite()) {
                    return new BufferedOutputStream(fileContents.getOutputStream(true));
                }
                throw new IOException("unable to create FileContents object");
            }
            catch (Exception ex) {
                throw new LSException("openOutputFile \"" + s + "\" failed", ex);
            }
        }
        
        @Override
        public boolean deleteFile(final String s) throws IOException {
            this.checkBasics("deleteFile");
            final URL fileNameToURL = this.fileNameToURL(s);
            try {
                this.ps.delete(fileNameToURL);
                return true;
            }
            catch (Exception ex) {
                throw new LSException("openInputFile \"" + s + "\" failed", ex);
            }
        }
    }
}

/*     */ package org.jdesktop.application;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.beans.DefaultPersistenceDelegate;
/*     */ import java.beans.Encoder;
/*     */ import java.beans.ExceptionListener;
/*     */ import java.beans.Expression;
/*     */ import java.beans.XMLDecoder;
/*     */ import java.beans.XMLEncoder;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.Closeable;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.lang.reflect.Method;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.jnlp.BasicService;
/*     */ import javax.jnlp.FileContents;
/*     */ import javax.jnlp.PersistenceService;
/*     */ import javax.jnlp.ServiceManager;
/*     */ import javax.jnlp.UnavailableServiceException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LocalStorage
/*     */   extends AbstractBean
/*     */ {
/*  49 */   private static Logger logger = Logger.getLogger(LocalStorage.class.getName());
/*     */   private final ApplicationContext context;
/*  51 */   private long storageLimit = -1L;
/*  52 */   private LocalIO localIO = null;
/*  53 */   private final File unspecifiedFile = new File("unspecified");
/*  54 */   private File directory = this.unspecifiedFile;
/*     */   
/*     */   protected LocalStorage(ApplicationContext paramApplicationContext) {
/*  57 */     if (paramApplicationContext == null) {
/*  58 */       throw new IllegalArgumentException("null context");
/*     */     }
/*  60 */     this.context = paramApplicationContext;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  65 */   protected final ApplicationContext getContext() { return this.context; }
/*     */ 
/*     */   
/*     */   private void checkFileName(String paramString) {
/*  69 */     if (paramString == null) {
/*  70 */       throw new IllegalArgumentException("null fileName");
/*     */     }
/*     */   }
/*     */   
/*     */   public InputStream openInputFile(String paramString) throws IOException {
/*  75 */     checkFileName(paramString);
/*  76 */     return getLocalIO().openInputFile(paramString);
/*     */   }
/*     */   
/*     */   public OutputStream openOutputFile(String paramString) throws IOException {
/*  80 */     checkFileName(paramString);
/*  81 */     return getLocalIO().openOutputFile(paramString);
/*     */   }
/*     */   
/*     */   public boolean deleteFile(String paramString) throws IOException {
/*  85 */     checkFileName(paramString);
/*  86 */     return getLocalIO().deleteFile(paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class AbortExceptionListener
/*     */     implements ExceptionListener
/*     */   {
/*  95 */     public Exception exception = null;
/*     */     
/*  97 */     public void exceptionThrown(Exception param1Exception) { if (this.exception == null) this.exception = param1Exception;  }
/*     */     
/*     */     private AbortExceptionListener() {}
/*     */   }
/*     */   private static boolean persistenceDelegatesInitialized = false;
/*     */   
/*     */   public void save(Object paramObject, String paramString) throws IOException {
/* 104 */     AbortExceptionListener abortExceptionListener = new AbortExceptionListener();
/* 105 */     XMLEncoder xMLEncoder = null;
/*     */ 
/*     */ 
/*     */     
/* 109 */     ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
/*     */     try {
/* 111 */       xMLEncoder = new XMLEncoder(byteArrayOutputStream);
/* 112 */       if (!persistenceDelegatesInitialized) {
/* 113 */         xMLEncoder.setPersistenceDelegate(Rectangle.class, new RectanglePD());
/* 114 */         persistenceDelegatesInitialized = true;
/*     */       } 
/* 116 */       xMLEncoder.setExceptionListener(abortExceptionListener);
/* 117 */       xMLEncoder.writeObject(paramObject);
/*     */     } finally {
/*     */       
/* 120 */       if (xMLEncoder != null) xMLEncoder.close(); 
/*     */     } 
/* 122 */     if (abortExceptionListener.exception != null) {
/* 123 */       throw new LSException("save failed \"" + paramString + "\"", abortExceptionListener.exception);
/*     */     }
/* 125 */     OutputStream outputStream = null;
/*     */     try {
/* 127 */       outputStream = openOutputFile(paramString);
/* 128 */       outputStream.write(byteArrayOutputStream.toByteArray());
/*     */     } finally {
/*     */       
/* 131 */       if (outputStream != null) outputStream.close(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object load(String paramString) throws IOException {
/* 136 */     InputStream inputStream = null;
/*     */     try {
/* 138 */       inputStream = openInputFile(paramString);
/*     */     }
/* 140 */     catch (IOException iOException) {
/* 141 */       return null;
/*     */     } 
/* 143 */     AbortExceptionListener abortExceptionListener = new AbortExceptionListener();
/* 144 */     XMLDecoder xMLDecoder = null;
/*     */     try {
/* 146 */       xMLDecoder = new XMLDecoder(inputStream);
/* 147 */       xMLDecoder.setExceptionListener(abortExceptionListener);
/* 148 */       Object object = xMLDecoder.readObject();
/* 149 */       if (abortExceptionListener.exception != null) {
/* 150 */         throw new LSException("load failed \"" + paramString + "\"", abortExceptionListener.exception);
/*     */       }
/* 152 */       return object;
/*     */     } finally {
/*     */       
/* 155 */       if (xMLDecoder != null) xMLDecoder.close(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void closeStream(Closeable paramCloseable, String paramString) throws IOException {
/* 160 */     if (paramCloseable != null) {
/* 161 */       try { paramCloseable.close(); }
/* 162 */       catch (IOException iOException)
/* 163 */       { throw new LSException("close failed \"" + paramString + "\"", iOException); }
/*     */     
/*     */     }
/*     */   }
/*     */ 
/*     */   
/* 169 */   public long getStorageLimit() { return this.storageLimit; }
/*     */ 
/*     */   
/*     */   public void setStorageLimit(long paramLong) {
/* 173 */     if (paramLong < -1L) {
/* 174 */       throw new IllegalArgumentException("invalid storageLimit");
/*     */     }
/* 176 */     long l = this.storageLimit;
/* 177 */     this.storageLimit = paramLong;
/* 178 */     firePropertyChange("storageLimit", Long.valueOf(l), Long.valueOf(this.storageLimit));
/*     */   }
/*     */   
/*     */   private String getId(String paramString1, String paramString2) {
/* 182 */     ResourceMap resourceMap = getContext().getResourceMap();
/* 183 */     String str = resourceMap.getString(paramString1, new Object[0]);
/* 184 */     if (str == null) {
/* 185 */       logger.log(Level.WARNING, "unspecified resource " + paramString1 + " using " + paramString2);
/* 186 */       str = paramString2;
/*     */     }
/* 188 */     else if (str.trim().length() == 0) {
/* 189 */       logger.log(Level.WARNING, "empty resource " + paramString1 + " using " + paramString2);
/* 190 */       str = paramString2;
/*     */     } 
/* 192 */     return str;
/*     */   }
/*     */   
/* 195 */   private String getApplicationId() { return getId("Application.id", getContext().getApplicationClass().getSimpleName()); }
/*     */ 
/*     */   
/* 198 */   private String getVendorId() { return getId("Application.vendorId", "UnknownApplicationVendor"); }
/*     */ 
/*     */ 
/*     */   
/*     */   private enum OSId
/*     */   {
/* 204 */     WINDOWS, OSX, UNIX; }
/*     */   private OSId getOSId() {
/* 206 */     PrivilegedAction<String> privilegedAction = new PrivilegedAction<String>() {
/*     */         public String run() {
/* 208 */           return System.getProperty("os.name");
/*     */         }
/*     */       };
/* 211 */     OSId oSId = OSId.UNIX;
/* 212 */     String str = AccessController.doPrivileged(privilegedAction);
/* 213 */     if (str != null) {
/* 214 */       if (str.toLowerCase().startsWith("mac os x")) {
/* 215 */         oSId = OSId.OSX;
/*     */       }
/* 217 */       else if (str.contains("Windows")) {
/* 218 */         oSId = OSId.WINDOWS;
/*     */       } 
/*     */     }
/* 221 */     return oSId;
/*     */   }
/*     */   
/*     */   public File getDirectory() {
/* 225 */     if (this.directory == this.unspecifiedFile) {
/* 226 */       this.directory = null;
/* 227 */       String str = null;
/*     */       try {
/* 229 */         str = System.getProperty("user.home");
/*     */       }
/* 231 */       catch (SecurityException securityException) {}
/*     */       
/* 233 */       if (str != null) {
/* 234 */         String str1 = getApplicationId();
/* 235 */         OSId oSId = getOSId();
/* 236 */         if (oSId == OSId.WINDOWS) {
/* 237 */           File file = null;
/*     */           try {
/* 239 */             String str3 = System.getenv("APPDATA");
/* 240 */             if (str3 != null && str3.length() > 0) {
/* 241 */               file = new File(str3);
/*     */             }
/*     */           }
/* 244 */           catch (SecurityException securityException) {}
/*     */           
/* 246 */           String str2 = getVendorId();
/* 247 */           if (file != null && file.isDirectory())
/*     */           {
/* 249 */             String str3 = str2 + "\\" + str1 + "\\";
/* 250 */             this.directory = new File(file, str3);
/*     */           }
/*     */           else
/*     */           {
/* 254 */             String str3 = "Application Data\\" + str2 + "\\" + str1 + "\\";
/* 255 */             this.directory = new File(str, str3);
/*     */           }
/*     */         
/* 258 */         } else if (oSId == OSId.OSX) {
/*     */           
/* 260 */           String str2 = "Library/Application Support/" + str1 + "/";
/* 261 */           this.directory = new File(str, str2);
/*     */         }
/*     */         else {
/*     */           
/* 265 */           String str2 = "." + str1 + "/";
/* 266 */           this.directory = new File(str, str2);
/*     */         } 
/*     */       } 
/*     */     } 
/* 270 */     return this.directory;
/*     */   }
/*     */   
/*     */   public void setDirectory(File paramFile) {
/* 274 */     File file = this.directory;
/* 275 */     this.directory = paramFile;
/* 276 */     firePropertyChange("directory", file, this.directory);
/*     */   }
/*     */ 
/*     */   
/*     */   private static class LSException
/*     */     extends IOException
/*     */   {
/*     */     public LSException(String param1String, Throwable param1Throwable) {
/* 284 */       super(param1String);
/* 285 */       initCause(param1Throwable);
/*     */     }
/*     */     
/* 288 */     public LSException(String param1String) { super(param1String); }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class RectanglePD
/*     */     extends DefaultPersistenceDelegate
/*     */   {
/* 301 */     public RectanglePD() { super(new String[] { "x", "y", "width", "height" }); }
/*     */     
/*     */     protected Expression instantiate(Object param1Object, Encoder param1Encoder) {
/* 304 */       Rectangle rectangle = (Rectangle)param1Object;
/* 305 */       Object[] arrayOfObject = { Integer.valueOf(rectangle.x), Integer.valueOf(rectangle.y), Integer.valueOf(rectangle.width), Integer.valueOf(rectangle.height) };
/*     */ 
/*     */       
/* 308 */       return new Expression(param1Object, param1Object.getClass(), "new", arrayOfObject);
/*     */     }
/*     */   } private abstract class LocalIO { private LocalIO() {} public abstract InputStream openInputFile(String param1String) throws IOException; public abstract OutputStream openOutputFile(String param1String) throws IOException;
/*     */     public abstract boolean deleteFile(String param1String) throws IOException; }
/*     */   private synchronized LocalIO getLocalIO() {
/* 313 */     if (this.localIO == null) {
/* 314 */       this.localIO = getPersistenceServiceIO();
/* 315 */       if (this.localIO == null) {
/* 316 */         this.localIO = new LocalFileIO();
/*     */       }
/*     */     } 
/* 319 */     return this.localIO;
/*     */   }
/*     */ 
/*     */   
/*     */   private class LocalFileIO
/*     */     extends LocalIO
/*     */   {
/*     */     private LocalFileIO() {}
/*     */ 
/*     */     
/*     */     public InputStream openInputFile(String param1String) throws IOException {
/* 330 */       File file = new File(LocalStorage.this.getDirectory(), param1String);
/*     */       try {
/* 332 */         return new BufferedInputStream(new FileInputStream(file));
/*     */       }
/* 334 */       catch (IOException iOException) {
/* 335 */         throw new LocalStorage.LSException("couldn't open input file \"" + param1String + "\"", iOException);
/*     */       } 
/*     */     }
/*     */     public OutputStream openOutputFile(String param1String) throws IOException {
/* 339 */       File file1 = LocalStorage.this.getDirectory();
/* 340 */       if (!file1.isDirectory() && 
/* 341 */         !file1.mkdirs()) {
/* 342 */         throw new LocalStorage.LSException("couldn't create directory " + file1);
/*     */       }
/*     */       
/* 345 */       File file2 = new File(file1, param1String);
/*     */       try {
/* 347 */         return new BufferedOutputStream(new FileOutputStream(file2));
/*     */       }
/* 349 */       catch (IOException iOException) {
/* 350 */         throw new LocalStorage.LSException("couldn't open output file \"" + param1String + "\"", iOException);
/*     */       } 
/*     */     }
/*     */     public boolean deleteFile(String param1String) throws IOException {
/* 354 */       File file = new File(LocalStorage.this.getDirectory(), param1String);
/* 355 */       return file.delete();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private LocalIO getPersistenceServiceIO() {
/*     */     try {
/* 366 */       Class<?> clazz = Class.forName("javax.jnlp.ServiceManager");
/* 367 */       Method method = clazz.getMethod("getServiceNames", new Class[0]);
/* 368 */       String[] arrayOfString = (String[])method.invoke(null, new Object[0]);
/* 369 */       boolean bool1 = false;
/* 370 */       boolean bool2 = false;
/* 371 */       for (String str : arrayOfString) {
/* 372 */         if (str.equals("javax.jnlp.BasicService")) {
/* 373 */           bool2 = true;
/*     */         }
/* 375 */         else if (str.equals("javax.jnlp.PersistenceService")) {
/* 376 */           bool1 = true;
/*     */         } 
/*     */       } 
/* 379 */       if (bool2 && bool1) {
/* 380 */         return new PersistenceServiceIO();
/*     */       }
/*     */     }
/* 383 */     catch (Exception exception) {}
/*     */ 
/*     */     
/* 386 */     return null;
/*     */   }
/*     */   
/*     */   private class PersistenceServiceIO
/*     */     extends LocalIO {
/*     */     private BasicService bs;
/*     */     private PersistenceService ps;
/*     */     
/* 394 */     private String initFailedMessage(String param1String) { return getClass().getName() + " initialization failed: " + param1String; }
/*     */ 
/*     */     
/*     */     PersistenceServiceIO() {
/*     */       try {
/* 399 */         this.bs = (BasicService)ServiceManager.lookup("javax.jnlp.BasicService");
/* 400 */         this.ps = (PersistenceService)ServiceManager.lookup("javax.jnlp.PersistenceService");
/*     */       }
/* 402 */       catch (UnavailableServiceException unavailableServiceException) {
/* 403 */         logger.log(Level.SEVERE, initFailedMessage("ServiceManager.lookup"), (Throwable)unavailableServiceException);
/* 404 */         this.bs = null; this.ps = null;
/*     */       } 
/*     */     }
/*     */     
/*     */     private void checkBasics(String param1String) throws IOException {
/* 409 */       if (this.bs == null || this.ps == null) {
/* 410 */         throw new IOException(initFailedMessage(param1String));
/*     */       }
/*     */     }
/*     */     
/*     */     private URL fileNameToURL(String param1String) throws IOException {
/*     */       try {
/* 416 */         return new URL(this.bs.getCodeBase(), param1String);
/*     */       }
/* 418 */       catch (MalformedURLException malformedURLException) {
/* 419 */         throw new LocalStorage.LSException("invalid filename \"" + param1String + "\"", malformedURLException);
/*     */       } 
/*     */     }
/*     */     
/*     */     public InputStream openInputFile(String param1String) throws IOException {
/* 424 */       checkBasics("openInputFile");
/* 425 */       URL uRL = fileNameToURL(param1String);
/*     */       try {
/* 427 */         return new BufferedInputStream(this.ps.get(uRL).getInputStream());
/*     */       }
/* 429 */       catch (Exception exception) {
/* 430 */         throw new LocalStorage.LSException("openInputFile \"" + param1String + "\" failed", exception);
/*     */       } 
/*     */     }
/*     */     
/*     */     public OutputStream openOutputFile(String param1String) throws IOException {
/* 435 */       checkBasics("openOutputFile");
/* 436 */       URL uRL = fileNameToURL(param1String);
/*     */       try {
/* 438 */         FileContents fileContents = null;
/*     */         try {
/* 440 */           fileContents = this.ps.get(uRL);
/*     */         }
/* 442 */         catch (FileNotFoundException fileNotFoundException) {
/*     */ 
/*     */ 
/*     */           
/* 446 */           long l1 = 131072L;
/* 447 */           long l2 = this.ps.create(uRL, l1);
/* 448 */           if (l2 >= l1) {
/* 449 */             fileContents = this.ps.get(uRL);
/*     */           }
/*     */         } 
/* 452 */         if (fileContents != null && fileContents.canWrite()) {
/* 453 */           return new BufferedOutputStream(fileContents.getOutputStream(true));
/*     */         }
/*     */         
/* 456 */         throw new IOException("unable to create FileContents object");
/*     */       
/*     */       }
/* 459 */       catch (Exception exception) {
/* 460 */         throw new LocalStorage.LSException("openOutputFile \"" + param1String + "\" failed", exception);
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean deleteFile(String param1String) throws IOException {
/* 465 */       checkBasics("deleteFile");
/* 466 */       URL uRL = fileNameToURL(param1String);
/*     */       try {
/* 468 */         this.ps.delete(uRL);
/* 469 */         return true;
/*     */       }
/* 471 */       catch (Exception exception) {
/* 472 */         throw new LocalStorage.LSException("openInputFile \"" + param1String + "\" failed", exception);
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\jdesktop\application\LocalStorage.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
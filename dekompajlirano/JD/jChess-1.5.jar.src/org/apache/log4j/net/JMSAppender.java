/*     */ package org.apache.log4j.net;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Properties;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.Message;
/*     */ import javax.jms.ObjectMessage;
/*     */ import javax.jms.Topic;
/*     */ import javax.jms.TopicConnection;
/*     */ import javax.jms.TopicConnectionFactory;
/*     */ import javax.jms.TopicPublisher;
/*     */ import javax.jms.TopicSession;
/*     */ import javax.naming.Context;
/*     */ import javax.naming.InitialContext;
/*     */ import javax.naming.NameNotFoundException;
/*     */ import javax.naming.NamingException;
/*     */ import org.apache.log4j.AppenderSkeleton;
/*     */ import org.apache.log4j.helpers.LogLog;
/*     */ import org.apache.log4j.spi.LoggingEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JMSAppender
/*     */   extends AppenderSkeleton
/*     */ {
/*     */   String securityPrincipalName;
/*     */   String securityCredentials;
/*     */   String initialContextFactoryName;
/*     */   String urlPkgPrefixes;
/*     */   String providerURL;
/*     */   String topicBindingName;
/*     */   String tcfBindingName;
/*     */   String userName;
/*     */   String password;
/*     */   boolean locationInfo;
/*     */   TopicConnection topicConnection;
/*     */   TopicSession topicSession;
/*     */   TopicPublisher topicPublisher;
/*     */   
/* 130 */   public void setTopicConnectionFactoryBindingName(String tcfBindingName) { this.tcfBindingName = tcfBindingName; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   public String getTopicConnectionFactoryBindingName() { return this.tcfBindingName; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 148 */   public void setTopicBindingName(String topicBindingName) { this.topicBindingName = topicBindingName; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 156 */   public String getTopicBindingName() { return this.topicBindingName; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 166 */   public boolean getLocationInfo() { return this.locationInfo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void activateOptions() {
/*     */     try {
/*     */       Context jndi;
/* 178 */       LogLog.debug("Getting initial context.");
/* 179 */       if (this.initialContextFactoryName != null) {
/* 180 */         Properties env = new Properties();
/* 181 */         env.put("java.naming.factory.initial", this.initialContextFactoryName);
/* 182 */         if (this.providerURL != null) {
/* 183 */           env.put("java.naming.provider.url", this.providerURL);
/*     */         } else {
/* 185 */           LogLog.warn("You have set InitialContextFactoryName option but not the ProviderURL. This is likely to cause problems.");
/*     */         } 
/*     */         
/* 188 */         if (this.urlPkgPrefixes != null) {
/* 189 */           env.put("java.naming.factory.url.pkgs", this.urlPkgPrefixes);
/*     */         }
/*     */         
/* 192 */         if (this.securityPrincipalName != null) {
/* 193 */           env.put("java.naming.security.principal", this.securityPrincipalName);
/* 194 */           if (this.securityCredentials != null) {
/* 195 */             env.put("java.naming.security.credentials", this.securityCredentials);
/*     */           } else {
/* 197 */             LogLog.warn("You have set SecurityPrincipalName option but not the SecurityCredentials. This is likely to cause problems.");
/*     */           } 
/*     */         } 
/*     */         
/* 201 */         jndi = new InitialContext(env);
/*     */       } else {
/* 203 */         jndi = new InitialContext();
/*     */       } 
/*     */       
/* 206 */       LogLog.debug("Looking up [" + this.tcfBindingName + "]");
/* 207 */       TopicConnectionFactory topicConnectionFactory = (TopicConnectionFactory)lookup(jndi, this.tcfBindingName);
/* 208 */       LogLog.debug("About to create TopicConnection.");
/* 209 */       if (this.userName != null) {
/* 210 */         this.topicConnection = topicConnectionFactory.createTopicConnection(this.userName, this.password);
/*     */       } else {
/*     */         
/* 213 */         this.topicConnection = topicConnectionFactory.createTopicConnection();
/*     */       } 
/*     */       
/* 216 */       LogLog.debug("Creating TopicSession, non-transactional, in AUTO_ACKNOWLEDGE mode.");
/*     */       
/* 218 */       this.topicSession = this.topicConnection.createTopicSession(false, 1);
/*     */ 
/*     */       
/* 221 */       LogLog.debug("Looking up topic name [" + this.topicBindingName + "].");
/* 222 */       Topic topic = (Topic)lookup(jndi, this.topicBindingName);
/*     */       
/* 224 */       LogLog.debug("Creating TopicPublisher.");
/* 225 */       this.topicPublisher = this.topicSession.createPublisher(topic);
/*     */       
/* 227 */       LogLog.debug("Starting TopicConnection.");
/* 228 */       this.topicConnection.start();
/*     */       
/* 230 */       jndi.close();
/* 231 */     } catch (JMSException e) {
/* 232 */       this.errorHandler.error("Error while activating options for appender named [" + this.name + "].", (Exception)e, 0);
/*     */     }
/* 234 */     catch (NamingException e) {
/* 235 */       this.errorHandler.error("Error while activating options for appender named [" + this.name + "].", e, 0);
/*     */     }
/* 237 */     catch (RuntimeException e) {
/* 238 */       this.errorHandler.error("Error while activating options for appender named [" + this.name + "].", e, 0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected Object lookup(Context ctx, String name) throws NamingException {
/*     */     try {
/* 245 */       return ctx.lookup(name);
/* 246 */     } catch (NameNotFoundException e) {
/* 247 */       LogLog.error("Could not find name [" + name + "].");
/* 248 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean checkEntryConditions() {
/* 253 */     String fail = null;
/*     */     
/* 255 */     if (this.topicConnection == null) {
/* 256 */       fail = "No TopicConnection";
/* 257 */     } else if (this.topicSession == null) {
/* 258 */       fail = "No TopicSession";
/* 259 */     } else if (this.topicPublisher == null) {
/* 260 */       fail = "No TopicPublisher";
/*     */     } 
/*     */     
/* 263 */     if (fail != null) {
/* 264 */       this.errorHandler.error(fail + " for JMSAppender named [" + this.name + "].");
/* 265 */       return false;
/*     */     } 
/* 267 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void close() {
/* 277 */     if (this.closed) {
/*     */       return;
/*     */     }
/* 280 */     LogLog.debug("Closing appender [" + this.name + "].");
/* 281 */     this.closed = true;
/*     */     
/*     */     try {
/* 284 */       if (this.topicSession != null)
/* 285 */         this.topicSession.close(); 
/* 286 */       if (this.topicConnection != null)
/* 287 */         this.topicConnection.close(); 
/* 288 */     } catch (JMSException e) {
/* 289 */       LogLog.error("Error while closing JMSAppender [" + this.name + "].", (Throwable)e);
/* 290 */     } catch (RuntimeException e) {
/* 291 */       LogLog.error("Error while closing JMSAppender [" + this.name + "].", e);
/*     */     } 
/*     */     
/* 294 */     this.topicPublisher = null;
/* 295 */     this.topicSession = null;
/* 296 */     this.topicConnection = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void append(LoggingEvent event) {
/* 303 */     if (!checkEntryConditions()) {
/*     */       return;
/*     */     }
/*     */     
/*     */     try {
/* 308 */       ObjectMessage msg = this.topicSession.createObjectMessage();
/* 309 */       if (this.locationInfo) {
/* 310 */         event.getLocationInformation();
/*     */       }
/* 312 */       msg.setObject((Serializable)event);
/* 313 */       this.topicPublisher.publish((Message)msg);
/* 314 */     } catch (JMSException e) {
/* 315 */       this.errorHandler.error("Could not publish message in JMSAppender [" + this.name + "].", (Exception)e, 0);
/*     */     }
/* 317 */     catch (RuntimeException e) {
/* 318 */       this.errorHandler.error("Could not publish message in JMSAppender [" + this.name + "].", e, 0);
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
/* 329 */   public String getInitialContextFactoryName() { return this.initialContextFactoryName; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 342 */   public void setInitialContextFactoryName(String initialContextFactoryName) { this.initialContextFactoryName = initialContextFactoryName; }
/*     */ 
/*     */ 
/*     */   
/* 346 */   public String getProviderURL() { return this.providerURL; }
/*     */ 
/*     */ 
/*     */   
/* 350 */   public void setProviderURL(String providerURL) { this.providerURL = providerURL; }
/*     */ 
/*     */ 
/*     */   
/* 354 */   String getURLPkgPrefixes() { return this.urlPkgPrefixes; }
/*     */ 
/*     */ 
/*     */   
/* 358 */   public void setURLPkgPrefixes(String urlPkgPrefixes) { this.urlPkgPrefixes = urlPkgPrefixes; }
/*     */ 
/*     */ 
/*     */   
/* 362 */   public String getSecurityCredentials() { return this.securityCredentials; }
/*     */ 
/*     */ 
/*     */   
/* 366 */   public void setSecurityCredentials(String securityCredentials) { this.securityCredentials = securityCredentials; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 371 */   public String getSecurityPrincipalName() { return this.securityPrincipalName; }
/*     */ 
/*     */ 
/*     */   
/* 375 */   public void setSecurityPrincipalName(String securityPrincipalName) { this.securityPrincipalName = securityPrincipalName; }
/*     */ 
/*     */ 
/*     */   
/* 379 */   public String getUserName() { return this.userName; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 390 */   public void setUserName(String userName) { this.userName = userName; }
/*     */ 
/*     */ 
/*     */   
/* 394 */   public String getPassword() { return this.password; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 401 */   public void setPassword(String password) { this.password = password; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 410 */   public void setLocationInfo(boolean locationInfo) { this.locationInfo = locationInfo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 418 */   protected TopicConnection getTopicConnection() { return this.topicConnection; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 426 */   protected TopicSession getTopicSession() { return this.topicSession; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 434 */   protected TopicPublisher getTopicPublisher() { return this.topicPublisher; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 442 */   public boolean requiresLayout() { return false; }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\log4j\net\JMSAppender.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.2
 */
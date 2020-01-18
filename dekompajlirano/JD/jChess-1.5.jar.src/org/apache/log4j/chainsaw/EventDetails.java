/*     */ package org.apache.log4j.chainsaw;
/*     */ 
/*     */ import org.apache.log4j.Priority;
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
/*     */ class EventDetails
/*     */ {
/*     */   private final long mTimeStamp;
/*     */   private final Priority mPriority;
/*     */   private final String mCategoryName;
/*     */   private final String mNDC;
/*     */   private final String mThreadName;
/*     */   private final String mMessage;
/*     */   private final String[] mThrowableStrRep;
/*     */   private final String mLocationDetails;
/*     */   
/*     */   EventDetails(long aTimeStamp, Priority aPriority, String aCategoryName, String aNDC, String aThreadName, String aMessage, String[] aThrowableStrRep, String aLocationDetails) {
/*  68 */     this.mTimeStamp = aTimeStamp;
/*  69 */     this.mPriority = aPriority;
/*  70 */     this.mCategoryName = aCategoryName;
/*  71 */     this.mNDC = aNDC;
/*  72 */     this.mThreadName = aThreadName;
/*  73 */     this.mMessage = aMessage;
/*  74 */     this.mThrowableStrRep = aThrowableStrRep;
/*  75 */     this.mLocationDetails = aLocationDetails;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  85 */   EventDetails(LoggingEvent aEvent) { this(aEvent.timeStamp, (Priority)aEvent.getLevel(), aEvent.getLoggerName(), aEvent.getNDC(), aEvent.getThreadName(), aEvent.getRenderedMessage(), aEvent.getThrowableStrRep(), (aEvent.getLocationInformation() == null) ? null : (aEvent.getLocationInformation()).fullInfo); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  98 */   long getTimeStamp() { return this.mTimeStamp; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 103 */   Priority getPriority() { return this.mPriority; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   String getCategoryName() { return this.mCategoryName; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 113 */   String getNDC() { return this.mNDC; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 118 */   String getThreadName() { return this.mThreadName; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 123 */   String getMessage() { return this.mMessage; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 128 */   String getLocationDetails() { return this.mLocationDetails; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 133 */   String[] getThrowableStrRep() { return this.mThrowableStrRep; }
/*     */ }


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\log4j\chainsaw\EventDetails.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.2
 */
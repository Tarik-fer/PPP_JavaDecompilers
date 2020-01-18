package org.apache.log4j.spi;

import org.apache.log4j.Appender;
import org.apache.log4j.Logger;

public interface ErrorHandler extends OptionHandler {
  void setLogger(Logger paramLogger);
  
  void error(String paramString, Exception paramException, int paramInt);
  
  void error(String paramString);
  
  void error(String paramString, Exception paramException, int paramInt, LoggingEvent paramLoggingEvent);
  
  void setAppender(Appender paramAppender);
  
  void setBackupAppender(Appender paramAppender);
}


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\log4j\spi\ErrorHandler.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.2
 */
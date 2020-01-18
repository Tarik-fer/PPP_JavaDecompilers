package org.apache.log4j.spi;

import java.util.Enumeration;
import org.apache.log4j.Appender;

public interface AppenderAttachable {
  void addAppender(Appender paramAppender);
  
  Enumeration getAllAppenders();
  
  Appender getAppender(String paramString);
  
  boolean isAttached(Appender paramAppender);
  
  void removeAllAppenders();
  
  void removeAppender(Appender paramAppender);
  
  void removeAppender(String paramString);
}


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\log4j\spi\AppenderAttachable.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.2
 */
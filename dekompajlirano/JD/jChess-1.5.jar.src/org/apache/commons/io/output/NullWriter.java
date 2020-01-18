package org.apache.commons.io.output;

import java.io.Writer;

public class NullWriter extends Writer {
  public void write(int idx) {}
  
  public void write(char[] chr) {}
  
  public void write(char[] chr, int st, int end) {}
  
  public void write(String str) {}
  
  public void write(String str, int st, int end) {}
  
  public void flush() {}
  
  public void close() {}
}


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\apache\commons\io\output\NullWriter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.2
 */
package org.junit.runner.notification;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.junit.runner.Description;
import org.junit.runner.Result;

public class RunListener {
  public void testRunStarted(Description description) throws Exception {}
  
  public void testRunFinished(Result result) throws Exception {}
  
  public void testStarted(Description description) throws Exception {}
  
  public void testFinished(Description description) throws Exception {}
  
  public void testFailure(Failure failure) throws Exception {}
  
  public void testAssumptionFailure(Failure failure) {}
  
  public void testIgnored(Description description) throws Exception {}
  
  @Documented
  @Target({ElementType.TYPE})
  @Retention(RetentionPolicy.RUNTIME)
  public static @interface ThreadSafe {}
}


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\runner\notification\RunListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
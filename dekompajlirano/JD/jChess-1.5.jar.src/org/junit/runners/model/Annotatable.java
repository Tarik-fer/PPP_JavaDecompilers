package org.junit.runners.model;

import java.lang.annotation.Annotation;

public interface Annotatable {
  Annotation[] getAnnotations();
  
  <T extends Annotation> T getAnnotation(Class<T> paramClass);
}


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\runners\model\Annotatable.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
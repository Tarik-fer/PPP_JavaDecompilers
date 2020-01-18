package org.hamcrest;

public interface Matcher<T> extends SelfDescribing {
  boolean matches(Object paramObject);
  
  void describeMismatch(Object paramObject, Description paramDescription);
  
  @Deprecated
  void _dont_implement_Matcher___instead_extend_BaseMatcher_();
}


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\hamcrest\Matcher.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
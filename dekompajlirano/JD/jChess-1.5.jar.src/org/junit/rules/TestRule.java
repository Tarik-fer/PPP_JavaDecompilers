package org.junit.rules;

import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public interface TestRule {
  Statement apply(Statement paramStatement, Description paramDescription);
}


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\rules\TestRule.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
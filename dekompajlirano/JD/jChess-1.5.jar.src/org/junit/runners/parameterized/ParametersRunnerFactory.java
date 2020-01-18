package org.junit.runners.parameterized;

import org.junit.runner.Runner;
import org.junit.runners.model.InitializationError;

public interface ParametersRunnerFactory {
  Runner createRunnerForTestWithParameters(TestWithParameters paramTestWithParameters) throws InitializationError;
}


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\runners\parameterized\ParametersRunnerFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
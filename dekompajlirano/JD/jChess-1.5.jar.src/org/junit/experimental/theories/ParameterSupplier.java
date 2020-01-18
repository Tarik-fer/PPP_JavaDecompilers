package org.junit.experimental.theories;

import java.util.List;

public abstract class ParameterSupplier {
  public abstract List<PotentialAssignment> getValueSources(ParameterSignature paramParameterSignature) throws Throwable;
}


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\junit\experimental\theories\ParameterSupplier.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
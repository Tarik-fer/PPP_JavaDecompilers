package org.jdesktop.swingworker;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import javax.swing.SwingUtilities;

public final class SwingPropertyChangeSupport extends PropertyChangeSupport {
  static final long serialVersionUID = 7162625831330845068L;
  
  private final boolean notifyOnEDT;
  
  public SwingPropertyChangeSupport(Object paramObject) { this(paramObject, false); }
  
  public SwingPropertyChangeSupport(Object paramObject, boolean paramBoolean) {
    super(paramObject);
    this.notifyOnEDT = paramBoolean;
  }
  
  public void firePropertyChange(final PropertyChangeEvent evt) {
    if (evt == null)
      throw new NullPointerException(); 
    if (!isNotifyOnEDT() || SwingUtilities.isEventDispatchThread()) {
      super.firePropertyChange(evt);
    } else {
      SwingUtilities.invokeLater(new Runnable() {
            public void run() { SwingPropertyChangeSupport.this.firePropertyChange(evt); }
          });
    } 
  }
  
  public final boolean isNotifyOnEDT() { return this.notifyOnEDT; }
}


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\org\jdesktop\swingworker\SwingPropertyChangeSupport.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.2
 */
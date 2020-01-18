import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

final class d implements PropertyChangeListener {
   // $FF: synthetic field
   private reflex a;

   d(reflex var1) {
	  super();
      this.a = var1;
   }

   public final void propertyChange(PropertyChangeEvent var1) {
      reflex.a(this.a, var1);
   }
}

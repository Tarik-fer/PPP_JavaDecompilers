// 
// Decompiled by Procyon v0.5.36
// 

package SJCE.more;

import java.net.URISyntaxException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.URI;
import java.awt.Desktop;

public class Mail_Url
{
    public static void goURL(final String suri) {
        Desktop d = null;
        if (Desktop.isDesktopSupported()) {
            d = Desktop.getDesktop();
        }
        if (d.isSupported(Desktop.Action.BROWSE)) {
            try {
                d.browse(new URI(suri));
            }
            catch (IOException ex) {
                Logger.getLogger(Mail_Url.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (URISyntaxException ex2) {
                Logger.getLogger(Mail_Url.class.getName()).log(Level.SEVERE, null, ex2);
            }
        }
    }
    
    public static void goMAIL(final String adres) {
        Desktop d = null;
        if (Desktop.isDesktopSupported()) {
            d = Desktop.getDesktop();
        }
        if (d.isSupported(Desktop.Action.MAIL)) {
            try {
                d.mail(new URI(adres));
            }
            catch (IOException ex) {
                Logger.getLogger(Mail_Url.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (URISyntaxException ex2) {
                Logger.getLogger(Mail_Url.class.getName()).log(Level.SEVERE, null, ex2);
            }
        }
    }
}

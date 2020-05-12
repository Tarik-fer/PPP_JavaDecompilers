/*
 * Decompiled with CFR 0.148.
 */
package SJCE.more;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mail_Url {
    public static void goURL(String suri) {
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
            catch (URISyntaxException ex) {
                Logger.getLogger(Mail_Url.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void goMAIL(String adres) {
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
            catch (URISyntaxException ex) {
                Logger.getLogger(Mail_Url.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}


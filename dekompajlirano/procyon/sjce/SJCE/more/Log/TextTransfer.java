// 
// Decompiled by Procyon v0.5.36
// 

package SJCE.more.Log;

import java.io.IOException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.datatransfer.DataFlavor;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;

public final class TextTransfer implements ClipboardOwner
{
    @Override
    public void lostOwnership(final Clipboard aClipboard, final Transferable aContents) {
    }
    
    public void setClipboardContents(final String aString) {
        final StringSelection stringSelection = new StringSelection(aString);
        final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, this);
    }
    
    public String getClipboardContents() {
        String result = "";
        final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        final Transferable contents = clipboard.getContents(null);
        final boolean hasTransferableText = contents != null && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
        if (hasTransferableText) {
            try {
                result = (String)contents.getTransferData(DataFlavor.stringFlavor);
            }
            catch (UnsupportedFlavorException ex) {
                System.out.println(ex);
                ex.printStackTrace();
            }
            catch (IOException ex2) {
                System.out.println(ex2);
                ex2.printStackTrace();
            }
        }
        return result;
    }
}

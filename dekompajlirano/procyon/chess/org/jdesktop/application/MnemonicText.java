// 
// Decompiled by Procyon v0.5.36
// 

package org.jdesktop.application;

import javax.swing.JLabel;
import javax.swing.AbstractButton;
import javax.swing.Action;
import java.text.StringCharacterIterator;

class MnemonicText
{
    private static final String DISPLAYED_MNEMONIC_INDEX_KEY = "SwingDisplayedMnemonicIndexKey";
    
    private MnemonicText() {
    }
    
    public static void configure(final Object o, final String s) {
        String string = s;
        int n = -1;
        int mnemonicKey = 0;
        int n2 = mnemonicMarkerIndex(s, '&');
        if (n2 == -1) {
            n2 = mnemonicMarkerIndex(s, '_');
        }
        if (n2 != -1) {
            string = string.substring(0, n2) + string.substring(n2 + 1);
            n = n2;
            mnemonicKey = mnemonicKey(new StringCharacterIterator(s, n2).next());
        }
        if (o instanceof Action) {
            configureAction((Action)o, string, mnemonicKey, n);
        }
        else if (o instanceof AbstractButton) {
            configureButton((AbstractButton)o, string, mnemonicKey, n);
        }
        else {
            if (!(o instanceof JLabel)) {
                throw new IllegalArgumentException("unrecognized target type " + o);
            }
            configureLabel((JLabel)o, string, mnemonicKey, n);
        }
    }
    
    private static int mnemonicMarkerIndex(final String s, final char c) {
        if (s == null || s.length() < 2) {
            return -1;
        }
        final StringCharacterIterator stringCharacterIterator = new StringCharacterIterator(s);
        for (int i = 0; i != -1; ++i) {
            i = s.indexOf(c, i);
            if (i != -1) {
                stringCharacterIterator.setIndex(i);
                final char previous = stringCharacterIterator.previous();
                stringCharacterIterator.setIndex(i);
                final char next = stringCharacterIterator.next();
                final boolean b = previous == '\'' && next == '\'';
                final boolean whitespace = Character.isWhitespace(next);
                if (!b && !whitespace && next != '\uffff') {
                    return i;
                }
            }
            if (i != -1) {}
        }
        return -1;
    }
    
    private static int mnemonicKey(final char c) {
        int n = c;
        if (n >= 97 && n <= 122) {
            n -= 32;
        }
        return n;
    }
    
    private static void configureAction(final Action action, final String s, final int n, final int n2) {
        action.putValue("Name", s);
        if (n != 0) {
            action.putValue("MnemonicKey", n);
        }
        if (n2 != -1) {
            action.putValue("SwingDisplayedMnemonicIndexKey", n2);
        }
    }
    
    private static void configureButton(final AbstractButton abstractButton, final String text, final int mnemonic, final int displayedMnemonicIndex) {
        abstractButton.setText(text);
        if (mnemonic != 0) {
            abstractButton.setMnemonic(mnemonic);
        }
        if (displayedMnemonicIndex != -1) {
            abstractButton.setDisplayedMnemonicIndex(displayedMnemonicIndex);
        }
    }
    
    private static void configureLabel(final JLabel label, final String text, final int displayedMnemonic, final int displayedMnemonicIndex) {
        label.setText(text);
        if (displayedMnemonic != 0) {
            label.setDisplayedMnemonic(displayedMnemonic);
        }
        if (displayedMnemonicIndex != -1) {
            label.setDisplayedMnemonicIndex(displayedMnemonicIndex);
        }
    }
}

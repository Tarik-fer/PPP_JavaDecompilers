/*
 * Decompiled with CFR 0.148.
 */
package SJCE.more.Log;

import SJCE.XChessFrame;
import SJCE.more.Log.LogShow;
import SJCE.more.Log.MFileFilter;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;

public class LogSave {
    public static String putf = "";
    public static String putd = "";
    public static FileInputStream fis;
    public static BufferedReader br;
    public static String bufer;
    public static String txt;
    public static String fullFileName;
    public static FileWriter writeFile;

    public static void Save() {
        JFileChooser fc = new JFileChooser();
        fc.addChoosableFileFilter(new MFileFilter(".log"));
        fc.setAcceptAllFileFilterUsed(false);
        fc.setDialogType(1);
        if (fc.showDialog(XChessFrame.logFrame, "Save Log") == 0) {
            try {
                fullFileName = fc.getSelectedFile().getPath();
                File outputfile = new File(fullFileName + ".log");
                writeFile = new FileWriter(outputfile);
                writeFile.append(XChessFrame.outputArea.getText());
            }
            catch (IOException ex) {
                Logger.getLogger(LogSave.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                if (writeFile != null) {
                    try {
                        writeFile.close();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static {
        bufer = "";
        txt = "";
        writeFile = null;
    }
}


// 
// Decompiled by Procyon v0.5.36
// 

package SJCE.more.Log;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.awt.Component;
import SJCE.XChessFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.JFileChooser;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileInputStream;

public class LogSave
{
    public static String putf;
    public static String putd;
    public static FileInputStream fis;
    public static BufferedReader br;
    public static String bufer;
    public static String txt;
    public static String fullFileName;
    public static FileWriter writeFile;
    
    public static void Save() {
        final JFileChooser fc = new JFileChooser();
        fc.addChoosableFileFilter(new MFileFilter(".log"));
        fc.setAcceptAllFileFilterUsed(false);
        fc.setDialogType(1);
        if (fc.showDialog(XChessFrame.logFrame, "Save Log") == 0) {
            try {
                LogSave.fullFileName = fc.getSelectedFile().getPath();
                final File outputfile = new File(LogSave.fullFileName + ".log");
                LogSave.writeFile = new FileWriter(outputfile);
                final FileWriter writeFile = LogSave.writeFile;
                final XChessFrame frame = XChessFrame.frame;
                writeFile.append(XChessFrame.outputArea.getText());
            }
            catch (IOException ex) {
                Logger.getLogger(LogSave.class.getName()).log(Level.SEVERE, null, ex);
                if (LogSave.writeFile != null) {
                    try {
                        LogSave.writeFile.close();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            finally {
                if (LogSave.writeFile != null) {
                    try {
                        LogSave.writeFile.close();
                    }
                    catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
    }
    
    static {
        LogSave.putf = "";
        LogSave.putd = "";
        LogSave.bufer = "";
        LogSave.txt = "";
        LogSave.writeFile = null;
    }
}

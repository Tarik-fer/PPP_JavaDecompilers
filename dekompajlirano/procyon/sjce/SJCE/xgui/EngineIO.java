// 
// Decompiled by Procyon v0.5.36
// 

package SJCE.xgui;

import java.io.IOException;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedReader;

public class EngineIO
{
    private Process process;
    private BufferedReader reader;
    private PrintWriter writer;
    private String command;
    
    public EngineIO(final String command) {
        this.command = command;
        this.start();
    }
    
    private void start() {
        final Runtime runtime = Runtime.getRuntime();
        try {
            this.process = runtime.exec(this.command);
            this.reader = new BufferedReader(new InputStreamReader(this.process.getInputStream()));
            this.writer = new PrintWriter(new OutputStreamWriter(this.process.getOutputStream()), true);
            runtime.addShutdownHook(new Thread() {
                @Override
                public void run() {
                    EngineIO.this.shutdownPerformed();
                }
            });
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void shutdownPerformed() {
        if (this.writer != null) {
            this.writeLine("quit");
            this.writer.close();
        }
        this.process.destroy();
    }
    
    public String readLine() throws IOException {
        final String string = this.reader.readLine();
        if (string != null) {
            System.out.println(string);
        }
        return string;
    }
    
    public void writeLine(final String string) {
        System.out.println(string);
        this.writer.println(string);
    }
    
    public void restart() {
        this.process.destroy();
        this.start();
    }
    
    public void destroy() {
        this.process.destroy();
    }
    
    public Process getProcess() {
        return this.process;
    }
    
    public String getCommand() {
        return this.command;
    }
}

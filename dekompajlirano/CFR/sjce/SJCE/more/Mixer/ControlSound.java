/*
 * Decompiled with CFR 0.148.
 */
package SJCE.more.Mixer;

import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.CompoundControl;
import javax.sound.sampled.Control;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;

public class ControlSound {
    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void setMasterOutputVolume(float value) {
        if (value < 0.0f || value > 1.0f) {
            throw new IllegalArgumentException("Volume can only be set to a value from 0 to 1. Given value is illegal: " + value);
        }
        Line line = ControlSound.getMasterOutputLine();
        if (line == null) {
            throw new RuntimeException("Master output port not found");
        }
        boolean opened = ControlSound.open(line);
        try {
            FloatControl control = ControlSound.getVolumeControl(line);
            if (control == null) {
                throw new RuntimeException("Volume control not found in master port: " + ControlSound.toString(line));
            }
            control.setValue(value);
        }
        finally {
            if (opened) {
                line.close();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Float getMasterOutputVolume() {
        Line line = ControlSound.getMasterOutputLine();
        if (line == null) {
            return null;
        }
        boolean opened = ControlSound.open(line);
        try {
            FloatControl control = ControlSound.getVolumeControl(line);
            if (control == null) {
                Float f = null;
                return f;
            }
            Float f = Float.valueOf(control.getValue());
            return f;
        }
        finally {
            if (opened) {
                line.close();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void setMasterOutputMute(boolean value) {
        Line line = ControlSound.getMasterOutputLine();
        if (line == null) {
            throw new RuntimeException("Master output port not found");
        }
        boolean opened = ControlSound.open(line);
        try {
            BooleanControl control = ControlSound.getMuteControl(line);
            if (control == null) {
                throw new RuntimeException("Mute control not found in master port: " + ControlSound.toString(line));
            }
            control.setValue(value);
        }
        finally {
            if (opened) {
                line.close();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Boolean getMasterOutputMute() {
        Line line = ControlSound.getMasterOutputLine();
        if (line == null) {
            return null;
        }
        boolean opened = ControlSound.open(line);
        try {
            BooleanControl control = ControlSound.getMuteControl(line);
            if (control == null) {
                Boolean bl = null;
                return bl;
            }
            Boolean bl = control.getValue();
            return bl;
        }
        finally {
            if (opened) {
                line.close();
            }
        }
    }

    public static Line getMasterOutputLine() {
        for (Mixer mixer : ControlSound.getMixers()) {
            for (Line line : ControlSound.getAvailableOutputLines(mixer)) {
                if (line.getLineInfo().toString().contains("Master")) {
                    return line;
                }
                if (!line.getLineInfo().toString().contains("SPEAKER")) continue;
                return line;
            }
        }
        return null;
    }

    public static FloatControl getVolumeControl(Line line) {
        if (!line.isOpen()) {
            throw new RuntimeException("Line is closed: " + ControlSound.toString(line));
        }
        return (FloatControl)ControlSound.findControl(FloatControl.Type.VOLUME, line.getControls());
    }

    public static BooleanControl getMuteControl(Line line) {
        if (!line.isOpen()) {
            throw new RuntimeException("Line is closed: " + ControlSound.toString(line));
        }
        return (BooleanControl)ControlSound.findControl(BooleanControl.Type.MUTE, line.getControls());
    }

    private static Control findControl(Control.Type type, Control ... controls) {
        if (controls == null || controls.length == 0) {
            return null;
        }
        for (Control control : controls) {
            CompoundControl compoundControl;
            Control member;
            if (control.getType().equals(type)) {
                return control;
            }
            if (!(control instanceof CompoundControl) || (member = ControlSound.findControl(type, (compoundControl = (CompoundControl)control).getMemberControls())) == null) continue;
            return member;
        }
        return null;
    }

    public static List<Mixer> getMixers() {
        Mixer.Info[] infos = AudioSystem.getMixerInfo();
        ArrayList<Mixer> mixers = new ArrayList<Mixer>(infos.length);
        for (Mixer.Info info : infos) {
            Mixer mixer = AudioSystem.getMixer(info);
            mixers.add(mixer);
        }
        return mixers;
    }

    public static List<Line> getAvailableOutputLines(Mixer mixer) {
        return ControlSound.getAvailableLines(mixer, mixer.getTargetLineInfo());
    }

    public static List<Line> getAvailableInputLines(Mixer mixer) {
        return ControlSound.getAvailableLines(mixer, mixer.getSourceLineInfo());
    }

    private static List<Line> getAvailableLines(Mixer mixer, Line.Info[] lineInfos) {
        ArrayList<Line> lines = new ArrayList<Line>(lineInfos.length);
        for (Line.Info lineInfo : lineInfos) {
            Line line = ControlSound.getLineIfAvailable(mixer, lineInfo);
            if (line == null) continue;
            lines.add(line);
        }
        return lines;
    }

    public static Line getLineIfAvailable(Mixer mixer, Line.Info lineInfo) {
        try {
            return mixer.getLine(lineInfo);
        }
        catch (LineUnavailableException ex) {
            return null;
        }
    }

    public static String getHierarchyInfo() {
        StringBuilder sb = new StringBuilder();
        for (Mixer mixer : ControlSound.getMixers()) {
            CompoundControl compoundControl;
            boolean opened;
            sb.append("Mixer: ").append(ControlSound.toString(mixer)).append("\n");
            for (Line line : ControlSound.getAvailableOutputLines(mixer)) {
                sb.append("  OUT: ").append(ControlSound.toString(line)).append("\n");
                opened = ControlSound.open(line);
                for (Control control : line.getControls()) {
                    sb.append("    Control: ").append(ControlSound.toString(control)).append("\n");
                    if (!(control instanceof CompoundControl)) continue;
                    compoundControl = (CompoundControl)control;
                    for (Control subControl : compoundControl.getMemberControls()) {
                        sb.append("      Sub-Control: ").append(ControlSound.toString(subControl)).append("\n");
                    }
                }
                if (!opened) continue;
                line.close();
            }
            for (Line line : ControlSound.getAvailableOutputLines(mixer)) {
                sb.append("  IN: ").append(ControlSound.toString(line)).append("\n");
                opened = ControlSound.open(line);
                for (Control control : line.getControls()) {
                    sb.append("    Control: ").append(ControlSound.toString(control)).append("\n");
                    if (!(control instanceof CompoundControl)) continue;
                    compoundControl = (CompoundControl)control;
                    for (Control subControl : compoundControl.getMemberControls()) {
                        sb.append("      Sub-Control: ").append(ControlSound.toString(subControl)).append("\n");
                    }
                }
                if (!opened) continue;
                line.close();
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static boolean open(Line line) {
        if (line.isOpen()) {
            return false;
        }
        try {
            line.open();
        }
        catch (LineUnavailableException ex) {
            return false;
        }
        return true;
    }

    public static String toString(Control control) {
        if (control == null) {
            return null;
        }
        return control.toString() + " (" + control.getType().toString() + ")";
    }

    public static String toString(Line line) {
        if (line == null) {
            return null;
        }
        Line.Info info = line.getLineInfo();
        return info.toString();
    }

    public static String toString(Mixer mixer) {
        if (mixer == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        Mixer.Info info = mixer.getMixerInfo();
        sb.append(info.getName());
        sb.append(" (").append(info.getDescription()).append(")");
        sb.append(mixer.isOpen() ? " [open]" : " [closed]");
        return sb.toString();
    }
}


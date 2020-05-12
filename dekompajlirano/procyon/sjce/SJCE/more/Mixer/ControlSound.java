// 
// Decompiled by Procyon v0.5.36
// 

package SJCE.more.Mixer;

import javax.sound.sampled.LineUnavailableException;
import java.util.ArrayList;
import javax.sound.sampled.AudioSystem;
import java.util.List;
import javax.sound.sampled.CompoundControl;
import javax.sound.sampled.Control;
import java.util.Iterator;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;

public class ControlSound
{
    public static void setMasterOutputVolume(final float value) {
        if (value < 0.0f || value > 1.0f) {
            throw new IllegalArgumentException("Volume can only be set to a value from 0 to 1. Given value is illegal: " + value);
        }
        final Line line = getMasterOutputLine();
        if (line == null) {
            throw new RuntimeException("Master output port not found");
        }
        final boolean opened = open(line);
        try {
            final FloatControl control = getVolumeControl(line);
            if (control == null) {
                throw new RuntimeException("Volume control not found in master port: " + toString(line));
            }
            control.setValue(value);
        }
        finally {
            if (opened) {
                line.close();
            }
        }
    }
    
    public static Float getMasterOutputVolume() {
        final Line line = getMasterOutputLine();
        if (line == null) {
            return null;
        }
        final boolean opened = open(line);
        try {
            final FloatControl control = getVolumeControl(line);
            if (control == null) {
                return null;
            }
            return control.getValue();
        }
        finally {
            if (opened) {
                line.close();
            }
        }
    }
    
    public static void setMasterOutputMute(final boolean value) {
        final Line line = getMasterOutputLine();
        if (line == null) {
            throw new RuntimeException("Master output port not found");
        }
        final boolean opened = open(line);
        try {
            final BooleanControl control = getMuteControl(line);
            if (control == null) {
                throw new RuntimeException("Mute control not found in master port: " + toString(line));
            }
            control.setValue(value);
        }
        finally {
            if (opened) {
                line.close();
            }
        }
    }
    
    public static Boolean getMasterOutputMute() {
        final Line line = getMasterOutputLine();
        if (line == null) {
            return null;
        }
        final boolean opened = open(line);
        try {
            final BooleanControl control = getMuteControl(line);
            if (control == null) {
                return null;
            }
            return control.getValue();
        }
        finally {
            if (opened) {
                line.close();
            }
        }
    }
    
    public static Line getMasterOutputLine() {
        for (final Mixer mixer : getMixers()) {
            for (final Line line : getAvailableOutputLines(mixer)) {
                if (line.getLineInfo().toString().contains("Master")) {
                    return line;
                }
                if (line.getLineInfo().toString().contains("SPEAKER")) {
                    return line;
                }
            }
        }
        return null;
    }
    
    public static FloatControl getVolumeControl(final Line line) {
        if (!line.isOpen()) {
            throw new RuntimeException("Line is closed: " + toString(line));
        }
        return (FloatControl)findControl(FloatControl.Type.VOLUME, line.getControls());
    }
    
    public static BooleanControl getMuteControl(final Line line) {
        if (!line.isOpen()) {
            throw new RuntimeException("Line is closed: " + toString(line));
        }
        return (BooleanControl)findControl(BooleanControl.Type.MUTE, line.getControls());
    }
    
    private static Control findControl(final Control.Type type, final Control... controls) {
        if (controls == null || controls.length == 0) {
            return null;
        }
        for (final Control control : controls) {
            if (control.getType().equals(type)) {
                return control;
            }
            if (control instanceof CompoundControl) {
                final CompoundControl compoundControl = (CompoundControl)control;
                final Control member = findControl(type, compoundControl.getMemberControls());
                if (member != null) {
                    return member;
                }
            }
        }
        return null;
    }
    
    public static List<Mixer> getMixers() {
        final Mixer.Info[] infos = AudioSystem.getMixerInfo();
        final List<Mixer> mixers = new ArrayList<Mixer>(infos.length);
        for (final Mixer.Info info : infos) {
            final Mixer mixer = AudioSystem.getMixer(info);
            mixers.add(mixer);
        }
        return mixers;
    }
    
    public static List<Line> getAvailableOutputLines(final Mixer mixer) {
        return getAvailableLines(mixer, mixer.getTargetLineInfo());
    }
    
    public static List<Line> getAvailableInputLines(final Mixer mixer) {
        return getAvailableLines(mixer, mixer.getSourceLineInfo());
    }
    
    private static List<Line> getAvailableLines(final Mixer mixer, final Line.Info[] lineInfos) {
        final List<Line> lines = new ArrayList<Line>(lineInfos.length);
        for (final Line.Info lineInfo : lineInfos) {
            final Line line = getLineIfAvailable(mixer, lineInfo);
            if (line != null) {
                lines.add(line);
            }
        }
        return lines;
    }
    
    public static Line getLineIfAvailable(final Mixer mixer, final Line.Info lineInfo) {
        try {
            return mixer.getLine(lineInfo);
        }
        catch (LineUnavailableException ex) {
            return null;
        }
    }
    
    public static String getHierarchyInfo() {
        final StringBuilder sb = new StringBuilder();
        for (final Mixer mixer : getMixers()) {
            sb.append("Mixer: ").append(toString(mixer)).append("\n");
            for (final Line line : getAvailableOutputLines(mixer)) {
                sb.append("  OUT: ").append(toString(line)).append("\n");
                final boolean opened = open(line);
                for (final Control control : line.getControls()) {
                    sb.append("    Control: ").append(toString(control)).append("\n");
                    if (control instanceof CompoundControl) {
                        final CompoundControl compoundControl = (CompoundControl)control;
                        for (final Control subControl : compoundControl.getMemberControls()) {
                            sb.append("      Sub-Control: ").append(toString(subControl)).append("\n");
                        }
                    }
                }
                if (opened) {
                    line.close();
                }
            }
            for (final Line line : getAvailableOutputLines(mixer)) {
                sb.append("  IN: ").append(toString(line)).append("\n");
                final boolean opened = open(line);
                for (final Control control : line.getControls()) {
                    sb.append("    Control: ").append(toString(control)).append("\n");
                    if (control instanceof CompoundControl) {
                        final CompoundControl compoundControl = (CompoundControl)control;
                        for (final Control subControl : compoundControl.getMemberControls()) {
                            sb.append("      Sub-Control: ").append(toString(subControl)).append("\n");
                        }
                    }
                }
                if (opened) {
                    line.close();
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    
    public static boolean open(final Line line) {
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
    
    public static String toString(final Control control) {
        if (control == null) {
            return null;
        }
        return control.toString() + " (" + control.getType().toString() + ")";
    }
    
    public static String toString(final Line line) {
        if (line == null) {
            return null;
        }
        final Line.Info info = line.getLineInfo();
        return info.toString();
    }
    
    public static String toString(final Mixer mixer) {
        if (mixer == null) {
            return null;
        }
        final StringBuilder sb = new StringBuilder();
        final Mixer.Info info = mixer.getMixerInfo();
        sb.append(info.getName());
        sb.append(" (").append(info.getDescription()).append(")");
        sb.append(mixer.isOpen() ? " [open]" : " [closed]");
        return sb.toString();
    }
}

// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.commons.io;

import java.io.IOException;
import java.io.OutputStream;

public class HexDump
{
    public static final String EOL;
    private static final StringBuffer _lbuffer;
    private static final StringBuffer _cbuffer;
    private static final char[] _hexcodes;
    private static final int[] _shifts;
    
    public static void dump(final byte[] data, final long offset, final OutputStream stream, final int index) throws IOException, ArrayIndexOutOfBoundsException, IllegalArgumentException {
        if (index < 0 || index >= data.length) {
            throw new ArrayIndexOutOfBoundsException("illegal index: " + index + " into array of length " + data.length);
        }
        if (stream == null) {
            throw new IllegalArgumentException("cannot write to nullstream");
        }
        long display_offset = offset + index;
        final StringBuffer buffer = new StringBuffer(74);
        for (int j = index; j < data.length; j += 16) {
            int chars_read = data.length - j;
            if (chars_read > 16) {
                chars_read = 16;
            }
            buffer.append(dump(display_offset)).append(' ');
            for (int k = 0; k < 16; ++k) {
                if (k < chars_read) {
                    buffer.append(dump(data[k + j]));
                }
                else {
                    buffer.append("  ");
                }
                buffer.append(' ');
            }
            for (int k = 0; k < chars_read; ++k) {
                if (data[k + j] >= 32 && data[k + j] < 127) {
                    buffer.append((char)data[k + j]);
                }
                else {
                    buffer.append('.');
                }
            }
            buffer.append(HexDump.EOL);
            stream.write(buffer.toString().getBytes());
            stream.flush();
            buffer.setLength(0);
            display_offset += chars_read;
        }
    }
    
    private static StringBuffer dump(final long value) {
        HexDump._lbuffer.setLength(0);
        for (int j = 0; j < 8; ++j) {
            HexDump._lbuffer.append(HexDump._hexcodes[(int)(value >> HexDump._shifts[j]) & 0xF]);
        }
        return HexDump._lbuffer;
    }
    
    private static StringBuffer dump(final byte value) {
        HexDump._cbuffer.setLength(0);
        for (int j = 0; j < 2; ++j) {
            HexDump._cbuffer.append(HexDump._hexcodes[value >> HexDump._shifts[j + 6] & 0xF]);
        }
        return HexDump._cbuffer;
    }
    
    static {
        EOL = System.getProperty("line.separator");
        _lbuffer = new StringBuffer(8);
        _cbuffer = new StringBuffer(2);
        _hexcodes = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        _shifts = new int[] { 28, 24, 20, 16, 12, 8, 4, 0 };
    }
}

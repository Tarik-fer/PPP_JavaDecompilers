// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.utils;

import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.security.MessageDigest;
import org.apache.log4j.Logger;

public class MD5
{
    private static final Logger LOG;
    
    public static String encrypt(final String str) {
        try {
            final MessageDigest message = MessageDigest.getInstance("MD5");
            message.update(str.getBytes(), 0, str.length());
            return new BigInteger(1, message.digest()).toString(16);
        }
        catch (NoSuchAlgorithmException ex) {
            MD5.LOG.error("NoSuchAlgorithmException: " + ex);
            return null;
        }
    }
    
    static {
        LOG = Logger.getLogger(MD5.class);
    }
}

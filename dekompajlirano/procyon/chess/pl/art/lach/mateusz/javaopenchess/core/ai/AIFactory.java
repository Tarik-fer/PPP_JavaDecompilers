// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.core.ai;

import pl.art.lach.mateusz.javaopenchess.core.ai.joc_ai.Level2;
import pl.art.lach.mateusz.javaopenchess.core.ai.joc_ai.Level1;

public class AIFactory
{
    public static final AI getAI(final int level) {
        AI result = new Level1();
        if (1 == level) {
            result = new Level1();
        }
        else if (2 == level) {
            result = new Level2();
        }
        return result;
    }
    
    public static final AI getAI(final String level) {
        return getAI(Integer.parseInt(level));
    }
}

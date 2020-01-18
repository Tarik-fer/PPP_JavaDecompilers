// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.display.views.chessboard;

import pl.art.lach.mateusz.javaopenchess.core.Chessboard;
import pl.art.lach.mateusz.javaopenchess.core.Square;
import java.awt.event.MouseListener;

public interface ChessboardMouseView extends MouseListener
{
    Square getSquare(final int p0, final int p1);
    
    void draw(final Chessboard p0);
}

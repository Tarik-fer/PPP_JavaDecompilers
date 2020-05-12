// 
// Decompiled by Procyon v0.5.36
// 

package SJCE.xgui.Interfaces;

import SJCE.xgui.JList.MoveListUI;
import SJCE.xgui.JPanel.ChessClock;
import SJCE.xgui.JPanel.BoardUI;

public interface IChessContext
{
    BoardUI getBoardUI();
    
    ChessClock getChessClock();
    
    MoveListUI getMoveListUI();
}

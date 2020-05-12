/*
 * Decompiled with CFR 0.148.
 */
package SJCE.xgui.Interfaces;

import SJCE.xgui.JList.MoveListUI;
import SJCE.xgui.JPanel.BoardUI;
import SJCE.xgui.JPanel.ChessClock;

public interface IChessContext {
    public BoardUI getBoardUI();

    public ChessClock getChessClock();

    public MoveListUI getMoveListUI();
}


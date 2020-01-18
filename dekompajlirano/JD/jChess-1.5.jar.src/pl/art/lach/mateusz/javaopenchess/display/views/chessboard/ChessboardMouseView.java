package pl.art.lach.mateusz.javaopenchess.display.views.chessboard;

import java.awt.event.MouseListener;
import pl.art.lach.mateusz.javaopenchess.core.Chessboard;
import pl.art.lach.mateusz.javaopenchess.core.Square;

public interface ChessboardMouseView extends MouseListener {
  Square getSquare(int paramInt1, int paramInt2);
  
  void draw(Chessboard paramChessboard);
}


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\display\views\chessboard\ChessboardMouseView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */
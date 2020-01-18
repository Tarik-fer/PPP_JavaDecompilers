package pl.art.lach.mateusz.javaopenchess.core.players;

import java.io.Serializable;
import pl.art.lach.mateusz.javaopenchess.core.Chessboard;
import pl.art.lach.mateusz.javaopenchess.core.Colors;
import pl.art.lach.mateusz.javaopenchess.core.pieces.Piece;

public interface Player extends Serializable {
  public static final String CPU_NAME = "CPU";
  
  Colors getColor();
  
  String getName();
  
  PlayerType getPlayerType();
  
  boolean isGoDown();
  
  void setGoDown(boolean paramBoolean);
  
  void setName(String paramString);
  
  void setType(PlayerType paramPlayerType);
  
  Piece getPromotionPiece(Chessboard paramChessboard);
}


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\core\players\Player.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */
package pl.art.lach.mateusz.javaopenchess.core.data_transfer;

import pl.art.lach.mateusz.javaopenchess.core.Game;
import pl.art.lach.mateusz.javaopenchess.core.exceptions.ReadGameError;

public interface DataImporter {
  Game importData(String paramString) throws ReadGameError;
  
  void importData(String paramString, Game paramGame) throws ReadGameError;
}


/* Location:              C:\Users\Tarik\OneDrive - fer.hr\FAKS\5. semestar\PPP\Testiranje\Test programi\jChess-1.5\jChess-1.5\jChess-1.5.jar!\pl\art\lach\mateusz\javaopenchess\core\data_transfer\DataImporter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.2
 */
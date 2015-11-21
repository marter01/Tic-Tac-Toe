package unit.GameDataModelTests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import game.GameDataModel;

public class GameDataModelTests {
  public static GameDataModel dataModel = new GameDataModel();

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    dataModel.setUserWins(0);
    dataModel.setComputerWins(0);
    dataModel.setTies(0);
  }

  @Test
  public void testWinsLosesTiesAndTotalGames() {
    int timesToPretendPlaying = ((int) (Math.random() * 1500));
    
    for (int playTime = 0; playTime < timesToPretendPlaying; playTime++) {
      int whatHappened = ((int) (Math.random() * 2));
      switch (whatHappened) {
      case 0:
        dataModel.incrementComputerWins();
        break;
      case 1:
        dataModel.incrementTies();
        break;
      case 2:
        dataModel.incrementUserWins();
        break;
      }
      
      assertEquals("Error in games played logic", dataModel.getComputerWins() + dataModel.getTies() + dataModel.getUserWins(), dataModel.getGamesPlayed());
      assertEquals("Error in games counting logic", playTime + 1, dataModel.getGamesPlayed());
    }
    
  }    

}

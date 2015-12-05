package unit;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import game.GameConstants;
import game.GameUtilities;

public class GameUtilitiesHasSomeoneWonTests {
  public static final int numberOfCells = GameConstants.ROWS * GameConstants.COLUMNS;
  public static String boardStateNoOneWins[] = new String[numberOfCells];
  public static String boardStateUserWinsTopRow[] = new String[numberOfCells];
  
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    
    for (int i = 0; i < numberOfCells; i++) {
      boardStateNoOneWins[i] = GameConstants.NOONEMARK;
    }

    for (int i = 0; i < numberOfCells; i++) {
      if (i < GameConstants.COLUMNS) {
        boardStateUserWinsTopRow[i] = GameConstants.USERMARK;
      }
      else {
        boardStateUserWinsTopRow[i] = GameConstants.NOONEMARK;
      }
    }

  }

  @Test
  public void testNoOneWins() {
    if (GameUtilities.hasSomeoneWon(boardStateNoOneWins) != GameConstants.Result.NONE) {
      fail("We did not expect anyone to win");
    }    
  }

  @Test
  public void testUserWinsTopRow() {
    if (GameUtilities.hasSomeoneWon(boardStateUserWinsTopRow) != GameConstants.Result.USERWIN) {
      fail("We expected the user to win");
    }    
  }

}

package unit;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import game.GameConstants;
import game.GameController;
import game.GameDataModel;
import mocks.GameViewMock;

public class GameControllerTests {
  public static GameDataModel dataModel = new GameDataModel();
  public static GameViewMock gameView = new GameViewMock();
  public static GameController controller = new GameController();

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    gameView.initializeView(dataModel);
    controller.startGame(gameView, dataModel);
  }

  @Test
  public void testUserMovedHard() {
    controller.startNewGame();
    dataModel.difficultyLevel = GameConstants.DifficultyLevel.Hard;
    long startMoves = gameView.movesMade;
    controller.userMoved();
    long endMoves = gameView.movesMade;
    assertEquals("Computer did not move", startMoves + 1, endMoves);
  }

  @Test
  public void testUserMovedMedium() {
    controller.startNewGame();
    dataModel.difficultyLevel = GameConstants.DifficultyLevel.Medium;
    long startMoves = gameView.movesMade;
    controller.userMoved();
    long endMoves = gameView.movesMade;
    assertEquals("Computer did not move", startMoves + 1, endMoves);
  }

  @Test
  public void testUserMovedEasy() {
    controller.startNewGame();
    dataModel.difficultyLevel = GameConstants.DifficultyLevel.Easy;
    long startMoves = gameView.movesMade;
    controller.userMoved();
    long endMoves = gameView.movesMade;
    assertEquals("Computer did not move", startMoves + 1, endMoves);
  }

  @Test
  public void testUserNoMove() {
    for (int row = 0; row < GameConstants.ROWS; row++) {
      for (int column = 0; column < GameConstants.COLUMNS; column++) {
        dataModel.boardState[row * GameConstants.ROWS + column] = GameConstants.USERMARK;
      }
    }
    long startMoves = gameView.movesMade;
    controller.userMoved();
    long endMoves = gameView.movesMade;
    assertEquals("Computer should not have moved", startMoves, endMoves);
  }

  @Test
  public void startNewGameTest() {
    dataModel.boardState[3] = GameConstants.USERMARK;
    controller.startNewGame();
    int filledCells = 0;
    for (int row = 0; row < GameConstants.ROWS; row++) {
      for (int column = 0; column < GameConstants.COLUMNS; column++) {
        if (dataModel.boardState[row * GameConstants.ROWS + column] != GameConstants.NOONEMARK) {
          filledCells++;
        }
      }
    }
    assertEquals("The board was not cleared", filledCells, 0);
  }
  
}

package unit.GameUtilitiesTests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import game.GameConstants;
import game.GameUtilities;

public class GameUtilitiesConvertBoardTests {
  public static final int numberOfCells = GameConstants.ROWS * GameConstants.COLUMNS;
  public static String boardStateEmpty[] = new String[numberOfCells];
  public static String boardStateTopRowComputerBottomRowUser[] = new String[numberOfCells];

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    
    for (int i = 0; i < numberOfCells; i++) {
      boardStateEmpty[i] = GameConstants.NOONEMARK;
    }

    for (int i = 0; i < numberOfCells; i++) {
      if (i < GameConstants.COLUMNS) {
        boardStateTopRowComputerBottomRowUser[i] = GameConstants.COMPUTERMARK;
      }
      else if (i > (GameConstants.ROWS - 1) * GameConstants.COLUMNS - 1) {
        boardStateTopRowComputerBottomRowUser[i] = GameConstants.USERMARK;
      }
      else {
        boardStateTopRowComputerBottomRowUser[i] = GameConstants.NOONEMARK;
      }
    }
    
  }

  @Test
  public void testConvertBoardStateToIntArrayEmptyBoard() {

    GameConstants.Player[][] result = GameUtilities.convertBoardStateToIntArray(boardStateEmpty);

    for (int row = 0; row < GameConstants.ROWS; row++) {
      for (int column = 0; column < GameConstants.COLUMNS; column++) {
        if (result[row][column] != GameConstants.Player.NOONE) {
          fail("We expected row: " + String.valueOf(row) + " column: " + String.valueOf(column) + " to be assigned to no one");
        }        
      }
    }
    
  }    

  @Test
  public void testConvertBoardStateToIntArrayTopRowComputerBottomRowUser() {

    GameConstants.Player[][] result = GameUtilities.convertBoardStateToIntArray(boardStateTopRowComputerBottomRowUser);

    for (int column = 0; column < GameConstants.COLUMNS; column++) {
      if (result[0][column] != GameConstants.Player.COMPUTER) {
        fail("We expected row: " + String.valueOf(0) + " column: " + String.valueOf(column) + " to be assigned to the computer");
      }        
    }

    for (int column = 0; column < GameConstants.COLUMNS; column++) {
      if (result[GameConstants.ROWS - 1][column] != GameConstants.Player.USER) {
        fail("We expected row: " + String.valueOf(GameConstants.ROWS - 1) + " column: " + String.valueOf(column) + " to be assigned to the user");
      }        
    }
    
    for (int row = 1; row < GameConstants.ROWS - 1; row++) {
      for (int column = 0; column < GameConstants.COLUMNS; column++) {
        if (result[row][column] != GameConstants.Player.NOONE) {
          fail("We expected row: " + String.valueOf(row) + " column: " + String.valueOf(column) + " to be assigned to no one");
        }        
      }
    }
    
  }    

  @Test
  public void testConvertBoardStateToIntArrayRandom() {
    String boardStateRandom[] = new String[numberOfCells];
    
    for (int i = 0; i < numberOfCells; i++) {
      int choice = ((int) (Math.random() * 3));
      
      if (choice == 0) {
        boardStateRandom[i] = GameConstants.COMPUTERMARK;
      }
      else if (choice == 1) {
        boardStateRandom[i] = GameConstants.USERMARK;
      }
      else {
        boardStateRandom[i] = GameConstants.NOONEMARK;        
      }
    }
    
    GameConstants.Player[][] result = GameUtilities.convertBoardStateToIntArray(boardStateRandom);

    int stateIndex = 0;
    for (int row = 0; row < GameConstants.ROWS; row++) {
      for (int column = 0; column < GameConstants.COLUMNS; column++, stateIndex++) {
        GameConstants.Player playerResult = result[row][column];
        String boardState = boardStateRandom[stateIndex];
        
        if (playerResult == GameConstants.Player.NOONE && boardState != GameConstants.NOONEMARK) {
          fail("At row: " + String.valueOf(row) + " column: " + String.valueOf(column) + " player cells and board state are inconsistent (No One)");
        }

        if (playerResult == GameConstants.Player.COMPUTER && boardState != GameConstants.COMPUTERMARK) {
          fail("At row: " + String.valueOf(row) + " column: " + String.valueOf(column) + " player cells and board state are inconsistent (Computer)");
        }

        if (playerResult == GameConstants.Player.USER && boardState != GameConstants.USERMARK) {
          fail("At row: " + String.valueOf(row) + " column: " + String.valueOf(column) + " player cells and board state are inconsistent (User)");
        }

      }
    }
  
  }
  
}

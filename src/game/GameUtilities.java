package game;
import java.util.ArrayList;
import java.util.List;

public final class GameUtilities {

  public static GameConstants.Player[][] convertBoardStateToIntArray(String boardState[]) {
    GameConstants.Player[][] array = new GameConstants.Player[GameConstants.ROWS][GameConstants.COLUMNS];
    
    for (int row = 0; row < GameConstants.ROWS; row++) {
      for (int column = 0; column < GameConstants.COLUMNS; column++) {

        switch (boardState[row * GameConstants.COLUMNS + column]) {
        case GameConstants.NOONEMARK:
          array[row][column] = GameConstants.Player.NOONE;
          break;
        case GameConstants.USERMARK:
          array[row][column] = GameConstants.Player.USER;
          break;
        case GameConstants.COMPUTERMARK:
          array[row][column] = GameConstants.Player.COMPUTER;
          break;
        }
        
      }
      
    }
    
    return array;
  }

  public static List<int[]> generatePossibleMoves(GameConstants.Player[][] boardCells) {
    List<int[]> nextMoves = new ArrayList<int[]>(); // allocate List

    // If someone has won then there are no more moves
    if (hasWon(boardCells, GameConstants.Player.USER) || hasWon(boardCells, GameConstants.Player.COMPUTER)) {
       return nextMoves;
    }

    // Search for empty cells and add to the List
    for (int row = 0; row < GameConstants.ROWS; row++) {
       for (int column = 0; column < GameConstants.COLUMNS; column++) {
          if (boardCells[row][column] == GameConstants.Player.NOONE) {
             nextMoves.add(new int[] {row, column});
          }
       }
    }
    
    return nextMoves;
  }
  
  public static GameConstants.Result hasSomeoneWon(String boardState[]) {
    GameConstants.Result winner = GameConstants.Result.NONE;
    GameConstants.Player[][] boardCells = convertBoardStateToIntArray(boardState);

    if (hasWon(boardCells, GameConstants.Player.USER)) {
      winner = GameConstants.Result.USERWIN;
    }
    else if (hasWon(boardCells, GameConstants.Player.COMPUTER)) {
      winner = GameConstants.Result.COMPUTERWIN;
    }
    else if (generatePossibleMoves(boardCells).isEmpty()) {
      winner = GameConstants.Result.TIE;
    }
    
    return winner;
  }
  
  private static boolean hasWon(GameConstants.Player[][] boardCells, GameConstants.Player player) {
    int pattern = 0b000000000;  // 9-bit pattern for the 9 cells

    for (int row = 0; row < GameConstants.ROWS; row++) {
       for (int column = 0; column < GameConstants.COLUMNS; ++column) {
          if (boardCells[row][column] == player) {
             pattern |= (1 << (row * GameConstants.COLUMNS + column));
          }
       }
    }
    for (int winningPattern : GameConstants.WinningPatterns) {
       if ((pattern & winningPattern) == winningPattern) {
         return true;
       }
    }
    
    return false;  
  }

}

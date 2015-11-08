package game;
import java.util.List;
import game.GameConstants;
import game.GameUtilities;

public final class MinimumMaximumScoreAlgorithm {
  private GameConstants.Player[][] boardCells;
  
  public int returnBestMove(String boardState[]) {
    int bestMove = 0;
    
    boardCells = GameUtilities.convertBoardStateToIntArray(boardState);
    bestMove = getBestMove(GameConstants.Player.USER);

    return bestMove;
  }
    
  private int getBestMove(GameConstants.Player x) {
    int[] result = minimumMaximumScore(2, x);
    return result[1] * GameConstants.COLUMNS + result[2];
  }
  
  private int[] minimumMaximumScore(int depth, GameConstants.Player player) {
    int currentScore;
    int bestRow = -1;
    int bestColumn = -1;
    int bestScore = (player == GameConstants.Player.USER) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

    List<int[]> nextMoves = GameUtilities.generatePossibleMoves(boardCells);
      
    if (nextMoves.isEmpty() || depth == 0) {
      bestScore = evaluate();
    }
    else {
      
      for (int[] move : nextMoves) {
        
        boardCells[move[0]][move[1]] = player;
        if (player == GameConstants.Player.USER) {
          currentScore = minimumMaximumScore(depth - 1, GameConstants.Player.COMPUTER)[0];
          if (currentScore > bestScore) {
             bestScore = currentScore;
             bestRow = move[0];
             bestColumn = move[1];
          }
          
        }
        else {
          currentScore = minimumMaximumScore(depth - 1, GameConstants.Player.USER)[0];
          if (currentScore < bestScore) {
            bestScore = currentScore;
            bestRow = move[0];
            bestColumn = move[1];  
          }
          
        }
        
        // Undo move
        boardCells[move[0]][move[1]] = GameConstants.Player.NOONE;
        
      }
      
    }
    
    return new int[] {bestScore, bestRow, bestColumn};  
  }
  
  /** The heuristic evaluation function for the given line of 3 cells
  @Return +100, +10, +1 for 3-in-a-line, 2-in-a-line, 1-in-a-line for computer
          -100, -10, -1 for 3-in-a-line, 2-in-a-line, 1-in-a-line for opponent
           0 otherwise */
  private int evaluate() {
    int score = 0;
    
    // Evaluate score for each of the 8 lines (3 rows, 3 columns, 2 diagonals)
    score += evaluateLine(0, 0, 0, 1, 0, 2);  // row 0
    score += evaluateLine(1, 0, 1, 1, 1, 2);  // row 1
    score += evaluateLine(2, 0, 2, 1, 2, 2);  // row 2
    score += evaluateLine(0, 0, 1, 0, 2, 0);  // column 0
    score += evaluateLine(0, 1, 1, 1, 2, 1);  // column 1
    score += evaluateLine(0, 2, 1, 2, 2, 2);  // column 2
    score += evaluateLine(0, 0, 1, 1, 2, 2);  // diagonal 0
    score += evaluateLine(0, 2, 1, 1, 2, 0);  // diagonal 1
    
    return score;  
  }

  /** The heuristic evaluation function for the given line of 3 cells
  @Return +100, +10, +1 for 3-in-a-line, 2-in-a-line, 1-in-a-line for computer
          -100, -10, -1 for 3-in-a-line, 2-in-a-line, 1-in-a-line for opponent
           0 otherwise */
  private int evaluateLine(int row1, int column1, int row2, int column2, int row3, int column3) {
    int score = 0;
    
    // First cell
    if (boardCells[row1][column1] == GameConstants.Player.USER) {
       score = 1;
    }
    else if (boardCells[row1][column1] == GameConstants.Player.COMPUTER) {
       score = -1;
    }

    // Second cell
    if (boardCells[row2][column2] == GameConstants.Player.USER) {
      if (score == 1) {
        score = 10;    
      }
       else if (score == -1) {
          return 0;
       }
       else {
          score = 1;
       }
    }
    else if (boardCells[row2][column2] == GameConstants.Player.COMPUTER) {
      if (score == -1) {
        score = -10;  
      }
      else if (score == 1) {
        return 0;  
      }
      else {
        score = -1;  
      }       
    }

    // Third cell
    if (boardCells[row3][column3] == GameConstants.Player.USER) {
      if (score > 0) {
        score *= 10;  
      }
      else if (score < 0) {
        return 0;  
      }
      else {
        score = 1;  
      }
    }
    else if (boardCells[row3][column3] == GameConstants.Player.COMPUTER) {
      if (score < 0) {
        score *= 10;  
      }
      else if (score > 1) {
        return 0;
      }
      else {
        score = -1;
      }
    }
    
    return score;    
  }
  
}

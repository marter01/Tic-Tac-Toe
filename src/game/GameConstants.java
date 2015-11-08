package game;

public final class GameConstants {

  public final static int ROWS = 3;
  public final static int COLUMNS = 3;

  public final static String NOONEMARK = "";
  public final static String USERMARK = "X";
  public final static String COMPUTERMARK = "O";
  
  public enum DifficultyLevel {
    Easy, Medium, Hard
  }

  public enum Player {
    NOONE,
    USER,
    COMPUTER
  }

  public enum Result {
    NONE, 
    USERWIN,
    COMPUTERWIN,
    TIE
  }

  public final static int[] WinningPatterns = {
      0b111000000, 0b000111000, 0b000000111,  // Rows
      0b100100100, 0b010010010, 0b001001001,  // Columns
      0b100010001, 0b001010100                // Diagonals
  };
  
  private GameConstants(){
    throw new AssertionError();
  }

}

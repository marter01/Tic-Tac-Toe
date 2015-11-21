package game;

public class GameController implements UserMovedListener {
  private GameView gameView;
  private GameDataModel dataModel;
  private MinimumMaximumScoreAlgorithm bestMoveAlgorithm = new MinimumMaximumScoreAlgorithm();
  
  public void startGame(GameView view, GameDataModel model) {
    gameView = view;
    dataModel = model;

    gameView.addUserMovedListener(this);
    startNewGame();
  }
 
  public void startNewGame() {
    gameView.startNewGame();      
  }
  
  public void userMoved() {
    if (!didSomeoneWin()) {
      computerMove();
    }
  }
        
  private void computerMove() {
    boolean guess = false;
    
    if (dataModel.difficultyLevel == GameConstants.DifficultyLevel.Easy) {
      guess = true;
    }
    else if (dataModel.difficultyLevel == GameConstants.DifficultyLevel.Medium) {
      int guessIfThreeOfFive = ((int) (Math.random() * 5));
      if (guessIfThreeOfFive == 3) {
        guess = true;
      }
    }

    int move = -1;
    if (guess) {
      move = randomMove();
    }
    else {
      move = bestMoveAlgorithm.returnBestMove(dataModel.boardState);
    }
 
    gameView.makeComputerMove(move);
  
    didSomeoneWin();
    
  }

  private int randomMove() {
    int move = -1;
    
    while (move == -1) {
      int attempt = ((int) (Math.random() * GameConstants.NUMBEROFCELLS));
      if (dataModel.boardState[attempt] == GameConstants.NOONEMARK) {
        move = attempt;
      }
    }
    
    return move;  
  }
  
  private boolean didSomeoneWin() {
    boolean someoneWon = false;

    GameConstants.Result gameWinner = GameUtilities.hasSomeoneWon(dataModel.boardState);
    if (gameWinner != GameConstants.Result.NONE) {
      someoneWon = true;

      switch (gameWinner) {
      case TIE:
        gameView.updateWinningDisplay("We Tied");
        dataModel.incrementTies();
        break;
      case USERWIN:
        gameView.updateWinningDisplay("You Won");
        dataModel.incrementUserWins();
        break;
      case COMPUTERWIN:
        gameView.updateWinningDisplay("I Won");
        dataModel.incrementComputerWins();
        break;
      default:
          break;
      }
      
      gameView.askForNewGame();
      
    }
    
    return someoneWon;
  }

}

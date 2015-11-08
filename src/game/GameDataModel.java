package game;

import java.util.List;
import java.util.ArrayList;

interface GameStatisticsUpdateListener {
  void gameStatisticsUpdated();
}

public class GameDataModel {
  private static int numberOfCells = GameConstants.ROWS * GameConstants.COLUMNS;

  public String boardState[] = new String[numberOfCells];

  private int gamesPlayed = 0;
  public int getGamesPlayed() {
    return this.gamesPlayed;
  }
  public void setGamesPlayed(int gamesPlayed) {
    this.gamesPlayed = gamesPlayed;
    gameStatisticsUpdated();
  }
  public void incrementGamesPlayed() {
    this.gamesPlayed++;
    gameStatisticsUpdated();
  }

  private int userWins = 0;
  public int getUserWins() {
    return this.userWins;
  }
  public void setUserWins(int userWins) {
    this.userWins = userWins;
    gameStatisticsUpdated();
  }
  public void incrementUserWins() {
    this.userWins++;
    gameStatisticsUpdated();
  }
  
  private int computerWins = 0;
  public int getComputerWins() {
    return this.computerWins;
  }
  public void setComputerWins(int computerWins) {
    this.computerWins = computerWins;
    gameStatisticsUpdated();
  }
  public void incrementComputerWins() {
    this.computerWins++;
    gameStatisticsUpdated();
  }
  
  private int ties = 0;
  public int getTies() {
    return this.ties;
  }
  public void setTies(int ties) {
    this.ties = ties;
    gameStatisticsUpdated();
  }
  public void incrementTies() {
    this.ties++;
    gameStatisticsUpdated();
  }

  public GameConstants.DifficultyLevel difficultyLevel = GameConstants.DifficultyLevel.Hard;

  private List<GameStatisticsUpdateListener> gameStatisticsUpdateListeners = new ArrayList<GameStatisticsUpdateListener>();
  public void addGameStatisticsUpdateListenerListener(GameStatisticsUpdateListener listener) {
    gameStatisticsUpdateListeners.add(listener);
  }
  private void gameStatisticsUpdated() {
    for (GameStatisticsUpdateListener listener: gameStatisticsUpdateListeners) {
      listener.gameStatisticsUpdated();
    }  
  }

}

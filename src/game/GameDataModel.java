package game;

import java.util.List;
import java.util.ArrayList;

interface GameStatisticsUpdateListener {
  void gameStatisticsUpdated();
}

public class GameDataModel {
  public String boardState[] = new String[GameConstants.NUMBEROFCELLS];

  public long getGamesPlayed() {
    return userWins + computerWins + ties;
  }

  private long userWins = 0;
  public long getUserWins() {
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
  
  private long computerWins = 0;
  public long getComputerWins() {
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
  
  private long ties = 0;
  public long getTies() {
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

  public void clearBoard() {
    for (int i = 0; i < GameConstants.NUMBEROFCELLS; i++ ) {
      boardState[i] = GameConstants.NOONEMARK;  
    }
  }
  
}

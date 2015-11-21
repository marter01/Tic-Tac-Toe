package mocks;

import javax.swing.JPanel;

import game.GameConstants;
import game.GameDataModel;
import game.GameView;

public class GameViewMock extends GameView {
  private GameDataModel dataModel;
  public long movesMade = 0;

  public JPanel initializeView(GameDataModel model) {
    dataModel = model;
    return this;
  }

  public void startNewGame() {
    movesMade = 0;
    for (int i = 0; i < GameConstants.NUMBEROFCELLS; i++ ) {
      dataModel.boardState[i] = GameConstants.NOONEMARK;  
    }
  }

  public void makeComputerMove(int move) {
    dataModel.boardState[move] = GameConstants.COMPUTERMARK;
    movesMade++;
  }

  public void gameStatisticsUpdated() {
  }

  public void updateWinningDisplay(String display) {
  }

  public void askForNewGame() {
  }

}

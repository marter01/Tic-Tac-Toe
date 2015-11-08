package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;

import game.GameDataModel;

interface UserMovedListener {
  void userMoved();
}

public class GameView extends JPanel implements GameStatisticsUpdateListener {
  private static final long serialVersionUID = 1L;

  private GameDataModel dataModel;

  private static int numberOfCells = GameConstants.ROWS * GameConstants.COLUMNS;
  private JButton buttons[] = new JButton[numberOfCells];

  private JLabel winningLabel;
  private JButton playAgainButton;
  private JMenu difficultyMenu;
  private JLabel gamesPlayedDisplay;
  private JLabel userWonDisplay;
  private JLabel computerWonDisplay;
  private JLabel tiedDisplay;

  private List<UserMovedListener> userMovedListeners = new ArrayList<UserMovedListener>();
  public void addUserMovedListener(UserMovedListener listener) {
      userMovedListeners.add(listener);
  }
  private void userMoved() {
    for (UserMovedListener listener: userMovedListeners) {
      listener.userMoved();
    }  
  }

  public JPanel initializeView(GameDataModel model) {
    dataModel = model;
    dataModel.addGameStatisticsUpdateListenerListener(this);
    
    UIManager.put("Button.disabledText", Color.black);
    setLayout(new GridLayout(2,1));
    add(createBoard());
    add(createControls());
    
    return this;
  }

  private JPanel createBoard() {

    JPanel board = new JPanel();
    board.setLayout(new GridLayout(GameConstants.ROWS, GameConstants.COLUMNS, 2, 2));

    for (int i = 0; i < numberOfCells; i++) {
      buttons[i] = new JButton();
      buttons[i].setFocusPainted(false);
      buttons[i].setActionCommand(Integer.toString(i));
      buttons[i].setFont(new Font("Dialog", 0, 48));
      buttons[i].setPreferredSize(new Dimension(100, 100));
      buttons[i].setToolTipText("Click to make your move");
      buttons[i].addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          buttonAction(evt.getActionCommand()); }
        });
      board.add( buttons[ i ] );
    }     
    
    return board;
  }

  private JPanel createControls() {
    
    JPanel controls = new JPanel();
    controls.setLayout(new BorderLayout());

    winningLabel = new JLabel();
    winningLabel.setFont(new Font("Dialog", 0, 36));
    winningLabel.setHorizontalAlignment(SwingConstants.CENTER);
    controls.add(winningLabel, BorderLayout.NORTH);
    controls.add(createMiddleControls(), BorderLayout.CENTER);    
    controls.add(createStatistics(), BorderLayout.SOUTH);
    
    return controls;
  }

  private JPanel createMiddleControls() {
    JPanel middleControls = new JPanel();
    
    middleControls.setLayout(new GridLayout(2, 1, 10, 5));

    playAgainButton = new JButton("Play Again");
    playAgainButton.setVisible(false);
    playAgainButton.setEnabled(false);
    playAgainButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        playAgainButtonPressed(e);
      }      
    });
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(3, 3, 10, 5));
    buttonPanel.add(new JLabel());
    buttonPanel.add(new JLabel());
    buttonPanel.add(new JLabel());
    buttonPanel.add(new JLabel());
    buttonPanel.add(playAgainButton);
    buttonPanel.add(new JLabel());
    buttonPanel.add(new JLabel());
    buttonPanel.add(new JLabel());
    buttonPanel.add(new JLabel());
    middleControls.add(buttonPanel);

    JPanel menuPanel = new JPanel();
    menuPanel.setLayout(new GridLayout(3, 2, 10, 5));
    menuPanel.add(new JLabel());
    menuPanel.add(new JLabel());
    JLabel difficultyLabel = new JLabel("Difficulty Level:");
    difficultyLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    menuPanel.add(difficultyLabel);
    menuPanel.add(createDifficultyMenu());
    menuPanel.add(new JLabel());
    menuPanel.add(new JLabel());
    middleControls.add(menuPanel);
    
    return middleControls;
  }
  
  private JMenuBar createDifficultyMenu() {
    
    JMenuItem easyItem = new JMenuItem("Easy");
    easyItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        easyDifficultySelected();
      }      
    });

    JMenuItem mediumItem = new JMenuItem("Medium");
    mediumItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        mediumDifficultySelected();
      }      
    });

    JMenuItem hardItem = new JMenuItem("Hard");
    hardItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        hardDifficultySelected();
      }      
    });

    difficultyMenu = new JMenu();
    difficultyMenu.add(easyItem);
    difficultyMenu.add(mediumItem);
    difficultyMenu.add(hardItem);
    switch (dataModel.difficultyLevel) {
    case Easy:
      difficultyMenu.setText("Easy");
      break;
    case Medium:
      difficultyMenu.setText("Medium");
      break;
    case Hard:
      difficultyMenu.setText("Hard");
      break;
    }
    JMenuBar menuBar = new JMenuBar();  
    menuBar.add(difficultyMenu);
    
    return menuBar;
  }

  private JPanel createStatistics() {

    JPanel statistics = new JPanel();
    statistics.setLayout(new BorderLayout());
    JPanel buffer = new JPanel();
    statistics.add(buffer, BorderLayout.SOUTH);

    JPanel content = new JPanel();
    content.setLayout(new GridLayout(4, 2, 10, 5));

    JLabel gamesPlayedLabel = new JLabel("Games Played:");
    gamesPlayedLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    content.add(gamesPlayedLabel);
    gamesPlayedDisplay = new JLabel("0");
    content.add(gamesPlayedDisplay);

    JLabel userWonLabel = new JLabel("User Won:");
    userWonLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    content.add(userWonLabel);
    userWonDisplay = new JLabel("0");
    content.add(userWonDisplay);

    JLabel computerWonLabel = new JLabel("Computer Won:");
    computerWonLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    content.add(computerWonLabel);
    computerWonDisplay = new JLabel("0");
    content.add(computerWonDisplay);

    JLabel tiedLabel = new JLabel("Tied:");
    tiedLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    content.add(tiedLabel);
    tiedDisplay = new JLabel("0");
    content.add(tiedDisplay);

    statistics.add(content, BorderLayout.CENTER);
    
    return statistics;
  }

  private void buttonAction(String btn) {
    int index = Integer.parseInt(btn);

    if (buttons[index].getText() == GameConstants.NOONEMARK) {
      buttons[index].setText(GameConstants.USERMARK);
      buttons[index].setToolTipText("");
      buttons[index].setEnabled(false);
      dataModel.boardState[index] = GameConstants.USERMARK;
      userMoved();      
    }
    
  }

  private void playAgainButtonPressed(java.awt.event.ActionEvent evt) {
    playAgainButton.setEnabled(false);
    playAgainButton.setVisible(false);
    winningLabel.setText("");
    startNewGame();
  }

  private void easyDifficultySelected() {
    difficultyMenu.setText("Easy");
    dataModel.difficultyLevel = GameConstants.DifficultyLevel.Easy;
  }

  private void mediumDifficultySelected() {
    difficultyMenu.setText("Medium");
    dataModel.difficultyLevel = GameConstants.DifficultyLevel.Medium;
  }

  private void hardDifficultySelected() {
    difficultyMenu.setText("Hard");
    dataModel.difficultyLevel = GameConstants.DifficultyLevel.Hard;
  }

  public void startNewGame() {
    for (int i = 0; i < numberOfCells; i++ ) {
      buttons[i].setText(GameConstants.NOONEMARK);
      buttons[i].setEnabled(true);
      dataModel.boardState[i] = GameConstants.NOONEMARK;  
    }
  }

  public void askForNewGame() {
    playAgainButton.setEnabled(true);
    playAgainButton.setVisible(true);      
  }

  public void gameStatisticsUpdated() {
    gamesPlayedDisplay.setText(String.valueOf(dataModel.getGamesPlayed()));
    userWonDisplay.setText(String.valueOf(dataModel.getUserWins()));
    computerWonDisplay.setText(String.valueOf(dataModel.getComputerWins()));
    tiedDisplay.setText(String.valueOf(dataModel.getTies()));
  }

  public void makeComputerMove(int move) {
    buttons[move].setText(GameConstants.COMPUTERMARK);
    buttons[move].setToolTipText("");
    buttons[move].setEnabled(false);
    dataModel.boardState[move] = GameConstants.COMPUTERMARK;    
  }
  
  public void updateWinningDisplay(String display) {
    winningLabel.setText(display);
  }
  
}

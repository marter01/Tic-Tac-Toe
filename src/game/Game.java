package game;

import java.awt.Frame;
import java.awt.MenuBar;
import java.awt.Menu;
import java.awt.MenuItem;

public class Game extends Frame {
	private static final long serialVersionUID = 1L;
		
  private GameDataModel dataModel = new GameDataModel();
  private GameView gameView = new GameView();

  public static void main( String args[] ) {
    new Game().setVisible(true);
  }

  public Game() {
    super("Tic Tac Toe");
    initializeGameBoard();
  }

  private void initializeGameBoard() {

    setResizable(false);
    setMenuBar(createMainMenuBar());
    add(gameView.initializeView(dataModel));
    
    pack();
    
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent evt) {
        exitForm(evt);  
      }
    });
        
  }
  
  private MenuBar createMainMenuBar() {
    
    MenuItem newGameMenuItem = new MenuItem("New Game");
    newGameMenuItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        newGameActionPerformed(evt);
      }
    });

    MenuItem exitMenuItem = new MenuItem("Exit");
    exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        exitActionPerformed(evt);
      }
    });

    Menu menu = new Menu("File");
    menu.add(newGameMenuItem);
    menu.add(exitMenuItem);

    MenuBar menuBar = new MenuBar();
    menuBar.add(menu);
            
    return menuBar;
  }
  
  private void exitForm(java.awt.event.WindowEvent evt) {
    System.exit(0);  
  }
  
  private void exitActionPerformed(java.awt.event.ActionEvent evt) {
    System.exit(0);
  }

  private void newGameActionPerformed(java.awt.event.ActionEvent evt) {
    gameView.startNewGame();  
  }
    
}

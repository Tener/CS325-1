package frs.hotgammon.tests.visual;

import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.*;
import java.util.HashMap;

import javax.swing.*;

import frs.hotgammon.common.GameImpl;
import frs.hotgammon.framework.Game;
import frs.hotgammon.tests.stub.StubGame1;
import frs.hotgammon.variants.factory.AlphaFactory;
import frs.hotgammon.variants.factory.DeltaFactory;
import frs.hotgammon.view.HotgammonDrawing;
import frs.hotgammon.view.HotgammonTool;
import frs.hotgammon.view.figures.CheckerFigure;
import frs.hotgammon.view.figures.DieFigure;
import frs.hotgammon.view.tools.CheckerTool;
import frs.hotgammon.view.tools.DieRollTool;

/** Show the dice and some checkers on the
 * backgammon board.  
 * 
   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Computer Science Department
     Aarhus University
   
   This source code is provided WITHOUT ANY WARRANTY either 
   expressed or implied. You may study, use, modify, and 
   distribute it for non-commercial purposes. For any 
   commercial use, see http://www.baerbak.com/
 */
public class ShowCheckersAndDice {
  
  public static void main(String[] args) {
    DrawingEditor editor = 
      new MiniDrawApplication( "Show HotGammon figures...",  
                               new HotGammonFactory() );
 
    editor.open();
    
    //Game game = new StubGame1();
    Game game = new GameImpl(new AlphaFactory());
    
    HotgammonDrawing model = (HotgammonDrawing) editor.drawing();
    
    model.setGame(game);
    
    game.addObserver((HotgammonDrawing) editor.drawing());

    DieFigure redDie = new DieFigure(4, new Point(216, 202));
    DieFigure blackDie = new DieFigure(2, new Point(306, 202));
    model.add(redDie);
    model.add(blackDie);
    
//    CheckerFigure bc = new CheckerFigure(frs.hotgammon.framework.Color.BLACK, new Point(21,21));
//    editor.drawing().add(bc);
//    CheckerFigure rc = new CheckerFigure(frs.hotgammon.framework.Color.RED, new Point(507,390));
//    editor.drawing().add(rc);
    
    
  //HotGammonTool Setup
    final Tool dieRollTool = new DieRollTool(editor,game);
    final Tool moveTool = new CheckerTool(editor,game);
    HashMap<String, Tool> states = new HashMap<String, Tool>(){{
		put( HotgammonTool.DIETOOL, dieRollTool );
		put( HotgammonTool.MOVETOOL, moveTool );
		}};
	//
    
	//Add tool to Editor
    editor.setTool( 
    		new HotgammonTool(editor,game, HotgammonTool.MOVETOOL, states) );
    
    game.newGame();
    game.nextTurn();

  }
}

class HotGammonFactory implements Factory {
  public DrawingView createDrawingView( DrawingEditor editor ) {
    DrawingView view = 
      new StdViewWithBackground(editor, "board");
    return view;
  }

  public Drawing createDrawing( DrawingEditor editor ) {
    return new HotgammonDrawing( editor);
  }

  public JTextField createStatusField( DrawingEditor editor ) {
    JTextField statusField = new JTextField( "Hello HotGammon..." );
    statusField.setEditable(false);
    return statusField;
  }
}



package frs.hotgammon.view;

import java.awt.Point;
import java.awt.Rectangle;

import frs.hotgammon.framework.Color;
import frs.hotgammon.framework.Game;
import frs.hotgammon.framework.GameObserver;
import frs.hotgammon.framework.Location;
import frs.hotgammon.view.figures.CheckerFigure;
import frs.hotgammon.view.figures.DieFigure;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.StandardDrawing;

public class HotgammonDrawing extends StandardDrawing implements GameObserver{
	
	private DieFigure diceFigures[] = new DieFigure[2];
	int diceIndex = 0;
	Point [] dicePoints = {new Point(216, 202), new Point(306, 202)};
	private Game game;
	private DrawingEditor editor;

	public HotgammonDrawing(DrawingEditor editor){
		this.editor = editor;
	}
	
	public void addChecker(Color color, Point point){
		
		CheckerFigure checker = new CheckerFigure(color, point);
		add(checker);
		checker.changed();
		
	}
	
	public void addDie(int dieValue){
		
		int currentDiceModIndex = diceIndex %2;
		
		if (diceFigures[currentDiceModIndex] == null) {
		      diceFigures[currentDiceModIndex] = new DieFigure(dieValue, dicePoints[currentDiceModIndex]);
		      super.add(diceFigures[currentDiceModIndex]);
		    }
		    else{
		    	diceFigures[currentDiceModIndex].set("die" + dieValue, dicePoints[currentDiceModIndex]);
		    	diceFigures[currentDiceModIndex].changed();
		    }
		
		diceIndex++;
	}
	
	private boolean isChecker(Figure f){
		return f != null && (f instanceof CheckerFigure);
		
	}

	@Override
	public void checkerMove(Location from, Location to) {
		
		if((from == Location.R_BEAR_OFF || from == Location.B_BEAR_OFF) || (to == Location.R_BAR || to ==Location.B_BAR) ){

		
		Point fromPoint = Convert.locationAndCount2xy(from, game.getCount(from) );
		Point toPoint = Convert.locationAndCount2xy(to, game.getCount(to) -1);

		lock();
		
		//find figure at point

	    Figure f = findFigure(fromPoint.x, fromPoint.y);

	    unlock();

	  //check if checker
	    if(!isChecker(f)){
	    	Color color = game.getColor(to);
	    	lock();
	    	f = new CheckerFigure(color, fromPoint);
	    	unlock();
	    	
	    	lock();
			add(f);
			unlock();
				
	    }
	    lock();
		f.moveBy(toPoint.x - fromPoint.x, toPoint.y - fromPoint.y);
		unlock();
		}

		if( this.game.getNumberOfMovesLeft() == 0){
			((HotgammonTool) this.editor.tool()).setState(HotgammonTool.DIETOOL);
		}
	
		
	}

	@Override
	public void diceRolled(int[] values) {
		
		for(int i = 0; i < values.length; i++){
			addDie(values[i]);
		}
		((HotgammonTool) this.editor.tool()).setState(HotgammonTool.MOVETOOL);
		
	}

	public void setGame(Game game) {
		this.game = game;
		
	}
	
	@Override
	public void setStatus(String status) {
		this.editor.showStatus(status);
	}

}



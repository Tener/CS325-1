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
	
	private DieFigure dieFigureCache[] = new DieFigure[2];
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
		
		if (dieFigureCache[currentDiceModIndex] == null) {
		      dieFigureCache[currentDiceModIndex] = new DieFigure(dieValue, dicePoints[currentDiceModIndex]);
		      super.add(dieFigureCache[currentDiceModIndex]);
		    }
		    else{
		    	dieFigureCache[currentDiceModIndex].set("die" + dieValue, dicePoints[currentDiceModIndex]);
		    }
		dieFigureCache[currentDiceModIndex].changed();
		
		diceIndex++;
	}
	
	private boolean isChecker(Figure f){
		return f != null && (f instanceof CheckerFigure);
		
	}

	@Override
	public void checkerMove(Location from, Location to) {

		
		Point fromPoint = Convert.locationAndCount2xy(from, game.getCount(from) + 1);
		Point toPoint = Convert.locationAndCount2xy(to, game.getCount(to)-1);

		lock();
		
		//find figure at point

	    Figure f = findFigure(toPoint.x, fromPoint.y);

	    unlock();

	  //check if checker
	    if(!isChecker(f)){
	    	Color color = game.getColor(to);
	    	this.remove(f);
	    	f = new CheckerFigure(color, fromPoint);
				
	    }

		f.moveBy(toPoint.x - fromPoint.x, toPoint.y - fromPoint.y);

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



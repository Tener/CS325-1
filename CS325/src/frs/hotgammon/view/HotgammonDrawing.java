package frs.hotgammon.view;

import java.awt.Point;
import java.awt.Rectangle;

import frs.hotgammon.framework.Color;
import frs.hotgammon.framework.Game;
import frs.hotgammon.framework.GameObserver;
import frs.hotgammon.framework.Location;
import frs.hotgammon.view.figures.CheckerFigure;
import frs.hotgammon.view.figures.DieFigure;
import minidraw.framework.Figure;
import minidraw.standard.StandardDrawing;

public class HotgammonDrawing extends StandardDrawing implements GameObserver{
	
	private DieFigure dieFigureCache[] = new DieFigure[2];
	int diceIndex = 0;
	Point [] dicePoints = {new Point(216, 202), new Point(306, 202)};
	private Game game;
	
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
		Point fromPoint = Convert.locationAndCount2xy(from, game.getCount(from)+1);
		//find figure at point
		Figure f =findFigure(fromPoint.x, fromPoint.y);
		//check if checker
		if(f instanceof CheckerFigure){
			//if is then remove it and redraw with invalidate
			
			System.out.println("This is a figre to be removed");
			remove(f);
			invalidate();
		}
		
		//to to a point
		Point toPoint = Convert.locationAndCount2xy(to, game.getCount(to)-1);
		//color form game
		Color color = game.getColor(to);
		System.out.println(color);
		//add checker.
		addChecker(color, toPoint);
		
	}

	@Override
	public void diceRolled(int[] values) {
		
		for(int i = 0; i < values.length; i++){
			addDie(values[i]);
		}
		
	}

	public void setGame(Game game) {
		this.game = game;
		
	}

}



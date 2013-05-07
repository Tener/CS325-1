package frs.hotgammon.view.tools;

import java.awt.Point;
import java.awt.event.MouseEvent;

import frs.hotgammon.framework.Game;
import frs.hotgammon.framework.Location;
import frs.hotgammon.view.Convert;
import frs.hotgammon.view.figures.CheckerFigure;
import frs.hotgammon.view.figures.DieFigure;

import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.AbstractTool;
import minidraw.standard.SelectionTool;

public class CheckerTool extends SelectionTool{
	
	protected Figure figure;
	
	private int figureX;
	private int figureY;
	private Location originalLocation;
	private Game game;

	public CheckerTool(DrawingEditor editor, Game game) {
		super(editor);
		this.game=game;
	}
	
	private boolean isChecker(Figure f){
		return f != null && (f instanceof CheckerFigure);
		
	}

	@Override
	public void mouseDown(MouseEvent e, int x, int y) {
		
		Drawing model = editor().drawing();

	    model.lock();
	    
		figure = model.findFigure(e.getX(), e.getY());


		if(isChecker(figure)){
			if ( e.isShiftDown() ) {
				
				model.toggleSelection(figure);
				
			} else if ( ! model.selection().contains(figure) ) {
				
				model.clearSelection();
				model.addToSelection(figure);
			}
			
			originalLocation=Convert.xy2Location(x, y);
			figureX = x; figureY = y; 
		}

	}

	@Override
	public void mouseDrag(MouseEvent e, int x, int y) {
	    for ( Figure f : editor().drawing().selection() ) {
	    	f.moveBy( x - figureX, y - figureY );
		}
		figureX = x;
		figureY = y; 
	}
	@Override
	public void mouseUp(MouseEvent e, int x, int y) {
		
		Location location =Convert.xy2Location(x, y);
		
		if(game.move(originalLocation, location )){
			
			Point point=Convert.locationAndCount2xy(location , game.getCount(location ) -1);
			
			for (Figure f :editor().drawing().selection()){
				
				f.moveBy(point.x-f.displayBox().x,point.y-f.displayBox().y);
			}
		}else {
			Point oldPoint=Convert.locationAndCount2xy(originalLocation, game.getCount(originalLocation) -1);
			
			for (Figure f :editor().drawing().selection()){
				
				f.moveBy(oldPoint.x-f.displayBox().x,oldPoint.y-f.displayBox().y);
			}
		}
		editor().drawing().clearSelection();
		editor().drawing().unlock();
	}

}

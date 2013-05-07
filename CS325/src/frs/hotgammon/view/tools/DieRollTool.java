package frs.hotgammon.view.tools;

import java.awt.event.MouseEvent;

import frs.hotgammon.framework.Game;
import frs.hotgammon.framework.GameObserver;
import frs.hotgammon.view.HotgammonDrawing;
import frs.hotgammon.view.HotgammonTool;
import frs.hotgammon.view.figures.CheckerFigure;
import frs.hotgammon.view.figures.DieFigure;

import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.AbstractTool;

public class DieRollTool extends AbstractTool {

	Game game;
	public DieRollTool(DrawingEditor editor, Game game) {
		super(editor);
		this.game = game;
	}
	
	private boolean isDie(Figure f){
		return f != null && (f instanceof DieFigure);
		
	}
	
	public void mouseUp(MouseEvent e, int x, int y) {
		
		HotgammonDrawing model = (HotgammonDrawing) editor.drawing();
		
		model.lock();
		Figure f = model.findFigure(e.getX(), e.getY());
		model.unlock();
		
        if (isDie(f)) {
            game.nextTurn();
            
            ((HotgammonTool) editor.tool()).setState(HotgammonTool.MOVETOOL);
        }
        else{
        	for( GameObserver gO : this.game.getObservers() ){
				  gO.setStatus("Roll die for next turn");
			  }
        }
    }

}

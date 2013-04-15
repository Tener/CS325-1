package frs.hotgammon.view.tools;

import java.awt.event.MouseEvent;

import frs.hotgammon.framework.Game;
import frs.hotgammon.view.HotgammonDrawing;
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
	
	public void mouseUp(MouseEvent e, int x, int y) {
		
		HotgammonDrawing model = (HotgammonDrawing) editor.drawing();
		model.lock();
		Figure f = model.findFigure(e.getX(), e.getY());
		model.unlock();
		
        if (f instanceof DieFigure) {
            game.nextTurn();
        }
    }

}

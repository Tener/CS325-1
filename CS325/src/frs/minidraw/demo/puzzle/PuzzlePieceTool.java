package frs.minidraw.demo.puzzle;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.HashMap;
import javax.swing.JPanel;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.SelectionTool;

public class PuzzlePieceTool extends SelectionTool {
	
	public PuzzlePieceTool(DrawingEditor editor) {
		super(editor);
	}

	public void mouseUp(MouseEvent e, int x, int y) {
		
		// from DragTracker
		for (Figure f : editor().drawing().selection()) {


			Point2D closestSquare = closestSquareMap.get(SqureSlots.valueOf(getNameOfPanel(x, y)));

			int moveByX = (int) (closestSquare.getX() - f.displayBox().getX());
			int moveByY = (int) (closestSquare.getY() - f.displayBox().getY());

			f.moveBy(moveByX, moveByY);

		}
	}
	
	public String getNameOfPanel(int x, int y){
		
		return ((JPanel) editor().view()).getComponentAt((int) x, (int) y).getName();
		
	}


	
	public enum SqureSlots {PIECE1, PIECE2, PIECE3, PIECE4, PIECE5, PIECE6, PIECE7, PIECE8, PIECE9};

	private HashMap<SqureSlots, Point2D> closestSquareMap = new HashMap<SqureSlots, Point2D>() {
		{
			put(SqureSlots.PIECE1, new Point2D.Double(0, 0));
			put(SqureSlots.PIECE2, new Point2D.Double(225, 0));
			put(SqureSlots.PIECE3, new Point2D.Double(450, 0));
			put(SqureSlots.PIECE4, new Point2D.Double(0, 225));
			put(SqureSlots.PIECE5, new Point2D.Double(225, 225));
			put(SqureSlots.PIECE6, new Point2D.Double(450, 225));
			put(SqureSlots.PIECE7, new Point2D.Double(0, 450));
			put(SqureSlots.PIECE8, new Point2D.Double(225, 450));
			put(SqureSlots.PIECE9, new Point2D.Double(450, 450));

		}
	};

}

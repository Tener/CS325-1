package frs.hotgammon.view;

import java.awt.event.MouseEvent;
import java.util.HashMap;

import frs.hotgammon.framework.Game;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Tool;
import minidraw.standard.AbstractTool;
import minidraw.standard.SelectionTool;

public class HotgammonTool extends SelectionTool{

	private Tool currentTool;
	private Game game;
	final public static String DIETOOL = "DIETOOL";
	final public static String MOVETOOL = "MOVETOOL";
	
	private HashMap<String, Tool> state;

	public HotgammonTool( DrawingEditor editor, Game game, String initialState, HashMap<String,Tool> state) {
		
		super(editor);
		this.game = game;		
		this.state = state;
		setState(initialState);
	}

	public void mouseUp(MouseEvent e, int x, int y) { 

	    this.currentTool.mouseUp(e,x,y);

	}

	public void mouseDrag(MouseEvent e, int x, int y) {

	    this.currentTool.mouseDrag(e,x,y);
	}

	public void mouseDown(MouseEvent e, int x, int y) {

	    this.currentTool.mouseDown(e,x,y);
	}

	public void setState(String toolKey){
		this.currentTool = state.get(toolKey);
	}

}

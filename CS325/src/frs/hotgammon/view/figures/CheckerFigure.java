package frs.hotgammon.view.figures;


import java.awt.Point;

import frs.hotgammon.framework.Color;

import minidraw.standard.ImageFigure;


public class CheckerFigure extends ImageFigure {

	public CheckerFigure(Color color, Point point){
		super(color.toString().toLowerCase() + "checker", point);
		
	}
}

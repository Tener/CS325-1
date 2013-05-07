package frs.hotgammon;

import frs.hotgammon.framework.Color;

public class BoardPoint {
	
	public int checkers = 0;
	
	public Color playerInTurn = Color.NONE;
	
	public boolean add(Color col){

		if(this.playerInTurn == Color.NONE || this.playerInTurn == col){
			this.playerInTurn = col;
			this.checkers++;
			return true;
		}			
		return false;
	}

	public boolean remove(Color col){

		if(this.playerInTurn == col && this.checkers > 0){
			this.checkers--;
			if (this.checkers == 0){
				this.playerInTurn = Color.NONE;
			}
			return true;
		}			
		return false;
	}

}

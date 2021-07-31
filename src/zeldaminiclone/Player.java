package zeldaminiclone;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

//EXTENDS RECTANGLE PARA COLISOES E MOVIMENTACAO DO PLAYER
public class Player extends Rectangle{
	
	//PARA CONTROLAR O PLAYER
	public boolean right,up,down,left;
	public int spd = 4;
	
	//OS PARAMETROS SAO A POSICAO DO PLAYER
	public Player(int x, int y) {
		//POSICAO E TAMANHO DO PLAYER
		super(x,y,32,32);
	}
	
	
	//LOGICA DO PLAYER
	public void tick() {
		if(right && World.isFree(x+spd,y)) {
			x+=spd;
		}else if(left && World.isFree(x-spd,y)) {
			x-=spd;
		}
		
		else if(up && World.isFree(x,y-spd)) {
			y-=spd;
		}else if(down && World.isFree(x,y+spd)) {
			y+=spd;
		}
		
	}
	
	
	//RENDERIZACAO DO PLAYER
	public void render(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(x, y, width, height);
	}
}

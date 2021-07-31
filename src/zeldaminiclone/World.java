package zeldaminiclone;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class World {

	public static List<Blocks>  blocos = new ArrayList<Blocks>();
	
	public World() {
		//PARTE DE CIMA DOS BLOCOS
		 for(int xx = 0; xx < 15; xx++) {
			 blocos.add(new Blocks(xx*32,0));
		 }
		 //PARTE DE BAIXO DOS BLOCOS
		 for(int xx = 0; xx < 15; xx++) {
			 blocos.add(new Blocks(xx*32,480-32));
		 }
		 
		 //VERTICAL
		 for(int yy = 0; yy < 15; yy++) {
			 blocos.add(new Blocks(0,yy*32));
		 }
		 for(int yy = 0; yy < 15; yy++) {
			 blocos.add(new Blocks(480-32,yy*32));
		 }

	}
	
	//VERIFICA SE ESTA LIVRE
	public static boolean isFree(int x, int y) {
		
		for(int i = 0;i < blocos.size(); i++) {
			Blocks blocoAtual = blocos.get(i);
			//                       SIMULAR O PLAYER E SE COLIDIR RETORNA FALSE
			if(blocoAtual.intersects(new Rectangle(x,y,32,32))) {
				return false;
			}	
		}
		return true;
	}
	
	public void render(Graphics g) {
		//ENQUANTO O I FOR MENOR QUE TAMANHO DA LISTA DE BLOCOS, CONTINUA
		for(int i = 0;i < blocos.size(); i++) {
			blocos.get(i).render(g);
		}
		
	}
}

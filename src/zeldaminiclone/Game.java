package zeldaminiclone;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

//KEY LISTENER PARA IMPLEMENTAR OS CONTROLES
public class Game extends Canvas implements Runnable,KeyListener{

	public static int WIDTH = 480, HEIGHT = 480;
	public Player player;
	public World world;
	
	public Game() {
		this.addKeyListener(this);
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		
		player = new Player(32,32);
		world = new World();
	}
	
	//RESPONSAVEL PELA LOGICA DO JOGO, MOVIMENTACAO DO PERSONAGEM ETC
	public void tick() {
		player.tick();
	}
	
	
	//RENDERIZACAO DO PERSONAGEM, MAPA E AFINS
	public void render() {
		//VERIFICA SE PODE RENDERIZAR
		BufferStrategy bs = this.getBufferStrategy();
		
		//OTIMIZAR A PARTE GRAFICA
		//CASO ESTEJA VAZIO, CRIA
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		//DEFININDO O FUNDO DE TELA PARA NAO PISCAR
		g.setColor(Color.black);
		g.fillRect(0,0,WIDTH,WIDTH);
		
		//RENDERIZANDO O MUNDO
		world.render(g);
		//RENDERIZANDO O PLAYER
		player.render(g);
		
		
		//MOSTRAR O QUE TEM
		bs.show();
	}
	
	
	
	public static void main(String[] args) {
		//INSTANCIAR O JOGO
		Game game = new Game();
		
		//INSTANCIAR A JANELA
		JFrame frame = new JFrame();
		
		//ADICIONANDO O JOGO NO FRAME
		frame.add(game);
		
		//TITULO DA JANELA
		frame.setTitle("Mini Zelda");
		
		//EMPACOTAR TUDO E DEFINIR O TAMANHO CERTO -_O.O_-
		frame.pack();
		
		//COLOCANDO A JANELA NO CENTRO, TEM QUE SER DPS DO PACK
		frame.setLocationRelativeTo(null);
		
		
		//AO FECHAR O GAME FECHA O PROCESSO DO JAVA TB
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//TORNA VISIVEL A JANELA CRIADA
		frame.setVisible(true);
		
		//CRIAR O GAME EM LOOPING
		new Thread(game).start();
	}
	
	@Override
	//METODO PRINCIPAL DO JOGO
	public void run() {
		
		while(true) {
			tick();
			render();
			//RODAR EM 60FPS
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	//NAO SE COLOCA A LOGICA DE SE MEXER DENTRO DESSE METODO POR QUE SE NAO, NAO FICA NOS FRAMES
	//DEFINIDOS.
	//LOGICA FICA NO KEYPRESSED E A AÇÃO DE FATO FICA NO PLAYER
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = false;
		}
		
	}

	
}

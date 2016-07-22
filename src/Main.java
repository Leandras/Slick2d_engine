import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import display.windows.MainWindow;
import display.windows.Play;


public class Main extends StateBasedGame{
	
	public static final String gamename = "The Ones Who Came Before";
	public static final int MAINMENU = 0;
	public static final int PLAY = 1;
	public static final int xSize = 800;
	public static final int ySize = 600;
	
	public Main(String gamename){
		super(gamename);
	}
	
	public static void main(String[] args) throws SlickException{
		AppGameContainer appgc;
	      try{
	         appgc = new AppGameContainer(new Main(gamename));
	         appgc.setDisplayMode(xSize, ySize, false);
	         appgc.setTargetFrameRate(60);
	         appgc.start();
	      }catch(SlickException e){
	         e.printStackTrace();
	      }
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		this.addState(new Play(1, xSize, ySize));
		
	}
	
	public int getXSize(){
		return xSize;
	}
	
	public int getYSize(){
		return ySize;
	}
}
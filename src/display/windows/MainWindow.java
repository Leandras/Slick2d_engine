package display.windows;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


public class MainWindow extends BasicGameState {
	
	Image newgame_button_unselected;
	Image newgame_button_selected;
	Image continue_button;
	Image continue_button_selected;
	Image exit_button;
	Image exit_button_selected;
	int xSize;
	int ySize;
	int imgYSize = 64;
	int id;
	
	public MainWindow(int id, int xSize, int ySize){
		this.id = id;
		this.xSize = xSize;
		this.ySize = ySize;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		newgame_button_unselected = new Image("res/texts/menu/new_game.png");
		newgame_button_selected = new Image("res/texts/menu/new_game_selected.png");
		continue_button = new Image("res/texts/menu/continue.png");
		exit_button = new Image("res/texts/menu/exit_game.png");
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		newgame_button_unselected.draw((xSize/2) - newgame_button_unselected.getWidth()/2, 100 - newgame_button_unselected.getHeight()/2 + imgYSize * 1);
		continue_button.draw((xSize/2) - continue_button.getWidth()/2, 100 - continue_button.getHeight()/2 + imgYSize * 2);
		exit_button.draw((xSize/2) - exit_button.getWidth()/2, 100 - exit_button.getHeight()/2 + imgYSize * 3);
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
		Input input = container.getInput();
		if(input.isKeyDown(Input.KEY_DOWN)){
			
		}
		if(input.isKeyDown(Input.KEY_UP)){
			
		}
	}

	@Override
	public int getID() {
		return id;
	}

	
	
}

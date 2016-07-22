package entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import logic.Direction.PlayerDirections;


public class PlayerActor extends AbstractActor {

	/*public enum PlayerDirection{
		UP,
		RIGHT,
		DOWN,
		LEFT;
		
		private PlayerDirection player_dir;
		
		public void setPlayerDirection(PlayerDirection dir){
			this.player_dir = dir;
		}
		
		public PlayerDirection getPlayerDirection(){
			return player_dir;
		}
	}*/
	
	public enum Team{
		RED,
		BLUE;
		
		private int score = 0;
		public void incraseScore(){
			score++;
		}
		
		public int getScore(){
			return score;
		}
	}
	
	private Team team;
	private Image image;
	private int index;
	private PlayerDirections direction;
	
	public PlayerActor(String id, int spawn_position_x, int spawn_position_y,Image image, String imagepath, boolean collisonOn,
			ActorsEnum type, Team team) {
		super(id, spawn_position_x, spawn_position_y, imagepath, collisonOn, type);
		this.team = team;
		this.image = image;
		index = 0;
	}
	
	@Override
	public Image getImage(){
		return image;
	}
	
	public void setImage(int index){
		this.index = index;
	}
	
	public int getCurrentImageIndex(){
		return index;
	}
	
	public void rotate(float angle, Image[] images){
		if(angle != this.getImage().getRotation()){
			System.out.println("jappi");
			for(int i = 0; i < images.length; ++i){
				images[i].setRotation(0);
				images[i].setRotation(angle);
			}
			/*this.getImage().setRotation(0);
			this.getImage().setRotation(angle);*/
		}
	}
}

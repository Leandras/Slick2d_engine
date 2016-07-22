package entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

import entities.PlayerActor.Team;

import org.newdawn.slick.geom.Polygon;

public class BallActor extends AbstractActor {
	
	private Shape hitbox;
	private float[] points;
	
	private enum CollisonSide{
		LEFT,
		UP,
		RIGHT,
		DOWN
	}
	
	private CollisonSide collison_side;
	private boolean out_of_bounds = false;

	public BallActor(String id, float spawn_position_x, float spawn_position_y, String imagepath, boolean collisonOn,
			ActorsEnum type) {
		super(id, spawn_position_x, spawn_position_y, imagepath, collisonOn, type);
		float x = spawn_position_x;
		float y = spawn_position_y;
		points = new float[]{x, y-16, x-8, y-8, x-16, y, x-8, y+8, x, y+16, x+8, y+8, x+16, y, x+8, y-8};
	}
	
	/*@Override
	public void createHitbox(){
		hitbox = new Polygon(points);
	}
	
	@Override
	public Shape getHitbox(){
		return hitbox;
	}
	*/
	
	public void outOfBounds(AbstractActor other_actor){

		if(this.getCurrentPositionX() <= other_actor.getCurrentPositionX() + 10){
			collison_side = CollisonSide.LEFT;
			out_of_bounds = true;
		}else if(this.getCurrentPositionX() + this.getWidth() >= other_actor.getCurrentPositionX() + other_actor.getWidth() - 30){
			collison_side = CollisonSide.RIGHT;
			out_of_bounds = true;
		}else if(this.getCurrentPositionY() <= other_actor.getCurrentPositionY() + 10){
			collison_side = CollisonSide.UP;
			out_of_bounds = true;
		}else if(this.getCurrentPositionY() + this.getHight() >= other_actor.getCurrentPositionY() + other_actor.getHight() - 10){
			collison_side = CollisonSide.DOWN;
			out_of_bounds = true;
		}else{
			out_of_bounds = false;
		}
	}
	
	public float[] redirectDirection(AbstractActor other_actor){
		float[] mirrored = new float[]{1,1};
		switch(collison_side){
			case LEFT:
				mirrored[0] *= -1;
				break;
			case RIGHT:
				mirrored[0] *= -1;
				break;
			case UP:
				mirrored[1] *= -1;
				break;
			case DOWN:
				mirrored[1] *= -1;
				break;
			default:
				break;
		}
		
		
		return mirrored;
	}
	
	public CollisonSide getMirroredDirection(){
		return collison_side;
	}
	
	public boolean isOutOfBounds(){
		return out_of_bounds;
	}
	
	public void setOutOfBounds(boolean out){
		this.out_of_bounds = out;
	}
	
	public void changeDirection(float x, float y){
		switch(collison_side){
		case LEFT:
			x *= -1;
			break;
		case RIGHT:
			x *= -1;
			break;
		case UP:
			y *= -1;
			break;
		case DOWN:
			y *= -1;
			break;
		default:
			break;
	}
	}
	
	public boolean goal(){
		if(this.getCurrentPositionX() <= 60 ){
			if(this.getCurrentPositionY() > 200 || this.getCurrentPositionY() < 400 ){
				Team.RED.incraseScore();
				return true;
			}else{
				return false;
			}
		}else if(this.getCurrentPositionX() >= 700){
			if(this.getCurrentPositionY() > 200 || this.getCurrentPositionY() < 400){
				Team.BLUE.incraseScore();
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
}

package logic;

import entities.AbstractActor;

public class BallMovement {
	private float timer;
	private float collisonX;
	private float collisonY;
	private AbstractActor actor;
	private float[] stepps;
	private float stepTowardsX;
	private float stepTowardsY;
	
	public BallMovement(AbstractActor actor, float timer, float collisonX, float collisonY){
		this.actor = actor;
		this.timer = timer;
		this.collisonX = collisonX;
		this.collisonY = collisonY;
		stepps = new float[]{0,0};
		stepTowardsX = collisonX / 10;
		stepTowardsY = collisonY / 10;
	}
	
	public boolean checkGoal(){
		
		return false;
	}
	
	public float[] calculateDirections(){
		if(actor.getCurrentPositionX() <= 95 || actor.getCurrentPositionX() >= 665){
			if(actor.getCurrentPositionY() < 200 || actor.getCurrentPositionY() > 400){
				stepps[0] = stepTowardsX * -1;
				stepps[1] = stepTowardsY;
			}else{
				stepps[0] = stepTowardsX;
				stepps[1] = stepTowardsY;
			}
		}else if(actor.getCurrentPositionY() <= 50 || actor.getCurrentPositionY() >= 522){
			stepps[0] = stepTowardsX;
			stepps[1] = stepTowardsY * -1;
		}else{
			stepps[0] = stepTowardsX;
			stepps[1] = stepTowardsY;
		}
		//System.out.println("SteppX: " + stepps[0] + "    SteppY: " + stepps[1]);
		return stepps;
	}
	
}

package logic;

import org.newdawn.slick.SlickException;

import Exception.ImageNotFoundException;
import entities.AbstractActor;
import entities.ActorsList;

public class CollisionDetector {
	
	private ActorsList<AbstractActor> actors_list;
	private float[] collison_points;
	private int collided_actorsA = 0;
	private int collided_actorsB = 1;
	private double distance = 0;
	
	public CollisionDetector(ActorsList<AbstractActor> actors_list){
		this.actors_list = actors_list;
		collison_points = new float[]{0,0};
	}

	public boolean checkCollison() {
		
		for(int i = 0; i < actors_list.size(); ++i){
			for(int j = actors_list.size() - 1; j >= 0; --j){			
				if(!actors_list.getActor(i).equal(actors_list.getActor(j))){
					if(Math.abs(actors_list.getActor(i).getMiddlePoints()[0] - actors_list.getActor(j).getMiddlePoints()[0]) < 80){
						float tempX = (actors_list.getActor(i).getMiddlePoints()[0] - actors_list.getActor(j).getMiddlePoints()[0]);
						float tempY = (actors_list.getActor(i).getMiddlePoints()[1] - actors_list.getActor(j).getMiddlePoints()[1]);
						tempX *= tempX;
						tempY *= tempY;
						distance = Math.sqrt(tempX + tempY);
						//System.out.println(distance);
						//System.out.println("Radius 1: " + actors_list.getActor(i).getRadius() + "   Radius2: " + actors_list.getActor(j).getRadius() );
						if(/*(actors_list.getActor(i).getRadius() / 2)  + actors_list.getActor(j).getRadius()*/ 34 >= distance && collided_actorsA != collided_actorsB){
							collided_actorsA = i;
							collided_actorsB = j;
							//System.out.println(getCollisonPoints()[0] + "        " + getCollisonPoints()[1]);
							//System.out.println("collison");
							return true;
						}else{
							return false;
						}
					}
					return false;
				}
				return false;
			}
		}
		return false;
	}
	
	public String getDistance(){
		return String.valueOf(distance);
	}
	
	public float[] getCollisonPoints(){
		collison_points[0] = actors_list.getActor(collided_actorsA).getMiddlePoints()[0] - actors_list.getActor(collided_actorsB).getMiddlePoints()[0];
		collison_points[1] = actors_list.getActor(collided_actorsA).getMiddlePoints()[1] - actors_list.getActor(collided_actorsB).getMiddlePoints()[1];
		return collison_points;
	}

	public boolean outOfBoundsCheck(AbstractActor actor){
		if(actor.getCurrentPositionX() <= 95 || actor.getCurrentPositionX() >= 665 || actor.getCurrentPositionY() <= 50 || actor.getCurrentPositionY() >= 522){
			return true;
		}
		return false;
	}
}

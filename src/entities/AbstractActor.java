package entities;



import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import Exception.ImageNotFoundException;

public class AbstractActor {
	
	private String id;
	private int width;
	private int height;
	private String imagepath;
	private boolean collisonOn;
	private ActorsEnum type;
	
	private Image image;
	private float position_x;
	private float position_y;
	
	private Shape hitbox;
	
	
	public AbstractActor(String id, float spawn_position_x, float spawn_position_y, String imagepath, boolean collisonOn, ActorsEnum type){
		this.id = id;
		this.position_x = spawn_position_x;
		this.position_y = spawn_position_y;
		this.imagepath = imagepath;
		this.collisonOn = collisonOn;
		this.type = type;
		
	}
	
	public String getId(){
		return id;
	}
	
	public float getCurrentPositionX(){
		return (float)position_x;
	}
	
	public void setCurrentPositionX(float pos){
		this.position_x = pos;
	}
	
	public float getCurrentPositionY(){
		return (float)position_y;
	}
	
	public void setCurrentPositionY(float pos){
		this.position_y = pos;
	}
	
	public int getWidth(){
		return this.image.getWidth();
	}
	
	public int getHight(){
		return this.image.getHeight();
	}
	
	public void setImage(Image image){
		this.image = image;
	}
	
	public Image getImage() throws ImageNotFoundException, SlickException{
		image = new Image(this.imagepath);
		return this.image;
	}
	
	public String getImagePath(){
		return imagepath;
	}
	
	public boolean isCollisonOn(){
		return collisonOn;
	}
	
	public void setPositionX(float position_x){
		this.position_x = position_x;
	}
	
	public double getPositionX(){
		return position_x;
	}
	
	public void setPositionY(float position_y){
		this.position_y = position_y;
	}
	
	public double getPositionY(){
		return position_y;
	}
	
	public void createHitbox(){
		hitbox = new Rectangle(position_x, position_y, getWidth(), getHight());
	}
	
	public Shape getHitbox(){
		return hitbox;
	}
	
	public void updateHitbox(float x, float y){
		hitbox.setLocation(x, y);
	}
	
	public float getMiddlePoint() throws ImageNotFoundException, SlickException{
		return (this.getWidth() + (this.image.getWidth()/2)) + (this.getHight() + (this.getImage().getHeight() / 2));
	}
	
	public float[] getMiddlePoints(){
		float[] points = new float[]{0,0};
		points[0] = this.getCurrentPositionX() + this.getWidth();
		points[1] = this.getCurrentPositionY() + this.getHight();
		return points;
	}
	
	public double getRadius(){
		if(this.getType() == ActorsEnum.BALL){
			return this.getWidth() / 2;
		}else if(this.getType() == ActorsEnum.PLAYER){
			return Math.sqrt((this.getWidth() * this.getWidth())+(this.getHight() * this.getHight()));
		}else{
			return 0;
		}
	}
	
	public ActorsEnum getType(){
		return type;
	}
	
	private double getDistance(AbstractActor other_actor) throws SlickException, ImageNotFoundException{
		double distance = 0;
		
		double distance_x = (this.getMiddlePoints()[0]) - (other_actor.getMiddlePoints()[0]);
		double distance_y = (this.getMiddlePoints()[1]) - (other_actor.getMiddlePoints()[1]);
		if(distance_x < 0){
			distance_x *= -1;
		}
		if(distance_y < 0){
			distance_y *= -1;
		}
		double temp = (distance_x * distance_x) + (distance_y * distance_y);
		distance = Math.sqrt(temp);
		distance -= this.getWidth();
		/*double player_alto = Math.sqrt((this.getWidth() * 2) + (this.getWidth() * 2));
		distance = (Math.sqrt((distance_x) * 2) * 2) + Math.sqrt((distance_y * 2) * 2) - other_actor.getWidth() /2 ;*/
		//System.out.println(distance);
		return distance;
	}
	
	public boolean collided(AbstractActor other_actor)throws SlickException, ImageNotFoundException{
		double distance = this.getDistance(other_actor);
		//System.out.println(distance);
		return this.getDistance(other_actor) < 1;
	}
	
	public float[] getCollisonPoints(AbstractActor other_actor) throws SlickException, ImageNotFoundException{
		/* A két actor x és y kivonjuk egymásból, így megkapjuk az x és y tengelyen
		 * az ütközés pontos pontját */
		float points[] = new float[]{0,0};
		points[0] = other_actor.getCurrentPositionX() - this.getCurrentPositionX();
		points[1] = other_actor.getCurrentPositionY() - this.getCurrentPositionY();
		//System.out.println("X1: " + other_actor.getCurrentPositionX() + "    Y1: " + other_actor.getCurrentPositionY() + "     X2: " + this.getCurrentPositionX() + "     Y2: " + this.getCurrentPositionY());
		//System.out.println("PointX: " + points[0] + "    PointY: " + points[1]);
		return points;
	}
	
	public boolean equal(AbstractActor other){
		if(this.getId() == other.getId()){
			return true;
		}else{
			return false;
		}
	}
	
}

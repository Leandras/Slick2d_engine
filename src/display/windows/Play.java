package display.windows;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.state.*;
import org.newdawn.slick.Animation;

import Exception.ImageNotFoundException;
import entities.AbstractActor;
import entities.ActorsEnum;
import entities.ActorsList;
import entities.BallActor;
import entities.FieldActor;
import entities.PlayerActor;
import entities.PlayerActor.Team;
import logic.Direction.PlayerDirections;
import logic.BallMovement;
import logic.CollisionDetector;
import logic.Direction;

public class Play extends BasicGameState {

	int id;
	private int xSize;
	private int ySize;
	private Image football_field;
	private Image player_one;
	private Image ball;
	private int teamAScore = 0;
	private int teamBScore = 0;
	private int playeraxpos = 100;
	private int playeraypos = 280;
	
	private float playerspeed = 0.2f;
	private float ballspeed;
	private float ballTimer = 0;
	
	
	private float middleX;
	private float middleY;
	
	private float ball_middle_x_pos;
	private float ball_middle_y_pos;
	
	private float ballxpos;
	private float ballypos;
	private float lepesX;
	private float lepesY;
	
	private float distance;
	private boolean collided = false;
	
	private float direction;
	private float[] collisonpoints = new float[]{0,0};
	
	private Direction dir;
	private Shape probaPath;
	
	//Actors
	private FieldActor field;
	private BallActor ball_a;
	
	private PlayerActor player_a;
	//private PlayerDirection player_dir;
	
	private Image[] player_movement;
	private SpriteSheet forward_movement;
	private Animation move_forward;
	private int currentImage;
	private int animationTimer;
	private float rotateToZero = 0;
	private boolean already_rotated = false;
	private float rotationAngle = 0;
	
	private ActorsList<AbstractActor> actors_list;
	
	private BallMovement movement;
	
	private boolean out_of_bounds = false;
	private Thread collison_thread;
	private CollisionDetector collison_detection;
	
	//private double[] collisonpoints = new double[]{0,0};
	
	public Play(int id, int xSize, int ySize){
		this.id = id;
		this.xSize = xSize;
		this.ySize = ySize; 
		ballxpos = (xSize/2) -16;
		ballypos = (ySize/2) -16;
		middleX = xSize/2;
		middleY = ySize/2;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		field = new FieldActor("fieldA", 0, 0, "res/textures/backgrounds/football/field_bg.png", true, ActorsEnum.FIELD);
		field.setImage(new Image(field.getImagePath()));
		
		ball_a = new BallActor("ballA",ballxpos, ballypos, "res/objects/ball.png", true, ActorsEnum.BALL);
		ball_a.setImage(new Image(ball_a.getImagePath()));
		
		currentImage = 0;
		player_movement = new Image[]{new Image("res/players/move_forward/forward_still.png"), new Image("res/players/move_forward/forward_left.png"), new Image("res/players/move_forward/forward_right.png")};
		player_a = new PlayerActor("playerA", 100, 280, player_movement[0], "res/players/move_forward/forward_still.png", true, ActorsEnum.PLAYER, Team.RED);
		player_a.setImage(player_a.getImage());
		
		
		actors_list = new ActorsList<AbstractActor>();
		//actors_list.add(field);
		actors_list.add(ball_a);
		actors_list.add(player_a);
		
		field.setCurrentPositionX(0);
		field.setCurrentPositionY(0);
		ball_a.setCurrentPositionX(ballxpos);
		ball_a.setCurrentPositionY(ballypos);
		player_a.setCurrentPositionX(100);
		player_a.setCurrentPositionY(280);
		
		ball_a.createHitbox();
		player_a.createHitbox();
		
		ballTimer = 0.0f;
		
		collison_detection = new CollisionDetector(actors_list);
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		//collison_thread.start();
		//collison_thread.run();
		collison_detection.checkCollison();
		//System.out.println("Thread alive:" + collison_thread.isAlive());
		
		//Actors
		try{
			field.getImage().draw(field.getCurrentPositionX(), field.getCurrentPositionY());
			ball_a.getImage().draw(ballxpos, ballypos);
			//player_a.getImage().draw(player_a.getCurrentPositionX(), player_a.getCurrentPositionY());	
			player_movement[player_a.getCurrentImageIndex()].draw(player_a.getCurrentPositionX(), player_a.getCurrentPositionY());
		}catch(ImageNotFoundException e){
			System.out.println("Kép nem található");
			e.printStackTrace();
		}
		//football_field.draw(0, 0);
		//player_one.draw(playeraxpos, playeraypos);
		//ball.draw(ballxpos, ballypos);
		//Score Board
		g.drawString(Integer.toString(Team.RED.getScore()), 363, 4);
		g.drawString(Integer.toString(Team.BLUE.getScore()), 423, 4);
		//Debug Info
		g.drawString(Integer.toString(playeraxpos), 100, 10);
		g.drawString(Integer.toString(playeraypos), 130, 10);
		g.drawString(Float.toString(ball_a.getCurrentPositionX()), 100, 30);
		g.drawString(Float.toString(ball_a.getCurrentPositionY()), 200, 30);
		g.drawString("Distance: " + collison_detection.getDistance(), 100, 50);
		//g.drawString(String.valueOf(ball_a.getRadius()), 150, 50);
		//g.drawString(String.valueOf(player_a.getRadius()), 200, 50);
		///g.drawString(String.valueOf(ball_a.getMiddlePoints()[0]), 100, 70);
		///g.drawString(String.valueOf(ball_a.getMiddlePoints()[1]), 150, 70);
		//g.drawString(String.valueOf(player_a.getMiddlePoints()[0]), 200, 70);
		//g.drawString(String.valueOf(player_a.getMiddlePoints()[1]), 250, 70);
		//g.drawString(Double.toString(distance), 100, 50);
		//g.drawString(Boolean.toString(collided), 100, 70);
		
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
		Input input = container.getInput();		
			animationTimer += delta;
			if(collison_detection.checkCollison()){
				collisonpoints[0] = collison_detection.getCollisonPoints()[0];
				collisonpoints[1] = collison_detection.getCollisonPoints()[1];
				collided = true;
				ballTimer = 1500;
				movement = new BallMovement(ball_a, ballTimer, collison_detection.getCollisonPoints()[0], collison_detection.getCollisonPoints()[1]);
				playerspeed = 0.05f;
				lepesX = movement.calculateDirections()[0];
				lepesY = movement.calculateDirections()[1];
			}
			if(ballTimer > 0f){
				if(collison_detection.outOfBoundsCheck(ball_a)){
					lepesX = movement.calculateDirections()[0];
					lepesY = movement.calculateDirections()[1];
				}
				ballxpos += lepesX;
				ballypos += lepesY;
				ball_a.setCurrentPositionX(ballxpos);
				ball_a.setCurrentPositionY(ballypos);
				ballTimer -= delta * 2;
				if(playerspeed < 0.2f){
					playerspeed += 0.005f;
				}
			}	
			if(ballTimer <= 0f){
				collided = false;
				ballTimer = -1f;
				
			}
			
			//Goal
			if(ball_a.goal()){
				ball_a.setCurrentPositionX((xSize / 2) - 16);
				ball_a.setCurrentPositionY((ySize / 2) - 16);
				ballxpos = ball_a.getCurrentPositionX();
				ballypos = ball_a.getCurrentPositionY();
				ballTimer = 0;
				lepesX = 0;
				lepesY = 0;
			}
		//Player 1 Controlls
		//Animation
		
		//Image Rotation
			
		if(input.isKeyPressed(Input.KEY_W)){
			player_a.rotate(-90, player_movement);	
		}else if(input.isKeyPressed(Input.KEY_D)){
			player_a.rotate(0, player_movement);
		}else if(input.isKeyPressed(Input.KEY_S)){
			player_a.rotate(-270, player_movement);
		}else if(input.isKeyPressed(Input.KEY_A)){
			player_a.rotate(-180, player_movement);
		}
			
		if(animationTimer >= 100){
			if(input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_D)){
			//System.out.println("Wut???");
			if(currentImage == 3){
				currentImage = 0;
				animationTimer = 0;
			}else{
				player_a.setImage(currentImage);
				currentImage++;
				animationTimer = 0;
			}
			}else{
				animationTimer = 0;
			}
			
		}
		if(input.isKeyDown(Input.KEY_W)){
			if(playeraypos >= 55){
				rotationAngle = 90;
				playeraypos -= delta * playerspeed;
				player_a.setCurrentPositionY((playeraypos));
				player_a.updateHitbox(playeraxpos, playeraypos);
				//player_a.rotate(90, PlayerDirections.UP);
			}
		}
		if(input.isKeyDown(Input.KEY_S)){
			if(playeraypos <= 515){
				rotationAngle = 270;
				playeraypos += delta * playerspeed;
				player_a.setCurrentPositionY(playeraypos);
				player_a.updateHitbox(playeraxpos, playeraypos);
				//player_a.rotate(270, PlayerDirections.DOWN);
			}
		}
		if(input.isKeyDown(Input.KEY_A)){
			if(playeraxpos >= 105){
				rotationAngle = 180;
				playeraxpos -= delta * playerspeed;
				player_a.setCurrentPositionX(playeraxpos);
				player_a.updateHitbox(playeraxpos, playeraypos);
			//	player_a.rotate(180, PlayerDirections.LEFT);
			}
		}
		if(input.isKeyDown(Input.KEY_D)){
			if(playeraxpos <= 665){
				rotationAngle = 0;
				playeraxpos += delta * playerspeed;
				player_a.setCurrentPositionX(playeraxpos);
				player_a.updateHitbox(playeraxpos, playeraypos);
				player_a.getImage().rotate(rotationAngle);
			//	player_a.rotate(0, PlayerDirections.RIGHT);
			}
		}
		
	}

	public void goal(){
		
	}

	@Override
	public int getID() {
		return id;
	}
	
	public void setTeamScoreA(int scoreA){
		this.teamAScore = scoreA;
	}
	
	public void setTeamScoreB(int scoreB){
		this.teamBScore = scoreB;
	}
	
/*	private void setDirection(int direction){
		this.direction = direction;
	}

	
	public double[] getMiddlePoint(int objX, int objY, int size){
		int x = objX + (size/2);
		int y = objY + (size/2);
		double[] middle = {x,y};
		return middle;
	}*/
}

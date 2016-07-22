package logic;

public class Direction {
	public enum Directions{
	  UP, UP_RIGHT, RIGHT_UP_RIGHT, RIGHT_UP, RIGHT, RIGHT_DOWN, RIGHT_DOWN_RIGHT, DOWN_RIGHT, DOWN, DOWN_LEFT, LEFT_DOWN_LEFT, LEFT_DOWN, LEFT, LEFT_UP, LEFT_UP_LEFT 
	}
	
	public enum PlayerDirections{
		UP (90),
		RIGHT (0),
		DOWN (270),
		LEFT (180);
		
		private final float angle;
		private PlayerDirections(float angle) {
			this.angle = angle;
		}
		
		public float getAngle(){
			return angle;
		}
	}
	
	private double[] objOneMiddle = new double[]{0,0};
	private double[] objTwoMiddle = new double[]{0,0};
	
	public Direction(double[] objOneMiddle, double[] objTwoMiddle){
		if(objOneMiddle.length == 2 && objTwoMiddle.length == 2){
			for(int i = 0; i < 2; ++i){
				this.objOneMiddle[i] = objOneMiddle[i];
				this.objTwoMiddle[i] = objTwoMiddle[i];
			}
		}
	}
	
	
	public double[] setCollisionPoint(){
		double[] xandy = new double[]{0,0};
		double direction_x = (objOneMiddle[0] - objTwoMiddle[0]);
		double direction_y = (objOneMiddle[1] - objTwoMiddle[1]);
		float multiplier = 1f;
		
		/*if(direction_x < 0){
			multiplier *= -1;
		}
		if(direction_y < 0){
			multiplier *= -1;
		}*/
		
		if(objOneMiddle.length == 2 && objTwoMiddle.length == 2){
			xandy[0] = (direction_x * multiplier) / 8;
			xandy[1] = (direction_y * multiplier) / 8;
		}
		
		return xandy;
	}
	
}

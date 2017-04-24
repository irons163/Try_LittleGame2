package com.example.try_littlegame.game6;

public class MathLogic {
	private float fAngle;
	private float fMoveAngle;
	private float speedY = -15;
	private float speedX = -15;
	final int BALL_INIT_SPEEDX = 10;
	
	public void initAngle() {
		// fAngle = Math.round(360 * Math.random());
		// setTitle(fAngle+"«×");
		fAngle = 315;
	}
	
	public void genMoveAngle(){
		fMoveAngle = fAngle + 90;
		if (fMoveAngle >= 360) {
			fMoveAngle = fMoveAngle - 360;
		}
	}

	public void genSpeed() {
		this.speedX = (float) Math.cos(Math.toRadians(this.fMoveAngle))
				* BALL_INIT_SPEEDX;
		this.speedY = (float) Math.sin(Math.toRadians(this.fMoveAngle))
				* BALL_INIT_SPEEDX * (-1);
	}
	
	public float getSpeedX() {
		return speedX;
	}
	
	public float getSpeedY() {
		return speedY;
	}

	public float getSpeedX(float fAngle) {
		return (float) Math.cos(Math.toRadians(fAngle)) * BALL_INIT_SPEEDX;
	}

	public float getSpeedY(float fAngle) {
		return (float) Math.sin(Math.toRadians(fAngle)) * BALL_INIT_SPEEDX
				* (-1);
	}

	public float getSpeedXBySpeedY(float fAngle) {
		return (float) Math.cos(Math.toRadians(fAngle)) * BALL_INIT_SPEEDX;
	}

	public float getSpeedYBySpeedX(float speedX) {
		double speedTotalPOW = (float) (Math.pow(this.speedX, 2) + Math.pow(
				this.speedY, 2));
		float speedTotal = (float) Math.sqrt(speedTotalPOW);
		double newSpeedYPOW = Math.pow(speedTotal * 3, 2) - Math.pow(speedX, 2);
		float newSpeedY = (float) Math.sqrt(newSpeedYPOW);
		return newSpeedY / 3;
	}

	public void getNewSpeedAfterHitCoener(float newAngleAfterHitCoener) {
		this.speedX = (float) Math.cos(Math.toRadians(newAngleAfterHitCoener))
				* (BALL_INIT_SPEEDX);
		this.speedY = (float) Math.sin(Math.toRadians(newAngleAfterHitCoener))
				* (BALL_INIT_SPEEDX) * (-1);
	}

	public void genAngle() {
		this.fAngle = (float) ((Math.atan2(this.speedY, (-1) * this.speedX) + Math.PI)
				/ Math.PI * 180);
		// setTitle(fAngle+"«×");
	}
	
	public void genAngle(int point1_X, int point1_Y, float point2_X, float point2_Y) {
		this.fAngle = getTwoPointsAngle(point1_X, point1_Y, point2_X, point2_Y);
	}

	public float getTwoPointsAngle(int cornerX, int cornerY, float ballCenterX,
			float ballCenterY) {
		// this.fAngle = (float) ((Math.atan2(this.speedY, (-1) * this.speedX) +
		// Math.PI)
		// / Math.PI * 180);
		// float hitCornerAngle = ((float)(Math.atan((-1)*(cornerY-ballCenterY)
		// / (cornerX-ballCenterX))/Math.PI *180));
		float hitCornerAngle = ((float) ((Math.atan2((-1)
				* (cornerY - ballCenterY), (cornerX - ballCenterX)))
				/ Math.PI * 180));
		if (hitCornerAngle < 0) {
			hitCornerAngle = 360 + hitCornerAngle;
		}

		// float x =
		// (float)(Math.atan(((-1)*(cornerY-ballCenterY)/(cornerX-ballCenterX))));
		// float hitCornerAngle = (float)
		// (((float)((Math.atan2((-1)*(cornerY-ballCenterY),
		// (cornerX-ballCenterX)))))*(180/Math.PI));
		return hitCornerAngle;
	}

	public float getStartAngle(int startTriangleX, int startTriangleY,
			float ballCenterX, float ballCenterY) {
		// this.fAngle = (float) ((Math.atan2(this.speedY, (-1) * this.speedX) +
		// Math.PI)
		// / Math.PI * 180);
		// float hitCornerAngle = ((float)(Math.atan((-1)*(cornerY-ballCenterY)
		// / (cornerX-ballCenterX))/Math.PI *180));
		float startAngle = ((float) ((Math.atan2((-1)
				* (startTriangleY - ballCenterY),
				(startTriangleX - ballCenterX)))
				/ Math.PI * 180));
		if (startAngle < 0) {
			startAngle = 360 + startAngle;
		}

		// float x =
		// (float)(Math.atan(((-1)*(cornerY-ballCenterY)/(cornerX-ballCenterX))));
		// float hitCornerAngle = (float)
		// (((float)((Math.atan2((-1)*(cornerY-ballCenterY),
		// (cornerX-ballCenterX)))))*(180/Math.PI));
		return startAngle;
	}

	public float getNewAngleAfterHitCoener(float hitCornerAngle) {
		float newAngleAfterHitCoener = 0;
		if (this.fAngle - hitCornerAngle <= 45) {
			newAngleAfterHitCoener = this.fAngle - 180
					- (this.fAngle - hitCornerAngle);
		} else {
			newAngleAfterHitCoener = this.fAngle
					+ (this.fAngle - hitCornerAngle);
		}
		return newAngleAfterHitCoener;
	}

	public float getNewAngleTowardsPoint(int targetX, int targetY,
			float ballCenterX, float ballCenterY) {
		float hitCornerAngle = ((float) ((Math.atan2((-1)
				* (targetY - ballCenterY), (targetX - ballCenterX)))
				/ Math.PI * 180));
		if (hitCornerAngle < 0) {
			hitCornerAngle = 360 + hitCornerAngle;
		}
		return hitCornerAngle;
	}
}

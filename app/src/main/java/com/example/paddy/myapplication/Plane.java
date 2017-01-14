package com.example.paddy.myapplication;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class Plane {

	private float x,y;
	private Bitmap img;

	public float width, heigth;


	public Plane(float x, float y, Bitmap bitmap) {
		this.x = x;
		this.y = y;
		this.img = bitmap;
		width = bitmap.getWidth();
		heigth = bitmap.getHeight();

	}

	public Bitmap getImage() {
		return img;
	}
	
	public void setPosition(float[] coords) {
		this.x = coords[0];
		this.y = coords[1];
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

/*

	CollisionDetection not ready yet!

	public boolean collidesWith(Asteroid obj){
		if(obj.getY() <= this.y + this.heigth && obj.getY() + obj.getWidth() >= this.y && obj.getX() + obj.getWidth() >= this.x && obj.getX() <= this.x + this.width){
			return true;
		} return false;
	}
*/
}

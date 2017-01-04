package com.example.paddy.myapplication;

import android.graphics.Bitmap;

public class Plane {

	private float x,y;
	private Bitmap img;

	public Plane(float x, float y, Bitmap bitmap) {
		this.x = x;
		this.y = y;
		this.img = bitmap;
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


}

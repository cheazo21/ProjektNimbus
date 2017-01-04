package com.example.paddy.myapplication;

import android.graphics.Bitmap;

public class Background {

	public int speed = -20;
	
	private int x;

	private Bitmap bitmap;

	public Background(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public void update() {
		x += speed;

		if (x <= -bitmap.getWidth()) {
			x = 0;
		}
	}


	public void setSpeed(int speed) {
		this.speed = -speed;
	}
	
	public int getSpeed() {
		return speed;
	}

	public float getX() {
		return x;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}
}

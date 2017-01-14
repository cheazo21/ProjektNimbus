package com.example.paddy.myapplication;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Region;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class BackgroundView extends SurfaceView implements Runnable {

	Background bg;
	Thread renderThread;
	SurfaceHolder holder;
	volatile boolean running;

	public BackgroundView(Context context) {
		super(context);
		this.bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.background_space));
		this.holder = getHolder();
	}

	public void resume() {
		running = true;
		renderThread = new Thread(this);
		renderThread.start();
	}
	//Hintergrund Bewegen
	public void run() {
		long startTime = System.nanoTime();
		while (running) {
			if (!holder.getSurface().isValid())
				continue;
			Canvas canvas = null;
			try {
				canvas = holder.lockCanvas(null);
				canvas.clipRect(0, 0, getWidth(), getHeight(), Region.Op.REPLACE);

				synchronized (bg) {
					//Zeichnet das Bild zweimal aber versetzt, damit es nicht ruckelt
					if (bg.getX()+bg.getBitmap().getWidth() >= 0 || (bg.getX() >= 0 && bg.getX() <= getWidth()))
						canvas.drawBitmap(bg.getBitmap(), bg.getX(),0, null);
					if (bg.getX()+bg.getBitmap().getWidth()+bg.getBitmap().getWidth() >= 0 || (bg.getX()+bg.getBitmap().getWidth() >= 0 && bg.getX() <= getWidth()))
						canvas.drawBitmap(bg.getBitmap(), (bg.getX()+bg.getBitmap().getWidth()),0, null);

					if (getDeltaTime(startTime) > 0.1) {
						bg.update();
						startTime = System.nanoTime();
					}

				}
			} finally { 
				if (canvas != null)
					holder.unlockCanvasAndPost(canvas);
			}
		}
	}

	public void pause() {
		running = false;
		while (true) {
			try {
				renderThread.join();
				break;
			} catch (InterruptedException e) {
				// do nothing
			}
		}
	}

	public Background getBackgroundModel(){
		return bg;
	}

	private float getDeltaTime(long startTime) {
		return (System.nanoTime() - startTime) / 100000000f;
	}
}
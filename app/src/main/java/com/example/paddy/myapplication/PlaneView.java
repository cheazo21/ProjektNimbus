package com.example.paddy.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.View;

import com.example.paddy.myapplication.*;

public class PlaneView extends View {

	long startTime = System.nanoTime();
	private Plane plane;
	private Asteroid asteroid;
	private Background bg;
	private Paint paint;
	private Rect rect;
	private final String textLabel = "Speed: ";
	private final String pointsLabel = "Points: ";
	private Context context;

	private float[] coords = new float[2];

	private int yOffset;
	private int speedXCoordinate;

	public PlaneView(Context context) {
		super(context);
	}

	public PlaneView(Context context, Plane plane, Background bg) {
		super(context);
		this.plane = plane;
		this.bg = bg;
		this.context = context;

	}

	@Override
	protected void onDraw(Canvas canvas) {
		keepPlaneVisible(plane);
		canvas.drawBitmap(plane.getImage(), coords[0], coords[1], null);

		String speedText = ""+(-bg.getSpeed()*23);
		String pointsText = ""+ String.format("%.2f", getDeltaTime(startTime));
		int fontSize = context.getResources().getDimensionPixelSize(R.dimen.speedFontSize);
		Paint paint = getPaint(fontSize);
		paint.getTextBounds(textLabel, 0, speedText.length(), rect);

		if (yOffset == 0)
			yOffset = rect.height();

		paint.getTextBounds(speedText, 0, speedText.length(), rect);
		int labelPadding = context.getResources().getDimensionPixelSize(R.dimen.labelPadding);
		
		if (speedXCoordinate == 0)
			speedXCoordinate = (getWidth()-rect.width())-labelPadding;

		paint.getTextBounds(textLabel, 0, textLabel.length(), rect);
		canvas.drawText(textLabel, (speedXCoordinate-rect.width()-labelPadding), yOffset, paint);
		canvas.drawText(speedText, speedXCoordinate, yOffset, paint);

		canvas.drawText(pointsLabel, 0, 100, paint);
		canvas.drawText(pointsText, 300 , 100, paint);

		//CollisionDetection not ready yet!
		//if(plane.collidesWith(asteroid)){
		//	canvas.drawText("Collision!",0,300,paint);
		//}
	}


	//HUD
	private Paint getPaint(float fontSize) {
		if (rect == null)
			rect = new Rect();
		if (paint == null) {
			paint = new Paint();
			Typeface tf = Typeface.create("Droid Sans", Typeface.BOLD);
			paint.setTypeface(tf);
			paint.setStyle(Style.FILL);
			paint.setAntiAlias(true);
			paint.setColor(context.getResources().getColor(android.R.color.holo_green_dark));
			paint.setTextSize(fontSize);
		}
		return paint;
	}

	public Plane getPlane() {
		return plane;
	}


	//Sorgt dafür dass das Flugzeug nicht aus dem Bild fliegen kann
	private void keepPlaneVisible(Plane plane) {
		int height = plane.getImage().getHeight();
		int width = plane.getImage().getWidth();
		float xPlane = plane.getX();
		float yPlane = plane.getY();

		if (xPlane < 0)
			coords[0] = 1;
		else if (xPlane > getWidth()-width)
			coords[0] = getWidth()-width;
		else
			coords[0] = xPlane;

		if (yPlane < 0)
			coords[1] = 1;
		else if (yPlane > getHeight()-height)
			coords[1] = getHeight()-height;
		else
			coords[1] = yPlane;

	}
	private float getDeltaTime(long startTime) {
		return (System.nanoTime() - startTime) / 100000000f;
	}


}
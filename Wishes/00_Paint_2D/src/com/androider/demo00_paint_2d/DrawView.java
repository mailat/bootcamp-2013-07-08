package com.androider.demo00_paint_2d;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class DrawView extends View implements OnTouchListener {
    private static final String TAG = "DrawView";

    List<Point> points = new ArrayList<Point>();
    Paint paint = new Paint();

    public DrawView(Context context) {
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);

        this.setOnTouchListener(this);

        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
    }

    @Override
    public void onDraw(Canvas canvas) {
		//TODO
    	int[] colors = new int[] { Color.parseColor("#284b91"), Color.parseColor("#6d87c1")};
		
        for (Point point : points) {
            canvas.drawCircle(point.x, point.y, getRandomSize(), paint);
        }
    }

    public boolean onTouch(View view, MotionEvent event) {
        Point point = new Point();
        point.x = event.getX();
        point.y = event.getY();
        points.add(point);
        invalidate();
        Log.d(TAG, "point: " + point);
        return true;
    }
    
    int getRandomSize()
    {
    	Random r = new Random();
    	int valRandom = r.nextInt(10-3) + 3;
    	return (valRandom);
    }
}

class Point {
    float x, y;

    @Override
    public String toString() {
        return x + ", " + y;
    }
}
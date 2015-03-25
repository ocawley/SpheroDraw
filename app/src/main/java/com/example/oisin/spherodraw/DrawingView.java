package com.example.oisin.spherodraw;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Oisin on 19/02/2015.
 */
public class DrawingView extends View
{
    //drawing path
    private Path drawPath;

    //drawing and canvas paint
    private Paint drawPaint, canvasPaint, textPaint;
    //initial color
    private int paintColor = 0xFF660000;
    //canvas
    private Canvas drawCanvas;
    //canvas bitmap
    private Bitmap canvasBitmap;

    public DrawingView(Context context, AttributeSet attrs){
        super(context, attrs);
        setupDrawing();
    }
    private void setupDrawing(){
        //get drawing area setup for interaction
        drawPath = new Path();
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(20);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        canvasPaint = new Paint(Paint.DITHER_FLAG);

        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setAntiAlias(true);
        textPaint.setStrokeWidth(2);
//        textPaint.setStyle(Paint.Style.STROKE);
//        textPaint.setStrokeJoin(Paint.Join.ROUND);
//        textPaint.setStrokeCap(Paint.Cap.ROUND);
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        //view given size
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        //draw view
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        canvas.drawPath(drawPath, drawPaint);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //detect user touch
        Globals g = Globals.getInstance();
        int[][] j=g.getData();
        float touchX = event.getX();
        float touchY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(touchX, touchY);
//                drawPaint.setTextSize(20);
//                drawCanvas.drawText("            ", 50, 50, textPaint);
//                drawCanvas.drawText(Float.toString(touchX), 50, 50, textPaint);
                ((TextView) ((Activity)this.getContext()).findViewById(R.id.myPoint)).setText(Integer.toString(g.trackLength));
                break;
            case MotionEvent.ACTION_UP:
                drawCanvas.drawPath(drawPath, drawPaint);
                drawPath.reset();
                j=g.getData();
                for (int i=0; i<g.trackLength; i++) {
                    drawCanvas.drawText(Integer.toString(j[0][i]), 1, 2+i, textPaint);
                    ((TextView) ((Activity)this.getContext()).findViewById(R.id.myPoint)).setText("Testing....");
                    ((TextView) ((Activity)this.getContext()).findViewById(R.id.myPoint)).setText(Integer.toString(j[0][i]));
//                    ((EditText) findViewById(R.id.editText)).setText(Integer.toString(track[0][i]));
                }
                break;
            default:
                return false;
        }
        j[0][g.trackLength] = (int) touchX;
        j[1][g.trackLength] = (int) touchY;
        g.setData(j);
        Log.d("Track", Integer.toString(j[0][g.trackLength]) + " - " + Integer.toString(j[1][g.trackLength]));
        g.trackLength = g.trackLength+1;


        invalidate();
        return true;
    }

    public void sendtoSphero(){

    }

}


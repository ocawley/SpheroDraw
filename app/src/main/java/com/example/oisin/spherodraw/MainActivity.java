package com.example.oisin.spherodraw;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void goSphero(View v) {
        ((TextView) ((Activity)this).findViewById(R.id.myPoint)).setText("Inside goSphero.");

        Globals g = Globals.getInstance();
        int[][] j=g.getData();
        try {
            //thread to sleep for the specified number of milliseconds
            Thread.sleep(2000);
        } catch ( java.lang.InterruptedException ie) {
            System.out.println(ie);
        }

        for (int i=0; i<g.trackLength; i++) {
            ((TextView) this.findViewById(R.id.myPoint)).setText(Integer.toString(j[0][i]));
            Log.d("GoSphero", Integer.toString(j[0][i]) + " - " + Integer.toString(j[1][i]));
            try {
                //thread to sleep for the specified number of milliseconds
                Thread.sleep(50);
            } catch ( java.lang.InterruptedException ie) {
                System.out.println(ie);
            }
        }

    }

}

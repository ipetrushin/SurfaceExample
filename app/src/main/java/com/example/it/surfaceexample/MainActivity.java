package com.example.it.surfaceexample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout layout = (FrameLayout) findViewById(R.id.frame);
        layout.addView(new DrawView(this));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
}

class DrawThread extends Thread
{
    SurfaceHolder surfaceHolder;
    boolean running = true;
    DrawThread (Context context, SurfaceHolder surfaceHolder)
    {
        this.surfaceHolder = surfaceHolder;
    }
    long time = 0;

    void requestStop()
    {
        running = false;
    }

    public void run()
    {
        while (running)
        {
            Canvas canvas = surfaceHolder.lockCanvas();
            if(canvas != null)
            {
                try
                {
                    Paint paint = new Paint();
                    paint.setColor(Color.BLUE);
                    paint.setStyle(Paint.Style.STROKE);
                    paint.setStrokeWidth(1);
                    canvas.drawColor((int) 4279308561L);
                    canvas.drawCircle(200, 300 + Math.round(Math.sin(time/1000)*100), 100 , paint);

                    time ++;


                }
                finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
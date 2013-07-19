package com.androider.demo00_paint_2d;

import android.os.Bundle;
import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

	DrawView drawView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set full screen view
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                         WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        drawView = new DrawView(this);
        setContentView(drawView);
        drawView.requestFocus();
    }
}

package com.example.computer.doantn;

import android.content.ClipData;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Computer on 4/9/2017.
 */

public class SelectClassActivity extends AppCompatActivity {


    private ImageView img;
    private Button buton;

    private String msg = "REPORT";
    private View.DragShadowBuilder dragShadowBuilder;
    private ClipData data;
    private android.widget.RelativeLayout.LayoutParams layoutParams;

    private float touchX = 0;
    private float touchY = 0;
    private float dx = 0;
    private float dy = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chon_lop);
        img = (ImageView) findViewById(R.id.imgView);
        buton = (Button) findViewById(R.id.btnLop1);

        final float buttonX = buton.getX();
        final float buttonY = buton.getY();

        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        touchX = event.getX();
                        touchY = event.getY();
                        dx = touchX - img.getX();
                        dy = touchY - img.getY();
                    }
                    break;
                    case MotionEvent.ACTION_MOVE: {
                        img.setX(event.getX() - dx);
                        img.setY(event.getY() - dy);
                    }
                    break;
                    case MotionEvent.ACTION_UP: {
                        if (((touchX <buttonX +10)&&(touchX >buttonX -10))&&((touchY <buttonY +10)&&(touchY >buttonY -10))){
                            Log.d("fuck::::::","INTO");
                        }
                    }
                }
                return true;
            }
        });
    }
}
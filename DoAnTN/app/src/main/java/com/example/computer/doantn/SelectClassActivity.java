package com.example.computer.doantn;

import android.content.ClipData;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by Computer on 4/9/2017.
 */
public class SelectClassActivity extends AppCompatActivity {
    private ImageView img;
    private Button button;
    ViewGroup viewGroup;
    private String msg = "REPORT";
    private View.DragShadowBuilder dragShadowBuilder;
    private ClipData data;
    private android.widget.RelativeLayout.LayoutParams layoutParams;
    private int _xDelta;
    private int _yDelta;
    private float touchX = 0;
    private float touchY = 0;
    private float dx = 0;
    private float dy = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chon_lop);
        img = (ImageView) findViewById(R.id.btnBack);
        button = (Button) findViewById(R.id.btnLop1);
        viewGroup = (ViewGroup) findViewById(R.id.view_root);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(150, 150);
        img.setLayoutParams(layoutParams);
        img.setOnTouchListener(new ChoiceTouchListener());
        final float buttonX = button.getX();
        final float buttonY = button.getY();
    }

    private final class ChoiceTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            final int X = (int) event.getRawX();
            final int Y = (int) event.getRawY();
      //      Log.d("TAG", "X: " + X + " Y: " + Y);
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    RelativeLayout.LayoutParams layoutParams =
                        (RelativeLayout.LayoutParams) v.getLayoutParams();
                    _xDelta = X - layoutParams.leftMargin;
                    _yDelta = Y - layoutParams.topMargin;
             //       Log.d("TAG", "action down  " + "X: " + _xDelta + " Y: " + _yDelta);
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
       //             Log.d("TAG", "action move");
                    RelativeLayout.LayoutParams layoutParams1 =
                        (RelativeLayout.LayoutParams) v.getLayoutParams();
                    layoutParams1.leftMargin = X - _xDelta;
                    layoutParams1.topMargin = Y - _yDelta;
                    layoutParams1.rightMargin = -250;
                    layoutParams1.bottomMargin = -250;
                    v.setLayoutParams(layoutParams1);
                    if(CheckCollision(v,button)){
                        Log.d("TAG","CAI DIS ME MAY");
                    }
                    break;
            }
            viewGroup.invalidate();
            return true;
        }
    }

    public boolean CheckCollision(View v1,View v2) {
        Rect R1=new Rect(v1.getLeft(), v1.getTop(), v1.getRight(), v1.getBottom());
        Rect R2=new Rect(v2.getLeft(), v2.getTop(), v2.getRight(), v2.getBottom());
        return R1.intersect(R2);
        //dis me csi loz j vay
    }
}
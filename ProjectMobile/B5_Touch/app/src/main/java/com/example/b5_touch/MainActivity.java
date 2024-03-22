package com.example.b5_touch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private EditText e1,e2,e3,e4;
    private ImageView img;
    private float xDown=0,yDown=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                int action=event.getAction();
                switch (action){
                    case MotionEvent.ACTION_DOWN:
                        xDown=event.getX();
                        yDown=event.getY();
                        e1.setText(xDown+"");
                        e2.setText(yDown+"");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float moveX=event.getX();
                        float moveY=event.getY();
                        e3.setText(moveX+"");
                        e4.setText(moveY+"");
                        float dx=moveX-xDown;
                        float dy=moveY-yDown;
                        img.setX(img.getX()+dx);
                        img.setY(img.getY()+dy);
                        break;
                }
                return true;
            }
        });
    }

    private void initView() {
        e1=findViewById(R.id.txt1);
        e2=findViewById(R.id.txt2);
        e3=findViewById(R.id.txt3);
        e4=findViewById(R.id.txt4);
        img=findViewById(R.id.img);
    }

}
package com.kurus.flick;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    int[] buttonId = {
            R.id.button1, R.id.button2, R.id.button3,
            R.id.button4, R.id.button5, R.id.button6,
            R.id.button7, R.id.button8
    };
    Button[] buttons = new Button[8];
    Button buttonSpace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSpace = findViewById(R.id.buttonSpace);
        for(int i = 0; i < buttonId.length; i++){
            buttons[i] = findViewById(buttonId[i]);

            buttons[i].setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent event) {
                    view.startDrag(null, new View.DragShadowBuilder(view), view, 0);
                    return true;
                }
            });
        }

        buttonSpace.setOnDragListener(new View.OnDragListener(){
            @Override
            public boolean onDrag(View view, DragEvent event) {
                switch (event.getAction()){

                    case DragEvent.ACTION_DROP:

                        swapButton(view, event);

                }
                return true;
            }
        });
    }

    public void swapButton(View view, DragEvent event){
        Button toButton = (Button) view;
        Button fromButton = (Button) event.getLocalState();
        android.support.v7.widget.GridLayout.LayoutParams toButtonLayoutParams = (android.support.v7.widget.GridLayout.LayoutParams) toButton.getLayoutParams();
        android.support.v7.widget.GridLayout.LayoutParams fromButtonLayoutParams = (android.support.v7.widget.GridLayout.LayoutParams) fromButton.getLayoutParams();

        GridLayout.Spec tempSpec;
        tempSpec = toButtonLayoutParams.columnSpec;
        toButtonLayoutParams.columnSpec = fromButtonLayoutParams.columnSpec;
        fromButtonLayoutParams.columnSpec = tempSpec;

        tempSpec = toButtonLayoutParams.rowSpec;
        toButtonLayoutParams.rowSpec = fromButtonLayoutParams.rowSpec;
        fromButtonLayoutParams.rowSpec = tempSpec;

        toButton.setLayoutParams(toButtonLayoutParams);
        fromButton.setLayoutParams(fromButtonLayoutParams);
    }
}

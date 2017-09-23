package com.xtremedeveloper.counter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button less,more;
    RelativeLayout card;
    TextView text;
    SharedPreferences pref;
    @Override
    protected void onResume() {
        super.onResume();
        if(!pref.getString("txt", "").equals("")){text.setText(pref.getString("txt", ""));}
    }
    @Override
    protected void onPause() {
        super.onPause();
        try {
            SharedPreferences.Editor prefsEditor = pref.edit();
            prefsEditor.putString("txt",text.getText().toString());
            prefsEditor.apply();
        }
        catch (Exception e){}
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        pref = getSharedPreferences("text_value", Context.MODE_PRIVATE);
        setContentView(R.layout.activity_main);

        card=(RelativeLayout) findViewById(R.id.card);
        card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                card.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake));
                text.setText("0");
                return false;
            }
        });
        text=(TextView) findViewById(R.id.text);
        less=(Button)findViewById(R.id.less);
        less.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        less.setBackgroundResource(R.drawable.button_pressed);less.setTextColor(getResources().getColor(R.color.colorPrimary));
                        break;
                    case MotionEvent.ACTION_UP:
                        less.setBackgroundResource(R.drawable.button);less.setTextColor(getResources().getColor(R.color.colorAccent));
                        card.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_left));
                        text.setText((Integer.parseInt(text.getText().toString())-1)+"");
                        break;
                }
                return true;
            }
        });
        more=(Button)findViewById(R.id.more);
        more.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        more.setBackgroundResource(R.drawable.button_pressed);more.setTextColor(getResources().getColor(R.color.colorPrimary));
                        break;
                    case MotionEvent.ACTION_UP:
                        more.setBackgroundResource(R.drawable.button);more.setTextColor(getResources().getColor(R.color.colorAccent));
                        card.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_right));
                        text.setText((Integer.parseInt(text.getText().toString())+1)+"");
                        break;
                }
                return true;
            }
        });
    }
}

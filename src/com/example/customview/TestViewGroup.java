package com.example.customview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.customview.view.MyViewGroup;

public class TestViewGroup extends Activity {

	LinearLayout layout;
	MyViewGroup group;
	TextView textView;
	ImageView imageView;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        layout = new LinearLayout(this);
        group = new MyViewGroup(this);
        group.setPadding(20, 20, 20, 20);
        imageView = new ImageView(this);
        textView = new TextView(this);
        
        imageView.setImageResource(R.drawable.ic_launcher);
        imageView.setBackgroundColor(Color.BLUE);
        textView.setText("Hello");
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(
        		LinearLayout.LayoutParams.MATCH_PARENT, 
        		LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.gravity=Gravity.RIGHT|Gravity.BOTTOM;
        textView.setTextSize(50);
        textView.setLayoutParams(lp);
        textView.setGravity(Gravity.RIGHT);
        
        
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundColor(Color.RED);
        
        //layout.addView(imageView);
        layout.addView(textView);
        group.addView(layout, new LinearLayout.LayoutParams(100, 100));
        group.setBackgroundColor(Color.GREEN);
        setContentView(group);
        
        
    }
	
}

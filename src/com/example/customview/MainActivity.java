package com.example.customview;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Toast;

import com.example.customview.view.RotateViewGroup;


public class MainActivity extends ActionBarActivity {

	//private RotateImageView rotateImageView;
	boolean flag=false;
	private RotateViewGroup rotateLayout;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //rotateImageView=(RotateImageView)findViewById(R.id.rotateImageView);
        rotateLayout=(RotateViewGroup)findViewById(R.id.form1);
        
    }
    
    public void test(View v){
    	Toast.makeText(this, "this is a test", 1).show();
    	if(flag){
    		flag=false;
    		rotateLayout.setAngle(90);
    	}else{
    		flag=true;
    		rotateLayout.setAngle(0);
    	}
    }
    
    public void switchView(View view){
    	if(flag){
    		flag=false;
    		rotateLayout.setAngle(90);
    	}else{
    		flag=true;
    		rotateLayout.setAngle(0);
    	}
    	
    }

}

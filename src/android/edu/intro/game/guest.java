package android.edu.intro.game;

  
 import  java.lang.Math;




import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.edu.intro.R;
import android.edu.intro.network.client;

import android.graphics.drawable.AnimationDrawable;


import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.TextView;

public class guest extends Activity {
    /** Called when the activity is first created. */
	
    int num=0,num1,num2,num3;
    private ImageView mImageView01;
    private ImageView mImageView02;
    private ImageView mImageView03;
    
    private String account;
    private int npc_index;
    private client cln=new client();
    
    TextView t1;
    Button b1;
    Button b2;
    AnimationDrawable frameAnimation1=null,frameAnimation2=null,frameAnimation3=null;
    private static int[] s1=new int[]{R.drawable.di1,R.drawable.di21,R.drawable.di31,R.drawable.di41,R.drawable.di51,
    	R.drawable.di61};
    private static int[] s2=new int[]{R.drawable.di1,R.drawable.di21,R.drawable.di31,R.drawable.di41,R.drawable.di51,
    	R.drawable.di61};
    private static int[] s3=new int[]{R.drawable.di1,R.drawable.di21,R.drawable.di31,R.drawable.di41,R.drawable.di51,
    	R.drawable.di61};
   
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest);
        t1 = (TextView)findViewById(R.id.T1);
        b1 = (Button)findViewById(R.id.Button1);
        b2 = (Button)findViewById(R.id.Button2);
        mImageView01=(ImageView)findViewById(R.id.mImage01);
        mImageView02=(ImageView)findViewById(R.id.mImage02);
        mImageView03=(ImageView)findViewById(R.id.mImage03);
        mImageView01.setImageResource(R.anim.picture);
        mImageView02.setImageResource(R.anim.picture);
        mImageView03.setImageResource(R.anim.picture);
        
        frameAnimation1 = (AnimationDrawable) mImageView01.getDrawable();
        frameAnimation2 = (AnimationDrawable) mImageView02.getDrawable();
        frameAnimation3 = (AnimationDrawable) mImageView03.getDrawable();
        
        Bundle bundle=getIntent().getExtras();
        account=bundle.getString("act");
	    npc_index=bundle.getInt("npc_index");
   
        b1.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
			{
				
				
				
					 num1= (int)((Math.random()*6));
			         num2= (int)((Math.random()*6));
			         num3= (int)((Math.random()*6));
			       
			    	 num=num1+num2+num3+3;
			    	 mImageView01.setImageDrawable(getResources().getDrawable(s1[num1]));
			    	 mImageView02.setImageDrawable(getResources().getDrawable(s2[num2]));
			    	 mImageView03.setImageDrawable(getResources().getDrawable(s3[num3])); 
			       if(num<=9)
			       {
			         t1.setText(num+"點");
			         new AlertDialog.Builder(guest.this)
			         .setTitle("結果")
			         .setMessage(num+"點"+"you lose")
			         .setPositiveButton("Retry",
			                         new DialogInterface.OnClickListener(){
			                                 public void onClick(
			                                                 DialogInterface dialoginterface, int i){
			                                	 t1.setText("");  }
			                         }) .show();
			       }
			       else
			       {
			    	   t1.setText(num+"點");
				         new AlertDialog.Builder(guest.this)
				         .setTitle("結果")
				         .setMessage(num+"點"+"you win")
				         .setPositiveButton("Ok",
				                         new DialogInterface.OnClickListener(){
				                                 public void onClick(
				                                                 DialogInterface dialoginterface, int i)
				                                 {
				                                	 cln.updateScoreByGID(account, npc_index+1, num*10);
				            				         cln.updateFinGame(account, npc_index+1);
				            				         finish();
				                                	  }
				                         }) .show();
				         
			       }
	                
				
		         
				
			}
		});
        b2.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
			{
				
				
				
					 num1= (int)((Math.random()*6));
			         num2= (int)((Math.random()*6));
			         num3= (int)((Math.random()*6));
			       
			    	 num=num1+num2+num3+3;
			    	 mImageView01.setImageDrawable(getResources().getDrawable(s1[num1]));
			    	 mImageView02.setImageDrawable(getResources().getDrawable(s2[num2]));
			    	 mImageView03.setImageDrawable(getResources().getDrawable(s3[num3])); 
			       if(num<=9)
			       {
			         t1.setText(num+"點");
			         new AlertDialog.Builder(guest.this)
			         .setTitle("結果")
			         .setMessage(num+"點"+"you win")
			         .setPositiveButton("OK",
			                         new DialogInterface.OnClickListener(){
			                                 public void onClick(
			                                                 DialogInterface dialoginterface, int i){
			                                	   }
			                         }) .show();
			       }
			       else
			       {
			    	   t1.setText(num+"點");
				         new AlertDialog.Builder(guest.this)
				         .setTitle("結果")
				         .setMessage(num+"點"+"you lose")
				         .setPositiveButton("Retry",
				                         new DialogInterface.OnClickListener(){
				                                 public void onClick(
				                                                 DialogInterface dialoginterface, int i){
				                                	 t1.setText("");   }
				                         }) .show();
			       }
	                
				
		         
				
			}
		});

    }
    public boolean onTouchEvent(MotionEvent event) {
    	if (event.getAction() == MotionEvent.ACTION_DOWN) {
    		frameAnimation1.start();
      		  frameAnimation2.start();
      		  frameAnimation3.start();
      		
    	return true;
    	}
    	return super.onTouchEvent(event);
    	}
    
}
package android.edu.intro.game;

import java.text.DecimalFormat;


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
import android.widget.Toast;
public class Slots extends Activity {
  
  /*宣告物件變數*/
    //private ImageView mImageView04;
    private ImageView mImageView01;
    private ImageView mImageView02;
    private ImageView mImageView03;
    private Button mButton;
    private Button Button2;
    private double i=100; 
    private TextView result;
    private TextView mText1;
    private String account;
    private int npc_index;
    private client cln=new client();
    int n = 0;
    AnimationDrawable frameAnimation1=null,frameAnimation2=null,frameAnimation3=null;
    private static int[] s1=new int[]{R.drawable.a1,R.drawable.a2,R.drawable.b1,R.drawable.b2,R.drawable.c1,R.drawable.c2,R.drawable.d1,R.drawable.d2,R.drawable.d3
      ,R.drawable.e1,R.drawable.e2,R.drawable.f1,R.drawable.f2,R.drawable.f3,R.drawable.g1,R.drawable.g2,R.drawable.h1,R.drawable.h2,R.drawable.h3,R.drawable.p71
      ,R.drawable.p72,R.drawable.p73};
   
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
      
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slots2);
        
        Bundle bundle=getIntent().getExtras();
        account=bundle.getString("act");
        npc_index=bundle.getInt("npc_index");
      
        mImageView01=(ImageView)findViewById(R.id.mImage01);
        mImageView02=(ImageView)findViewById(R.id.mImage02);
        mImageView03=(ImageView)findViewById(R.id.mImage03);
        mText1=(TextView)findViewById(R.id.mText1);
        mButton=(Button)findViewById(R.id.mButton);
        Button2=(Button)findViewById(R.id.Button2);
        
        result = (TextView)findViewById(R.id.result);
        Toast.makeText(Slots.this, "三個   7  獎金為 500\n三個草莓獎金為 100\n三個櫻桃獎金為 50\n", Toast.LENGTH_SHORT).show();
        Toast.makeText(Slots.this, "兩個櫻桃獎金為 20\n一個櫻桃獎金為 10", Toast.LENGTH_SHORT).show();
        Toast.makeText(Slots.this, "注意:小心炸彈會扣分!!", Toast.LENGTH_SHORT).show();
        mButton.setOnClickListener(new Button.OnClickListener()
        {
        
          
        	
          public void onClick(View v)
          {
        	  
        	  mImageView01.setImageResource(R.anim.picture);
              mImageView02.setImageResource(R.anim.picture);
              mImageView03.setImageResource(R.anim.picture);
              frameAnimation1 = (AnimationDrawable) mImageView01.getDrawable();
              frameAnimation2 = (AnimationDrawable) mImageView02.getDrawable();
              frameAnimation3 = (AnimationDrawable) mImageView03.getDrawable();
              frameAnimation1.start();
      		  frameAnimation2.start();
      		  frameAnimation3.start();
      		 mButton.setEnabled(false);
      		Button2.setEnabled(true);
          }
        });
   
        Button2.setOnClickListener(new Button.OnClickListener()
        {
        	
        	 public void onClick(View v)
             {
        		 
        		 if(frameAnimation1.isRunning()&&frameAnimation2.isRunning()&&frameAnimation3.isRunning()){
                     
                     frameAnimation1.stop();
                     frameAnimation2.stop();
                     frameAnimation3.stop();
                     
                    }
        		 DecimalFormat nf = new DecimalFormat("0.0");
        		 mButton.setEnabled(true);
           		Button2.setEnabled(false);
                 
                 randon();
               
                
                 /*三個 7 的判斷*/
                 if(s1[0]==R.drawable.p71 && s1[1]==R.drawable.p72 && s1[2]==R.drawable.p73)
                 {mText1.setText("恭喜中獎!!!");
                 i=i+500;
                 }
                 else if(s1[0]==R.drawable.p71 && s1[1]==R.drawable.p73 && s1[2]==R.drawable.p72)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+500;  
                 }
                 else if(s1[0]==R.drawable.p72 && s1[1]==R.drawable.p71 && s1[2]==R.drawable.p73)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+500;
                 }
                 else if(s1[0]==R.drawable.p72 && s1[1]==R.drawable.p73 && s1[2]==R.drawable.p71)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+500;
                 }
                 else if(s1[0]==R.drawable.p73 && s1[1]==R.drawable.p71 && s1[2]==R.drawable.p72)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+500;
                 }
                 else if(s1[0]==R.drawable.p73 && s1[1]==R.drawable.p72 && s1[2]==R.drawable.p71)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+500;
                 }
                 
                 /*三個草莓的判斷*/
                 else if(s1[0]==R.drawable.d1 && s1[1]==R.drawable.d2 && s1[2]==R.drawable.d3)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+100;  
                 }
                 else if(s1[0]==R.drawable.d1 && s1[1]==R.drawable.d3 && s1[2]==R.drawable.d2)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+100;
                 }
                 else if(s1[0]==R.drawable.d2 && s1[1]==R.drawable.d1 && s1[2]==R.drawable.d3)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+100;
                 }else if(s1[0]==R.drawable.d2 && s1[1]==R.drawable.d3 && s1[2]==R.drawable.d1)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+100;  
                 }
                 else if(s1[0]==R.drawable.d3 && s1[1]==R.drawable.d1 && s1[2]==R.drawable.d2)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+100;
                 }
                 else if(s1[0]==R.drawable.d3 && s1[1]==R.drawable.d2 && s1[2]==R.drawable.d1)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+100;
                 }
                 
                 /*三個 櫻桃  的判斷*/
                 else if(s1[0]==R.drawable.f1 && s1[1]==R.drawable.f2 && s1[2]==R.drawable.f3)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+50;  
                 }
                 else if(s1[0]==R.drawable.f1 && s1[1]==R.drawable.f3 && s1[2]==R.drawable.f2)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+50;
                 }
                 else if(s1[0]==R.drawable.f2 && s1[1]==R.drawable.f1 && s1[2]==R.drawable.f3)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+50;
                 }else if(s1[0]==R.drawable.f2 && s1[1]==R.drawable.f3 && s1[2]==R.drawable.f1)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+50;  
                 }
                 else if(s1[0]==R.drawable.f3 && s1[1]==R.drawable.f1 && s1[2]==R.drawable.f2)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+50;
                 }
                 else if(s1[0]==R.drawable.f3 && s1[1]==R.drawable.f2 && s1[2]==R.drawable.f1)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+50;
                 }
                 
               
                 /*三個炸彈(and)*/
                 else if(s1[0]==R.drawable.h1 && s1[1]==R.drawable.h2 &&  s1[2]==R.drawable.h3)
                 {mText1.setText("運氣超差!!!") ;
                 i=i/2;  
                 }
                 else if(s1[0]==R.drawable.h1 &&  s1[1]==R.drawable.h3 &&  s1[2]==R.drawable.h2)
                 {mText1.setText("運氣超差!!!") ;
                 i=i/2;
                 }
                 else if(s1[0]==R.drawable.h2 &&  s1[1]==R.drawable.h1 &&  s1[2]==R.drawable.h3)
                 {mText1.setText("運氣超差!!!") ;
                 i=i/2;
                 }
                 else if(s1[0]==R.drawable.h2 &&  s1[1]==R.drawable.h3 &&  s1[2]==R.drawable.h1)
                 {mText1.setText("運氣超差!!!") ;
                 i=i/2;  
                 }
                 else if(s1[0]==R.drawable.h3 &&  s1[1]==R.drawable.h1 &&  s1[2]==R.drawable.h2)
                 {mText1.setText("運氣超差!!!") ;
                 i=i/2;
                 }
                 else if(s1[0]==R.drawable.h3 &&  s1[1]==R.drawable.h2 &&  s1[2]==R.drawable.h1)
                 {mText1.setText("運氣超差!!!") ;
                 i=i/2;
                 }
                 
                 /* 兩個 櫻桃  的判斷*/
                 else if(s1[0]==R.drawable.f1 && s1[1]==R.drawable.f2 && s1[2]!=R.drawable.f3)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+20;  
                 }
                 else if(s1[0]==R.drawable.f1 && s1[1]==R.drawable.f3 && s1[2]!=R.drawable.f2)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+20;
                 }
                 else if(s1[0]==R.drawable.f2 && s1[1]==R.drawable.f1 && s1[2]!=R.drawable.f3)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+20;
                 }
                 else if(s1[0]==R.drawable.f2 && s1[1]==R.drawable.f3 && s1[2]!=R.drawable.f1)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+20;  
                 }
                 else if(s1[0]==R.drawable.f3 && s1[1]==R.drawable.f1 && s1[2]!=R.drawable.f2)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+20;
                 }
                 else if(s1[0]==R.drawable.f3 && s1[1]==R.drawable.f2 && s1[2]!=R.drawable.f1)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+20;
                 }
                 
                 /* 兩個 櫻桃  的判斷*/
                 else if(s1[0]==R.drawable.f1 && s1[1]!=R.drawable.f2 && s1[2]==R.drawable.f3)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+20;  
                 }
                 else if(s1[0]==R.drawable.f1 && s1[1]!=R.drawable.f3 && s1[2]==R.drawable.f2)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+20;
                 }
                 else if(s1[0]==R.drawable.f2 && s1[1]!=R.drawable.f1 && s1[2]==R.drawable.f3)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+20;
                 }
                 else if(s1[0]==R.drawable.f2 && s1[1]!=R.drawable.f3 && s1[2]==R.drawable.f1)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+20;  
                 }
                 else if(s1[0]==R.drawable.f3 && s1[1]!=R.drawable.f1 && s1[2]==R.drawable.f2)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+20;
                 }
                 else if(s1[0]==R.drawable.f3 && s1[1]!=R.drawable.f2 && s1[2]==R.drawable.f1)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+20;
                 }
                 
                 /* 兩個 櫻桃  的判斷*/
                 else if(s1[0]!=R.drawable.f1 && s1[1]==R.drawable.f2 && s1[2]==R.drawable.f3)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+20;  
                 }
                 else if(s1[0]!=R.drawable.f1 && s1[1]==R.drawable.f3 && s1[2]==R.drawable.f2)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+20;
                 }
                 else if(s1[0]!=R.drawable.f2 && s1[1]==R.drawable.f1 && s1[2]==R.drawable.f3)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+20;
                 }
                 else if(s1[0]!=R.drawable.f2 && s1[1]==R.drawable.f3 && s1[2]==R.drawable.f1)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+20;  
                 }
                 else if(s1[0]!=R.drawable.f3 && s1[1]==R.drawable.f1 && s1[2]==R.drawable.f2)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+20;
                 }
                 else if(s1[0]!=R.drawable.f3 && s1[1]==R.drawable.f2 && s1[2]==R.drawable.f1)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+20;
                 }
                 
                 /* 一個 櫻桃  的判斷*/
                 else if(s1[0]==R.drawable.f1 && s1[1]!=R.drawable.f2 && s1[2]!=R.drawable.f3)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+10;  
                 }
                 else if(s1[0]==R.drawable.f1 && s1[1]!=R.drawable.f3 && s1[2]!=R.drawable.f2)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+10;
                 }
                 else if(s1[0]==R.drawable.f2 && s1[1]!=R.drawable.f1 && s1[2]!=R.drawable.f3)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+10;
                 }
                 else if(s1[0]==R.drawable.f2 && s1[1]!=R.drawable.f3 && s1[2]!=R.drawable.f1)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+10;  
                 }
                 else if(s1[0]==R.drawable.f3 && s1[1]!=R.drawable.f1 && s1[2]!=R.drawable.f2)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+10;
                 }
                 else if(s1[0]==R.drawable.f3 && s1[1]!=R.drawable.f2 && s1[2]!=R.drawable.f1)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+10;
                 }
                 
                 /* 一個 櫻桃  的判斷*/
                 else if(s1[0]!=R.drawable.f1 && s1[1]==R.drawable.f2 && s1[2]!=R.drawable.f3)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+10;  
                 }
                 else if(s1[0]!=R.drawable.f1 && s1[1]==R.drawable.f3 && s1[2]!=R.drawable.f2)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+10;
                 }
                 else if(s1[0]!=R.drawable.f2 && s1[1]==R.drawable.f1 && s1[2]!=R.drawable.f3)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+10;
                 }
                 else if(s1[0]!=R.drawable.f2 && s1[1]==R.drawable.f3 && s1[2]!=R.drawable.f1)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+10;  
                 }
                 else if(s1[0]!=R.drawable.f3 && s1[1]==R.drawable.f1 && s1[2]!=R.drawable.f2)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+10;
                 }
                 else if(s1[0]!=R.drawable.f3 && s1[1]==R.drawable.f2 && s1[2]!=R.drawable.f1)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+10;
                 }
                 
                 /* 一個 櫻桃  的判斷*/
                 else if(s1[0]!=R.drawable.f1 && s1[1]!=R.drawable.f2 && s1[2]==R.drawable.f3)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+10;  
                 }
                 else if(s1[0]!=R.drawable.f1 && s1[1]!=R.drawable.f3 && s1[2]==R.drawable.f2)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+10;
                 }
                 else if(s1[0]!=R.drawable.f2 && s1[1]!=R.drawable.f1 && s1[2]==R.drawable.f3)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+10;
                 }
                 else if(s1[0]!=R.drawable.f2 && s1[1]!=R.drawable.f3 && s1[2]==R.drawable.f1)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+10;  
                 }
                 else if(s1[0]!=R.drawable.f3 && s1[1]!=R.drawable.f1 && s1[2]==R.drawable.f2)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+10;
                 }
                 else if(s1[0]!=R.drawable.f3 && s1[1]!=R.drawable.f2 && s1[2]==R.drawable.f1)
                 {mText1.setText("恭喜中獎!!!") ;
                 i=i+10;
                 }
                 
                 /* 其他 的判斷*/
                 else
                 {
                   mText1.setText("再接再厲!!!");
                   i=i-10;
                 }
                 
                 /*將陣列資料放入*/
                 mImageView01.setImageDrawable(getResources().getDrawable(s1[0]));
                 mImageView02.setImageDrawable(getResources().getDrawable(s1[1]));
                 mImageView03.setImageDrawable(getResources().getDrawable(s1[2]));
                 
                 /*將改變的i值回傳顯示*/
                 final double b = i;
                 result.setText(nf.format(b));
                 
               
                //String[][] rank = cln.getTopScore("Slots″, 1, 10");
                
                		n++;
             	 if(n>=3)
             	 {
             		 new AlertDialog.Builder(Slots.this)
			         .setTitle("result")
			         .setMessage("your score:"+nf.format(b)/*+"\n"+"The best:"+result.append(rank[0][1])*/)
			         .setPositiveButton("OK",
			                         new DialogInterface.OnClickListener(){
			                                 public void onClick(
			                                                 DialogInterface dialoginterface, int i)
			                            {
			                                	 cln.updateScoreByGID(account, npc_index, (int)b);
			                             		 cln.updateFinGame(account, npc_index+1);
			                             		 finish();
			                            }
			                         }) .show();
             		 
                 }
             }
        });
     }
        
   

    
    /*重新洗牌*/
    public void randon()
    {
      for(int i=0;i<22;i++)
      {
        int tmp=s1[i];
        int s=(int)(Math.random()*21);
        s1[i]=s1[s];
        s1[s]=tmp;
      }        
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
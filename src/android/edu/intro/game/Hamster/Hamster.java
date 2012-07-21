package android.edu.intro.game.Hamster;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.edu.intro.R;
import android.edu.intro.network.client;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Hamster extends Activity {
	static boolean gameStop = false;
	ImageAdapter imageAdapter = new ImageAdapter(this);
	Handler handler = new MyHandler();
	TextView timeText;
	TextView scoreText;
	Thread fightControl;
	int time;
	int score = 0;
	int npc_index;
	String account;
	client cln=new client();
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hamster);
        
        Bundle bundle=this.getIntent().getExtras();
        npc_index=bundle.getInt("npc_index");
        account=bundle.getString("act");
        
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(imageAdapter);


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
        gridview.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        		//打中
        		if( imageAdapter.getImage(position)!=R.drawable.hole
        				&& imageAdapter.getImage(position)!=R.drawable.hit_diglett){
        			hit(position);
        		}
        	}
        });
        
        initial();
        
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
    		//gameStop = true;
    	}
    	return false;
    }
    
    public void hit(int position){
    	Message message = new Message();
		message.what = position*10+3;
		handler.sendMessage(message);
		//加分
		score = score + 10;
		scoreText.setText(Integer.toString(score));
		
		Message message1 = new Message();
		message1.what = position*10+2;
		handler.sendMessageDelayed(message1, 500);
    }
    
    public void initial(){
    	gameStop = false;
    	time = FightControl.max_run_time;
        timeText = (TextView) findViewById(R.id.timeText);
        timeText.setText(Integer.toString(time));
        score = 0;
        scoreText = (TextView) findViewById(R.id.scoreText);
        scoreText.setText(Integer.toString(score));
        
        fightControl = new Thread(new FightControl(imageAdapter, handler));
        fightControl.start();
    }
    
    class MyHandler extends Handler {
    	int position;
    	public synchronized void handleMessage(Message msg) {
    		/*
    		if(msg.arg2==1){
    			position = msg.arg1;
	    		imageAdapter.setImage(position,R.drawable.whole_diglett);
	    		imageAdapter.notifyDataSetChanged();
    		}
    		*/
    		if(msg.what%10==0){//出現一半
    			position = msg.what/10;
	    		imageAdapter.setImage(position,R.drawable.half_diglett);
	    		imageAdapter.notifyDataSetChanged();
    		}
    		else if(msg.what%10==1){//出現
    			position = msg.what/10;
	    		imageAdapter.setImage(position,R.drawable.whole_diglett);
	    		imageAdapter.notifyDataSetChanged();
    		}
    		else if(msg.what%10==2){//消失
    			position = msg.what/10;
				imageAdapter.setImage(position,R.drawable.hole);
				imageAdapter.notifyDataSetChanged();
    		}
    		else if(msg.what%10==3){//打中
    			position = msg.what/10;
    			imageAdapter.setImage(position,R.drawable.hit_diglett);
				imageAdapter.notifyDataSetChanged();
    		}
    		else if(msg.what==5){//時間減一
    			time = time - 1;
    			timeText.setText(Integer.toString(time));
    		}
    		else if(msg.what==6){//結束
    			AlertDialog.Builder builder = new AlertDialog.Builder(Hamster.this);
    			builder.setMessage("再玩一次?");
    			builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    				public void onClick(DialogInterface dialog, int which) {
    					dialog.cancel();
    					initial();
    				}
                });
    			builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) 
                    {

            			cln.updateScoreByGID(account, npc_index+1, score);
            			cln.updateFinGame(account, npc_index+1);
        				Toast popup = Toast.makeText(Hamster.this, "接下來去旁邊的天空球場吧!!", Toast.LENGTH_LONG);
        				popup.show();
                    	Hamster.this.finish();
                    }
                });
                AlertDialog againDialog = builder.create();
                againDialog.show();
    		}
    		else if(msg.what==7){//Ready Go
    			Toast toast = new Toast(getApplicationContext()); 
    	        LinearLayout mLayout = new LinearLayout(Hamster.this);
    			TextView readyText = new TextView(Hamster.this);
    			readyText.setText("Ready\n Go!");
    			readyText.setTextSize(50);
    			readyText.setTextColor(Color.BLUE);
    			readyText.setTypeface(Typeface.SERIF, 1);
    			readyText.setShadowLayer(1, 4, 4, Color.rgb(255, 255, 0));
    			
    			//View toastView = toast.getView();
    			mLayout.addView(readyText);
    			//mLayout.addView(toastView);

    			toast.setView(mLayout);
    			toast.setGravity(Gravity.CENTER, 20, 0);
    			toast.setDuration(Toast.LENGTH_SHORT);
    			toast.show();
    		}
    		else if(msg.what==8){//Time's Up!
    			Toast toast = new Toast(getApplicationContext()); 
    	        LinearLayout mLayout = new LinearLayout(Hamster.this);
    			TextView readyText = new TextView(Hamster.this);
    			readyText.setText("Time's Up!");
    			readyText.setTextSize(50);
    			readyText.setTextColor(Color.RED);
    			readyText.setTypeface(Typeface.SERIF, 1);
    			readyText.setShadowLayer(1, 4, 4, Color.rgb(255, 255, 0));
    			
    			//View toastView = toast.getView();
    			mLayout.addView(readyText);
    			//mLayout.addView(toastView);

    			toast.setView(mLayout);
    			toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
    			toast.setDuration(Toast.LENGTH_SHORT);
    			toast.show();
    		}
    		super.handleMessage(msg);
    	}  
    }
   
}

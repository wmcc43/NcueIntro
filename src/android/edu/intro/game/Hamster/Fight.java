package android.edu.intro.game.Hamster;

import android.edu.intro.R;
import android.os.Handler;
import android.os.Message;

public class Fight implements Runnable {
	static int max_run_time = FightControl.max_run_time;
	static int max_show_number = 2;
	static int show_number = 0;
	static int interval_time = 2500;
	static int show_half_time = 300;
	static int show_whole_time = 1000;
	ImageAdapter imageAdapter;
	Handler handler;
	int position;
	
	public Fight(ImageAdapter imageAdapter, Handler handler, int position) {
		this.imageAdapter = imageAdapter;
		this.handler = handler;
		this.position = position;
	}
	
	public void run() {
		while( FightControl.run_time < max_run_time){
			
			if( FightControl.run_time < 2 ){
				int sleep_time = (int)(Math.random()*3000+3500);
				threadSleep(sleep_time);
			}
			else{
				int sleep_time = (int)(Math.random()*3500+interval_time);
				threadSleep(sleep_time);
			}
			
			if( FightControl.run_time < max_run_time && show_number<max_show_number ){
				show_number++;
				
				//出現一半
				sendMessage(position*10+0);
				threadSleep(show_half_time);
				
				//完全出現
				if( FightControl.run_time < max_run_time && hitCheck(0) ){
					sendMessage(position*10+1);
					threadSleep(show_whole_time);
				}
				
				//消失一半
				if( FightControl.run_time < max_run_time  && hitCheck(1) ){
					sendMessage(position*10+0);
					threadSleep(show_half_time);
				}
				
				//完全消失
				if( FightControl.run_time < max_run_time && hitCheck(0) ){
					sendMessage(position*10+2);
				}
				
				show_number--;
			}
		}
		
	}
	public void sendMessage(int arg){
		Message message = new Message();
		message.what = arg;
		handler.sendMessage(message);
	}
	public void threadSleep(int ms){
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public boolean hitCheck(int image){
		if(image==0 && imageAdapter.getImage(position)==R.drawable.half_diglett)
			return true;
		else if(image==1 && imageAdapter.getImage(position)==R.drawable.whole_diglett)
			return true;
		else
			return false;
	}
}

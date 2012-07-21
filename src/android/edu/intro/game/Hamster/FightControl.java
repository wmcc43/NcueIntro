package android.edu.intro.game.Hamster;

import android.os.Handler;
import android.os.Message;

public class FightControl implements Runnable {
	static int max_run_time = 60;
	static int run_time;
	ImageAdapter imageAdapter;
	Handler handler;
	
	public FightControl(ImageAdapter imageAdapter, Handler handler) {
		this.imageAdapter = imageAdapter;
		this.handler = handler;
	}
	public void run() {
			resetAllImage();
			run_time = 0;
			Fight.max_show_number = 2;
			Fight.show_half_time = 300;
			Fight.show_whole_time = 1000;
			
			sendMessage(7);//Ready Go
			
			for(int i=0;i<9;i++){
				Thread fight = new Thread(new Fight(imageAdapter, handler, i));
		        fight.start();
			}
			
			
			threadSleep(2500);
			
			for(int i=0;i<max_run_time;i++){
				threadSleep(1000);
				sendMessage(5);//時間減一
				run_time++;
				
				if(run_time%20==0){
					Fight.max_show_number++;
					
				}
				if(run_time%10==0){
					Fight.show_half_time -= 30;
					Fight.show_whole_time -= 100;
				}
			}
			sendMessage(8);//時間到
			//等待所有Thread跑完
			threadSleep(2500);
			
			sendMessage(6);//再玩一次?
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
	public void resetAllImage(){
		for(int i=0;i<9;i++){
			Message message = new Message();
			message.what = i*10+2;
			handler.sendMessage(message);
		}
	}
}

package android.edu.intro.game;
import android.edu.intro.game.Hamster.Hamster;
import android.edu.intro.network.*;

public class GameCollection 
{
	private Object[] GameActivity;
	private boolean[] mission=new boolean[11];
	private client cln=new client();
	public GameCollection()
	{
		GameActivity=new Object[11];
		for(int i=0;i<11;i++)
		{
			if(i==3||i==8||i==10)
			{
				if(i==3)
					GameActivity[i] = new Hamster();
				if(i==8)
					GameActivity[i] = new Climb2();
				if(i==10)
					GameActivity[i] = new quiz();
			}
			else
				GameActivity[i] = new GameChoice();
		}
	}
	
	public void getCompleteMission(String account)
	{
		int[] complete=cln.getFinGame(account);
		for(int i=0;i<complete.length;i++)
			mission[complete[i]-1]=true;
		for(int i=0;i<mission.length;i++)
			if(mission[i]!=true)
				mission[i]=false;
		
	}
	
	public boolean isMissionComplete(int i)
	{
		return mission[i];
	}
	
	public boolean[] getMissionComplete()
	{
		return mission.clone();
	}
	
	public Object getGame(int i)
	{
		return GameActivity[i];
	}
	
	public boolean getGameComplete(int i)
	{
		return mission[i];
	}
}

package android.edu.intro.game;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.edu.intro.*;
import android.edu.intro.network.client;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class sudoku extends Activity 
{
	private EditText[] EditTextnum=new EditText[9];
	private Button submit;
	private String account;
	private int npc_index;
	private client cln=new client();
	
	public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sudoku);
        getBundle();
        findview();
        setlistener();
    }
	
	private void getBundle()
	{
		Bundle bundle=getIntent().getExtras();
        account=bundle.getString("act");
        npc_index=bundle.getInt("npc_index");
	}
	
	private void findview() 
	{
		int numid=R.id.num0;
		submit=(Button)findViewById(R.id.sudoku_submit);
		for(int i=0;i<9;i++,numid++)
		{
			EditTextnum[i]=(EditText)findViewById(numid);
		}
	}
	
	private void setlistener()
	{
		submit.setOnClickListener(btnlis);
	}
	
	private OnClickListener btnlis=new OnClickListener()
	{
		public void onClick(View v) 
		{
			if(v.getId()==submit.getId())
			{
				boolean checkrow=false;
				boolean checkcol=false;
				boolean checkobli=false;
				boolean check_all_filled=true;
				int[] number=new int[9];
				
				for(int i=0;i<9;i++)
				{
					if(String.valueOf(EditTextnum[i].getText()).equalsIgnoreCase(""))
					{
						check_all_filled=false;
					}
				}
				if(check_all_filled)
				{
					for(int i=0;i<9;i++)
					{
						number[i]=Integer.parseInt(String.valueOf(EditTextnum[i].getText()));
					}
					for(int i=0;i<9;i+=3)
					{
						if((number[i]+number[i+1]+number[i+2])==15)
						{
							if(i==6)
								checkrow=true;
							continue;
						}
					}
					for(int i=0;i<3;i++)
					{
						if((number[i]+number[i+3]+number[i+6])==15)
						{
							if(i==2)
								checkcol=true;
							continue;
						}
					}
					if(((number[0]+number[4]+number[8])==15)&&((number[2]+number[4]+number[6])==15))
						checkobli=true;
					if(checkcol&&checkobli&&checkrow)
					{
						Builder pass=new Builder(sudoku.this);
						pass.setTitle("通過!!");
						pass.setMessage("你通過了這個簡單的小數讀");
						pass.show();
						cln.updateScoreByGID(account, npc_index+1, 100);
						cln.updateFinGame(account, npc_index+1);
						finish();
					}
					else
					{
						Builder fail=new Builder(sudoku.this);
						fail.setTitle("失敗!!");
						fail.setMessage("你中間有格子填錯喔，要不要回去重改呢?");
						fail.show();
					}
				}
				else
				{
					Builder no_num=new Builder(sudoku.this);
					no_num.setTitle("錯誤!!");
					no_num.setMessage("你有數字沒填喔!!");
					no_num.show();
				}
			}
		}
	};
}

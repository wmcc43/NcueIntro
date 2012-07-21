package android.edu.intro.game;

import android.app.AlertDialog.Builder;
import android.edu.intro.R;
import android.edu.intro.network.client;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class quiz extends game
{
	private String account;
	private int npc_index;
	private client cln=new client();
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
		getBundle();
		getView();
		setques();
		setButtonText();
	}
	
	private void getBundle()
	{
		Bundle bundle=getIntent().getExtras();
        account=bundle.getString("act");
        npc_index=bundle.getInt("npc_index");
	}
	
	@Override
	protected void setButtonText()
	{
		send.setOnClickListener(anslis);
		send.setText("送出答案");
	}

	@Override
	protected void setques()
	{
		ques.setText(R.string.quest);
	}
	
	
	private OnClickListener anslis=new OnClickListener()
	{
		public void onClick(View v) 
		{
			String answer;
			answer=ans.getText().toString();
			if(send.getText().toString().equalsIgnoreCase("送出答案"))
			{
				if(answer.equalsIgnoreCase("Google Android 2"))//Google Android 2程式設計與應用 
				{	
					Builder correct_ans=new Builder(quiz.this);//答案錯誤
					correct_ans.setTitle("答對了");
					correct_ans.setMessage("恭喜你答對囉，趕快偷偷看一下你想知道的!!");
					correct_ans.show();
					describe.setText(R.string.descr);
					cln.updateScoreByGID(account, npc_index+1, 50);
					cln.updateFinGame(account, npc_index+1);
					send.setText("完成小活動");
				}
				else
				{
					Builder wrong_ans=new Builder(quiz.this);//答案錯誤
					wrong_ans.setTitle("Wrong Ans");
					wrong_ans.setMessage("Your Ans is Wrong.");
					wrong_ans.show();
				}
			}
			else
			{
				finish();
			}
		}
	};

}

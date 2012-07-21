package android.edu.intro.game;

import android.content.Intent;
import android.edu.intro.R;
import android.edu.intro.network.client;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class GameChoice extends Activity 
{
	protected static int res_cho[] = {R.array.cho01, R.array.cho02, R.array.cho03, 0,R.array.cho04, R.array.cho05, 
		R.array.cho06, R.array.cho07, 0,R.array.cho08};
	protected static int name_btn[] = {R.id.Choice1, R.id.Choice2, R.id.Choice3, R.id.Choice4};
	protected int npc_index;
	protected String spot_text;
	protected String question_text;
	protected String[] choice_text;
	protected String answer;
	protected String describe_text;
	protected TextView spot;
	protected TextView question;
	protected int max_choice = 4;
	protected TextView describe;
	protected RadioButton[] rb;
	protected RadioGroup rg;
	private Button btn;
	private String account;
	private Bundle next_bundle=new Bundle();
	private client cln=new client();
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_choice);
		
		Bundle bundle = getIntent().getExtras();
		npc_index = bundle.getInt("npc_index");
		account=bundle.getString("act");
		
		next_bundle=new Bundle();
		next_bundle.putString("act", account);
		next_bundle.putInt("npc_index", npc_index);
		btn=(Button)findViewById(R.id.finished);
		btn.setOnClickListener(finished);
		
		spot_text = getResources().getStringArray(R.array.spot)[npc_index];
		question_text = getResources().getStringArray(R.array.question)[npc_index];
		choice_text = getResources().getStringArray(res_cho[npc_index]);
		answer = getResources().getStringArray(R.array.answer)[npc_index];
		describe_text = getResources().getStringArray(R.array.describe)[npc_index];
		
		spot = (TextView)findViewById(R.id.Spot);
		spot.setText(spot_text);
		question = (TextView)findViewById(R.id.Question);
		question.setText(question_text);
		describe = (TextView)findViewById(R.id.Describe);
		
		rb = new RadioButton[max_choice];
		for(int i=0;i<rb.length;i++){
			rb[i] = (RadioButton)findViewById(name_btn[i]);
			if(i<choice_text.length)
				rb[i].setText(choice_text[i]);
			else
				rb[i].setVisibility(4);
		}
			
		rg =(RadioGroup)findViewById(R.id.CRadioGroup);
		rg.setOnCheckedChangeListener(changeradio);
		
	}
	private RadioGroup.OnCheckedChangeListener changeradio = new RadioGroup.OnCheckedChangeListener()
	{
		public void onCheckedChanged(RadioGroup group, int checkedId)
		{
			for(int i=0;i<choice_text.length;i++)
				if(checkedId == rb[i].getId())
					if(answer.equals(rb[i].getText())){
						Builder correct_ans=new Builder(GameChoice.this);//答案錯誤
						correct_ans.setTitle("答對了");
						correct_ans.setMessage("你答對囉，請慢慢閱讀簡介來了解我們學校");
						correct_ans.show();
						describe.setText(describe_text);
						//Toast.makeText(GameChoice.this,"Correct",Toast.LENGTH_SHORT).show();
					}
					else{
						Builder wrong_ans=new Builder(GameChoice.this);//答案錯誤
						wrong_ans.setTitle("Wrong Ans");
						wrong_ans.setMessage("Your Ans is Wrong.");
						wrong_ans.show();
					}
		}
	};
	
	private OnClickListener finished=new OnClickListener() 
	{
		public void onClick(View v) 
		{
			
			if(npc_index==0||npc_index==1||npc_index==2||npc_index==6||npc_index==9)
			{
				cln.updateScoreByGID(account, npc_index+1, 50);
				cln.updateFinGame(account, npc_index+1);
				finish();
			}
			else
			{
				if(npc_index==4)
				{
					Intent start=new Intent();
					start.setClass(GameChoice.this,guest.class);
      				start.putExtras(next_bundle);
      				startActivity(start);
      				finish();
				}
				if(npc_index==5)
				{
					Intent start=new Intent();
					start.setClass(GameChoice.this,sudoku.class);
      				start.putExtras(next_bundle);
      				startActivity(start);
				}
				if(npc_index==7)
				{
					Intent start=new Intent();
					start.setClass(GameChoice.this,Slots.class);
      				start.putExtras(next_bundle);
      				startActivity(start);
				}
			}
		}
	};
}

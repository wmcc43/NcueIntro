package android.edu.intro.game;

import android.app.Activity;
import android.edu.intro.R;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public abstract class game extends Activity 
{
	protected TextView ques;
	protected EditText ans;
	protected Button send;
	protected TextView describe; 
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
	}
	
	public void getView()
	{
		ques=(TextView)findViewById(R.id.enter_q);
		ans=(EditText)findViewById(R.id.enter_a);
		send=(Button)findViewById(R.id.enter_send);
		describe=(TextView)findViewById(R.id.describe);
	}
	
	abstract protected void setques();
	abstract protected void setButtonText();
}

package android.edu.intro.func;

import android.content.Intent;
import android.edu.intro.R;
import android.edu.intro.Map.MapStart;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class menu extends Activity 
{
	private Button manul_button;
	private TextView manul;
	private String account;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		getBundle();
		getView();
		setText();
		manul_button.setOnClickListener(listen);
	}

	private void getBundle() 
	{
		Bundle bundle=this.getIntent().getExtras();
		account=bundle.getString("act");
	}

	private void getView() 
	{
		manul=(TextView)findViewById(R.id.manul);
		manul_button=(Button)findViewById(R.id.manul_click);
	}
	
	private void setText() 
	{
		manul.setText(R.string.manul);
		manul_button.setText(R.string.manul_button);
	}
	
	private OnClickListener listen=new OnClickListener() 
	{
		public void onClick(View v) 
		{
			Bundle nextbundle=new Bundle();
			nextbundle.putString("act", account);
			Intent next=new Intent();
			next.putExtras(nextbundle);
			next.setClass(menu.this, MapStart.class);
			startActivity(next);
		}
	};
}

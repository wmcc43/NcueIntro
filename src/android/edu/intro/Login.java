package android.edu.intro;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.edu.intro.func.Reg;
import android.edu.intro.func.menu;
import android.edu.intro.network.*;

public class Login extends Activity 
{
	private EditText account;
    private EditText password;
    private Button reg;
    private Button login;
    private client cln;
    protected static final int MENU_ITEM_TEST=Menu.FIRST;
	/** Called when the activity is first created. */
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findview();
        setListener();
    }
	
	public boolean onCreateOptionsMenu(Menu menu)//menu鍵按下去的選單
	{
		menu.add(0, MENU_ITEM_TEST, 0, "about");
		menu.add(0, MENU_ITEM_TEST+1, 0, "exit");
		menu.add(0, MENU_ITEM_TEST+2, 0, "about_3");
		menu.add(0, MENU_ITEM_TEST+3, 0, "about_4");
		menu.add(0, MENU_ITEM_TEST+4, 0, "about_5");
		menu.add(0, MENU_ITEM_TEST+5, 0, "about_6");
		menu.add(0, MENU_ITEM_TEST+6, 0, "about_7");
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item)//menu選單按下去所要執行的程式碼
	{
		switch(item.getItemId())
		{
			case MENU_ITEM_TEST:
				Builder about=new Builder(this);
				about.setTitle("About");
				about.setMessage("It about Test menu");
				about.show();
				break;
			case MENU_ITEM_TEST+1:
				Builder about2=new Builder(this);
				about2.setTitle("Shutdown");
				about2.setMessage("The program is going to shutdown.");
				about2.show();
				finish();
				break;
			case MENU_ITEM_TEST+2:
				Builder about3=new Builder(this);
				about3.setTitle("About3");
				about3.setMessage("It about Test menu3");
				about3.show();
				break;
			case MENU_ITEM_TEST+3:
				Builder about4=new Builder(this);
				about4.setTitle("About4");
				about4.setMessage("It about Test menu4");
				about4.show();
				break;
			case MENU_ITEM_TEST+4:
				Builder about5=new Builder(this);
				about5.setTitle("About5");
				about5.setMessage("It about Test menu5");
				about5.show();
				break;
			case MENU_ITEM_TEST+5:
				Builder about6=new Builder(this);
				about6.setTitle("About6");
				about6.setMessage("It about Test menu6");
				about6.show();
				break;
			case MENU_ITEM_TEST+6:
				Builder about7=new Builder(this);
				about7.setTitle("About7");
				about7.setMessage("It about Test menu7");
				about7.show();
				break;
				
		}
		return true;
	}
    
    private void findview()//找出所有介面上要用到的的元件
    {
    	account=(EditText)findViewById(R.id.account);
    	password=(EditText)findViewById(R.id.password);
    	reg=(Button)findViewById(R.id.reg);
    	login=(Button)findViewById(R.id.login);
    }
    
    private void setListener()//幫按鈕加listener
    {
    	reg.setOnClickListener(btnlis);
    	login.setOnClickListener(btnlis);
    }
    
    private OnClickListener btnlis=new OnClickListener()//實作listener
    {
		public void onClick(View v)
		{
			if(v.getId()==reg.getId())
			{
				Intent regintent=new Intent();//意圖宣告
				regintent.setClass(Login.this, Reg.class);//設定下一個要執行的class
				startActivity(regintent);//跳轉Activity
			}
			if(v.getId()==login.getId())
			{
				cln=new client();//宣告client物件作連線用
				if(cln.login(account.getText().toString(),password.getText().toString()))//client物件回傳登入是否成功
				{
					Intent login_map=new Intent();
					Bundle act=new Bundle();
					act.putString("act", account.getText().toString());
					login_map.putExtras(act);
					login_map.setClass(Login.this,menu.class);
					startActivity(login_map);
				}
				else
				{
					Builder wrong_login=new Builder(Login.this);//登入錯誤訊息視窗
					wrong_login.setTitle("Wrong Connection");
					wrong_login.setMessage("Your connection fail.\n Please try again.");
					wrong_login.show();
				}
			}
		}
	};
}
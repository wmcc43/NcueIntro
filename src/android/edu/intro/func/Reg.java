package android.edu.intro.func;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog.OnDateSetListener;
import android.edu.intro.*;
import android.edu.intro.network.client;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class Reg extends Activity
{
	private DatePickerDialog datepickerdialog;
	private GregorianCalendar calendar=new GregorianCalendar();
	
	private EditText account;
	private EditText password;
	private EditText password_chk;
	private EditText email;
	private RadioButton male;
	private RadioButton female;
	private TextView birth;
	private Button choosedate;
	private Button clear;
	private Button submit;
	private client cln;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_table);
        findview();
        setdatepicker();
        setListener();
	}
	

	public void findview()
	{
		account=(EditText)findViewById(R.id.reg_account);
		password=(EditText)findViewById(R.id.reg_password);
		password_chk=(EditText)findViewById(R.id.reg_passwordchk);
		email=(EditText)findViewById(R.id.reg_email);
		male=(RadioButton)findViewById(R.id.reg_male);
		female=(RadioButton)findViewById(R.id.reg_female);
		birth=(TextView)findViewById(R.id.reg_birth);
		choosedate=(Button)findViewById(R.id.choosedate);
		clear=(Button)findViewById(R.id.clear_all);
		submit=(Button)findViewById(R.id.submit);
	}
	
	public void setdatepicker()
	{
		datepickerdialog=new DatePickerDialog(this, new OnDateSetListener()
		{
			
			public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth)
			{
				birth.setText(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);
			}
		},calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
	}
	
	private void setListener()
	{
		choosedate.setOnClickListener(listener);
		clear.setOnClickListener(listener);
		submit.setOnClickListener(listener);
	}
	
	public OnClickListener listener=new OnClickListener()
	{
		
		public void onClick(View v)
		{
			if(v.getId()==choosedate.getId())
			{
				datepickerdialog.show();
			}
			if(v.getId()==clear.getId())
			{
				account.setText("");
				password.setText("");
				password_chk.setText("");
				male.setChecked(false);
				female.setChecked(false);
				email.setText("");
				birth.setText("Your BirthDay");
			}
			if(v.getId()==submit.getId())
			{
				if(account.getText().toString().equals(""))
				{
					Builder wrong_account=new Builder(Reg.this);
					wrong_account.setTitle("Account is empty");
					wrong_account.setMessage("Account is empty.\nPlease input your account");
					wrong_account.show();
				}
				else if(!password.getText().toString().equals(password_chk.getText().toString())||password.getText().toString().equals(""))
				{
					Builder wrong_password=new Builder(Reg.this);
					wrong_password.setTitle("Password_not match");
					wrong_password.setMessage("The Password is not the same or empty.\nPlease input again");
					wrong_password.show();
					password.setText("");
					password_chk.setText("");
				}
				else if(email.getText().toString().equals(""))
				{
					Builder wrong_mail=new Builder(Reg.this);
					wrong_mail.setTitle("E-Mail is empty");
					wrong_mail.setMessage("E-Mail is empty.\nPlease input your E-Mail");
					wrong_mail.show();
				}
				else if(!(male.isChecked()||female.isChecked()))
				{
					Builder wrong_sex=new Builder(Reg.this);
					wrong_sex.setTitle("Sex is wrong");
					wrong_sex.setMessage("You didn't choose your sex.\nPlease choose it.");
					wrong_sex.show();
				}
				else if(birth.getText().toString().equals("Your BirthDay"))
				{
					Builder wrong_birth=new Builder(Reg.this);
					wrong_birth.setTitle("Wrong birthday");
					wrong_birth.setMessage("You didn't choose your BirthDay.\nPlease choose it.");
					wrong_birth.show();
				}
				else
				{
					String sex;
					if(male.isChecked())
						sex="M";
					else
						sex="F";
					cln=new client();
					if(cln.register(account.getText().toString(), password.getText().toString(), email.getText().toString(), sex, birth.getText().toString()))
					{
						Builder reg_success=new Builder(Reg.this);
						reg_success.setTitle("Regist successful!!");
						reg_success.setMessage("Regist successful\nCongratulation!!\nNow you can Login");
						reg_success.show();
						finish();
					}
					else
					{
						Builder reg_fail=new Builder(Reg.this);
						reg_fail.setTitle("Regist Fail!!");
						reg_fail.setMessage("Regist fail\nThat's awfal!!\nPlease check you network connetction is OK");
						reg_fail.show();
					}
				}
			}
		}
	};
}

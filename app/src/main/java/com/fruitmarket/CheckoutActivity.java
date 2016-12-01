package com.fruitmarket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.fruitmarket.R.id;

import fragments.MyCart;
import util.Common;
import util.ConnectionDetector;
import Config.ConstValue;
import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class CheckoutActivity extends ActionBarActivity {
	public SharedPreferences settings;
	public ConnectionDetector cd;
	EditText txtCity,txtName,txtAddress,txtZipcode,txtEmail, txtPhone;
	Button btnSave;
	ProgressDialog dialog;
	Common common;

	@SuppressLint("CutPasteId") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_checkout);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		 common = new Common();
	        
		 
		 settings = getSharedPreferences(ConstValue.MAIN_PREF, 0);
			cd=new ConnectionDetector(this);

			txtCity = (EditText)findViewById(R.id.editCity);
			
			txtName = (EditText)findViewById(R.id.editFname);
			txtPhone = (EditText)findViewById(R.id.editPhone);
			txtAddress = (EditText)findViewById(R.id.editAddress);
			txtZipcode = (EditText)findViewById(R.id.editZipcode);
			txtEmail = (EditText)findViewById(R.id.editEmail);
			

	        txtName.setText(settings.getString("user_name", ""));

	        txtEmail.setText(settings.getString("user_email", ""));
	        txtPhone.setText(settings.getString("user_mobile", ""));
	    
	        txtAddress.setText(settings.getString("user_address", ""));
			
	    
	        txtZipcode.setText(settings.getString("user_zipcode", ""));
	        
	      
	        txtCity.setText(settings.getString("user_city", ""));
	        
	        btnSave = (Button)findViewById(id.btnsave);
	        btnSave.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(txtCity.getText().toString().length()==0)
					{
						txtCity.setError("Enter City");
					}
					if(txtEmail.getText().toString().length()==0)
					{
						txtEmail.setError("Enter Email");
					}
					else if (txtName.getText().toString().length()==0) {
						txtName.setError("Enter Name");
					}
					else if (txtAddress.getText().toString().length()==0) {
						txtAddress.setError("Enter Address");
					}
					
					else if (txtZipcode.getText().toString().length()==0) {
						txtZipcode.setError("Enter Zipcode");
					}
					else{
						
					
						settings.edit().putString("order_name", txtName.getText().toString()).commit();
						settings.edit().putString("order_city", txtCity.getText().toString()).commit();
						settings.edit().putString("order_email", txtEmail.getText().toString()).commit();
						settings.edit().putString("order_zipcode", txtZipcode.getText().toString()).commit();
						settings.edit().putString("order_address", txtAddress.getText().toString()).commit();
						
						settings.edit().putString("order_phone", txtPhone.getText().toString()).commit();
						
						Intent intent = new Intent(CheckoutActivity.this,PlaceorderActivity.class);
						
						startActivity(intent);
						}
					
				}
			});
	        
	      
	    
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.checkout, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}else if(id == android.R.id.home){
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
}

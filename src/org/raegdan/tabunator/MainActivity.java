package org.raegdan.tabunator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputFilter.LengthFilter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;

public class MainActivity extends ActivityEx implements OnClickListener, OnCheckedChangeListener {

	Button btnProceed;
	CheckBox cbDontShow;
	SharedPreferences sp;
	String ShareText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	    
		btnProceed = (Button) findViewById(R.id.btnProceed);
		cbDontShow = (CheckBox) findViewById(R.id.cbDontShow);
		
		btnProceed.setOnClickListener(this);
		cbDontShow.setOnCheckedChangeListener(this);
		
		Intent intent = getIntent();
	    ShareText = intent.getStringExtra(Intent.EXTRA_TEXT);
	    if (ShareText.trim().equalsIgnoreCase(""))
	    {
	    	Toast.makeText(this, getString(R.string.nothing_to_share), Toast.LENGTH_LONG).show();
	    	finish();
	    }
	    
	    sp = this.getSharedPreferences(this.getPackageName(), MODE_PRIVATE);
	    if (sp.getBoolean("dont_show", false))
	    {
	    	Go();
	    }
	}
	
	@SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	protected void Go()
	{
		if (cbDontShow.isChecked())
		{
			Editor ed = sp.edit();
			ed.putBoolean("dont_show", true);
			ed.commit();
		}
		
		if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB)
		{
		    android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(this.CLIPBOARD_SERVICE);
		    clipboard.setText(ShareText);
		} else {
		    android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(this.CLIPBOARD_SERVICE); 
		    android.content.ClipData clip = android.content.ClipData.newPlainText("", ShareText);
		    clipboard.setPrimaryClip(clip);
		}
		
    	Toast.makeText(this, getString(R.string.copied_to_cb), Toast.LENGTH_LONG).show();
		
    	startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://tabun.everypony.ru/topic/add")));
    	
    	finish();
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		Editor ed = sp.edit();
		ed.putBoolean("dont_show", isChecked);
		ed.commit();
	}

	@Override
	public void onClick(View v) {
		Go();
	}

}

package org.raegdan.tabunator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;

public class ActivityEx extends Activity {
	@SuppressLint("InlinedApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    	//Apply theme if supported
    	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
    	{
    		setTheme(android.R.style.Theme_Holo_NoActionBar);
    	}
    }
}

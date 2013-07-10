package com.marakana.yamba;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StatusActivity extends Activity {

	Button mButtonTweet;
	EditText mTextStatus;
	TextView mTextCount;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.status);

		Log.d("Yamba", "StatusActivity onCreate");

		// get a reference to the widgets
		mButtonTweet = (Button) findViewById(R.id.status_button_tweet);
		mTextStatus = (EditText) findViewById(R.id.status_text);
		mTextCount = (TextView) findViewById(R.id.status_text_count);

		// listner for the click on Twitt button
		mButtonTweet.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText mTextStatus = (EditText) findViewById(R.id.status_text);
				Log.d("Yamba", "Click on Tweet, we post:"
						+ mTextStatus.getText().toString());
				
				//TODO post this on twitter
			}
		});
		
		mTextCount.setText(Integer.toString(140 - mTextStatus.getText().length()));
		
		// watch for the number of inserted characters
		TextWatcher watcher = new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			@Override
			public void afterTextChanged(Editable s) {
				int count = 140 - s.length();
				mTextCount.setText(Integer.toString(count));
				Log.d("Yamba", "Chars left:" + count );
				
				// if we have 50 chars left we change the colour to RED
				if (count<50)
					mTextCount.setTextColor(Color.RED);
				else
					mTextCount.setTextColor(Color.BLACK);
				
			}
		};
		mTextStatus.addTextChangedListener(watcher);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.status, menu);
		return true;
	}

}

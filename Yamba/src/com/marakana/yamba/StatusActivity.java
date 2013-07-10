package com.marakana.yamba;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StatusActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.status);

		Log.d("Yamba", "StatusActivity onCreate");

		// get a reference to the widgets
		Button mButtonTweet = (Button) findViewById(R.id.status_button_tweet);
		EditText mTextStatus = (EditText) findViewById(R.id.status_text);
		TextView mTextCount = (TextView) findViewById(R.id.status_text_count);

		// listner for the click on Twitt button
		mButtonTweet.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText mTextStatus = (EditText) findViewById(R.id.status_text);
				Log.d("Yamba", "Click on Tweet, we post:"
						+ mTextStatus.getText().toString());

			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.status, menu);
		return true;
	}

}

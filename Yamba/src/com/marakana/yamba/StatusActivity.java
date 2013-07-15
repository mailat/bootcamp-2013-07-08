package com.marakana.yamba;

import com.marakana.android.yamba.clientlib.YambaClient;

import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StatusActivity extends Activity implements OnSharedPreferenceChangeListener {

	Button mButtonTweet;
	EditText mTextStatus;
	TextView mTextCount;
	SharedPreferences prefs;
	YambaClient cloud = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.status);
		
		Log.d("Yamba", "StatusActivity onCreate");

		//register on shared preferences changes
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		prefs.registerOnSharedPreferenceChangeListener(this);
		
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
				
				//post entered text on twitter
				PostTask postTask = new PostTask();
				postTask.execute(mTextStatus.getText().toString());
				
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
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//action_settings
		switch ( item.getItemId() )
		{
			case R.id.action_settings :
				startActivity(new Intent(this, PrefsActivity.class));
				break;
		}
		
		return true;
	}

	// our thread safe AsyncTask poster
	class PostTask extends AsyncTask<String, Integer, String>
	{
		private ProgressDialog progress;

		@Override
		protected String doInBackground(String... params) {
			//post entered text on twitter
			
			try {
				getYambaClient().postStatus(params[0]);
				Log.d("Yamba", "Post text was ok." );
				return ("We just posted in the cloud.");
			} catch (Throwable e) {
				Log.d("Yamba", "Post text was NOT OK." );
				e.printStackTrace();
				return ("Failed to post in the cloud.");
			}
		}

		@Override
		protected void onPostExecute(String result) {
			//display notificatio about the posting
			progress.dismiss();
			Log.d("Yamba", "Show the text." );
			Toast.makeText(StatusActivity.this, result, Toast.LENGTH_LONG).show();
		}

		@Override
		protected void onPreExecute() {
			progress = ProgressDialog.show(StatusActivity.this, "Posting to yamba server", "Please wait ....");
		}
		
	}
	
	private YambaClient getYambaClient()
	{
		//check if we already initialized the YambaClient object
		if (cloud == null)
		{
			//use the preference values for username, password, etc
			String username, password, apiRoot;
			username = prefs.getString("username", "student");
			password = prefs.getString("password", "password");
			apiRoot = prefs.getString("apiRoot", "");
			
			
			//create an instance because it is null
			cloud = new YambaClient(username, password);
		}
		
		return(cloud);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		//back from preference activity, it is possible that the credentials are changed
		//TODO
	}

}

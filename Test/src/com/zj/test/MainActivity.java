package com.zj.test;

import java.net.URLEncoder;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.preference.PreferenceActivity.Header;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	int i=0;
	TextView tv_info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_info=(TextView) findViewById(R.id.tv_infor);
    }
    
    public void click(View view)
    {
    	i++;
    	AsyncHttpClient client = new AsyncHttpClient();

        String data = "userName="+i;
        
        client.get("http://10.0.2.2/MyProject/Query?" + data, new MyResponseHandler());
    }
    
    class MyResponseHandler extends AsyncHttpResponseHandler {

	

		@Override
		public void onSuccess(int statusCode, org.apache.http.Header[] headers,
				byte[] responseBody) {
			// TODO Auto-generated method stub
			tv_info.setText(new String(responseBody));
			Toast.makeText(MainActivity.this, 
					"³É¹¦: statusCode: " + statusCode + ", body: " + new String(responseBody), 
					0).show();
			
		}

		@Override
		public void onFailure(int statusCode, org.apache.http.Header[] headers,
				byte[] responseBody, Throwable error) {
			// TODO Auto-generated method stub
			Toast.makeText(MainActivity.this, "Ê§°Ü: statusCode: " + statusCode, 0).show();
		}
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
        }
        return super.onOptionsItemSelected(item);
    }
}

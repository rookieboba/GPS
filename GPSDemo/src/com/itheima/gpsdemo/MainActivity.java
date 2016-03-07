package com.itheima.gpsdemo;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;


import android.app.Activity;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	// �õ�λ�÷���
	private LocationManager lm;
	private MyLocationListener listener;
	TextView tv_info;
	String information;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lm = (LocationManager) getSystemService(LOCATION_SERVICE);
		tv_info=(TextView) findViewById(R.id.tv_info);
		
//		List<String> provider = lm.getAllProviders();
//		for(String l: provider){
//			System.out.println(l);
//		}
		listener = new MyLocationListener();
		//ע�����λ�÷���
		//��λ���ṩ����������
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);

//���ò���ϸ����
//criteria.setAccuracy(Criteria.ACCURACY_FINE);//����Ϊ��󾫶� 
//criteria.setAltitudeRequired(false);//��Ҫ�󺣰���Ϣ 
//criteria.setBearingRequired(false);//��Ҫ��λ��Ϣ 
//criteria.setCostAllowed(true);//�Ƿ������� 
//criteria.setPowerRequirement(Criteria.POWER_LOW);//�Ե�����Ҫ�� 

		String proveder= lm.getBestProvider(criteria, true);
		lm.requestLocationUpdates(proveder, 0, 0, listener);
		
	}
	
	 public void click(View view)
	    {
	    	
	    	AsyncHttpClient client = new AsyncHttpClient();

	        String data = "userName="+information;
	        
	        client.get("http://10.0.2.2/MyProject/Show?" + data, new MyResponseHandler());
	    }
	 
	 class MyResponseHandler extends AsyncHttpResponseHandler {

			

			@Override
			public void onSuccess(int statusCode, org.apache.http.Header[] headers,
					byte[] responseBody) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, 
						"�ɹ�: statusCode: " + statusCode + ", body: " + new String(responseBody), 
						0).show();
				
			}

			@Override
			public void onFailure(int statusCode, org.apache.http.Header[] headers,
					byte[] responseBody, Throwable error) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "ʧ��: statusCode: " + statusCode, 0).show();
			}
	    }



	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// ȡ������λ�÷���
		lm.removeUpdates(listener);
		listener = null;

	}

	class MyLocationListener implements LocationListener {

		/**
		 * ��λ�øı��ʱ��ص�
		 */

		@Override
		public void onLocationChanged(Location location) {
			String longitude = "longitude=" + location.getLongitude();
			String latitude = "latitude=" + location.getLatitude();
			String accuracy = "accuracy=" + location.getAccuracy();
			//TextView textview = new TextView(MainActivity.this);
			tv_info.setText(longitude + "\n" + latitude + "\n" + accuracy);
            information=longitude + latitude ;
			//setContentView(textview);

		}

		/**
		 * ��״̬�����ı��ʱ��ص� ����--�ر� ���ر�--����
		 */
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}

		/**
		 * ĳһ��λ���ṩ�߿���ʹ����
		 */
		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}

		/**
		 * ĳһ��λ���ṩ�߲�����ʹ����
		 */
		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub

		}

	}

}

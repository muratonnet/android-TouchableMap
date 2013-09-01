package com.muratonnet.map;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.muratonnet.map.TouchableWrapper.TouchActionDown;
import com.muratonnet.map.TouchableWrapper.TouchActionUp;

public class MainActivity extends FragmentActivity implements TouchActionDown,
		TouchActionUp {

	CameraPosition mDownCameraPosition;
	CameraPosition mUpCameraPosition;

	TextView mDownCenterLat;
	TextView mDownCenterLong;
	TextView mDownZoom;
	TextView mUpCenterLat;
	TextView mUpCenterLong;
	TextView mUpZoom;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);

		// get data views
		mDownCenterLat = (TextView) findViewById(R.id.down_center_lat);
		mDownCenterLong = (TextView) findViewById(R.id.down_center_long);
		mDownZoom = (TextView) findViewById(R.id.down_zoom);
		mUpCenterLat = (TextView) findViewById(R.id.up_center_lat);
		mUpCenterLong = (TextView) findViewById(R.id.up_center_long);
		mUpZoom = (TextView) findViewById(R.id.up_zoom);

	}

	@Override
	protected void onResume() {
		super.onResume();

		// check google play services
		int isAvailable = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(this);
		if (isAvailable != ConnectionResult.SUCCESS) {
			GooglePlayServicesUtil.getErrorDialog(isAvailable, this, 1).show();
		}

	}

	@Override
	public void onTouchDown(MotionEvent event) {
		mDownCameraPosition = getMap().getMap().getCameraPosition();
	}

	@Override
	public void onTouchUp(MotionEvent event) {
		mUpCameraPosition = getMap().getMap().getCameraPosition();
		updateCameraPositionData();
	}

	private void updateCameraPositionData() {
		mDownCenterLat.setText(String
				.valueOf(mDownCameraPosition.target.latitude));
		mDownCenterLong.setText(String
				.valueOf(mDownCameraPosition.target.longitude));
		mDownZoom.setText(String.valueOf(mDownCameraPosition.zoom));
		mUpCenterLat.setText(String.valueOf(mUpCameraPosition.target.latitude));
		mUpCenterLong.setText(String
				.valueOf(mUpCameraPosition.target.longitude));
		mUpZoom.setText(String.valueOf(mUpCameraPosition.zoom));

	}

	private SupportMapFragment getMap() {
		return ((SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map));
	}

}

package com.logon.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.gallery.R;
import com.gallery.activity.MyGalleryActivity;
import com.sqlite.db.mSqlite;

public class logonActivity extends Activity {

	private mSqlite m_HospitalSqlite;

	private Button logonButton;
	private Button closeButton;

	private Intent mainIntent;

	private String userIdNumString;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.logon);
		// 初始化人员id
		userIdNumString = new String("0");

		// 初始化数据库
		m_HospitalSqlite = new mSqlite(this);

		// 初始化按钮
		logonButton = (Button) findViewById(R.id.logon_button);
		closeButton = (Button) findViewById(R.id.close_button);

		// 初始化意图
		mainIntent = new Intent();

		mainIntent.setComponent(new ComponentName(logonActivity.this,
				MyGalleryActivity.class));

		// 按钮事件
		logonButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String logonNameString = "";
				logonNameString = ((EditText) findViewById(R.id.logonName_edit))
						.getText().toString().trim();

				String logonPassString = "";
				logonPassString = ((EditText) findViewById(R.id.logonPass_edit))
						.getText().toString().trim();

				boolean logonFlag = false;
				logonFlag = m_HospitalSqlite.logonJudge(logonNameString,
						logonPassString);

				if (true == logonFlag) {
					((EditText) findViewById(R.id.logonName_edit)).setText("");
					((EditText) findViewById(R.id.logonPass_edit)).setText("");

					userIdNumString = m_HospitalSqlite.getUserId(
							logonNameString, logonPassString);

					mainIntent.putExtra("userIdNumString", userIdNumString);
					startActivity(mainIntent);
					finish();
				} else {
					Toast.makeText(getApplicationContext(), "用户名或密码错误.",
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		closeButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (m_HospitalSqlite != null) {
			m_HospitalSqlite.close();
			m_HospitalSqlite = null;
		}
	}
}
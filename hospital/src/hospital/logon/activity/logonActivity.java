package hospital.logon.activity;

import hospital.admin.activity.adminActivity;
import hospital.doctor.activity.doctorActivity;
import hospital.nurse.activity.nurseActivity;
import hospital.patient.activity.patientActivity;
import hospital.patient.namespace.R;
import hospital.sqlite.db.HospitalSqlite;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class logonActivity extends Activity {
	
	private HospitalSqlite m_HospitalSqlite;
	
	private Button logonButton;
	private Button closeButton;

	private Intent adminIntent;
	private Intent doctorIntent;
	private Intent nurseIntent;
	private Intent patientIntent;
	
	private String userIdNumString;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		//初始化人员id
		userIdNumString = new String("0");

		// 初始化数据库
		m_HospitalSqlite = new HospitalSqlite(this);

		// 初始化按钮
		logonButton = (Button) findViewById(R.id.logon_button);
		closeButton = (Button) findViewById(R.id.close_button);

		// 初始化意图
		adminIntent = new Intent();
		doctorIntent = new Intent();
		nurseIntent = new Intent();
		patientIntent = new Intent();

		adminIntent.setComponent(new ComponentName(logonActivity.this,
				adminActivity.class));
		doctorIntent.setComponent(new ComponentName(logonActivity.this,
				doctorActivity.class));
		nurseIntent.setComponent(new ComponentName(logonActivity.this,
				nurseActivity.class));
		patientIntent.setComponent(new ComponentName(logonActivity.this,
				patientActivity.class));

		// 按钮事件
		logonButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				String logonNameString = "";
				logonNameString = ((EditText) findViewById(R.id.logonName_edit))
						.getText().toString().trim();

				String logonPassString = "";
				logonPassString = ((EditText) findViewById(R.id.logonPass_edit))
						.getText().toString().trim();

				String logonRoleString = "";
				logonRoleString = ((Spinner) findViewById(R.id.logon_spinner))
						.getSelectedItemPosition() + "";

				boolean logonFlag = false;
				logonFlag = m_HospitalSqlite.logonJudge(logonNameString,
						logonPassString, logonRoleString);

				if (true == logonFlag) {
					((EditText) findViewById(R.id.logonName_edit)).setText("");
					((EditText) findViewById(R.id.logonPass_edit)).setText("");
					
					userIdNumString = m_HospitalSqlite.getUserId(logonNameString, logonPassString, logonRoleString);

					switch (((Spinner) findViewById(R.id.logon_spinner))
							.getSelectedItemPosition()) {
					case 0:
						doctorIntent.putExtra("userIdNumString", userIdNumString);  
						startActivity(doctorIntent);
						break;
					case 1:
						nurseIntent.putExtra("userIdNumString", userIdNumString);  
						startActivity(nurseIntent);
						break;
					case 2:
						patientIntent.putExtra("userIdNumString", userIdNumString);  
						startActivity(patientIntent);
						break;
					case 3:
						adminIntent.putExtra("userIdNumString", userIdNumString);  
						startActivity(adminIntent);
						break;
					default:
						break;
					}
					finish();
				} else {
					Toast.makeText(getApplicationContext(), "用户名或密码错误.",
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		closeButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				finish();
			}
		});

		// 检查sd卡状态
		String status = Environment.getExternalStorageState();
		if (status.equals(Environment.MEDIA_MOUNTED)) {
			// 创建头像文件夹
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
					.detectDiskReads().detectDiskWrites().detectNetwork()
					.penaltyLog().build());
			StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
					.detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath()
					.build());

			File destDir = new File(getResources()
					.getString(R.string.avatarDir));
			if (!destDir.exists()) {
				destDir.mkdirs();
				Toast.makeText(getApplicationContext(), destDir.getPath(),
						Toast.LENGTH_SHORT).show();
			}
			destDir = new File(getResources().getString(
					R.string.avatarDoctorDir));
			if (!destDir.exists()) {
				destDir.mkdirs();
			}
			destDir = new File(getResources()
					.getString(R.string.avatarNurseDir));
			if (!destDir.exists()) {
				destDir.mkdirs();
			}
			destDir = new File(getResources().getString(
					R.string.avatarPatientDir));
			if (!destDir.exists()) {
				destDir.mkdirs();
			}
		} else {
			AlertDialog.Builder sdcardAlertDialog = new AlertDialog.Builder(
					logonActivity.this);
			sdcardAlertDialog
					.setTitle("sd卡")
					.setMessage("没有sd卡将无法运行.")
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									finish();
								}
							}).show();
		}

		// 复制默认头像
		try {
			copyBigDataToSD(getResources().getString(R.string.avatarDefaultPNG));
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void copyBigDataToSD(String strOutFileName) throws IOException {
		InputStream myInput;
		OutputStream myOutput = new FileOutputStream(strOutFileName);
		myInput = this.getAssets().open("avatar.png");
		byte[] buffer = new byte[1024];
		int length = myInput.read(buffer);
		while (length > 0) {
			myOutput.write(buffer, 0, length);
			length = myInput.read(buffer);
		}

		myOutput.flush();
		myInput.close();
		myOutput.close();
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
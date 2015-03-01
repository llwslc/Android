package hospital.patient.activity;

import hospital.patient.namespace.R;
import hospital.sqlite.db.HospitalSqlite;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class patientActivity extends Activity {

	private HospitalSqlite m_HospitalSqlite;

	private String userIdNumString;

	private Button doctorButton;
	private Button passwordButton;
	private Button caseButton;
	private Button RFIDButton;

	private Intent doctorIntent;
	private Intent caseIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.patient);

		// 初始化人员id
		userIdNumString = (String) this.getIntent().getSerializableExtra(
				"userIdNumString");

		// 初始化数据库
		m_HospitalSqlite = new HospitalSqlite(this);

		// 初始化按钮
		doctorButton = (Button) findViewById(R.id.patientDoctor_button);
		passwordButton = (Button) findViewById(R.id.patientPassword_button);
		caseButton = (Button) findViewById(R.id.patientCase_button);
		RFIDButton = (Button) findViewById(R.id.RFID_button);

		// 初始化意图
		doctorIntent = new Intent();
		caseIntent = new Intent();

		doctorIntent.setComponent(new ComponentName(patientActivity.this,
				patientDoctorActivity.class));
		caseIntent.setComponent(new ComponentName(patientActivity.this,
				patientCaseActivity.class));

		doctorButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				doctorIntent.putExtra("userIdNumString", userIdNumString);
				startActivity(doctorIntent);				
			}
		});

		caseButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				caseIntent.putExtra("userIdNumString", userIdNumString);
				startActivity(caseIntent);
			}
		});

		passwordButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				final LayoutInflater passwoedInflater = getLayoutInflater();
				final View passwordLayout = passwoedInflater.inflate(
						R.layout.password, null);
				final Button passwordOkButton = (Button) passwordLayout
						.findViewById(R.id.passwordOk_button);
				final Button passwordCancelButton = (Button) passwordLayout
						.findViewById(R.id.passwordCancel_button);
				final AlertDialog.Builder passwordBuilder = new AlertDialog.Builder(
						patientActivity.this).setTitle("设置密码 : ").setView(
						passwordLayout);
				final Dialog passwordDialog = passwordBuilder.show();

				passwordOkButton.setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						// TODO Auto-generated method stub
						boolean successFlag = false;
						String passwordOneString = ((EditText) passwordLayout
								.findViewById(R.id.passwordOne_edit)).getText()
								.toString().trim();
						String passwordTwoString = ((EditText) passwordLayout
								.findViewById(R.id.passwordTwo_edit)).getText()
								.toString().trim();

						if (true == passwordOneString.equals(passwordTwoString)) {
							successFlag = m_HospitalSqlite.modPassword(
									userIdNumString, passwordOneString);
							if (true == successFlag) {
								Toast.makeText(getApplicationContext(), "修改成功",
										Toast.LENGTH_SHORT).show();
								passwordDialog.dismiss();
							} else {
								Toast.makeText(getApplicationContext(), "修改失败",
										Toast.LENGTH_SHORT).show();
								passwordDialog.dismiss();
							}
						} else {
							Toast.makeText(getApplicationContext(), "两次输入不一致",
									Toast.LENGTH_SHORT).show();
						}
					}
				});

				passwordCancelButton.setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						// TODO Auto-generated method stub
						passwordDialog.dismiss();
					}
				});
			}
		});

		RFIDButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "请插入 RFID 设备",
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (m_HospitalSqlite != null) {
			m_HospitalSqlite.close();
			m_HospitalSqlite = null;
		}
	}

}

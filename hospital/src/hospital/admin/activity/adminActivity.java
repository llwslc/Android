package hospital.admin.activity;

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

public class adminActivity extends Activity {

	private HospitalSqlite m_HospitalSqlite;

	private String userIdNumString;

	private Button userButton;
	private Button departmentButton;
	private Button drugButton;
	private Button passwordButton;
	private Button caseButton;
	private Button RFIDButton;

	private Intent userManageIntent;
	private Intent departmentManageIntent;
	private Intent drugManageIntent;
	private Intent caseManageIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin);

		// ��ʼ����Աid
		userIdNumString = (String) this.getIntent().getSerializableExtra(
				"userIdNumString");

		// ��ʼ�����ݿ�
		m_HospitalSqlite = new HospitalSqlite(this);

		// ��ʼ����ť
		userButton = (Button) findViewById(R.id.adminUser_button);
		departmentButton = (Button) findViewById(R.id.adminDepartment_button);
		drugButton = (Button) findViewById(R.id.adminDrug_button);
		passwordButton = (Button) findViewById(R.id.adminPassword_button);
		caseButton = (Button)findViewById(R.id.adminCase_button);
		RFIDButton = (Button)findViewById(R.id.RFID_button);

		// ��ʼ����ͼ
		userManageIntent = new Intent();
		departmentManageIntent = new Intent();
		drugManageIntent = new Intent();
		caseManageIntent = new Intent();

		userManageIntent.setComponent(new ComponentName(adminActivity.this,
				userManageActivity.class));
		departmentManageIntent.setComponent(new ComponentName(
				adminActivity.this, departmentManageActivity.class));
		drugManageIntent.setComponent(new ComponentName(adminActivity.this,
				drugManageActivity.class));
		caseManageIntent.setComponent(new ComponentName(adminActivity.this,
				caseManageActivity.class));

		userButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(userManageIntent);
			}
		});

		departmentButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(departmentManageIntent);
			}
		});

		drugButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(drugManageIntent);
			}
		});
		
		caseButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(caseManageIntent);
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
						adminActivity.this).setTitle("�������� : ").setView(
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
								Toast.makeText(getApplicationContext(), "�޸ĳɹ�",
										Toast.LENGTH_SHORT).show();
								passwordDialog.dismiss();
							} else {
								Toast.makeText(getApplicationContext(), "�޸�ʧ��",
										Toast.LENGTH_SHORT).show();
								passwordDialog.dismiss();
							}
						} else {
							Toast.makeText(getApplicationContext(), "�������벻һ��",
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
				Toast.makeText(getApplicationContext(), "����� RFID �豸",
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

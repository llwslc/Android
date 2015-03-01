package hospital.admin.activity;

import hospital.patient.namespace.R;
import hospital.sqlite.db.HospitalSqlite;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class userManageActivity extends Activity {

	private HospitalSqlite m_HospitalSqlite;
	private Spinner userManageSpinner;
	private ImageView avatarImageView;
	private Intent avatarIntent;
	private File avatarFile;
	private String userIdNumString;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.usermanage);
		// ��ʼ�����ݿ�
		m_HospitalSqlite = new HospitalSqlite(this);

		// ��ʼ�������б�
		initDepartmentSpinner();

		// ��ʼ����ԱȨ���б�
		userManageSpinner = (Spinner) findViewById(R.id.userManage_spinner);

		// ��ԱȨ���б��¼�
		userManageSpinner
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						if (3 == userManageSpinner.getSelectedItemPosition()) {
							((ImageView) findViewById(R.id.avatarPic_view))
									.setVisibility(View.INVISIBLE);
							((TextView) findViewById(R.id.userName_text))
									.setVisibility(View.INVISIBLE);
							((EditText) findViewById(R.id.userName_edit))
									.setVisibility(View.INVISIBLE);
							((Spinner) findViewById(R.id.userSex_spinner))
									.setVisibility(View.INVISIBLE);
							((TextView) findViewById(R.id.userIdNum_text))
									.setVisibility(View.INVISIBLE);
							((EditText) findViewById(R.id.userIdNum_edit))
									.setVisibility(View.INVISIBLE);
							((TextView) findViewById(R.id.userPhone_text))
									.setVisibility(View.INVISIBLE);
							((EditText) findViewById(R.id.userPhone_edit))
									.setVisibility(View.INVISIBLE);
							((Spinner) findViewById(R.id.userDepartment_spinner))
									.setVisibility(View.INVISIBLE);
							((TextView) findViewById(R.id.userDes_text))
									.setVisibility(View.INVISIBLE);
							((EditText) findViewById(R.id.userDes_edit))
									.setVisibility(View.INVISIBLE);
						} else {
							((ImageView) findViewById(R.id.avatarPic_view))
									.setVisibility(View.VISIBLE);
							((TextView) findViewById(R.id.userName_text))
									.setVisibility(View.VISIBLE);
							((EditText) findViewById(R.id.userName_edit))
									.setVisibility(View.VISIBLE);
							((Spinner) findViewById(R.id.userSex_spinner))
									.setVisibility(View.VISIBLE);
							((TextView) findViewById(R.id.userIdNum_text))
									.setVisibility(View.VISIBLE);
							((EditText) findViewById(R.id.userIdNum_edit))
									.setVisibility(View.VISIBLE);
							((TextView) findViewById(R.id.userPhone_text))
									.setVisibility(View.VISIBLE);
							((EditText) findViewById(R.id.userPhone_edit))
									.setVisibility(View.VISIBLE);
							((Spinner) findViewById(R.id.userDepartment_spinner))
									.setVisibility(View.VISIBLE);
							((TextView) findViewById(R.id.userDes_text))
									.setVisibility(View.VISIBLE);
							((EditText) findViewById(R.id.userDes_edit))
									.setVisibility(View.VISIBLE);
						}
					}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
					}
				});

		// ��ʼ����ͼ
		avatarIntent = new Intent();
		avatarIntent.setType("image/*");
		avatarIntent.putExtra("crop", "true");
		avatarIntent.putExtra("aspectX", 1);
		avatarIntent.putExtra("aspectY", 1);
		avatarIntent.putExtra("outputX", 200);
		avatarIntent.putExtra("outputY", 200);
		avatarIntent.putExtra("noFaceDetection", true);
		avatarIntent.putExtra("scale", true);
		avatarIntent.setAction(Intent.ACTION_GET_CONTENT);

		// ��ʼ��ͷ��ť
		avatarImageView = ((ImageView) findViewById(R.id.avatarPic_view));

		// ��ʼ��ͷ��·��
		avatarFile = new File(getResources().getString(
				R.string.avatarDefaultPNG));

		// ͷ��ť�¼�
		avatarImageView.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				avatarFile = new File(getResources().getString(
						R.string.avatarTempJPEG));
				avatarIntent.putExtra("output", Uri.fromFile(avatarFile));
				avatarIntent.putExtra("outputFormat", "JPEG");

				startActivityForResult(avatarIntent, 0);
			}
		});

		// ��ʼ����Ա����
		userIdNumString = new String("0");
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case 0:
				avatarImageView.setImageBitmap(BitmapFactory
						.decodeFile(avatarFile.getPath()));
				break;
			default:
				break;
			}
		}
		Toast.makeText(getApplicationContext(), avatarFile.getPath(),
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		// ��Ӳ˵�
		int base = Menu.FIRST;
		int i = 0;

		menu.add(base, base + (i++), Menu.NONE, "���");
		menu.add(base, base + (i++), Menu.NONE, "ɾ��");
		menu.add(base, base + (i++), Menu.NONE, "�޸�");
		SubMenu subMenu = menu.addSubMenu(base, base + (i++), Menu.NONE, "��ѯ");
		subMenu.add(base, base + (i++), Menu.NONE, "���û�����ѯ");
		subMenu.add(base, base + (i++), Menu.NONE, "�����֤�Ų�ѯ");

		return true;
	}

	public void initDepartmentSpinner() {
		// ��ʼ�������б�
		List<String> departmentList = new ArrayList<String>();
		departmentList = m_HospitalSqlite.getDepartment();

		ArrayAdapter<String> arrayAdapter;
		arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, departmentList);
		arrayAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		((Spinner) findViewById(R.id.userDepartment_spinner))
				.setAdapter(arrayAdapter);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		// �˵�ѡ��
		String logonNameString = ((EditText) findViewById(R.id.userLogonName_edit))
				.getText().toString().trim();
		String logonPassString = "";
		String logonRoleString = ((Spinner) findViewById(R.id.userManage_spinner))
				.getSelectedItemPosition() + "";
		String name = ((EditText) findViewById(R.id.userName_edit)).getText()
				.toString().trim();
		String sex = ((Spinner) findViewById(R.id.userSex_spinner))
				.getSelectedItemPosition() + "";
		String pic = avatarFile.getPath();
		String idnumstr = ((EditText) findViewById(R.id.userIdNum_edit))
				.getText().toString().trim();
		String phone = ((EditText) findViewById(R.id.userPhone_edit)).getText()
				.toString().trim();
		String departmentid = ((Spinner) findViewById(R.id.userDepartment_spinner))
				.getSelectedItemPosition() + "";
		String des = ((EditText) findViewById(R.id.userDes_edit)).getText()
				.toString().trim();
		boolean successFlag = false;
		List<String> userInfoList = new ArrayList<String>();
		int listIndexInt = 0;

		if (true == logonNameString.equals("")) {
			Toast.makeText(getApplicationContext(), "����д�û���",
					Toast.LENGTH_SHORT).show();
			return false;
		}

		switch (item.getItemId()) {
		case 1:
			successFlag = m_HospitalSqlite.addUser(logonNameString,
					logonPassString, logonRoleString);
			if (true == successFlag) {
				switch (Integer.parseInt(logonRoleString)) {
				case 0:
					pic = getResources().getString(R.string.avatarDoctorDir)
							+ "/" + logonNameString + ".jpg";
					successFlag = m_HospitalSqlite.addDoctorInfo(
							logonNameString, logonRoleString, name, sex, pic,
							idnumstr, phone, departmentid, des);
					if (true == successFlag) {
						Toast.makeText(getApplicationContext(), "���ҽ���ɹ�",
								Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(getApplicationContext(), "���ʧ��",
								Toast.LENGTH_SHORT).show();
					}

					break;
				case 1:
					pic = getResources().getString(R.string.avatarNurseDir)
							+ "/" + logonNameString + ".jpg";
					successFlag = m_HospitalSqlite.addNurseInfo(
							logonNameString, logonRoleString, name, sex, pic,
							idnumstr, phone, departmentid, des);
					if (true == successFlag) {
						Toast.makeText(getApplicationContext(), "��ӻ�ʿ�ɹ�",
								Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(getApplicationContext(), "���ʧ��",
								Toast.LENGTH_SHORT).show();
					}
					break;
				case 2:
					pic = getResources().getString(R.string.avatarPatientDir)
							+ "/" + logonNameString + ".jpg";
					successFlag = m_HospitalSqlite.addPatientInfo(
							logonNameString, logonRoleString, name, sex, pic,
							idnumstr, phone, departmentid, des);
					if (true == successFlag) {
						Toast.makeText(getApplicationContext(), "��Ӳ��˳ɹ�",
								Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(getApplicationContext(), "���ʧ��",
								Toast.LENGTH_SHORT).show();
					}
					break;

				default:
					break;
				}
			} else {
				Toast.makeText(getApplicationContext(), "�û����ظ�",
						Toast.LENGTH_SHORT).show();
			}
			moveFile(avatarFile, new File(pic), true);
			userIdNumString = m_HospitalSqlite.getUserIdOnName(logonNameString,
					logonRoleString);
			if (true == userIdNumString.equals("0")) {
				Toast.makeText(getApplicationContext(), "�û����ʧ��",
						Toast.LENGTH_SHORT).show();
				return false;
			}
			successFlag = false;
			break;
		case 2:
			userIdNumString = m_HospitalSqlite.getUserIdOnName(logonNameString,
					logonRoleString);
			if (true == userIdNumString.equals("0")) {
				Toast.makeText(getApplicationContext(), "���û�������",
						Toast.LENGTH_SHORT).show();
				return false;
			}
			successFlag = m_HospitalSqlite.delUser(userIdNumString);
			if (true == successFlag) {
				switch (Integer.parseInt(logonRoleString)) {
				case 0:
					successFlag = m_HospitalSqlite
							.delDoctorInfo(userIdNumString);
					if (true == successFlag) {
						Toast.makeText(getApplicationContext(), "ɾ��ҽ���ɹ�",
								Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(getApplicationContext(), "ɾ��ʧ��",
								Toast.LENGTH_SHORT).show();
					}
					break;
				case 1:
					successFlag = m_HospitalSqlite
							.delNurseInfo(userIdNumString);
					if (true == successFlag) {
						Toast.makeText(getApplicationContext(), "ɾ����ʿ�ɹ�",
								Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(getApplicationContext(), "ɾ��ʧ��",
								Toast.LENGTH_SHORT).show();
					}
					break;
				case 2:
					successFlag = m_HospitalSqlite
							.delPatientInfo(userIdNumString);
					if (true == successFlag) {
						Toast.makeText(getApplicationContext(), "ɾ�����˳ɹ�",
								Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(getApplicationContext(), "ɾ��ʧ��",
								Toast.LENGTH_SHORT).show();
					}
					break;

				default:
					break;
				}
			} else {
				Toast.makeText(getApplicationContext(), "δ�ҵ����û�",
						Toast.LENGTH_SHORT).show();
			}
			successFlag = false;
			break;
		case 3:
			if (true == userIdNumString.equals("0")) {
				Toast.makeText(getApplicationContext(), "���û�������",
						Toast.LENGTH_SHORT).show();
				return false;
			}
			successFlag = m_HospitalSqlite.modUser(userIdNumString,
					logonNameString);
			if (true == successFlag) {
				successFlag = false;
				switch (Integer.parseInt(logonRoleString)) {
				case 0:
					pic = getResources().getString(R.string.avatarDoctorDir)
							+ "/" + logonNameString + ".jpg";
					successFlag = m_HospitalSqlite
							.delDoctorInfo(userIdNumString);
					if (true == successFlag) {
						successFlag = false;
						successFlag = m_HospitalSqlite.addDoctorInfo(
								logonNameString, logonRoleString, name, sex,
								pic, idnumstr, phone, departmentid, des);
						if (true == successFlag) {
							successFlag = false;
							Toast.makeText(getApplicationContext(), "�޸ĳɹ�",
									Toast.LENGTH_SHORT).show();
						} else {
							Toast.makeText(getApplicationContext(), "�޸�ʧ��",
									Toast.LENGTH_SHORT).show();
						}
					} else {
						Toast.makeText(getApplicationContext(), "�޸�ʧ��",
								Toast.LENGTH_SHORT).show();
					}
					break;
				case 1:
					pic = getResources().getString(R.string.avatarNurseDir)
							+ "/" + logonNameString + ".jpg";
					successFlag = m_HospitalSqlite
							.delNurseInfo(userIdNumString);
					if (true == successFlag) {
						successFlag = false;
						successFlag = m_HospitalSqlite.addNurseInfo(
								logonNameString, logonRoleString, name, sex,
								pic, idnumstr, phone, departmentid, des);
						if (true == successFlag) {
							successFlag = false;
							Toast.makeText(getApplicationContext(), "�޸ĳɹ�",
									Toast.LENGTH_SHORT).show();
						} else {
							Toast.makeText(getApplicationContext(), "�޸�ʧ��",
									Toast.LENGTH_SHORT).show();
						}
					} else {
						Toast.makeText(getApplicationContext(), "�޸�ʧ��",
								Toast.LENGTH_SHORT).show();
					}
					break;
				case 2:
					pic = getResources().getString(R.string.avatarPatientDir)
							+ "/" + logonNameString + ".jpg";
					successFlag = m_HospitalSqlite
							.delPatientInfo(userIdNumString);
					if (true == successFlag) {
						successFlag = false;
						successFlag = m_HospitalSqlite.addPatientInfo(
								logonNameString, logonRoleString, name, sex,
								pic, idnumstr, phone, departmentid, des);
						if (true == successFlag) {
							successFlag = false;
							Toast.makeText(getApplicationContext(), "�޸ĳɹ�",
									Toast.LENGTH_SHORT).show();
						} else {
							Toast.makeText(getApplicationContext(), "�޸�ʧ��",
									Toast.LENGTH_SHORT).show();
						}
					} else {
						Toast.makeText(getApplicationContext(), "�޸�ʧ��",
								Toast.LENGTH_SHORT).show();
					}
					break;
				default:
					break;
				}
			} else {
				Toast.makeText(getApplicationContext(), "δ�ҵ����û�",
						Toast.LENGTH_SHORT).show();
			}
			moveFile(avatarFile, new File(pic), true);
			successFlag = false;
			break;
		case 4:
			successFlag = false;
			break;
		case 5:
			userIdNumString = m_HospitalSqlite.getUserIdOnName(logonNameString,
					logonRoleString);
			if (true == userIdNumString.equals("0")) {
				Toast.makeText(getApplicationContext(), "���û�������",
						Toast.LENGTH_SHORT).show();
				return false;
			}
			switch (Integer.parseInt(logonRoleString)) {
			case 0:
				userInfoList = m_HospitalSqlite.getDoctorInfo(userIdNumString);
				break;
			case 1:
				userInfoList = m_HospitalSqlite.getNurseInfo(userIdNumString);
				break;
			case 2:
				userInfoList = m_HospitalSqlite.getPatientInfo(userIdNumString);
				break;
			default:
				break;
			}
			((EditText) findViewById(R.id.userName_edit)).setText(userInfoList
					.get(listIndexInt++).toString());
			((Spinner) findViewById(R.id.userSex_spinner)).setSelection(Integer
					.parseInt(userInfoList.get(listIndexInt++).toString()));
			avatarImageView.setImageBitmap(BitmapFactory.decodeFile((new File(
					userInfoList.get(listIndexInt++).toString())).getPath()));
			((EditText) findViewById(R.id.userIdNum_edit)).setText(userInfoList
					.get(listIndexInt++).toString());
			((EditText) findViewById(R.id.userPhone_edit)).setText(userInfoList
					.get(listIndexInt++).toString());
			((Spinner) findViewById(R.id.userDepartment_spinner))
					.setSelection(Integer.parseInt(userInfoList.get(
							listIndexInt++).toString()));
			((EditText) findViewById(R.id.userDes_edit)).setText(userInfoList
					.get(listIndexInt++).toString());
			((EditText) findViewById(R.id.userLogonName_edit))
					.setText(userInfoList.get(listIndexInt++).toString());
			listIndexInt = 0;

			Toast.makeText(getApplicationContext(), "��ѯ�ɹ�", Toast.LENGTH_SHORT)
					.show();
			successFlag = false;
			break;
		case 6:
			if (true == idnumstr.equals("")) {
				Toast.makeText(getApplicationContext(), "����д���֤����",
						Toast.LENGTH_SHORT).show();
			}
			userIdNumString = m_HospitalSqlite.getUserIdOnIdNum(idnumstr,
					logonRoleString);
			if (true == userIdNumString.equals("0")) {
				Toast.makeText(getApplicationContext(), "���û�������",
						Toast.LENGTH_SHORT).show();
				return false;
			}
			switch (Integer.parseInt(logonRoleString)) {
			case 0:
				userInfoList = m_HospitalSqlite.getDoctorInfo(userIdNumString);
				break;
			case 1:
				userInfoList = m_HospitalSqlite.getNurseInfo(userIdNumString);
				break;
			case 2:
				userInfoList = m_HospitalSqlite.getPatientInfo(userIdNumString);
				break;
			default:
				break;
			}
			((EditText) findViewById(R.id.userName_edit)).setText(userInfoList
					.get(listIndexInt++).toString());
			((Spinner) findViewById(R.id.userSex_spinner)).setSelection(Integer
					.parseInt(userInfoList.get(listIndexInt++).toString()));
			avatarImageView.setImageBitmap(BitmapFactory.decodeFile((new File(
					userInfoList.get(listIndexInt++).toString())).getPath()));
			((EditText) findViewById(R.id.userIdNum_edit)).setText(userInfoList
					.get(listIndexInt++).toString());
			((EditText) findViewById(R.id.userPhone_edit)).setText(userInfoList
					.get(listIndexInt++).toString());
			((Spinner) findViewById(R.id.userDepartment_spinner))
					.setSelection(Integer.parseInt(userInfoList.get(
							listIndexInt++).toString()));
			((EditText) findViewById(R.id.userDes_edit)).setText(userInfoList
					.get(listIndexInt++).toString());
			((EditText) findViewById(R.id.userLogonName_edit))
					.setText(userInfoList.get(listIndexInt++).toString());
			listIndexInt = 0;

			Toast.makeText(getApplicationContext(), "��ѯ�ɹ�", Toast.LENGTH_SHORT)
					.show();
			successFlag = false;
			break;
		default:
			Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT)
					.show();
			successFlag = false;
			break;
		}
		return true;
	}

	// �ļ�����
	public void moveFile(File fromFile, File toFile, Boolean rewrite) {
		if (!fromFile.exists()) {
			return;
		}
		if (!fromFile.isFile()) {
			return;
		}
		if (!fromFile.canRead()) {
			return;
		}
		if (!toFile.getParentFile().exists()) {
			toFile.getParentFile().mkdirs();
		}
		if (toFile.exists() && rewrite) {
			toFile.delete();
		}
		// ���ļ�����ʱ��canWriteһֱ���صĶ���false
		// if (!toFile.canWrite()) {
		// MessageDialog.openError(new Shell(),"������Ϣ","���ܹ�д��Ҫ���Ƶ�Ŀ���ļ�" +
		// toFile.getPath());
		// Toast.makeText(this,"���ܹ�д��Ҫ���Ƶ�Ŀ���ļ�", Toast.LENGTH_SHORT);
		// return ;
		// }
		try {
			java.io.FileInputStream fosfrom = new java.io.FileInputStream(
					fromFile);
			java.io.FileOutputStream fosto = new FileOutputStream(toFile);
			byte bt[] = new byte[1024];
			int c;
			while ((c = fosfrom.read(bt)) > 0) {
				fosto.write(bt, 0, c); // ������д�����ļ�����
			}
			fosfrom.close();
			fosto.close();
		} catch (Exception ex) {
			System.out.println("readfile : " + ex.getMessage());
		}
		if (fromFile.exists()) {
			fromFile.delete();
		}
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

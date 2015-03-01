package hospital.admin.activity;

import hospital.patient.namespace.R;
import hospital.sqlite.db.HospitalSqlite;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class drugManageActivity extends Activity {

	private HospitalSqlite m_HospitalSqlite;
	private String drugIdNumString;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drugmanage);
		// ��ʼ�����ݿ�
		m_HospitalSqlite = new HospitalSqlite(this);

		// ��ʼ��ҩ����
		drugIdNumString = new String("0");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		// ��Ӳ˵�
		int base = Menu.FIRST;
		int i = 0;

		menu.add(base, base + (i++), Menu.NONE, "���");
		menu.add(base, base + (i++), Menu.NONE, "�޸�");
		menu.add(base, base + (i++), Menu.NONE, "����Ų�ѯ");

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		// �˵�ѡ��
		String drugCodeString = ((EditText) findViewById(R.id.drugCode_edit))
				.getText().toString().trim();
		String drugNameString = ((EditText) findViewById(R.id.drugName_edit))
				.getText().toString().trim();
		String drugDesString = ((EditText) findViewById(R.id.drugDes_edit))
				.getText().toString().trim();
		String drugUnitString = ((EditText) findViewById(R.id.drugUnit_edit))
				.getText().toString().trim();
		String drugPriceString = ((EditText) findViewById(R.id.drugPrice_edit))
				.getText().toString().trim();
		String stockNumString = ((EditText) findViewById(R.id.stockNum_edit))
				.getText().toString().trim();
		List<String> drugInfoList = new ArrayList<String>();
		int listIndexInt = 0;
		boolean successFlag = false;

		if (true == drugCodeString.equals("")) {
			Toast.makeText(getApplicationContext(), "����дҩƷ���",
					Toast.LENGTH_SHORT).show();
			return false;
		}

		switch (item.getItemId()) {
		case 1:
			successFlag = m_HospitalSqlite.addDrug(drugCodeString,
					drugNameString, drugDesString, drugUnitString,
					drugPriceString, stockNumString);
			if (true == successFlag) {
				Toast.makeText(getApplicationContext(), "��ӳɹ�",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getApplicationContext(), "ҩ�����ظ�",
						Toast.LENGTH_SHORT).show();
			}
			break;
		case 2:
			successFlag = m_HospitalSqlite.modDrug(drugIdNumString,
					drugCodeString, drugNameString, drugDesString,
					drugUnitString, drugPriceString, stockNumString);
			if (true == successFlag) {
				Toast.makeText(getApplicationContext(), "�޸ĳɹ�",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getApplicationContext(), "�޸�ʧ��",
						Toast.LENGTH_SHORT).show();
			}
			break;
		case 3:
			drugInfoList = m_HospitalSqlite.getDrug(drugCodeString);
			if (0 == drugInfoList.size()) {
				Toast.makeText(getApplicationContext(), "�����ڸ�ҩƷ",
						Toast.LENGTH_SHORT).show();
			} else {
				((EditText) findViewById(R.id.drugName_edit))
						.setText(drugInfoList.get(listIndexInt++).toString());
				((EditText) findViewById(R.id.drugDes_edit))
						.setText(drugInfoList.get(listIndexInt++).toString());
				((EditText) findViewById(R.id.drugUnit_edit))
						.setText(drugInfoList.get(listIndexInt++).toString());
				((EditText) findViewById(R.id.drugPrice_edit))
						.setText(drugInfoList.get(listIndexInt++).toString());
				((EditText) findViewById(R.id.stockNum_edit))
						.setText(drugInfoList.get(listIndexInt++).toString());
				drugInfoList.get(listIndexInt++).toString();
				Toast.makeText(getApplicationContext(), "��ѯ�ɹ�",
						Toast.LENGTH_SHORT).show();
			}
			break;
		default:
			break;
		}
		return true;
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

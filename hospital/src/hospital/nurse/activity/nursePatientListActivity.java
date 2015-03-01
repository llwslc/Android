package hospital.nurse.activity;

import hospital.patient.namespace.R;
import hospital.sqlite.db.HospitalSqlite;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class nursePatientListActivity extends ListActivity {

	private HospitalSqlite m_HospitalSqlite;

	private Intent nursePatientInfoIntent;
	private String nurseIdNumString;
	private String patientLogonNameString;
	private List<List<String>> patientInfoList;

	// 元素的缓冲类,用于优化ListView
	private static class ItemViewCache {
		public TextView mLogonNameTextView;
		public TextView mNameTextView;
		public TextView mDesTextView;
		public ImageView mAvatarImageView;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 初始化人员id
		nurseIdNumString = (String) this.getIntent().getSerializableExtra(
				"userIdNumString");
		patientLogonNameString = new String("0");

		// 初始化数据库
		m_HospitalSqlite = new HospitalSqlite(this);

		// 初始化病人数据
		patientInfoList = new ArrayList<List<String>>();

		patientInfoList = m_HospitalSqlite
				.getPatientInfoOnNurse(nurseIdNumString);

		// 初始化意图
		nursePatientInfoIntent = new Intent();

		nursePatientInfoIntent.setComponent(new ComponentName(
				nursePatientListActivity.this, nursePatientInfoActivity.class));

		setListAdapter(new patientListAdapter(this));
	}

	private class patientListAdapter extends BaseAdapter {
		private final Context mContext;

		public patientListAdapter(Context context) {
			this.mContext = context;
		}

		public int getCount() {
			return patientInfoList.size();
		}

		public Object getItem(int position) {
			return null;
		}

		public long getItemId(int position) {
			return 0;
		}

		public View getView(int position, View convertView, ViewGroup parent) {

			if (convertView == null) {
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.mypatient, null);
				ItemViewCache viewCache = new ItemViewCache();
				viewCache.mLogonNameTextView = (TextView) convertView
						.findViewById(R.id.myPatientLogonName_text);
				viewCache.mNameTextView = (TextView) convertView
						.findViewById(R.id.myPatientName_text);
				viewCache.mDesTextView = (TextView) convertView
						.findViewById(R.id.myPatientDes_text);
				viewCache.mAvatarImageView = (ImageView) convertView
						.findViewById(R.id.myPatientAvatar_view);
				convertView.setTag(viewCache);
			}
			ItemViewCache cache = (ItemViewCache) convertView.getTag();

			cache.mLogonNameTextView.setText(patientInfoList.get(position)
					.get(1).toString());
			cache.mNameTextView.setText(patientInfoList.get(position).get(2)
					.toString());
			cache.mDesTextView.setText("症状 : "
					+ patientInfoList.get(position).get(0).toString());
			cache.mAvatarImageView
					.setImageBitmap(BitmapFactory.decodeFile(patientInfoList
							.get(position).get(3).toString()));

			// 失去焦点使得listview可点击
			cache.mLogonNameTextView.setFocusableInTouchMode(false);
			cache.mLogonNameTextView.setFocusable(false);
			cache.mNameTextView.setFocusableInTouchMode(false);
			cache.mNameTextView.setFocusable(false);
			cache.mDesTextView.setFocusableInTouchMode(false);
			cache.mDesTextView.setFocusable(false);
			cache.mAvatarImageView.setFocusableInTouchMode(false);
			cache.mAvatarImageView.setFocusable(false);

			return convertView;
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		patientLogonNameString = patientInfoList.get(position).get(1)
				.toString();
		nursePatientInfoIntent.putExtra("patientLogonNameString",
				patientLogonNameString);
		startActivity(nursePatientInfoIntent);

		super.onListItemClick(l, v, position, id);
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

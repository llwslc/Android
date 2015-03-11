package com.gallery.activity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.app.gallery.R;
import com.select.image.MainActivity;
import com.sqlite.db.mSqlite;

public class MyGalleryActivity extends Activity {

	private mSqlite m_Sqlite;
	private List<String> imageList;
	private String userIdNumString;
	Uri imageUri;
	String imagePathString;
	Intent imageSelectIntent;
	int REQUEST_CODE;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridview);

		// ��ʼ�����ݿ�
		m_Sqlite = new mSqlite(this);

		// ��ʼ����Աid
		userIdNumString = (String) this.getIntent().getSerializableExtra(
				"userIdNumString");

		// ��ʼ����Դ��ʶ��
		imageUri = null;

		initImageItems();

		// ��ʼ����ͼ
		imageSelectIntent = new Intent();
		imageSelectIntent.setComponent(new ComponentName(
				MyGalleryActivity.this, MainActivity.class));

		// ��ʼ����ͼ����ֵ
		REQUEST_CODE = 0;
	}

	public void initImageItems() {

		// ��ʼ��ͼ���б�
		imageList = new ArrayList<String>();

		// ��ȡͼ���б�
		imageList = m_Sqlite.getPicPath(userIdNumString);

		GridView gridview = (GridView) findViewById(R.id.photo_gridview);

		// ��Ӳ�����ʾ
		gridview.setAdapter(new PhotoWallAdapter(imageList, this));
		// �����Ϣ����
		gridview.setOnItemClickListener(new ItemClickListener());
	}

	// ��AdapterView������(���������߼���)���򷵻ص�Item�����¼�
	class ItemClickListener implements OnItemClickListener {
		public void onItemClick(AdapterView<?> arg0,// The AdapterView where the
													// click happened
				View arg1,// The view within the AdapterView that was clicked
				int arg2,// The position of the view in the adapter
				long arg3// The row id of the item that was clicked
		) {
			REQUEST_CODE = 2;
			imageSelectIntent.putExtra("imageString", imageList.get(arg2)
					.toString());
			imageSelectIntent.putExtra("userIdNumString", userIdNumString);
			startActivityForResult(imageSelectIntent, REQUEST_CODE);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		// ��Ӳ˵�
		int base = Menu.FIRST;
		int i = 0;

		menu.add(base, base + (i++), Menu.NONE, "���ͼƬ");
		menu.add(base, base + (i++), Menu.NONE, "�޸�����");
		menu.add(base, base + (i++), Menu.NONE, "����û�");

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		// �˵�ѡ��
		switch (item.getItemId()) {
		case 1: // ���ͼƬ
			showPicturePicker(MyGalleryActivity.this);
			break;
		case 2: // �޸�����
			final LayoutInflater passwordInflater = getLayoutInflater();
			final View passwordLayout = passwordInflater.inflate(
					R.layout.password, null);
			final Button passwordOkButton = (Button) passwordLayout
					.findViewById(R.id.passwordOk_button);
			final Button passwordCancelButton = (Button) passwordLayout
					.findViewById(R.id.passwordCancel_button);
			final AlertDialog.Builder passwordBuilder = new AlertDialog.Builder(
					MyGalleryActivity.this).setTitle("�������� : ").setView(
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
						successFlag = m_Sqlite.modPassword(userIdNumString,
								passwordOneString);
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
			break;
		case 3: // ����û�
			final LayoutInflater newUserInflater = getLayoutInflater();
			final View newUserLayout = newUserInflater.inflate(
					R.layout.newuser, null);
			final Button newUserOkButton = (Button) newUserLayout
					.findViewById(R.id.passwordOk_button);
			final Button newUserCancelButton = (Button) newUserLayout
					.findViewById(R.id.passwordCancel_button);
			final AlertDialog.Builder newUserBuilder = new AlertDialog.Builder(
					MyGalleryActivity.this).setTitle("����û� : ").setView(
					newUserLayout);
			final Dialog newUserDialog = newUserBuilder.show();

			newUserOkButton.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					// TODO Auto-generated method stub
					boolean successFlag = false;
					String nameNewString = ((EditText) newUserLayout
							.findViewById(R.id.nameNew_edit)).getText()
							.toString().trim();
					String passwordNewString = ((EditText) newUserLayout
							.findViewById(R.id.passwordNew_edit)).getText()
							.toString().trim();

					successFlag = m_Sqlite.insertUser(nameNewString,
							passwordNewString);
					if (true == successFlag) {
						Toast.makeText(getApplicationContext(), "��ӳɹ�",
								Toast.LENGTH_SHORT).show();
						newUserDialog.dismiss();
					} else {
						Toast.makeText(getApplicationContext(), "���ʧ��(�û����Ѵ���)",
								Toast.LENGTH_SHORT).show();
						newUserDialog.dismiss();
					}
				}
			});

			newUserCancelButton.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					// TODO Auto-generated method stub
					newUserDialog.dismiss();
				}
			});
			break;
		default:
			Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT)
					.show();
			break;
		}
		return true;
	}

	public void showPicturePicker(Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("ͼƬ��Դ");
		builder.setNegativeButton("ȡ��", null);
		builder.setItems(new String[] { "����", "���" },
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch (which) {
						case 0:
							REQUEST_CODE = 0;
							Intent openCameraIntent = new Intent(
									MediaStore.ACTION_IMAGE_CAPTURE);

							String fileName = "IMG_"
									+ new SimpleDateFormat("yyyyMMdd_HHmmss")
											.format(new Date()) + ".jpg";

							imageUri = Uri.fromFile(new File(
									Environment
											.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),
									fileName));

							openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,
									imageUri);

							startActivityForResult(openCameraIntent,
									REQUEST_CODE);
							break;

						case 1:
							REQUEST_CODE = 1;
							Intent openAlbumIntent = new Intent(
									Intent.ACTION_GET_CONTENT);

							openAlbumIntent
									.setDataAndType(
											MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
											"image/*");

							startActivityForResult(openAlbumIntent,
									REQUEST_CODE);
							break;

						default:
							break;
						}
					}
				});
		builder.create().show();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			imagePathString = "";
			switch (requestCode) {
			case 0:
				// ������Ƭ
				imagePathString = imageUri.getPath();
				m_Sqlite.insertPicPath(userIdNumString, imagePathString);
				Toast.makeText(getApplicationContext(), imageUri.getPath(),
						Toast.LENGTH_SHORT).show();
				break;
			case 1:
				// ѡ����Ƭ
				Uri originalUri = data.getData(); // ���ͼƬ��uri
				// ���￪ʼ�ĵڶ����֣���ȡͼƬ��·����
				String[] proj = { MediaStore.Images.Media.DATA };
				// ������android��ý�����ݿ�ķ�װ�ӿڣ�����Ŀ�Android�ĵ�
				Cursor cursor = managedQuery(originalUri, proj, null, null,
						null);
				// ���Ҹ������ ����ǻ���û�ѡ���ͼƬ������ֵ
				int column_index = cursor
						.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
				// �����������ͷ ���������Ҫ����С�ĺ���������Խ��
				cursor.moveToFirst();
				// ����������ֵ��ȡͼƬ·��
				imagePathString = cursor.getString(column_index);

				m_Sqlite.insertPicPath(userIdNumString, imagePathString);
				Toast.makeText(getApplicationContext(), imagePathString,
						Toast.LENGTH_SHORT).show();
				break;
			case 2:
				// �����б�
				initImageItems();
				break;
			default:
				break;
			}
			initImageItems();
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (m_Sqlite != null) {
			m_Sqlite.close();
			m_Sqlite = null;
		}
	}
}